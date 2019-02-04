package com.capgemini.beans;

public class Account {
	
	
	private String acountHolder=null;
	private int accountNumber;
	private int balance;
	
	
	
	
	
	//GETTER - SETTER Methods 
	
	public String getAcountHolder() {
		return acountHolder;
	}
	public void setAcountHolder(String acountHolder) {
		this.acountHolder = acountHolder;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		result = prime * result + ((acountHolder == null) ? 0 : acountHolder.hashCode());
		long temp;
		temp = balance;
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (acountHolder == null) {
			if (other.acountHolder != null)
				return false;
		} else if (!acountHolder.equals(other.acountHolder))
			return false;
		if (balance != other.balance)
			return false;
		return true;
	}
	
	

}
