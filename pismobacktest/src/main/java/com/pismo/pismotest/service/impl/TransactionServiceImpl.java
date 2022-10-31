package com.pismo.pismotest.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pismo.pismotest.dto.TransactionDTO;
import com.pismo.pismotest.dto.TransactionSavedDTO;
import com.pismo.pismotest.model.Account;
import com.pismo.pismotest.model.OperationType;
import com.pismo.pismotest.model.Transaction;
import com.pismo.pismotest.repository.TransactionRepository;
import com.pismo.pismotest.service.TransactionService;

import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {

	private TransactionRepository transactionRepository;

 
	@Autowired
	public TransactionServiceImpl(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	

	}

	@Override
	public Mono<TransactionSavedDTO> save(TransactionDTO transactionDto) {
		return Mono.just(transactionDto)
		.map(this::buildTransaction)
		.map(transaction -> transactionRepository.save(transaction))
		.map(this::buildTransactionSavedDTO);
	
	}
	
	
	
	private Transaction buildTransaction(TransactionDTO transactionDto) {
		BigDecimal amount = new BigDecimal(0);
		if (!transactionDto.getOperationTypeId().equals(OperationType.PAGAMENTO.getOperationTypeId())) {
			amount = amount.subtract(transactionDto.getAmount());
			} else {
			amount = transactionDto.getAmount();
		}
		return 	Transaction.builder().amount(amount)
				.account(Account.builder().accountId(transactionDto.getAccountId()).build())
				.operationTypeId(OperationType.findById(transactionDto.getOperationTypeId())).build();
	}
	

	private TransactionSavedDTO buildTransactionSavedDTO(Transaction transaction) {
		return TransactionSavedDTO.builder().transactionId(transaction.getTransactionId()).build();
	}
	


}
