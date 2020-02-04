package com.aj.service;

import java.util.List;

import com.aj.models.Account;

public interface AccountService {
	public List<Account> getAllAccounts();
	public Account getAccount(Account account);
	public void createAccount(Account account);
	public void depositMoney(Account account, int amount);
	public boolean withdrawMoney(Account account, int amount);
	
}
