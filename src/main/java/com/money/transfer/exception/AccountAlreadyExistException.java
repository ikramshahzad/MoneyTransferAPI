package com.money.transfer.exception;

import com.money.transfer.constant.ErrorCode;

public class AccountAlreadyExistException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public AccountAlreadyExistException(String mbmBkmsnumber) {
		super(ErrorCode.ACCOUNT_ALREADY_EXIST.getCode(), ErrorCode.ACCOUNT_ALREADY_EXIST.getDescription(),
				new Object[] { mbmBkmsnumber });
	}

}
