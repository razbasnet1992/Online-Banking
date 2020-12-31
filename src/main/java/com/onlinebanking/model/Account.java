package com.onlinebanking.model;

public class Account {
private String accountName;
private long accountNo;
private int accountId;
private String email;
private Long mobileNo;

public String getAccountName() {
	return accountName;
}
public void setAccountName(String accountName) {
	this.accountName = accountName;
}
public Long getAccountNo() {
	return accountNo;
}
public void setAccountNo(Long accountNo) {
	this.accountNo = accountNo;
}
public int getId() {
	return accountId;
}
public void setId(int id) {
	this.accountId = id;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Long getMobileNo() {
	return mobileNo;
}
public void setMobileNo(Long mobileNo) {
	this.mobileNo = mobileNo;
}

}
