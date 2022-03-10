package com.maxtrain.bootcamp.sales.orders;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders, Integer>{
	Iterable<Orders> findByStatus(String status); // Method that is like a where clause based on the column with the findBy (for the get operations)
}
