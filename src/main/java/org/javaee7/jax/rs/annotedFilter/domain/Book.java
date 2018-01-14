package org.javaee7.jax.rs.annotedFilter.domain;

public class Book {

	private Long id;
	private String name;
	
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
