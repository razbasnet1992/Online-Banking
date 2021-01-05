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
		System.out.println("Enter one of the following:\ncreate/deposit/draw/check/delete/list/customer/sum");
		String s = scanner.next();

		switch (s) {
		case "create":
			Account account = getAccount();
			double balance = Double.parseDouble(JOptionPane.showInputDialog("Enter your initial balance :"));
			int securityPin = Integer.parseInt(JOptionPane.showInputDialog("Create security pin :"));
			int created = operationService.createAccount(account,balance,securityPin);
			if(created>=1) {
				System.out.println("successfully created");
			}
			else {
				System.out.println("Try again");
			}
		break;
		case "deposit":
			int id = Integer.parseInt(JOptionPane.showInputDialog("Enter id :"));
			double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter amount :"));
			int pin = Integer.parseInt(JOptionPane.showInputDialog("Enter your security pin :"));
			int success = operationService.depositAmount(id, amount,pin);
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
			pin = Integer.parseInt(JOptionPane.showInputDialog("Enter your security pin :"));
			int draw = operationService.withdrawAmount(id, amount,pin);
			if(draw>=1) {
				System.out.println("successfully withdrawn");
			}
			else {
				System.out.println("Try again");
			}
			break;
		case "check":
			id = Integer.parseInt(JOptionPane.showInputDialog("Enter id :"));
			pin = Integer.parseInt(JOptionPane.showInputDialog("Enter your security pin :"));
			boolean check = operationService.checkBalance(id,pin);
			if(check) {
				System.out.println("successful");
			}
			else {
				System.out.println("Try again");
			}
			break;
		case "list":
			List<AccountInfo> list = operationService.listOfCustomers();
			list.stream().forEach(c-> {
				System.out.println("Account id :"+c.getAccountId());
				System.out.println("Account Name :"+c.getAccountName());
				System.out.println("Account number :"+c.getAccountNo());
				System.out.println("Email address :"+c.getEmail());
				System.out.println("Account Balance :"+c.getBalance());
				System.out.println("Phone number :"+c.getMobileNo());
				System.out.println("=============");
			});
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
			;
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
		case "sum":
			int count =operationService.sumAndCountCustomers();
			if(count>=1) {
				System.out.println("Sucess");
			}
			else {
				System.out.println("Something is wrong");
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
