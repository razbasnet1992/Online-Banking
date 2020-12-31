package com.onlinebanking.controller;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.onlinebanking.model.Account;
import com.onlinebanking.model.AccountInfo;
import com.onlinebanking.service.OperationService;
import com.onlinebanking.service.OperationServiceImp;

public class HomeController {

	public static void main(String[] args) {
		OperationService operationService = new OperationServiceImp();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter one of the following:\ncreate/deposit/draw/check/delete/list/customer");
		String s = scanner.next();

		switch (s) {
		case "create":
			Account account = getAccount();
			double balance = Double.parseDouble(JOptionPane.showInputDialog("Enter your initial balance :"));
			int created = operationService.createAccount(account,balance);
			if(created>=0) {
				System.out.println("successfully created");
			}
			else {
				System.out.println("Try again");
			}
		break;
		case "deposit":
			int id = Integer.parseInt(JOptionPane.showInputDialog("Enter id :"));
			double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter amount :"));
			int success = operationService.depositAmount(id, amount);
			if(success>=1) {
				System.out.println("successfully deposited");
			}
			else {
				System.out.println("Try again");
			}
			break;
		case "draw":
			id = Integer.parseInt(JOptionPane.showInputDialog("Enter id :"));
			amount = Double.parseDouble(JOptionPane.showInputDialog("Enter amount :"));
			int draw = operationService.withdrawAmount(id, amount);
			if(draw>=1) {
				System.out.println("successfully withdrawn");
			}
			else {
				System.out.println("Try again");
			}
			break;
		case "check":
			id = Integer.parseInt(JOptionPane.showInputDialog("Enter id :"));
			boolean check = operationService.checkBalance(id);
			if(check) {
				System.out.println("successful");
			}
			else {
				System.out.println("Try again");
			}
			break;
		case "list":
			List<AccountInfo> list = operationService.listOfCustomers();
			for (AccountInfo info : list) {
				System.out.println("Account id :"+info.getAccountId());
				System.out.println("Account Name :"+info.getAccountName());
				System.out.println("Account number :"+info.getAccountNo());
				System.out.println("Email address :"+info.getEmail());
				System.out.println("Account Balance :"+info.getBalance());
				System.out.println("Phone number :"+info.getMobileNo());
				System.out.println("=============");
			}
			break;
		case "customer":
			id = Integer.parseInt(JOptionPane.showInputDialog("Enter id :"));
			AccountInfo info = new AccountInfo();
			info = operationService.findCustomer(id);
			System.out.println("Account id :"+info.getAccountId());
			System.out.println("Account Name :"+info.getAccountName());
			System.out.println("Account number :"+info.getAccountNo());
			System.out.println("Email address :"+info.getEmail());
			System.out.println("Account Balance :"+info.getBalance());
			System.out.println("Phone number :"+info.getMobileNo());
			break;
		case "delete":
			id = Integer.parseInt(JOptionPane.showInputDialog("Enter id :"));
			int deleted = operationService.deleteCustomer(id);
			if(deleted>=1) {
				System.out.println("Deletion successful");
			}
			else {
				System.out.println("Try again");
			}
			break;

		default:
			System.out.println("Wrong choice!!");
		}
	}
	public static Account getAccount() {
		Account account = new Account();
		//int id = Integer.parseInt(JOptionPane.showInputDialog("Enter id :"));
		String name = JOptionPane.showInputDialog("Enter name :");
		String email = JOptionPane.showInputDialog("Enter email :");
		long accountNo = Long.parseLong(JOptionPane.showInputDialog("Enter account number :"));
		long mobileNo = Long.parseLong(JOptionPane.showInputDialog("Enter mobile number :"));
		//account.setId(id);
		account.setAccountNo(accountNo);
		account.setAccountName(name);
		account.setEmail(email);
		account.setMobileNo(mobileNo);
		
		return account;
		
	}
}
