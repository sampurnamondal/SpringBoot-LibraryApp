package com.example.springlibrary.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springlibrary.model.Book;
import com.example.springlibrary.service.BookService;

@RestController
@RequestMapping(value ="/book")
public class BookController {

	@Autowired
	private BookService bookService;

	// get all books
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Object> getAllBooks() {

		ResponseEntity<Object> responseEntity = null;
		HttpHeaders responseHeader = new HttpHeaders();

		try {
			List<Book> bookList = bookService.getAllBooks();

			if (bookList != null & bookList.size() > 0) {
				responseHeader.setOrigin("SomeServer");
				responseHeader.setContentType(MediaType.APPLICATION_JSON);
				responseHeader.set("Message", "No. of books obtained : " + bookList.size());
				responseEntity = new ResponseEntity<Object>(bookList, responseHeader, HttpStatus.OK);
			} else {
				responseHeader.set("Message", "NO BOOKS FOUND");
				responseEntity = new ResponseEntity<Object>(responseHeader, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	// gets one book based on given ID
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getBook(@PathVariable Integer id) {

		HttpHeaders responseHeader = new HttpHeaders();
		ResponseEntity<Object> responseEntity = null;

		try {
			List<Book> bookList = bookService.getBook(id);

			if (!bookList.contains(null)) {
				responseHeader.setOrigin("SomeServer");
				responseHeader.setContentType(MediaType.APPLICATION_JSON);
				responseHeader.set("Message", "Got book at : " + id);
				responseEntity = new ResponseEntity<Object>(bookList, responseHeader, HttpStatus.OK);
			} else {
				responseHeader.set("Message", "NO BOOK FOUND AT GIVEN ID");
				responseEntity = new ResponseEntity<Object>(responseHeader, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	// get books by bookName in request parameter
	@RequestMapping(value = "/book_name", method = RequestMethod.GET)
	public ResponseEntity<Object> getBookByBookName(@RequestParam String name) {

		ResponseEntity<Object> responseEntity = null;
		HttpHeaders responseHeader = new HttpHeaders();

		try {
			List<Book> bookList = bookService.getAllBooksByBookName(name);

			if (bookList.size() > 0) {
				responseHeader.setOrigin("SomeServer");
				responseHeader.setContentType(MediaType.APPLICATION_JSON);
				responseHeader.set("Message", "No. of books obtained : " + bookList.size());
				responseEntity = new ResponseEntity<Object>(bookList, responseHeader, HttpStatus.OK);
			} else {
				responseHeader.set("Message", "NO BOOKS FOUND");
				responseEntity = new ResponseEntity<Object>(responseHeader, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	// search book by parameters
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ResponseEntity<Object> searchBook(@RequestBody Book book) {

		ResponseEntity<Object> responseEntity = null;
		HttpHeaders responseHeader = new HttpHeaders();

		try {
			List<Book> bookList = bookService.searchBook(book);
			if (bookList.size() > 0) {
				responseHeader.setOrigin("SomeServer");
				responseHeader.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
				responseHeader.set("Message", "FOUND THESE BOOKS");
				responseEntity = new ResponseEntity<Object>(bookList, responseHeader, HttpStatus.OK);
			} else {
				responseHeader.set("Message", "NO BOOKS FOUND");
				responseEntity = new ResponseEntity<Object>(responseHeader, HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	// add a new book in the library
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Object> putNewBook(@RequestBody Book book) {

		HttpHeaders responseHeader = new HttpHeaders();
		ResponseEntity<Object> responseEntity = null;

		try {
			List<Book> bookList = bookService.addNewBook(book);
			responseHeader.setOrigin("SomeServer");
			responseHeader.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			responseHeader.set("Message", "NEW BOOK ADDED");
			responseEntity = new ResponseEntity<Object>(bookList, responseHeader, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	// update book by id or create new book if no id
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateBook(@RequestBody Book book, @PathVariable Integer id) {

		HttpHeaders responseHeader = new HttpHeaders();
		ResponseEntity<Object> responseEntity = null;

		try {
			List<Book> bookList = bookService.updateBook(book, id);
			responseHeader.setOrigin("SomeServer");
			responseHeader.set("Message", "Updated book at : " + id);
			responseEntity = new ResponseEntity<Object>(bookList, responseHeader, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	// delete book by id
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteBook(@PathVariable Integer id) {

		HttpHeaders responseHeader = new HttpHeaders();
		ResponseEntity<Object> responseEntity = null;

		try {
			if (bookService.deleteBook(id)) {
				responseHeader.set("Message", "Deleted book at : " + id);
				responseEntity = new ResponseEntity<Object>(responseHeader, HttpStatus.OK);
			} else {
				responseHeader.set("Message", "ID NOT FOUND");
				responseEntity = new ResponseEntity<Object>(responseHeader, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	// delete all books
	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAllBooks() {

		HttpHeaders responseHeader = new HttpHeaders();
		ResponseEntity<Object> responseEntity = null;

		try {

			if (bookService.deleteAllBooks()) {
				responseHeader.set("Message", "Deleted all books");
				responseEntity = new ResponseEntity<Object>(responseHeader, HttpStatus.OK);
			} else {
				responseHeader.set("Message", "NO BOOKS FOUND TO DELETE");
				responseEntity = new ResponseEntity<Object>(responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}
}
