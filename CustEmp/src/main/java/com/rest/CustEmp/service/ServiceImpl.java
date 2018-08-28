package com.rest.CustEmp.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rest.CustEmp.Customer;
import com.rest.CustEmp.repository.CustMongoRepository;

@Component
public class ServiceImpl implements Service{
	
	@Autowired
	//CustomerRepository repository;
	CustMongoRepository repository;

	public void addCustomer(Customer customer) {		
		repository.save(customer);
	}

	public List<Customer> viewAllCustomers() {
		return repository.findAll();
	}

	public Customer getById(int id) {
		return repository.findByCustomerId(id).get();
	}	

	public void updateCustomer(Customer customer) {
		repository.save(customer);
	}

	public void deleteCustomer(int customerId) {
		repository.deleteByCustomerId(customerId);
		
	}

	
	
}
