package com.onlinebanking.service;



import java.util.List;

import com.onlinebanking.dao.OperationDao;
import com.onlinebanking.dao.OperationDaoImp;
import com.onlinebanking.dao.SecurityCheck;
import com.onlinebanking.model.Account;
import com.onlinebanking.model.AccountInfo;


public class OperationServiceImp implements OperationService{

	OperationDao operationDao = new OperationDaoImp();
	SecurityCheck securityCheck = new SecurityCheck();
	@Override
	public int createAccount(Account account,double balance,int securityPin) {
	  return operationDao.createAccount(account,balance,securityPin);
	}

	@Override
	public int depositAmount(int id,double depositAmount,int pin) {
  		return operationDao.depositAmount(id,depositAmount,pin);
	}

	@Override
	public int withdrawAmount(int id,double withdrawnAmount,int pin) {
		return operationDao.withdrawAmount(id,withdrawnAmount,pin);
	}

	@Override
	public boolean checkBalance(int id,int pin) {
		return operationDao.checkBalance(id,pin);
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
	public int deleteCustomer(int id,int pin) {
		return operationDao.deleteCustomer(id,pin);
	}

	@Override
	public int sumAndCountCustomers() {
		return operationDao.sumAndCountCustomers();
	}

//	@Override
//	public boolean securityCheck() {
//		return securityCheck.securityCheck();
	//}

}
