package com.spring.web.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import com.spring.request.ChangePasswordRequest;
import com.spring.response.ProfileResponse;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.core.dao.UserDao;
import com.spring.core.model.User;
import com.spring.core.model.UserRole;
import com.spring.core.service.UserRoleService;
import com.spring.core.service.UserService;
import com.spring.exception.BadRequestException;
import com.spring.request.RegisterRequest;
import com.spring.response.BaseResponse;
import com.spring.util.CommonUtil;
import com.spring.util.Constants;
import com.spring.util.EmailSend;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.Objects;

@Controller
@Api(value = "MainController", description = "Spring security controller")
public class MainController extends BaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	UserDao userDAO;
	
	 @Autowired
	 Mapper mapper;

	@ApiOperation(value = "Index page", response = Iterable.class, tags = "index")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public final ModelAndView defaultPage() {
		ModelAndView model = new ModelAndView();
		if (CommonUtil.checkUserLogin()) {
			//model.setViewName("index");
		} else {
			model.setViewName("redirect:/");
		}
		return model;
	}

	private String getErrorMessage(final HttpServletRequest request, final String key) {
		Exception exception = (Exception) request.getSession().getAttribute(key);
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}
		return error;
	}

	@RequestMapping(value = {"/403", "/400", "/404"}, method = RequestMethod.GET)
	public final ModelAndView accesssDenied() {
		ModelAndView model = new ModelAndView();
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}
		model.setViewName("error");
		return model;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse> register(@RequestBody RegisterRequest registerRequest){
		
		if(StringUtils.isBlank(registerRequest.getUsername())) {
			throw new BadRequestException("usr.required");
		}
		
		if(StringUtils.isBlank(registerRequest.getEmailId())) {
			throw new BadRequestException("emailId.required");
		}
		
		if(StringUtils.isBlank(registerRequest.getPassword())) {
			throw new BadRequestException("password.required");
		}
		
		if(StringUtils.isBlank(registerRequest.getCountry())) {
			throw new BadRequestException("country.required");
		}
		
		User userExist = userService.getByUsername(registerRequest.getUsername());
		
		if(Objects.nonNull(userExist)) {
			throw new BadRequestException("usr.exist");
		}
		User user = new User();
		mapper.map(registerRequest, user);
		user.setConfirmPassword(user.getPassword());
		user.setEnabled(true);
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(Constants.ROLE_USER);
		userService.save(user);
		userRoleService.save(userRole);
		return ok(null, "registartion sucessfully");
	}

	/**
	 * @param emailId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/change-password",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<BaseResponse> changePassword(@RequestBody ChangePasswordRequest request){

		if (StringUtils.isBlank(request.getEmailId())) {
			throw new BadRequestException("emailId.required");
		}

		if (StringUtils.isBlank(request.getNewPassword())){
			return notFound("password.required");
		}
		
		if(!request.getOldPassword().equals(request.getNewPassword())) {
			return authentication("password.match");
		}

		User user = userService.getUserByEmailId(request.getEmailId());
		if (Objects.isNull(user)){
			return notFound("usr.notfound");
		}

		if (!request.getOldPassword().equals(user.getPassword())){
			return authentication("password.correct");
		}
		user.setPassword(request.getNewPassword());
		user.setConfirmPassword(request.getNewPassword());
		userService.update(user);
		return ok(null, "Password chenged successfully");
	}

	/**
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/profile/{username}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<BaseResponse> profile(@PathVariable("username") String username){

		if (StringUtils.isBlank(username)){
			return  badRequest("usr.required");
		}

		User user = userService.getByUsername(username);
		if (Objects.isNull(user)){
			return notFound("usr.notfound");
		}

		ProfileResponse profileResponse = new ProfileResponse();
		mapper.map(user,profileResponse);
		return ok(profileResponse,"User profile");
	}

	@RequestMapping(value = "/forgot-password/{emailId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BaseResponse> forgotPassword(@PathVariable("emailId") String emailId) throws MessagingException{

		if (StringUtils.isBlank(emailId)){
			return badRequest("usr.required");
		}

		User userDetails = userService.getUserByEmailId(emailId);

		if (Objects.isNull(userDetails)){
			return notFound("usr.notfound");
		}

		Boolean emailStatus = EmailSend.forgotpassword(userDetails.getUsername(), userDetails.getEmailId(), userDetails.getPassword());
		if(emailStatus) {
			return ok(null, "Email sent successfully");
		}else {
			throw new BadRequestException("mail.fail");
		}
		
		
	}
}