package com.example.springlibrary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springlibrary.model.BookDetails;
import com.example.springlibrary.repository.BookDetailsRepository;;

@Service
public class BookDetailsService {

	@Autowired
	private BookDetailsRepository bookDetailsRepository;

	public List<BookDetails> getAllBookDetails() {
		return bookDetailsRepository.findAll();
	}

	public Optional<BookDetails> getBookDetails(Integer id) {
		return bookDetailsRepository.findById(id);
	}
	
	public void addNewBookDetails(BookDetails bookDetails) {
		bookDetailsRepository.save(bookDetails);
	}
	
	public void updateBookDetails(BookDetails bookDetails, Integer id) {
		bookDetailsRepository.save(bookDetails);
	}
	public void deleteBookDetails(Integer id) {
		bookDetailsRepository.deleteById(id);
	}

	public void deleteAllBookDetails() {
		bookDetailsRepository.deleteAll();
	}
}
