package com.money.transfer.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.money.transfer.exception.AccountDoesNotExistException;
import com.money.transfer.exception.InsufficientBalanceException;
import com.money.transfer.exception.TransactionDoesNotExistException;
import com.money.transfer.model.Account;
import com.money.transfer.model.Transaction;
import com.money.transfer.repository.TransactionRepository;

@Service
public class TransactionService {

	private TransactionRepository transactionRepositry;

	private AccountService accountService;

	@Autowired
	public TransactionService(TransactionRepository transactionRepositry, AccountService accountService) {
		this.transactionRepositry = transactionRepositry;
		this.accountService = accountService;
	}

	public Transaction findByid(Long id) {
		return transactionRepositry.findById(id).orElseThrow(() -> new TransactionDoesNotExistException(id));
	}

	@Transactional
	public Account tranferMoney(Transaction transaction) {
		Account drAccount = accountService.getAccountById(transaction.getDebitId())
				.orElseThrow(() -> new AccountDoesNotExistException(transaction.getDebitId()));
		Account crAccount = accountService.getAccountById(transaction.getCreditId())
				.orElseThrow(() -> new AccountDoesNotExistException(transaction.getCreditId()));
		updateBalance(drAccount, transaction);
		updateBalance(crAccount, transaction);
		createTransactions(transaction);
		return crAccount;
	}

	private void updateBalance(Account account, Transaction transaction) {
		if (account.getId().equals(transaction.getDebitId())) {
			if (account.getBalance().compareTo(transaction.getAmount()) < 0) {
				throw new InsufficientBalanceException(account.getId());
			}
			account.setBalance(account.getBalance().subtract(transaction.getAmount()));
		} else if (account.getId().equals(transaction.getCreditId())) {
			account.setBalance(account.getBalance().add(transaction.getAmount()));
		}
	}

	private void createTransactions(Transaction transaction) {
		transactionRepositry.save(transaction);
	}

	public List<Transaction> findAll() {
		return transactionRepositry.findAll();
	}
}
