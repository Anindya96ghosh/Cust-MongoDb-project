package com.rest.CustEmp.service;



import java.util.List;

import com.rest.CustEmp.Customer;

public interface Service {

	public abstract void addCustomer(Customer customer);
	public abstract List<Customer> viewAllCustomers();
	public abstract void updateCustomer(Customer customer);
	public abstract void deleteCustomer(int customerId);
}
