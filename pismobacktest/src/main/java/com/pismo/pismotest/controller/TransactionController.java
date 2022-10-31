package com.pismo.pismotest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pismo.pismotest.dto.AccountDTO;
import com.pismo.pismotest.dto.ResponseDTO;
import com.pismo.pismotest.dto.TransactionDTO;
import com.pismo.pismotest.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	private TransactionService transactioService;

	@Autowired
	public TransactionController(TransactionService transactioService) {
		this.transactioService = transactioService;
	}

	@PostMapping
	@Operation(summary = "Create a Transaction")
	@ApiResponses(value = { 
			  @ApiResponse(responseCode = "200", description = "Transaction was created", 
			    content = { @Content(mediaType = "application/json", 
			      schema = @Schema(implementation = AccountDTO.class)) })})
	public ResponseEntity<Mono<ResponseDTO>> create(@RequestBody TransactionDTO transactionDTO) {
		return ResponseEntity.ok(transactioService.save(transactionDTO)
				.map(transaction -> ResponseDTO.builder()
						.status(HttpStatus.ACCEPTED.value()).value(transaction).build()));
	}

}
