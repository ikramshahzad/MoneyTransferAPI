package com.money.transfer.exception;

public abstract class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final String errorCode;
	protected final Object[] arguments;
	protected final Object[] IMMUTABLE_ZERO_LEN_ARRAY = new Object[0];

	public BusinessException(String errorCode, String message, Object... arguments) {
		super(message);
		this.errorCode = errorCode;
		this.arguments = arguments;
	}

	public BusinessException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.arguments = IMMUTABLE_ZERO_LEN_ARRAY;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Object[] getArguments() {
		return arguments;
	}
}
