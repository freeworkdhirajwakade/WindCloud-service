package com.windcloud.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.windcloud.config.Response;
import com.windcloud.constants.CommanConstants;

import io.jsonwebtoken.SignatureException;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody Response handleResourceNotFound(final ResourceNotFoundException exception,
			final HttpServletRequest request) {

		Response<?>error=new Response<>();
		error.setMessage(exception.getMessage());
		error.setStatus(CommanConstants.FAILED);

		return error;
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public @ResponseBody Response handleResourceNotFound(final UsernameNotFoundException exception,
			final HttpServletRequest request) {

		Response<?>error=new Response<>();
		error.setMessage(exception.getMessage());
		error.setStatus(CommanConstants.FAILED);

		return error;
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(SignatureException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public @ResponseBody Response handleInvalidToken(final SignatureException exception,
			final HttpServletRequest request) {

		Response<?>error=new Response<>();
		error.setMessage(CommanConstants.INVALID_TOKEN);
		error.setStatus(CommanConstants.FAILED);

		return error;
	}
	
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public @ResponseBody Response handleException(final BadCredentialsException exception,
			final HttpServletRequest request) {
		Response<?>error=new Response<>();
		error.setMessage(CommanConstants.ENTER_VALID_USERNAME_PASS);
		error.setStatus(CommanConstants.FAILED);
		return error;
	}
	

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody Response handleException(final Exception exception,
			final HttpServletRequest request) {
		Response<?>error=new Response<>();
		error.setMessage(exception.getMessage());
		error.setStatus(CommanConstants.FAILED);
		return error;
	}
}
