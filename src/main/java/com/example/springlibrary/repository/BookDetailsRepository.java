package com.example.springlibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springlibrary.model.BookDetails;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetails, Integer>{

}
