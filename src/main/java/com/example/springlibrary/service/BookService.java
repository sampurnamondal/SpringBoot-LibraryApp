package com.example.springlibrary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springlibrary.model.Book;
import com.example.springlibrary.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public List<Book> getBook(Integer id) {
		List<Book> bookList = new ArrayList<Book>();
		bookList.add(bookRepository.findById(id).isPresent() ? bookRepository.findById(id).get() : null);
		return bookList;
	}

	public List<Book> getAllBooksByBookName(String bookName) {
		return bookRepository.findBybookName(bookName);
	}
	
	public List<Book> searchBook(Book book){
		return bookRepository.findByAttribute(book.getBookName(), book.getAuthorName(), book.getPublishingCompany());
	}

	public List<Book> addNewBook(Book book) {
		List<Book> bookList = new ArrayList<Book>();
		 if (book != null) {
			bookRepository.save(book);
			bookList = getBook(book.getId());
		}
		return bookList;
	}

	public List<Book> updateBook(Book book, Integer id) {
		List<Book> bookList = new ArrayList<Book>();
		if (book != null) {
			bookRepository.save(book);
			bookList = getBook(id);
		}
		return bookList;
	}

	public boolean deleteBook(Integer id) {
		boolean isBookPresent = bookRepository.findById(id).isPresent();
		if (isBookPresent) {
			bookRepository.deleteById(id);
		} else {
			return false;
		}
		return !(bookRepository.findById(id).isPresent());
	}

	public boolean deleteAllBooks() {
		bookRepository.deleteAll();
		return getAllBooks().size() == 0 ? true : false;
	}
}
