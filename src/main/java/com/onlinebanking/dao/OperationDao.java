package com.onlinebanking.dao;

import java.util.List;

import com.onlinebanking.model.Account;
import com.onlinebanking.model.AccountInfo;

public interface OperationDao {
	int createAccount(Account account,double balance);
	int depositAmount(int id,double depositAmount);
	int withdrawAmount(int id,double withDrawnAmount);
	boolean checkBalance(int id);
	List<AccountInfo> listOfCustomers();
	AccountInfo findCustomer(int id);
	public int deleteCustomer(int id);
	
}
