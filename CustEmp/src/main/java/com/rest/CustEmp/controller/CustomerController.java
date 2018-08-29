package com.rest.CustEmp.controller;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.CustEmp.Customer;
import com.rest.CustEmp.service.ServiceImpl;

@CrossOrigin
@RestController
public class CustomerController {
	
	@Autowired
	private ServiceImpl service; 
	
	
	
	@RequestMapping(value="/customer/add", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		System.out.println(service);
		service.addCustomer(customer);
		return new ResponseEntity<Customer>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/customers", method=RequestMethod.GET, produces=MediaType.ALL_VALUE)
	public ResponseEntity<List<Customer>> viewAllCustomers() {
		return new ResponseEntity<List<Customer>>(service.viewAllCustomers(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/customer/update", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<Customer>  updateCustomer(@RequestBody Customer customer) {
		service.updateCustomer(customer);
		return new ResponseEntity<Customer>(HttpStatus.OK);
	}
	
	@RequestMapping(value="/customer/delete/{customerId}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {
		service.deleteCustomer(customerId);
		return new ResponseEntity<String>("Customer with id "+customerId+" has been deleted", HttpStatus.OK);
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/customer/{start}/{count}", method=RequestMethod.GET)
	public ResponseEntity<Resources> getCustomersByPages(@PathVariable int start, @PathVariable int count) {
		List<Customer> tempCustomers = service.viewAllCustomers();
		List<Customer> customers = new ArrayList<>();
		
		for(int i = start; i<start+count; i++) {
			customers.add(tempCustomers.get(i));
		}
	
		Link nextLink = linkTo(methodOn(this.getClass()).getCustomersByPages(start+count, count)).withRel("Next Link");
		Link previousLink = linkTo(methodOn(this.getClass()).getCustomersByPages(start-count>=0?start-count:1, count)).withRel("Previous Link");
		Resources resources = new Resources<>(customers,previousLink,nextLink);
		return new ResponseEntity<Resources>(resources, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getById/{customerId}", method=RequestMethod.GET)
	public ResponseEntity<Resource<Customer>> getById(@PathVariable int customerId){
		Resource<Customer> resource = null;

		
		resource= new Resource<Customer>(service.getById(customerId));
		
		Link viewAll = linkTo(methodOn(this.getClass()).viewAllCustomers()).withRel("All-Customers");

		resource.add(viewAll);
		

		return new ResponseEntity<Resource<Customer>>(resource,HttpStatus.OK);
		
	}

}
