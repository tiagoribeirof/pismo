package com.pismo.pismotest.repository;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pismo.pismotest.model.Account;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	
	 @Cacheable(value = "accountCache")
	 Optional<Account> findById(Long id);
		

	

}
