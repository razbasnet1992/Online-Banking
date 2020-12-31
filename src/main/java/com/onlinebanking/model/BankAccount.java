package com.onlinebanking.model;


public class BankAccount {
	private String account;
	private double balance;
	
	public BankAccount(String account, double balance){
		this.balance = balance;
		this.account = account;
	}
	
   public void deposit(double amount) {
	   double newBalance = balance + amount;
	   balance = newBalance;
   }
   public void withDraw(double amount) {
	   double newBalance = balance -amount;
	   balance = newBalance;
   }
   public double getBalance() {
	   return balance;
   }
   public String getAccount() {
	   return account;
   }
   public void transferFunds(BankAccount destination,double amount) {
	   destination.balance= destination.balance + amount;
	   this.balance = this.balance - amount;
   }
   public void transfer(BankAccount destination,double amount) {
	  destination.deposit(amount);
	   withDraw(amount);
   }
   
}

