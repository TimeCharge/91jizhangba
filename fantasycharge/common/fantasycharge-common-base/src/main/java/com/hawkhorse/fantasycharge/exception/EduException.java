package com.hawkhorse.fantasycharge.exception;

/**
 * 异常类
 * 
 * @author Administrator
 * 
 */
public class EduException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private int code;

	private Object[] params;

	public EduException() {
		super("系统异常");
	}

	public EduException(int code) {
		super(String.valueOf(code));
		this.code = code;
	}

	public EduException(String msg) {
		super(msg);
	}

	public EduException(int code, String msg) {
		super(msg);
		this.code = code;
	}

	public EduException(int code, String msg, Throwable cause) {
		super(msg, cause);
		this.code = code;
	}

	public EduException(int code, String msg, Object... params) {
		super(msg);
		this.code = code;
		this.params = params;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object... params) {
		this.params = params;
	}

	public EduException(Throwable cause) {
		super(cause);
	}

	public EduException(String message, Throwable cause) {
		super(message, cause);
	}
}
