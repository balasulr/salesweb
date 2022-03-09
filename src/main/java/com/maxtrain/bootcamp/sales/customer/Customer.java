package com.maxtrain.bootcamp.sales.customer;

import javax.persistence.*;

@Entity // Entity Attribute is required while the Table attribute only if have a unique value
@Table(uniqueConstraints=@UniqueConstraint(name="UIDX_code", columnNames={"code"})) // Attribute to define code column to be unique
public class Customer {
	@Id // Show in italic if everything fine & shows that primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY) // Generates a unique value
	private int id;
	@Column(length=30, nullable=false)
	private String code;
	@Column(length=30, nullable=false)
	private String name;
	@Column(columnDefinition="decimal(9,2) NOT NULL DEFAULT 0.0")
	private double sales;
	private boolean active;
	
	public Customer() {} // Default Constructor
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSales() {
		return sales;
	}
	public void setSales(double sales) {
		this.sales = sales;
	}
	public boolean getActive() { // Initially created to be isActive but for consistency, changed to getActive
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
