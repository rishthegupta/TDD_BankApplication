package com.capgemini.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.beans.Account;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientOpeningBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.repo.AccountRepo;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

public class BankApplication {

	@Mock 
	AccountRepo accountRepo;
	AccountService accountService;
	
	@Before
	public void setUp() throws Exception {
		
		
	
			MockitoAnnotations.initMocks(this);
			
			accountService = new AccountServiceImpl(accountRepo);
		
	}
	
	/*
	 * 	1. check if amount id >500
	 * 	2. create account on valid data
	 */

	@Test(expected=com.capgemini.exception.InsufficientOpeningBalanceException.class)
	public void whenTheAmountIsLessThan500SystemShouldThrowException() throws InsufficientOpeningBalanceException, InvalidAccountNumberException
	{
		accountService.createAccount(101, 400,"ABC");
	}
	
	
	
	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsufficientOpeningBalanceException, InvalidAccountNumberException
	{
		Account account =new Account();
		account.setAccountNumber(101);
		account.setBalance(5000);
		when(accountRepo.saveAccount(account)).thenReturn(true);
		assertEquals(account, accountService.createAccount(101, 5000, "ABC"));
	}

	
	/*
	 * 	DEPOSIT
	 * 	1. when account number is valid
	 * 	2. Deposit amount successfully
	 */
	
	@Test(expected=com.capgemini.exception.InvalidAccountNumberException.class)
	public void whenAccountNumberIsInvalidAndYouTryToDeposit() throws InvalidAccountNumberException
	{
		
		accountService.deposit(101,2000);
	}
	
	@Test()
	public void CheckIfAmountGetsDepositedWhenDetailsAreCiorrect() throws InvalidAccountNumberException
	{	
		
	
		Account account =new Account();
		account.setAccountNumber(101);
		account.setBalance(5000);
		when(accountRepo.searchAccount(101)).thenReturn(account);
		assertEquals(account.getBalance()+4000,accountService.deposit(101, 4000));
		
	}
	
	/*
	 *	WITHDRAW
	 * 	1. when account number is valid
	 * 	2. check weather after Withdrawal amount successfully deducted
	 *	3. Check weather Withdraw works correctly
	 */
	
	
	
	@Test(expected=com.capgemini.exception.InvalidAccountNumberException.class)
	public void whenAccountNumberIsInvalidAndYouTryToWithdraw() throws InvalidAccountNumberException, InsufficientBalanceException
	{
		
		accountService.withdraw(101,2000);
	}

	

	
	@Test()
	public void whenInsufficientBalanceWithdrawWillThrowException2() throws InvalidAccountNumberException, InsufficientBalanceException
	{	
		
	
		Account account =new Account();
		account.setAccountNumber(101);
		account.setBalance(5000);
		when(accountRepo.searchAccount(101)).thenReturn(account);
		assertEquals(account.getBalance()-4000,accountService.withdraw(101, 4000));
		
	}

	
	@Test()
	public void whenInsufficientBalanceWithdrawWillThrowException() throws InvalidAccountNumberException, InsufficientBalanceException
	{	
		
	
		Account account =new Account();
		account.setAccountNumber(101);
		account.setBalance(5000);
		when(accountRepo.searchAccount(101)).thenReturn(account);
		accountService.withdraw(101, 4000);
		
	}
	
	
	
}
