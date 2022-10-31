package com.pismo.pismotest.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class AccountDTO extends DocumentDTO {
	
	private static final long serialVersionUID = -2506354595303811186L;
	@NotBlank
	@JsonProperty("account_id")
	private Long accountId;
	

}
