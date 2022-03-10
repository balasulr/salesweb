package com.maxtrain.bootcamp.sales.orders;

import javax.persistence.*;

import com.maxtrain.bootcamp.sales.customer.Customer;

@Entity
public class Orders {

	@Id // Shows that id is primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) // Generates a unique value
	@Column(columnDefinition="int")
	private int id;
	@Column(length=50, nullable=false)
	private String description;
	@Column(columnDefinition="decimal(9,2) not null")
	private double total;
	@Column(length=30, nullable=false)
	private String status;

	@ManyToOne(optional=false) // Many orders for one customer & optional says can't allow to be null
	@JoinColumn(name="customerId",columnDefinition="int")
	private Customer customer; // Foreign key to Customer

	
	public Orders() {} // Default Constructor
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
