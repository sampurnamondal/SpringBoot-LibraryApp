package com.example.springlibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springlibrary.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
	
	@Query(value = "select * from book b where .book_name = ?1",
			nativeQuery = true)
	public List<Book> findBybookName(String bookName);
	
	@Query(value = "SELECT * from book b "
			+ "WHERE (b.book_name IS NULL OR b.book_name = ?1) "
			+ "AND (b.author_name IS NULL OR b.author_name = ?2 )"
			+ "AND (b.publishing_company IS NULL OR b.publishing_company = ?3)", nativeQuery = true)
	public List<Book> findByAttribute(String bookName, String authorName, String publishingCompany);

}
