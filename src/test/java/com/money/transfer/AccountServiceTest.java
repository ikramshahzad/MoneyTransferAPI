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

import com.money.transfer.model.Account;
import com.money.transfer.repository.AccountRepository;
import com.money.transfer.service.AccountService;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
	@Mock
	AccountRepository accountRepository;

	@InjectMocks
	AccountService accountService;

	@Test
	public void testGetaccount() {
		String id = new String("3d253e29-8785-464f-8fa0-9e4b57699db9");
		String name = new String("Junit");

		Account account = new Account(id, name, BigDecimal.TEN);

		when(accountRepository.findById(id)).thenReturn(Optional.of(account));

		assertEquals(BigDecimal.TEN, accountService.findByid(id).getBalance());
	}

}
