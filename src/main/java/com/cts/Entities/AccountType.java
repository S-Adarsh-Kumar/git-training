package com.cts.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

//import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table
@Entity
public class AccountType {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 private String accountType;
	 
	 @JsonBackReference
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name="User_Id")
	 private UserAccount userAccount;
	 
	 @Override
	 public String toString() {
		 return accountType;
	 }
}
