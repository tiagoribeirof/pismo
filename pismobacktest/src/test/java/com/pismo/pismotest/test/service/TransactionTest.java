package com.pismo.pismotest.test.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.pismo.pismotest.dto.TransactionDTO;
import com.pismo.pismotest.dto.TransactionSavedDTO;
import com.pismo.pismotest.model.Account;
import com.pismo.pismotest.model.OperationType;
import com.pismo.pismotest.model.Transaction;
import com.pismo.pismotest.repository.TransactionRepository;
import com.pismo.pismotest.service.impl.TransactionServiceImpl;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class TransactionTest {

	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private TransactionServiceImpl transactionService;

	private static final Long ACCOUNT_ID = 1L;
	private static final Long TRANSACTION_ID = 1L;
	private static final String DOCUMENT_NUMBER = "123456789";
	private static final BigDecimal AMOUNT = new BigDecimal(50.0);
	private OperationType COMPRA_AVISTA = OperationType.COMPRA_AVISTA;
	private OperationType COMPRA_PARCELADA = OperationType.COMPRA_PARCELADA;
	private OperationType SAQUE = OperationType.SAQUE;
	private OperationType PAGAMENTO = OperationType.PAGAMENTO;

	private TransactionDTO createTransactionDTO(Integer operationId) {
		return TransactionDTO.builder().accountId(ACCOUNT_ID).amount(AMOUNT).operationTypeId(operationId).build();
	}

	private Transaction createTransaction(BigDecimal amount, OperationType operation) {
		return Transaction.builder()
				.account(Account.builder().accountId(ACCOUNT_ID).documentNumber(DOCUMENT_NUMBER).build()).amount(AMOUNT)
				.operationTypeId(operation).eventDate(new Date()).transactionId(TRANSACTION_ID).build();
	}

	@Test
	public void shoudSaveTransactionForCompraAVista() {
		TransactionDTO transactionDto = createTransactionDTO(COMPRA_AVISTA.getOperationTypeId());
		Transaction transaction = createTransaction(new BigDecimal(0).subtract(AMOUNT), COMPRA_AVISTA);
		when(transactionRepository.save(Mockito.any())).thenReturn(transaction);

		Mono<TransactionSavedDTO> transactionSavedDTO = transactionService.save(transactionDto);
		StepVerifier.create(transactionSavedDTO).consumeNextWith(transactionSavedDTO2 -> {
			assertEquals(TRANSACTION_ID, transactionSavedDTO2.getTransactionId());

		}).verifyComplete();
	}

	@Test
	public void shoudSaveTransactionForCompraParcelada() {
		TransactionDTO transactionDto = createTransactionDTO(COMPRA_PARCELADA.getOperationTypeId());
		Transaction transaction = createTransaction(new BigDecimal(0).subtract(AMOUNT), COMPRA_PARCELADA);
		when(transactionRepository.save(Mockito.any())).thenReturn(transaction);

		Mono<TransactionSavedDTO> transactionSavedDTO = transactionService.save(transactionDto);
		StepVerifier.create(transactionSavedDTO).consumeNextWith(transactionSavedDTO2 -> {
			assertEquals(TRANSACTION_ID, transactionSavedDTO2.getTransactionId());

		}).verifyComplete();
	}

	@Test
	public void shoudSaveTransactionForSaque() {
		TransactionDTO transactionDto = createTransactionDTO(SAQUE.getOperationTypeId());
		Transaction transaction = createTransaction(new BigDecimal(0).subtract(AMOUNT), SAQUE);
		when(transactionRepository.save(Mockito.any())).thenReturn(transaction);

		Mono<TransactionSavedDTO> transactionSavedDTO = transactionService.save(transactionDto);
		StepVerifier.create(transactionSavedDTO).consumeNextWith(transactionSavedDTO2 -> {
			assertEquals(TRANSACTION_ID, transactionSavedDTO2.getTransactionId());

		}).verifyComplete();
	}

	@Test
	public void shoudSaveTransactionForPagamento() {
		TransactionDTO transactionDto = createTransactionDTO(PAGAMENTO.getOperationTypeId());
		Transaction transaction = createTransaction(AMOUNT, PAGAMENTO);
		when(transactionRepository.save(Mockito.any())).thenReturn(transaction);

		Mono<TransactionSavedDTO> transactionSavedDTO = transactionService.save(transactionDto);
		StepVerifier.create(transactionSavedDTO).consumeNextWith(transactionSavedDTO2 -> {
			assertEquals(TRANSACTION_ID, transactionSavedDTO2.getTransactionId());

		}).verifyComplete();
	}

	@Test
	public void shoudThrowDataIntegrityViolationExceptionWhenAccountIsNulll() {
		TransactionDTO transactionDto = createTransactionDTO(PAGAMENTO.getOperationTypeId());
		when(transactionRepository.save(Mockito.any()))
				.thenThrow(new DataIntegrityViolationException("DataIntegrityViolationException"));
		Mono<TransactionSavedDTO> transactionSavedDTO = transactionService.save(transactionDto);

		StepVerifier.create(transactionSavedDTO)
				.expectErrorMatches(throwable -> throwable instanceof DataIntegrityViolationException).verify();

	}

}
