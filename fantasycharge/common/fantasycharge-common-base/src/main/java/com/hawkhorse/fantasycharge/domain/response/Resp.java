package com.hawkhorse.fantasycharge.domain.response;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("response")
@JsonRootName("response")
public class Resp<T> {
	
	private int code;
	private String msg;
	private T data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
