package com.example.springlibrary.controller;

import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping(path = "/hello", method = RequestMethod.POST)
	public Object helloWorld(@RequestBody(required = false) Object body,  @RequestHeader(name = "myAttribute", required = false) String header) {

		ResponseEntity<Object> responseEntity = null;

		try {
			System.out.println(body + header);
			responseEntity = new ResponseEntity<Object>(HttpStatus.OK);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		} catch (Exception e) {
			responseEntity = new ResponseEntity<Object>("Error " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseEntity;
	}

}
