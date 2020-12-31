package com.onlinebanking.service;

import java.util.List;

import com.onlinebanking.dao.OperationDao;
import com.onlinebanking.dao.OperationDaoImp;
import com.onlinebanking.model.Account;
import com.onlinebanking.model.AccountInfo;

public class OperationServiceImp implements OperationService{

	OperationDao operationDao = new OperationDaoImp();
	@Override
	public int createAccount(Account account) {
	  return operationDao.createAccount(account);
	}

	@Override
	public int depositAmount(int id,double depositAmount) {
		return operationDao.depositAmount(id,depositAmount);
	}

	@Override
	public int withdrawAmount(int id,double withdrawnAmount) {
		return operationDao.withdrawAmount(id,withdrawnAmount);
	}

	@Override
	public boolean checkBalance(int id) {
		return operationDao.checkBalance(id);
	}

	@Override
	public List<AccountInfo> listOfCustomers() {
		return operationDao.listOfCustomers();
	}

	@Override
	public AccountInfo findCustomer(int id) {
		return operationDao.findCustomer(id);
	}

	@Override
	public int deleteCustomer(int id) {
		return operationDao.deleteCustomer(id);
	}

	
}
