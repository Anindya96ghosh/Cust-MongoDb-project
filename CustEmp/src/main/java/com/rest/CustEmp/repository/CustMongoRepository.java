package com.rest.CustEmp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rest.CustEmp.Customer;

@Repository
public interface CustMongoRepository extends MongoRepository<Customer,ObjectId> {

}
