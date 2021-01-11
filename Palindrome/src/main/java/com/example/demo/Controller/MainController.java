package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Palindrome;
import com.example.demo.Service.PalindromeService;

/**
 * MainController is the entry controller for this palindrome screen
 * 
 * @author Indirazith M
 * 
 * @since 21.01
 *
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/MainController")
public class MainController {

	@Autowired
	PalindromeService service;

	/**
	 * setFetchLimit is used to reset the fetch limit to default
	 * 
	 * @return void
	 */
	@GetMapping("/setDefaultLimit")
	public void setFetchLimit() {
		service.setLimit();
	}

	/**
	 * getList is used to load the palindrome table
	 * 
	 * @return List<Palindrome>
	 */
	@GetMapping("/getData")
	public List<Palindrome> getList() {
		return service.getData();
	}

	/**
	 * checkConditionAndInsert is used to check for palindrome and if insert in db
	 * 
	 * @param userInput
	 * @return boolean
	 */
	@PostMapping("/chcekForPalindrome")
	public boolean checkConditionAndInsert(@RequestBody String userInput) {
		boolean resultCondition = service.checkCondition(userInput);
		if (resultCondition) {
			service.insert(userInput);
		}
		return resultCondition;

	}

}
