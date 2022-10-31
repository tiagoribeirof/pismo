package com.pismo.pismotest.test.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.pismo.pismotest.controller.AccountController;
import com.pismo.pismotest.dto.AccountDTO;
import com.pismo.pismotest.dto.DocumentDTO;
import com.pismo.pismotest.service.AccountService;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(AccountController.class)
public class AccountControllerTest {

	@Autowired
	private WebTestClient webClient;

	@MockBean
	private AccountService accountService;
	

	private static final String DOCUMENT_NUMBER = "123456789";
	private static final Long ACCOUNT_ID = 1L;

	private static final String URL = "/accounts";

	private AccountDTO createAccountDTO() {
		AccountDTO accountDto = new AccountDTO();
		accountDto.setAccountId(ACCOUNT_ID);
		accountDto.setDocumentNumber(DOCUMENT_NUMBER);
		return accountDto;
	}

	@Test
	public void shouldCreateAccounAndReturnStatusCreated() {

		DocumentDTO documentDto = new DocumentDTO();
		documentDto.setDocumentNumber(DOCUMENT_NUMBER);

		when(accountService.create(documentDto)).thenReturn(Mono.just(createAccountDTO()));

		webClient.post().uri(URL).bodyValue(documentDto).exchange().expectStatus().isOk().expectBody()
				.jsonPath("status").isEqualTo(HttpStatus.CREATED.value());

	}
	
	@Test
	public void shouldReturnAccount() {

		when(accountService.findById(ACCOUNT_ID)).thenReturn(Mono.just(createAccountDTO()));

		webClient.get().uri(URL+ "/"+ ACCOUNT_ID).exchange().expectStatus().isOk().expectBody()
				.jsonPath("status").isEqualTo(HttpStatus.OK.value());

	}
	
	@Test
	public void shouldNotFiindAccountAndRerturnStatusNotFound() {

		when(accountService.findById(ACCOUNT_ID)).thenReturn(Mono.empty());

		webClient.get().uri(URL+ "/"+ ACCOUNT_ID).exchange().expectStatus().isOk().expectBody()
				.jsonPath("status").isEqualTo(HttpStatus.NOT_FOUND.value());

	}

}
