package com.lottostarlet.backendtest.app.controllers;

import com.lottostarlet.backendtest.app.exceptions.GameDoesNotExistException;
import com.lottostarlet.backendtest.app.model.ErrorResponse;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.zip.DataFormatException;

/**
 * Exception handlers for {@link BackendController}
 *  
 * @author roundrobine
 *
 */
@RestControllerAdvice
@EnableWebMvc
public class ExceptionHandlerController {

	protected ApplicationEventPublisher eventPublisher;

	/**
	 * @param path {@link String} that client tries to access
	 * @param exception {@link Exception} thrown when accessing the specified path
	 * @param status {@link HttpStatus} of the response
	 * @return {@link Exception} description and returned HTTP status
	 */
	private ResponseEntity<ErrorResponse> handleException(String path, Exception exception, HttpStatus status) {
		ErrorResponse response = new ErrorResponse(status, exception.getMessage(), path);
		return new ResponseEntity<>(response, status);
	}

	/**
	 * {@see #handleException(String, Exception, HttpStatus)}
	 */
	@ExceptionHandler(GameDoesNotExistException.class)
	public ResponseEntity<ErrorResponse> handleException(WebRequest request, GameDoesNotExistException exception) {
		String path = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
		return handleException(path, exception, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(NoHandlerFoundException ex, WebRequest request) {
		String path = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
		return handleException(path, ex, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class,
			MethodArgumentTypeMismatchException.class, IllegalArgumentException.class, HttpClientErrorException.class})
	public ResponseEntity<ErrorResponse> handleBadRequest(Exception ex, WebRequest request) {
		String path = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
		return handleException(path, ex, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ErrorResponse> handleAll(Exception ex, WebRequest request) {
		String path = ((ServletWebRequest) request).getRequest().getRequestURL().toString();
		return handleException(path, ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
