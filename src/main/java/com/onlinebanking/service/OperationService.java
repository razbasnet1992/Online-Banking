package com.onlinebanking.service;

import java.util.List;

import com.onlinebanking.model.Account;
import com.onlinebanking.model.AccountInfo;


public interface OperationService {
	int createAccount(Account account);
	int depositAmount(int id,double depositAmount);
	int withdrawAmount(int id,double withdrawnAmount);
	boolean checkBalance(int id);
	
	List<AccountInfo> listOfCustomers();
	AccountInfo findCustomer(int id);
	public int deleteCustomer(int id);
}
