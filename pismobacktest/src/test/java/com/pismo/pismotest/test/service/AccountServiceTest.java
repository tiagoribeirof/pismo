package com.pismo.pismotest.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pismo.pismotest.dto.AccountDTO;
import com.pismo.pismotest.dto.DocumentDTO;
import com.pismo.pismotest.model.Account;
import com.pismo.pismotest.repository.AccountRepository;
import com.pismo.pismotest.service.impl.AccountServiceImpl;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
 class AccountServiceTest {
	
 @InjectMocks	
 private AccountServiceImpl accountService;
 
 @Mock
 private ModelMapper modelMapper;
 
 @Mock
 private AccountRepository accountRepository;
 
 private static final Long ACCOUNT_ID = 1L;
 private static final String DOCUMENT_NUMBER= "123456789";
 
 private Account createAccount() {
	 return Account.builder().documentNumber(DOCUMENT_NUMBER)
			 .accountId(ACCOUNT_ID).build();
 }
 
 private AccountDTO createAccountDTO() {
	 AccountDTO accountDTO = new AccountDTO();
	 accountDTO.setAccountId(ACCOUNT_ID);
	 accountDTO.setDocumentNumber(DOCUMENT_NUMBER);
	 return accountDTO;
 }
 
 @Test
  void shoudFindAccountById() {
	     Account account = createAccount();
		 when(accountRepository.findById(ACCOUNT_ID))
		 .thenReturn(Optional.of(account));
		 when(modelMapper.map(account, AccountDTO.class)).thenReturn(createAccountDTO());
		 
		 Mono<AccountDTO> accountDTOMono = accountService.findById(ACCOUNT_ID);
	        StepVerifier
            .create(accountDTOMono)
            .consumeNextWith(accountDTO -> {
                assertEquals(ACCOUNT_ID, accountDTO.getAccountId());
                assertEquals(DOCUMENT_NUMBER, accountDTO.getDocumentNumber());
            })
            .verifyComplete();
}
	 
 @Test
  void shoudNotFindAccountById() {
	     
		 when(accountRepository.findById(ACCOUNT_ID))
		 .thenReturn(Optional.empty());	
		 Mono<AccountDTO> accountDTOMono = accountService.findById(ACCOUNT_ID);
		 StepVerifier.create(accountDTOMono).expectNextCount(0).verifyComplete();
}
	 

 @Test
  void shoudCreateAccount() {
	     DocumentDTO documentDTO = new DocumentDTO();
	     documentDTO.setDocumentNumber(DOCUMENT_NUMBER);
	     
	     Account accountCreated = createAccount();
	     Account accountToSave = Account.builder().documentNumber(DOCUMENT_NUMBER).build();
	     when(modelMapper.map(documentDTO, Account.class)).thenReturn(accountToSave);
		 when(accountRepository.save(accountToSave))
		 .thenReturn(accountCreated);
		 when(modelMapper.map(accountCreated, AccountDTO.class)).thenReturn(createAccountDTO());
		 
		 
		 Mono<AccountDTO> accountDTOMono = accountService.create(documentDTO);
	        StepVerifier
            .create(accountDTOMono)
            .consumeNextWith(accountDTO -> {
                assertEquals(ACCOUNT_ID, accountDTO.getAccountId());
                assertEquals(DOCUMENT_NUMBER, accountDTO.getDocumentNumber());
            })
            .verifyComplete();
}
 
 @Test
  void shoudThrowDataIntegrityViolationExceptionWhenDocumentNumberIsNull() {
	     DocumentDTO documentDTO = new DocumentDTO();
	      
	     Account accountToSave = Account.builder().build();
	     when(modelMapper.map(documentDTO, Account.class)).thenReturn(accountToSave);
         when(accountRepository.save(accountToSave))
         .thenThrow(new DataIntegrityViolationException("DataIntegrityViolationException"));		 
		 Mono<AccountDTO> accountDTOMono = accountService.create(documentDTO);
	   
	        StepVerifier
            .create(accountDTOMono)
            .expectErrorMatches(throwable -> throwable instanceof DataIntegrityViolationException)
            .verify();

}
	

}
