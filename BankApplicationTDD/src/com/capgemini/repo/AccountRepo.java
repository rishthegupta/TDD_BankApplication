package com.capgemini.repo;

import com.capgemini.beans.Account;

import com.capgemini.exception.InvalidAccountNumberException;

public interface AccountRepo {
	
	
	boolean saveAccount(Account account) throws InvalidAccountNumberException;
	Account searchAccount(int accountNumber) throws InvalidAccountNumberException;

}
