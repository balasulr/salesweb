package com.maxtrain.bootcamp.sales.customer;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
//Repository method to read Customer by Code. Will return a particular customer by Code
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	Optional<Customer> findByCode(String code);  //Optional because might not find a customer
}
