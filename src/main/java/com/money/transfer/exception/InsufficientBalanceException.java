package com.money.transfer.exception;

import com.money.transfer.constant.ErrorCode;

public class InsufficientBalanceException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException(String mbmBkmsnumber) {
		super(ErrorCode.ACCOUNT_INSUFFICIENT_BAL.getCode(),ErrorCode.ACCOUNT_INSUFFICIENT_BAL.getDescription(), 
				new Object[] { mbmBkmsnumber });
	}
}
