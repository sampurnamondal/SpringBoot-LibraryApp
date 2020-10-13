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
import org.springframework.web.bind.annotation.RestController;

import com.example.springlibrary.model.BookDetails;
import com.example.springlibrary.service.BookDetailsService;


@RestController
public class BookDetailsController {

	@Autowired
	private BookDetailsService bookDetailsService;

	// get all book details
	@RequestMapping(value = "/bookDetails", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllbookDetails() {

		ResponseEntity<Object> responseEntity = null;
		HttpHeaders responseHeader = new HttpHeaders();

		try {
			List<BookDetails> bookDetailsList = bookDetailsService.getAllBookDetails();
			
			if (bookDetailsList.size() > 0) {
				responseHeader.setOrigin("SomeServer");
				responseHeader.setContentType(MediaType.APPLICATION_JSON);
				responseHeader.set("Message", "Book details obtained of : " + bookDetailsList.size() + " books");
				responseEntity = new ResponseEntity<Object>(bookDetailsService.getAllBookDetails(), responseHeader, HttpStatus.OK);
			} else {
				responseHeader.set("Message", "NO BOOK DETAILS FOUND");
				responseEntity = new ResponseEntity<Object>(responseHeader, HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	// gets one book's details based on given ID 
	@RequestMapping(value = "/bookDetails/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getBookDetails(@PathVariable Integer id) {

		HttpHeaders responseHeader = new HttpHeaders();
		ResponseEntity<Object> responseEntity = null;

		try {
			if (bookDetailsService.getBookDetails(id).isPresent()) {
				responseHeader.setOrigin("SomeServer");
				responseHeader.setContentType(MediaType.APPLICATION_JSON);
				responseHeader.set("Message", "Got book details of book at : " + id);
				responseEntity = new ResponseEntity<Object>(bookDetailsService.getBookDetails(id), responseHeader, HttpStatus.OK);
			} else {
				responseHeader.set("Message", "NO SUCH BOOK DETAILS FOUND");
				responseEntity = new ResponseEntity<Object>(responseHeader, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	// add a new book's details in the library
	@RequestMapping(value = "/bookDetails", method = RequestMethod.POST)
	public ResponseEntity<Object> putNewBookDetails(@RequestBody BookDetails bookDetails) {

		HttpHeaders responseHeader = new HttpHeaders();
		ResponseEntity<Object> responseEntity = null;

		try {
			bookDetailsService.addNewBookDetails(bookDetails);
			responseHeader.setOrigin("SomeServer");
			responseHeader.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			responseHeader.set("Message", "NEW BOOK'S DETAILS ADDED");
			responseEntity = new ResponseEntity<Object>(bookDetails, responseHeader, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	// update book's details by id or create new book's details if no id present
	@RequestMapping(value = "/bookDetails/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateBookDetails(@RequestBody BookDetails bookDetails, @PathVariable Integer id) {

		HttpHeaders responseHeader = new HttpHeaders();
		ResponseEntity<Object> responseEntity = null;

		try {
			bookDetailsService.updateBookDetails(bookDetails, id);
			responseHeader.setOrigin("SomeServer");
			responseHeader.set("Message", "Updated book details of book at : " + id);
			responseEntity = new ResponseEntity<Object>(bookDetailsService.getBookDetails(id), responseHeader, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	// delete book's details by id
	@RequestMapping(value = "/bookDetails/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteBookDetails(@PathVariable Integer id) {

		HttpHeaders responseHeader = new HttpHeaders();
		ResponseEntity<Object> responseEntity = null;

		try {
			if (bookDetailsService.getBookDetails(id).isPresent()) {
				bookDetailsService.deleteBookDetails(id);
				responseHeader.set("Message", "Deleted book details at ID : " + id);
				responseEntity = new ResponseEntity<Object>(responseHeader, HttpStatus.OK);
			} else {
				responseHeader.set("Message", "BOOK DETAILS NOT FOUND");
				responseEntity = new ResponseEntity<Object>(responseHeader, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

	// delete all books' details
	@RequestMapping(value = "/bookDetails", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAllBookDetails() {

		HttpHeaders responseHeader = new HttpHeaders();
		ResponseEntity<Object> responseEntity = null;

		try {
			List<BookDetails> bookDetailsList = bookDetailsService.getAllBookDetails();

			if (bookDetailsList.size() > 0) {
				bookDetailsService.deleteAllBookDetails();
				responseHeader.set("Message", "Deleted all book details");
				responseEntity = new ResponseEntity<Object>(responseHeader, HttpStatus.OK);
			} else {
				responseHeader.set("Message", "NO BOOK DETAILS FOUND TO DELETE");
				responseEntity = new ResponseEntity<Object>(responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
	}

}
