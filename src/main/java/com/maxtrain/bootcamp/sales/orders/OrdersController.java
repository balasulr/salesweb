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
	
	@GetMapping("reviews") // Returns all Orders in review status
	public ResponseEntity<Iterable<Orders>> getOrdersInReview() {
		var orders = ordRepo.findByStatus("REVIEW"); // When did the ordRepo. the findByStatus was located from the OrderRepository
		return new ResponseEntity<Iterable<Orders>>(orders, HttpStatus.OK);
	}
	
	@PostMapping // Add order
	public ResponseEntity<Orders> postOrder(@RequestBody Orders order) {
		if(order == null || order.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Bad request
		}
		order.setStatus("NEW");
		var ord = ordRepo.save(order);
		return new ResponseEntity<Orders>(ord, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes") // Have so that can use the ResponseEntity without the <> after the ResponseEntity
	@PutMapping("review/{id}")
	public ResponseEntity reviewOrder(@PathVariable int id, @RequestBody Orders order) {
		var statusValue = (order.getTotal() <= 50) ? "APPROVED" : "REVIEW"; // ternery operator so if order is less than 50, approved
		order.setStatus(statusValue);
		return putOrder(id, order);
	}
	
	@SuppressWarnings("rawtypes") // Have so that can use the ResponseEntity without the <> after the ResponseEntity
	@PutMapping("approve/{id}")
	public ResponseEntity approveOrder(@PathVariable int id, @RequestBody Orders order) {
		order.setStatus("APPROVED");
		return putOrder(id, order);
	}
	
	@SuppressWarnings("rawtypes") // Have so that can use the ResponseEntity without the <> after the ResponseEntity
	@PutMapping("reject/{id}")
	public ResponseEntity rejectOrder(@PathVariable int id, @RequestBody Orders order) {
		order.setStatus("REJECTED");
		return putOrder(id, order);
	}
	
	
	@SuppressWarnings("rawtypes") // Have so that can use the ResponseEntity without the <> after the ResponseEntity
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
