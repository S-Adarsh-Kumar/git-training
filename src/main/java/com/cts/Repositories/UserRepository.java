package com.cts.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.Entities.UserAccount;

import AccountTypeDTO.UserSocialAccount;

public interface UserRepository extends JpaRepository<UserAccount, Long>{
	public UserAccount findByUsername(String username);
}
