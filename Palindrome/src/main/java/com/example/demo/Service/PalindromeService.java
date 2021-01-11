package com.example.demo.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Palindrome;
import com.example.demo.Repository.PalindromeRepo;

/**
 * PalindromeService is the class that holds all the logic implementations for
 * the application
 * 
 * @author Indirazith
 * 
 * @since 21.01
 *
 */
@Service
public class PalindromeService {

	@Autowired
	PalindromeRepo repo;

	@Autowired
	JdbcTemplate jdbc;

	int limitValue = 5;
	int offSet = 0;
	private static String FETCH_COUNT_SQL = "SELECT * FROM palindrome";

	public void setLimit() {
		limitValue = 5;
		offSet = 0;
	}

	public List<Palindrome> getData() {
		String sql = "SELECT * FROM palindrome LIMIT ";
		String limit = String.valueOf(this.limitValue + 5);
		int resultCount = (int) repo.count();
		String ORIGINAL_SQL = sql + String.valueOf(offSet) + "," + String.valueOf(limitValue);
		List<Palindrome> result = jdbc.query(ORIGINAL_SQL, new BeanPropertyRowMapper(Palindrome.class));
		if (resultCount <= limitValue) {
			return result;
		} else {
			if (result.size() <= 5) {
				offSet = offSet + result.size();
				limitValue = limitValue;
			} else {
				offSet = offSet + 5;
				limitValue = limitValue;
			}
			return result;
		}
	}

	public boolean checkCondition(String userInput) {
		char[] userInputArray = userInput.toLowerCase().toCharArray();
		StringBuilder reversedInput = new StringBuilder();
		char[] reverse = new char[userInputArray.length];
		for (int i = userInputArray.length - 1; i >= 0; i--) {
			if (userInputArray[i] != '\s')
				reversedInput.append(userInputArray[i]);
		}
		if (reversedInput.toString().equals(userInput.toLowerCase()) && userInputArray.length > 1)
			return true;
		else
			return false;
	}

	public void insert(String userInput) {
		jdbc.execute("INSERT INTO palindrome (palindrome) values (\"" + userInput + "\")");

	}

	public boolean checkOccurance(String userInput) {
		List<Map<String, Object>> a = jdbc.queryForList("SELECT * FROM palindrome WHERE palindrome = ?", userInput);
		int resultCount = a.lastIndexOf(a);
		if (resultCount == 0)
			return true;
		else
			return false;
	}

}
