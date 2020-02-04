package com.aj.service;

import java.util.ArrayList;
import java.util.List;

import com.aj.dao.AccDAO;
import com.aj.dao.AccDAOImpl;
import com.aj.models.Account;

public class AccountServiceImpl implements AccountService {
	private AccDAO accDao = new AccDAOImpl();
	private List<Account> accList = new ArrayList<>();
	private Account currentAcc = new Account();
	
	@Override
	public List<Account> getAllAccounts() {
		accList = accDao.selectAllAccounts();
		return accList;
	}

	@Override
	public Account getAccount(Account account) {
		currentAcc = accDao.selectAccByAccId(account.getAcc_id());
		
		return currentAcc;
	}

	@Override
	public void createAccount(Account account) {
		accDao.createNewAcc(account);
		
	}

	@Override
	public void depositMoney(Account account, int amount) {
		currentAcc = accDao.selectAccByAccId(account.getAcc_id());
		float currBal = currentAcc.getBalance();
		
		float newBal = currBal + amount;
		
		currentAcc.setBalance(newBal);
		accDao.updateAccount(currentAcc);
	}

	@Override
	public boolean withdrawMoney(Account account, int amount) {
		currentAcc = accDao.selectAccByAccId(account.getAcc_id());
		float currBal = currentAcc.getBalance();
		
		if (currBal < amount) {
			return false;
		}
		float newBal = currBal - amount;
		
		currentAcc.setBalance(newBal);
		accDao.updateAccount(currentAcc);
		return true;
	}

}
