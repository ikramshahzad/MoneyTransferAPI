package com.money.transfer.exception;

import com.money.transfer.constant.ErrorCode;

public class AccountDoesNotExistException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public AccountDoesNotExistException(String mbmBkmsnumber) {
		super(ErrorCode.ACCOUNT_DOES_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_DOES_NOT_EXIST.getDescription(),
				new Object[] { mbmBkmsnumber });
	}

}
