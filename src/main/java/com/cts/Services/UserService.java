package com.cts.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.Entities.AccountType;
import com.cts.Entities.UserAccount;
import com.cts.Repositories.UserRepository;

import AccountTypeDTO.UserSocialAccount;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class UserService {
	@Autowired
	private UserRepository userRepository;
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	public UserAccount addUser(UserAccount userAccount) {
		//userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        return userRepository.save(userAccount);
    }

    public UserAccount getUser(Long id) {
    	log.debug("Using debug");

        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));
    }
    
    public ResponseEntity<UserAccount> addSocialMediaAccount(Long userId, AccountType socialMediaAccount) {
        UserAccount user = userRepository.findById(userId).orElse(null);
        if(user.getSubscriptionName().equalsIgnoreCase("Basic") && user.getAccountType().size()>=3) {
        	throw new RuntimeException("Basic subscription allows only 3 social media accounts.");
        }
        else if(user != null) {
        	log.info("Using logging");
            socialMediaAccount.setUserAccount(user);
            user.getAccountType().add(socialMediaAccount);
            userRepository.save(user);
        }
       
        return ResponseEntity.ok(user);
    }
    
    public List<UserAccount> getAllusers(){
    	return userRepository.findAll();
    }
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
}
