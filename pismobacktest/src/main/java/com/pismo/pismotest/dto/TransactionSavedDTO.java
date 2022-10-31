package com.pismo.pismotest.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class TransactionSavedDTO implements Serializable {
	
	
	private static final long serialVersionUID = -8894713869125089587L;
	
	@JsonProperty("transaction_id")
	private Long transactionId;

}
