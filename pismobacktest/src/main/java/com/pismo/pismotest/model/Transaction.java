package com.pismo.pismotest.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "Transactions")
@Builder
@Getter
public class Transaction implements Serializable {
	
	
	    private static final long serialVersionUID = 4231929074680969634L;

		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    @Column(name = "transaction_id", nullable = false)
		private Long transactionId;
	    
	    @OneToOne
	    @JoinColumn(name = "account_id", nullable = false)
	    private Account account;
	    
	    @OneToOne
	    @JoinColumn(name = "operation_type_id", nullable = false)
	    @Enumerated(EnumType.ORDINAL)
	    private OperationType operationTypeId;
	    
	    @Column(name = "amount", nullable = false)
	    private BigDecimal amount;
	    
	    @CreationTimestamp
	    @Column(name = "EventDate" , nullable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date eventDate;
	    
	  

}
