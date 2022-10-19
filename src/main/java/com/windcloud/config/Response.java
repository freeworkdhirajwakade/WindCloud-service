package com.windcloud.config;

public class Response<T> {
	
	private T results;
	private Integer status;
	private String message;
	public T getResults() {
		return results;
	}
	public void setResults(T results) {
		this.results = results;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
