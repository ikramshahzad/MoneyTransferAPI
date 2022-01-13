package com.money.transfer.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.money.transfer.constant.FieldNameLength;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "BN_MS_GT_GENERALTRANSACTION ")
public class Transaction {
	@Id
	@GeneratedValue
	private Long id;

	@NotNull(message = "Debit Account id must be present")
	@ApiModelProperty(required = true)
	@Column(nullable = false)
	private String debitId;

	@NotNull(message = "Credit Account id must be present")
	@ApiModelProperty(required = true)
	@Column(nullable = false)
	private String creditId;

	@NotNull
	@ApiModelProperty(required = true)
	@DecimalMin(value = "0", inclusive = false, message = "Transaction amount should be greater than zero")
	private BigDecimal amount;

	@Column(name = "create", nullable = false, updatable = false, columnDefinition = FieldNameLength.DATETIME)
	private LocalDateTime create = LocalDateTime.now();

	@JsonCreator
	public Transaction(@NotNull(message = "Debit Account id must be present") @JsonProperty("debitId") String debitId,
			@NotNull(message = "Credit Account id must be present") @JsonProperty("creditId") String creditId,
			@NotNull @Min(value = 0, message = "Transaction amount can not be less than zero") @JsonProperty("amount") BigDecimal amount) {
		this.debitId = debitId;
		this.creditId = creditId;
		this.amount = amount;
	}

	@JsonCreator
	public Transaction() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDebitId() {
		return debitId;
	}

	public void setDebitId(String debitId) {
		this.debitId = debitId;
	}

	public String getCreditId() {
		return creditId;
	}

	public void setCreditId(String creditId) {
		this.creditId = creditId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public LocalDateTime getCreate() {
		return create;
	}

	public void setCreate(LocalDateTime create) {
		this.create = create;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", debitId=" + debitId + ", creditId=" + creditId + ", amount=" + amount
				+ ", create=" + create + "]";
	}

}
