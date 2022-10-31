package com.pismo.pismotest.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pismo.pismotest.dto.AccountDTO;
import com.pismo.pismotest.dto.DocumentDTO;
import com.pismo.pismotest.model.Account;
import com.pismo.pismotest.repository.AccountRepository;
import com.pismo.pismotest.service.AccountService;

import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountRepository accountRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository,ModelMapper modelMapper) {
		this.accountRepository = accountRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Mono<AccountDTO> create(DocumentDTO documentDto) {
		return Mono.justOrEmpty(modelMapper.map(documentDto, Account.class))
		.map(account -> accountRepository.save(account))
		.map(accountSaved -> modelMapper.map(accountSaved, AccountDTO.class));
	
	}


	@Override
	public Mono<AccountDTO> findById(Long accountId) {
		return Mono.justOrEmpty(accountRepository.findById(accountId))
				.map(account -> modelMapper.map(account, AccountDTO.class));
	}
	
	


}
