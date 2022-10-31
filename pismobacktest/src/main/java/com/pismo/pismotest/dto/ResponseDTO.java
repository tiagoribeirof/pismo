package com.pismo.pismotest.dto;



import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Builder;




@Builder
public class ResponseDTO {
	
	@JsonProperty("status")
	private Integer status;
	
	@JsonProperty("value")
	private Object value;

}
