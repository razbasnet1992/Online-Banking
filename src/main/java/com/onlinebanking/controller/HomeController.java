package com.onlinebanking.controller;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.onlinebanking.model.AccountInfo;
import com.onlinebanking.service.OperationService;
import com.onlinebanking.service.OperationServiceImp;

public class HomeController {

	public static void main(String[] args) {
		OperationService operationService = new OperationServiceImp();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter one of the following:\ndeposit/draw/check/list/customer");
		String s = scanner.next();

		switch (s) {
		case "deposit":
			int id = Integer.parseInt(JOptionPane.showInputDialog("Enter id :"));
			double amount = Double.parseDouble(JOptionPane.showInputDialog("Enter amount :"));
			operationService.depositAmount(id, amount);
			break;
		case "draw":
			id = Integer.parseInt(JOptionPane.showInputDialog("Enter id :"));
			amount = Double.parseDouble(JOptionPane.showInputDialog("Enter amount :"));
			operationService.withdrawAmount(id, amount);
			break;
		case "check":
			id = Integer.parseInt(JOptionPane.showInputDialog("Enter id :"));
			operationService.checkBalance(id);
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

		default:
			System.out.println("Wrong choice!!");
		}
	}
}
