package com.pismo.pismotest.service;

import com.pismo.pismotest.dto.TransactionDTO;
import com.pismo.pismotest.dto.TransactionSavedDTO;

import reactor.core.publisher.Mono;

public interface TransactionService {
	
	public Mono<TransactionSavedDTO> save(TransactionDTO transaction);

}
