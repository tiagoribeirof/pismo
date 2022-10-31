package com.pismo.pismotest.test.controller;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.pismo.pismotest.controller.TransactionController;
import com.pismo.pismotest.dto.TransactionDTO;
import com.pismo.pismotest.dto.TransactionSavedDTO;
import com.pismo.pismotest.model.Account;
import com.pismo.pismotest.model.OperationType;
import com.pismo.pismotest.model.Transaction;
import com.pismo.pismotest.service.TransactionService;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {
	
	@Autowired
	private WebTestClient webClient;

	@MockBean
	private TransactionService transactionService;
	
	private static final Long ACCOUNT_ID = 1L;
	private static final Long TRANSACTION_ID = 1L;
	private static final String DOCUMENT_NUMBER = "123456789";
	private static final String URL = "/transactions";
	private static final BigDecimal AMOUNT = new BigDecimal(50.0);
	private OperationType COMPRA_AVISTA = OperationType.COMPRA_AVISTA;
	
	
	private TransactionDTO createTransactionDTO(Integer operationId) {
		return TransactionDTO.builder().accountId(ACCOUNT_ID).amount(AMOUNT).operationTypeId(operationId).build();
	}
	
	private Transaction createTransaction() {
		return Transaction.builder()
				.account(Account.builder().accountId(ACCOUNT_ID).documentNumber(DOCUMENT_NUMBER).build()).amount(AMOUNT)
				.operationTypeId(COMPRA_AVISTA).eventDate(new Date()).transactionId(TRANSACTION_ID).build();
	}
	private TransactionSavedDTO buildTransactionSavedDTO(Transaction transaction) {
		return TransactionSavedDTO.builder().transactionId(transaction.getTransactionId()).build();
	}
	
	
	@Test
	public void shouldSaveTransactionAndReturnStatusAccepted() {

		TransactionDTO transactionDto = createTransactionDTO(COMPRA_AVISTA.getOperationTypeId());
		TransactionSavedDTO transactionSavedDTO = buildTransactionSavedDTO(createTransaction());

		when(transactionService.save(transactionDto)).thenReturn(Mono.just(transactionSavedDTO));

		webClient.post().uri(URL).bodyValue(transactionDto).exchange().expectStatus().isOk().expectBody()
				.jsonPath("status").isEqualTo(HttpStatus.ACCEPTED.value());

	}

}
