package com.maxtrain.bootcamp.sales.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrdersController {
	@Autowired
	private OrderRepository ordRepo;

	@GetMapping
	public ResponseEntity<Iterable<Orders>> getOrders() {
		var orders = ordRepo.findAll();
		return new ResponseEntity<Iterable<Orders>>(orders, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Orders> getOrder(@PathVariable int id) {
		var order = ordRepo.findById(id);
		if(order.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Orders>(order.get(), HttpStatus.OK);
	}
	
	@PostMapping // Add order
	public ResponseEntity<Orders> postOrder(@RequestBody Orders order) {
		if(order == null || order.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Bad request
		}
		var ord = ordRepo.save(order);
		return new ResponseEntity<Orders>(ord, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}") // Update order
	public ResponseEntity putOrder(@PathVariable int id, @RequestBody Orders order) { // Don't need the <>
		if(order == null || order.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var ord = ordRepo.findById(order.getId());
		if(ord.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ordRepo.save(order);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //The <> indicates that nothing is passed back
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteOrder(@PathVariable int id) {
		var order = ordRepo.findById(id);
		if(order.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		ordRepo.delete(order.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}