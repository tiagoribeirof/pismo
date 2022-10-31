package com.pismo.pismotest.service;

import com.pismo.pismotest.dto.AccountDTO;
import com.pismo.pismotest.dto.DocumentDTO;

import reactor.core.publisher.Mono;

public interface AccountService {
	
	public Mono<AccountDTO> create(DocumentDTO documentDto);
	
	public Mono<AccountDTO> findById(Long accountId);

}
