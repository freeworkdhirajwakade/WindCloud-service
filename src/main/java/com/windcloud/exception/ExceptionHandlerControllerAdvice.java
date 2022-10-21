package com.windcloud.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.windcloud.config.Response;
import com.windcloud.constants.CommanConstants;

import io.jsonwebtoken.SignatureException;

@ControllerAdvice
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
