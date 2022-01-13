package com.money.transfer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.money.transfer.constant.FieldNameLength;

@Entity
@Table(name = "BN_MS_BM_BANKMASTER")
public class Account {

	@Id
	@Column(name = "id", nullable = false, updatable = false, columnDefinition = FieldNameLength.CODE_50)
	private String id;
	@Column(name = "name", nullable = false, updatable = true, columnDefinition = FieldNameLength.CODE_200)
	private String name;
	@Column(name = "balance", columnDefinition = FieldNameLength.AMOUNT_REAL)

	@Min(value = 0, message = "account balance must be positive")
	private BigDecimal balance = BigDecimal.ZERO;

	@Column(name = "VERSION_NUM")
	@Version
	private int version;

	public Account() {
	}

	public Account(@NotNull String id, @NotNull String name,
			@NotNull @Min(value = 0, message = "account balance must be positive") BigDecimal balance) {
		this.id = id;
		this.name = name;
		this.balance = balance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", balance=" + balance + "]";
	}

}
