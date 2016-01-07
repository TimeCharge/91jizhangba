package com.hawkhorse.fantasycharge.domain.request;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("request")
@JsonRootName("request")
public class Req<T> {
	
	private String userId;
	private String token;
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	
	
}
