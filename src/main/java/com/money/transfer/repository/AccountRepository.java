package com.money.transfer.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.money.transfer.model.Account;

@Repository
@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, String> {

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Transactional
	@Query("SELECT a FROM Account a WHERE a.id = ?1")
	Optional<Account> getAccountById(String id);

}
