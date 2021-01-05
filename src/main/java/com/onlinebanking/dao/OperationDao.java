package com.onlinebanking.dao;

import java.util.List;

import com.onlinebanking.model.Account;
import com.onlinebanking.model.AccountInfo;

public interface OperationDao {
	int createAccount(Account account,double balance,int securityPin);
	int depositAmount(int id,double depositAmount,int pin);
	int withdrawAmount(int id,double withDrawnAmount,int pin);
	boolean checkBalance(int id,int pin);
	List<AccountInfo> listOfCustomers();
	AccountInfo findCustomer(int id);
	
	public int sumAndCountCustomers();
	int deleteCustomer(int id, int pin);
	
}
