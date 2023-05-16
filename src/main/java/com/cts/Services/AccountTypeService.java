package com.cts.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.Entities.AccountType;
import com.cts.Entities.UserAccount;
import com.cts.Repositories.AccountTypeRepository;
import com.cts.Repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;
//import com.cts.Repositories.AccountTypeRepository;
//import com.cts.Repositories.UserRepository;
@Service
@Slf4j
public class AccountTypeService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AccountTypeRepository typeRepository;
	@Autowired
	private UserService userService;
	
	 public void addSocialMediaAccount(Long userId, AccountType accountType) {
	        UserAccount userAccount = userService.getUser(userId);
	        if (userAccount.getSubscriptionName().equals("BASIC") && userAccount.getAccountType().size() >= 3) {
	            throw new RuntimeException("Basic subscription allows only 3 social media accounts.");
	        }
	        accountType.setUserAccount(userAccount);
	        userAccount.getAccountType().add(accountType);
	        typeRepository.save(accountType);
	    }
	 public void removeSocialMediaAccount(Long userId, AccountType accountType) {
	        UserAccount userAccount = userService.getUser(userId);
	        userAccount.getAccountType().remove(accountType);
	        userRepository.save(userAccount);
	    }
	 public List<AccountType> gellAllAccountsByUser(String username){
		 UserAccount user=userRepository.findByUsername(username);
		 return user.getAccountType();
//		 List<AccountType> accountsOfUser=new ArrayList<>();
//		 if(user.getUsername().equals(username)) {
//			 log.info("message");
//			 accountsOfUser.add(user.getAccountType());
//		 }
//		 System.out.println(accountsOfUser);
//		 return accountsOfUser;
		 
	    }
	

}
