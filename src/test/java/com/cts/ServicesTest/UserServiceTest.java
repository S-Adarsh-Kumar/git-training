package com.cts.ServicesTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.Entities.AccountType;
import com.cts.Entities.UserAccount;
import com.cts.Repositories.UserRepository;
import com.cts.Services.UserService;
public class UserServiceTest {
	
	private UserService userService;
	private UserRepository userRepository;

	@BeforeEach
	void setUp() {
		userRepository = mock(UserRepository.class);
		userService = new UserService();
		userService.setUserRepository(userRepository);
	}

	@Test
	void testAddUser() {
		UserAccount userAccount = new UserAccount();
		when(userRepository.save(userAccount)).thenReturn(userAccount);
		assertEquals(userAccount, userService.addUser(userAccount));
	}

	@Test
	void testGetUser() {
		Long id = 1L;
		UserAccount userAccount = new UserAccount();
		when(userRepository.findById(id)).thenReturn(Optional.of(userAccount));
		assertEquals(userAccount, userService.getUser(id));
	}
	
	@Test
	void testGetUserNotFound() {
		Long id = 1L;
		when(userRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(RuntimeException.class, () -> {
			userService.getUser(id);
		});
	}

	@Test
	void testAddSocialMediaAccountBasicSubscription() {
		Long userId = 1L;
		UserAccount userAccount = new UserAccount();
		userAccount.setId(userId);
		userAccount.setSubscriptionName("Basic");
		List<AccountType> accountTypes = new ArrayList<>();
		accountTypes.add(new AccountType());
		accountTypes.add(new AccountType());
		userAccount.setAccountType(accountTypes);

		AccountType socialMediaAccount = new AccountType();
		ResponseEntity<UserAccount> expectedResponseEntity = ResponseEntity.status(HttpStatus.OK).body(userAccount);

		when(userRepository.findById(userId)).thenReturn(Optional.of(userAccount));
		assertEquals(expectedResponseEntity, userService.addSocialMediaAccount(userId, socialMediaAccount));
	}

	@Test
	void testAddSocialMediaAccount() {
		Long userId = 1L;
		UserAccount userAccount = new UserAccount();
		userAccount.setId(userId);
		userAccount.setSubscriptionName("Premium");

		AccountType socialMediaAccount = new AccountType();
		ResponseEntity<UserAccount> expectedResponseEntity = ResponseEntity.status(HttpStatus.OK).body(userAccount);

		when(userRepository.findById(userId)).thenReturn(Optional.of(userAccount));
		assertEquals(expectedResponseEntity, userService.addSocialMediaAccount(userId, socialMediaAccount));
	}

	@Test
	void testAddSocialMediaAccountUserNotFound() {
		Long userId = 1L;
		AccountType socialMediaAccount = new AccountType();

		when(userRepository.findById(userId)).thenReturn(Optional.empty());
		assertThrows(RuntimeException.class, () -> {
			userService.addSocialMediaAccount(userId, socialMediaAccount);
		});
	}

	@Test
	void testGetAllusers() {
		List<UserAccount> expectedUserAccounts = new ArrayList<>();
		when(userRepository.findAll()).thenReturn(expectedUserAccounts);
		assertEquals(expectedUserAccounts, userService.getAllusers());
	}

}
