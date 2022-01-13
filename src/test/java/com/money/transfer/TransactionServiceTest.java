package com.money.transfer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.money.transfer.exception.AccountDoesNotExistException;
import com.money.transfer.exception.InsufficientBalanceException;
import com.money.transfer.model.Account;
import com.money.transfer.model.Transaction;
import com.money.transfer.repository.TransactionRepository;
import com.money.transfer.service.AccountService;
import com.money.transfer.service.TransactionService;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
	@Mock
	TransactionRepository transactionRepository;

	@Mock
	AccountService accountService;

	@InjectMocks
	TransactionService transactionService;

	@Test()
	public void testTranferMoney() {
		String debitId = new String("3d253e29-8785-464f-8fa0-9e4b57699db9");
		String creditId = new String("17f904c1-806f-4252-9103-74e7a5d3e340");
		String name = new String("Junit");
		BigDecimal amount = new BigDecimal(10);

		Account drAccount = new Account(debitId, name, BigDecimal.TEN);
		Account crAccount = new Account(creditId, name, BigDecimal.TEN);

		when(accountService.getAccountById(debitId)).thenReturn(Optional.of(drAccount));
		when(accountService.getAccountById(creditId)).thenReturn(Optional.of(crAccount));

		Transaction request = new Transaction();
		request.setDebitId(debitId);
		request.setCreditId(creditId);
		request.setAmount(amount);

		transactionService.tranferMoney(request);

		assertEquals(BigDecimal.ZERO, drAccount.getBalance());
		assertEquals(BigDecimal.TEN.add(BigDecimal.TEN), crAccount.getBalance());
	}

	@Test(expected = InsufficientBalanceException.class)
	public void testInsufficientBalanceException() {
		String debitId = new String("3d253e29-8785-464f-8fa0-9e4b57699db9");
		String creditId = new String("17f904c1-806f-4252-9103-74e7a5d3e340");
		String name = new String("Junit");
		BigDecimal amount = new BigDecimal(20);

		Account drAccount = new Account(debitId, name, BigDecimal.TEN);
		Account crAccount = new Account(creditId, name, BigDecimal.TEN);

		when(accountService.getAccountById(debitId)).thenReturn(Optional.of(drAccount));
		when(accountService.getAccountById(creditId)).thenReturn(Optional.of(crAccount));

		Transaction request = new Transaction();
		request.setDebitId(debitId);
		request.setCreditId(creditId);
		request.setAmount(amount);

		transactionService.tranferMoney(request);

	}

	@Test(expected = AccountDoesNotExistException.class)
	public void testAccountDoesNotExistException() {
		String debitId = new String("3d253e29-8785-464f-8fa0-9e4b57699db9");
		String creditId = new String("17f904c1-806f-4252-9103-74e7a5d3e340");
		String name = new String("Junit");
		BigDecimal amount = new BigDecimal(20);

		Account drAccount = new Account(debitId, name, BigDecimal.TEN);
		Account crAccount = new Account(creditId, name, BigDecimal.TEN);

		when(accountService.getAccountById(debitId)).thenReturn(Optional.empty());

		Transaction request = new Transaction();
		request.setDebitId(debitId);
		request.setCreditId(creditId);
		request.setAmount(amount);

		transactionService.tranferMoney(request);

		assertEquals(BigDecimal.ZERO, drAccount.getBalance());
		assertEquals(BigDecimal.TEN.add(BigDecimal.TEN), crAccount.getBalance());
	}

}
