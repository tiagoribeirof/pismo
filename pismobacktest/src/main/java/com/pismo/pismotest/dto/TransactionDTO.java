package com.pismo.pismotest.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDTO {
	
	@NotBlank
	@JsonProperty("account_id")
	private Long accountId;
	
	@NotBlank
	@JsonProperty("operation_type_id")
	private Integer operationTypeId;
	
	@NotBlank
	@JsonProperty("amount")
	private BigDecimal amount;

}
