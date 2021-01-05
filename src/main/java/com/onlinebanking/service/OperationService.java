package com.onlinebanking.service;

import java.util.List;

import com.onlinebanking.model.Account;
import com.onlinebanking.model.AccountInfo;


public interface OperationService {
	int createAccount(Account account,double balance,int securityPin);
	int depositAmount(int id,double depositAmount,int pin);
	int withdrawAmount(int id,double withdrawnAmount,int pin);
	boolean checkBalance(int id,int pin);
	
	List<AccountInfo> listOfCustomers();
	AccountInfo findCustomer(int id);
	public int deleteCustomer(int id);
	public int sumAndCountCustomers();
	//public boolean securityCheck();
}
