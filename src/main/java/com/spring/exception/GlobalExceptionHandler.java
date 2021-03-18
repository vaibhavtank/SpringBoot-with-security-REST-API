package com.spring.exception;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.util.ValidationUtil;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private MessageSource messageSource;

	@Autowired
	public GlobalExceptionHandler(HttpServletRequest request, MessageSource messageSource) {
		this.request = request;
		this.messageSource = messageSource;
	}

	/**
	 * This method handle exception for bad request and throw the exception to api.
	 *
	 * @param ex BadRequestException
	 * @return Error message with HttpStatus code 400
	 * @throws BadRequestException
	 */
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionResponse handleBadRequestException(BadRequestException ex) {
		return getErrorResponse(HttpStatus.BAD_REQUEST, getMessage(ex.getMessageKey(), ex.getArgs()));
	}

	/**
	 * This method handle exception for bad request and throw the exception to api.
	 * 
	 * @param ex BadRequestException for annotation
	 * @return @annotation message with HttpStatus code 400
	 * @throws BadRequestException
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		String messageKey = ValidationUtil.fromBindingErrors(result);
		return getErrorResponse(HttpStatus.BAD_REQUEST, messageSource.getMessage(messageKey, null, null));
	}

	/**
	 * This method handle exception for not found and throw the exception to api.
	 * 
	 * @param ex ResourceNotFoundException
	 * @return Error message with HttpStatus code 404
	 * @throws ResourceNotFoundException
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public ExceptionResponse handleResourceNotFoundException(ResourceNotFoundException ex) {
		return getErrorResponse(HttpStatus.NOT_FOUND, getMessage(ex.getMessageKey(), ex.getArgs()));
	}

	/**
	 * This method handle exception for conflict and throw the exception to api.
	 * 
	 * @param ex ConflictException
	 * @return Error message with HttpStatus code 409
	 * @throws ConflictException
	 */
	@ExceptionHandler(ConflictException.class)
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ResponseBody
	public ExceptionResponse handleConflictException(ConflictException ex) {
		return getErrorResponse(HttpStatus.CONFLICT, getMessage(ex.getMessageKey(), ex.getArgs()));
	}

	/**
	 * Create an exception response for each exception.
	 * 
	 * @param message Exception Message
	 * @return
	 */
	private ExceptionResponse getErrorResponse(HttpStatus status, String message) {
		ExceptionResponse response = new ExceptionResponse();

		response.setCode(status.value());
		response.setError(status.getReasonPhrase());
		response.setMessage(message);
		response.setPath(request.getRequestURI());
		response.setMethod(request.getMethod());

		return response;
	}

	private String getMessage(String key, Object[] args) {
		return messageSource.getMessage(key, args, null);
	}
	
}
