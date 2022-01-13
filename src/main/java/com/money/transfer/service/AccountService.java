package com.money.transfer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.money.transfer.exception.AccountAlreadyExistException;
import com.money.transfer.exception.AccountDoesNotExistException;
import com.money.transfer.model.Account;
import com.money.transfer.repository.AccountRepository;

@Service
public class AccountService {

	private AccountRepository accountRepository;

	@Autowired
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public Account createAccount(Account account) {
		accountRepository.findById(account.getId()).ifPresent(c -> {
			throw new AccountAlreadyExistException(account.getId());
		});
		return accountRepository.save(account);
	}

	public void createAccount(List<Account> account) {
		account.forEach(element -> {
			createAccount(element);
		});
	}

	public Account findByid(String id) {
		return accountRepository.findById(id).orElseThrow(() -> new AccountDoesNotExistException(id));
	}

	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	public Optional<Account> getAccountById(String id) {
		return accountRepository.getAccountById(id);
	}

//	public void updateBalance(Account account, Transaction transaction) {
//		if (account.getId().equals(transaction.getDebitId())) {
//			if (account.getBalance().compareTo(transaction.getAmount()) < 0) {
//				throw new InsufficientBalanceException(account.getId());
//			}
//			account.setBalance(account.getBalance().subtract(transaction.getAmount()));
//		} else if (account.getId().equals(transaction.getCreditId())) {
//			account.setBalance(account.getBalance().add(transaction.getAmount()));
//		}
//	}
}
