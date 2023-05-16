package com.cts.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cts.Entities.AccountType;
import com.cts.Entities.UserAccount;
import com.cts.Repositories.AccountTypeRepository;
import com.cts.Repositories.UserRepository;
import com.cts.Services.AccountTypeService;
import com.cts.Services.UserService;

import AccountTypeDTO.SocialAccount;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AccountTypeService accountTypeService;
	@Autowired
	private UserRepository userRepo;

	@PostMapping("/")
	public UserAccount addUser(@RequestBody UserAccount userAccount) {
		return userService.addUser(userAccount);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserAccount> getUser(@PathVariable Long userId) {
		UserAccount user = userService.getUser(userId);
		System.out.println(user.getAccountType().size());
		return ResponseEntity.ok(userService.getUser(userId));
	}

	@PutMapping(value = "/api/subscriptions/{id}/addsocialaccount")
	public void addSocialMediaAccount(@PathVariable Long id, @RequestBody AccountType accountType) {
		userService.addSocialMediaAccount(id, accountType);
	}

	@GetMapping(value = "/allUsers")
	public List<UserAccount> getAllUsers() {
		return userService.getAllusers();
	}

	@GetMapping(value = "/allAccountsByUser/{username}")
	public void getAllAccountsByUser(@PathVariable String username) {
		System.out.println(accountTypeService.gellAllAccountsByUser(username));
	}

}
