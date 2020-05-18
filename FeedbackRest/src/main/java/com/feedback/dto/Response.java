package com.feedback.dto;

public class Response {
	private String message;
	private int code;
	private Object response;
	private Object errors;
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}
	/**
	 * @return the response
	 */
	public Object getResponse() {
		return response;
	}
	/**
	 * @param response the response to set
	 */
	public void setResponse(Object response) {
		this.response = response;
	}
	/**
	 * @return the errors
	 */
	public Object getErrors() {
		return errors;
	}
	/**
	 * @param errors the errors to set
	 */
	public void setErrors(Object errors) {
		this.errors = errors;
	}
	
	
	
	
}
