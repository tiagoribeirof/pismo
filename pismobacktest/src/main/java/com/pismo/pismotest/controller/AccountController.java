package com.pismo.pismotest.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pismo.pismotest.dto.AccountDTO;
import com.pismo.pismotest.dto.DocumentDTO;
import com.pismo.pismotest.dto.ResponseDTO;
import com.pismo.pismotest.service.AccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class AccountController {
	
	private AccountService accountService;
	
	
	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	
	@Operation(summary = "Create an account based on document_number")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Account created", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = AccountDTO.class)) })})
	@PostMapping
	public ResponseEntity<Mono<ResponseDTO>> create(@RequestBody DocumentDTO documentDto) {
		return ResponseEntity.ok(accountService.create(documentDto).map(accountDto -> ResponseDTO.builder()
				.status(HttpStatus.CREATED.value()).value(accountDto).build()));

	}
	
	@Operation(summary = "Obtain account by Id")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Found an Account", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = AccountDTO.class)) })})
	@GetMapping("/{accountId}")
	public ResponseEntity<Mono<ResponseDTO>> getAccount(@Parameter(description = "id of account to be searched")
	@PathVariable Long accountId) {
		return ResponseEntity.ok(accountService.findById(accountId)
		.map(accountDTO -> ResponseDTO.builder().status(HttpStatus.OK.value()).value(accountDTO).build())
		.defaultIfEmpty(ResponseDTO.builder().status(HttpStatus.NOT_FOUND.value()).value(null).build()));
		
		
	}

}
