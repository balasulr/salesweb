package com.maxtrain.bootcamp.sales.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin // Annotation that is like the useCords in Startup.cs in C# specifically in prs-server-C35 (Makes sure frontend can talk to backend)
@RestController // Signifies that going to be sending & receiving JSON data
@RequestMapping("/api/customers")
public class CustomerController {
	
	@Autowired
	private CustomerRepository custRepo; // custRepo is the equivalent of _context in C#
	
	@GetMapping
	public ResponseEntity<Iterable<Customer>> getCustomers() { //async automatically & Iterable is a collection & is pretty much same as IEnumerable with list of customer instances
		var customers = custRepo.findAll();
		return new ResponseEntity<Iterable<Customer>>(customers, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
		var customer = custRepo.findById(id);
		if(customer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
	}
	
	@PostMapping // Add customer
	public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer) {
		if(customer == null || customer.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Bad request
		}
		var cust = custRepo.save(customer);
		return new ResponseEntity<Customer>(cust, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}") // Update customer
	public ResponseEntity putCustomer(@PathVariable int id, @RequestBody Customer customer) { // Don't need the <>
		if(customer == null || customer.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var cust = custRepo.findById(customer.getId());
		if(cust.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		custRepo.save(customer);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT); //The <> indicates that nothing is passed back
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteCustomer(@PathVariable int id) {
		var customer = custRepo.findById(id);
		if(customer.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		custRepo.delete(customer.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
