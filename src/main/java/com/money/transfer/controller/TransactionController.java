package com.money.transfer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.money.transfer.model.Account;
import com.money.transfer.model.Transaction;
import com.money.transfer.service.TransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("api/transaction")
@Api(tags = { "Transaction" }, value = "API for transaction related operation")
public class TransactionController {

	private TransactionService transactionService;

	@Autowired
	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "API for tranfer Money", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 400, message = "Account has insufficient balance."),
			@ApiResponse(code = 200, message = "Successful retrieval", response = Account.class) })
	public Account transferMoney(@RequestBody @Valid Transaction request) {
		return transactionService.tranferMoney(request);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "API for fetch transaction", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 400, message = "Transaction does not exist."),
			@ApiResponse(code = 200, message = "Successful retrieval", response = Account.class) })
	public Transaction getAccount(@PathVariable("id") Long id) {
		return transactionService.findByid(id);
	}

	@GetMapping()
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "API for fetch all transactions", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Server error"),
			@ApiResponse(code = 404, message = "Service not found"),
			@ApiResponse(code = 200, message = "Successful retrieval", response = Transaction.class, responseContainer = "List") })
	public List<Transaction> getAllTransaction() {
		return transactionService.findAll();
	}
}
