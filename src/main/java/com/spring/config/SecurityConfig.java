package com.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.config.core.MyFailureHandler;
import com.spring.config.core.MySaveSuccessHandler;

@Configuration
@EnableWebSecurity
//@Import(SwaggerConfiguration.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;

	
	@Autowired
    private MySaveSuccessHandler mySaveSuccessHandler;

	@Autowired
    private MyFailureHandler myFailureHandler;

	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	 @Override
	    public void configure(WebSecurity web) throws Exception {
		 web.ignoring().antMatchers("/v2/api-docs","/swagger-ui.html","/swagger-resources/**","/configuration/**","/webjars/**","/public");
	 }

	 @Override
	    protected void configure(final HttpSecurity http) throws Exception {
	       http.csrf().disable()
           .authorizeRequests()
           .and()
           .authorizeRequests()
           .antMatchers("/api/*").permitAll().and()
           .formLogin()
           .successHandler(mySaveSuccessHandler)
           .failureHandler(myFailureHandler)
           .and()
           .httpBasic()
           .and()
           .logout();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	

	
	
}