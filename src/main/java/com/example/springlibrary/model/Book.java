package com.example.springlibrary.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "book_name")
	private String bookName;

	@Column(name = "author_name")
	private String authorName;
	
	@Column(name  = "publishing_company")
	private String publishingCompany;
	
//	@OneToMany(mappedBy = "book")
//	private Set<BookDetails> bookDetails = new HashSet();

	public Book() {
	}

	public Book(Integer id, String bookName, String authorName, String publishingCompany) {
		this.id = id;
		this.bookName = bookName;
		this.authorName = authorName;
		this.publishingCompany = publishingCompany;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getPublishingCompany() {
		return publishingCompany;
	}

	public void setPublishingCompany(String publishingCompany) {
		this.publishingCompany = publishingCompany;
	}
	
}
