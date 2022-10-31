package com.pismo.pismotest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pismo.pismotest.model.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
