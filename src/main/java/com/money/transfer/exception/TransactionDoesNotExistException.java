package com.money.transfer.exception;

import com.money.transfer.constant.ErrorCode;

public class TransactionDoesNotExistException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public TransactionDoesNotExistException(Long id) {
		super(ErrorCode.TRANSACTION_DOES_NOT_EXIST.getCode(), ErrorCode.TRANSACTION_DOES_NOT_EXIST.getDescription(),
				new Object[] { id });
	}

}
