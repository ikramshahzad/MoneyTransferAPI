package com.money.transfer.constant;

public enum ErrorCode implements ErrorMessage {

	ACCOUNT_INSUFFICIENT_BAL("ERR_ACC_B_0001", "Account {0} has insufficient balance."),
	ACCOUNT_DOES_NOT_EXIST("ERR_ACC_B_0002", "Account {0} does not exist."),
	ACCOUNT_INVAID("ERR_ACC_B_0003", "Invalid account{0} id"),
	TRANSACTION_DOES_NOT_EXIST("ERR_ACC_B_0004", "Transaction {0} does not exist."),
	ACCOUNT_ALREADY_EXIST("ERR_ACC_B_0005", "Account {0} already exist."),;

	private ErrorCode(String code, String description) {
		this.code = code;
		this.description = description;
	}

	private String code;
	private String description;

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

}
