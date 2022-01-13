package com.money.transfer.exception;

import com.money.transfer.constant.ErrorCode;

public class InvalidAccountException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public InvalidAccountException() {
		super(ErrorCode.ACCOUNT_INVAID.getCode(), ErrorCode.ACCOUNT_INVAID.getDescription());
	}

}
