package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Palindrome;

@Repository
public interface PalindromeRepo extends CrudRepository<Palindrome,Long> {

}
