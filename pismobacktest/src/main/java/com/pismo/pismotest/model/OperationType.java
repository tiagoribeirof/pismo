package com.pismo.pismotest.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;




@Getter
@Entity
@Table(name = "Operation_Type")
public enum OperationType {
	 
	COMPRA_AVISTA(1, "COMPRA A VISTA"), COMPRA_PARCELADA(2, "COMPRA PARCELADA"), 
	SAQUE(3, "SAQUE"), PAGAMENTO(4, "PAGAMENTO");
	
	@Id
    @Column(name = "operation_type_id")
	private int operationTypeId;
	
    @Column(name = "description")
	private String description;
	
	private OperationType(int operationTypeId, String description) {
		this.operationTypeId = operationTypeId;
		this.description = description;
	}
	
	public static OperationType findById(int operationTypeId){
	    for(OperationType operation : values()){
	        if(operation.getOperationTypeId()== operationTypeId){
	            return operation;
	        }
	    }
	    return null;
	}
	

}
