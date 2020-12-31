package com.onlinebanking.model;

import java.util.Date;

public class Transaction {
 private double balance;
 private long accountId;
 private Date depositDate;
 private double withDrawnAmount;
 private double depositAmount;
 
public Date getDepositDate() {
	return depositDate;
}
public void setDepositDate(Date depositDate) {
	this.depositDate = depositDate;
}
public double getWithDrawnAmount() {
	return withDrawnAmount;
}
public void setWithDrawnAmount(double withDrawnAmount) {
	this.withDrawnAmount = withDrawnAmount;
}
public double getDepositAmount() {
	return depositAmount;
}
public void setDepositAmount(double depositAmount) {
	this.depositAmount = depositAmount;
}
public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}

public long getAccountId() {
	return accountId;
}
public void setAccountId(long accountId) {
	this.accountId = accountId;
}
public Date getDate() {
	return depositDate;
}
public void setDate(Date date) {
	this.depositDate = date;
}
 public double withDrawnAmount(long accountId,double amount) {
	double newBalance = this.balance-amount;
	balance = newBalance;
	 return balance;
 }
 public double depositAmount(long accountId,double amount,Date date) {
		double newBalance = this.balance-amount;
		balance = newBalance;
		 return balance;
	 }
 
}
