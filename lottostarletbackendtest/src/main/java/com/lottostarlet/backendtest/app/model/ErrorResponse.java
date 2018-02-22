package com.lottostarlet.backendtest.app.model;

import org.springframework.http.HttpStatus;

/**
 * ErrorResponse representation that is shown to client
 * 
 * @author roundrobine
 *
 */
public class ErrorResponse {

	private final int status;
	private final String message;
	private final String path;

	public ErrorResponse(HttpStatus status, String message, String path) {
		this.status = status.value();
		this.message = message;
		this.path = path;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "ErrorResponse [ status=" + status + ", message=" + message + ", path="
				+ path + "]";
	}
	
}
