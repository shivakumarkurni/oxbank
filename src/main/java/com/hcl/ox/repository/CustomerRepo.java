package com.hcl.ox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.ox.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	public Customer findBycustomerId(long customerId);
	
	//public List<Customer> findBycustomerId();
	
}
