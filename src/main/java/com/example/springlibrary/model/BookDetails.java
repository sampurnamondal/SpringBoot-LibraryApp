package com.example.springlibrary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book_details")
public class BookDetails {

	@Id
	@Column(name = "book_id")
	private Integer bookId;

	@Column(name = "pages")
	private Integer pages;
	
	@Column(name  = "country")
	private String country;
	
//	@ManyToOne
//	@JoinColumn(name = "id")
//	private Book book;
	
	public BookDetails() {
	}
	
	public BookDetails(Integer bookId, Integer pages, String country) {
		this.bookId = bookId;
		this.pages = pages;
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

}
