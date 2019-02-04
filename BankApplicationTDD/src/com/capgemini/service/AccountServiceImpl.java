package com.capgemini.service;

import com.capgemini.beans.Account;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientOpeningBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.repo.AccountRepo;


public class AccountServiceImpl implements AccountService {

	AccountRepo accountRepo;
	
	
	
	public AccountServiceImpl(AccountRepo accocuntRepo) {
		super();
		this.accountRepo = accocuntRepo;
	}

	@Override
	public Account createAccount(int accountNumber, int amount, String accountHolder) throws InsufficientOpeningBalanceException, InvalidAccountNumberException {
		
		if(amount<500)
		{
			throw new InsufficientOpeningBalanceException();
		}
		Account account = new Account();
		account.setAccountNumber(accountNumber);
		
		account.setBalance(amount);
		 
		if(accountRepo.saveAccount(account))
		{
			return account;
		}
	     
		return null;
		
	}

	@Override
	public int deposit(int accountNumber, int amount) throws InvalidAccountNumberException{
		Account account=accountRepo.searchAccount(accountNumber);
		if(account==null)
			throw new InvalidAccountNumberException();
		account.setBalance(account.getBalance()+amount);
		accountRepo.saveAccount(account);
			return account.getBalance();
		
	}

	@Override
	public int withdraw(int accountNumber, int amount) throws InvalidAccountNumberException,InsufficientBalanceException {
		Account account=accountRepo.searchAccount(accountNumber);
		
		
		if(account==null)
		{
			System.out.println("Im null");
			
			throw new InvalidAccountNumberException();
		}
		if(account.getBalance()<amount)
		{
			System.out.println("lol");
			throw new InsufficientBalanceException();
		}
		
		account.setBalance(account.getBalance()-amount);
		accountRepo.saveAccount(account);
			
			
		return account.getBalance();
		
	
		
		
		
			
	}


}
