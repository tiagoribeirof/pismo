package com.pismo.pismotest.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;




@Data
public class DocumentDTO implements Serializable {
	
	private static final long serialVersionUID = -5180857567690691270L;
	
	@NotBlank
	@NotNull
	@JsonProperty("document_number")
	private String documentNumber;

}
