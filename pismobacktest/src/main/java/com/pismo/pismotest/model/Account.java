package com.pismo.pismotest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name =  "Accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account implements Serializable {
	
    private static final long serialVersionUID = -4071936694393930810L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "account_id", nullable = false)
	private Long accountId;
	
    @Column(name = "document_number", nullable = false, length = 20, unique = true)
	private String documentNumber;

}
