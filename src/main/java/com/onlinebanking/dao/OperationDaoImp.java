package com.onlinebanking.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.onlinebanking.model.Account;
import com.onlinebanking.model.AccountInfo;
import com.onlinebanking.util.DbUtil;

public class OperationDaoImp implements OperationDao {
	SecurityCheck securityCheck = new SecurityCheck();

	public static final String INSERT_ACCOUNT_INFO = "call insert_into_twoTables(?,?,?,?,?,?)";
	public static final String DELETE_QUERY = "DELETE account_db,transaction_tbl from account_db  INNER JOIN transaction_tbl  \n"
			+ "WHERE account_db.id= transaction_tbl.id and account_db.id = ?";

	public static final String GET_CUSTOMER_BYID = "select * from bank_db.account_db inner join bank_db.transaction_tbl using(id)\n"
			+ "where id =?";
	public static final String GET_CUSTOMERS_LIST = "select * from bank_db.account_db inner join bank_db.transaction_tbl using(id)";
	public static final String GET_BALANCE = "select balance from transaction_tbl where id = ?";
	public static final String GET_SUM_AND_COUNT = "select count(id),sum(balance) from transaction_tbl";
	Date date = new Date();
	String pattern = "yyyy-MM-dd";
	SimpleDateFormat formatter = new SimpleDateFormat(pattern);
	String now = formatter.format(date);

	@Override
	public int createAccount(Account account, double balance, int securityPin) {
		int created = 0;
		try (PreparedStatement pa = DbUtil.getConnection().prepareStatement(INSERT_ACCOUNT_INFO);) {
			pa.setString(1, account.getAccountName());
			pa.setLong(2, account.getAccountNo());
			pa.setString(3, account.getEmail());
			pa.setLong(4, account.getMobileNo());
			pa.setDouble(5, balance);
			pa.setDouble(6, securityPin);
			created = pa.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return created;
	}

	@Override
	public int depositAmount(int id, double depositAmount, int pin) {
		int d = 0;
		boolean flag = securityCheck.securityCheck(id, pin);
		if (flag) {

			try (PreparedStatement pa = DbUtil.getConnection().prepareStatement(GET_BALANCE);) {

				pa.setInt(1, id);
				ResultSet rs = pa.executeQuery();
				double currentBalance = 0;
				if (rs.next()) {
					currentBalance = rs.getDouble("balance");
					System.out
							.println("Balance before deposit operation :" + currentBalance + " for account id :" + id);
					System.out.println("Deposited Amount :" + depositAmount);
					if (depositAmount > 0) {
						currentBalance += depositAmount;
						d = pa.executeUpdate(
								"update transaction_tbl set balance ='" + currentBalance + "',deposit_amount='"
										+ depositAmount + "',deposit_date ='" + now + "' where id ='" + id + "'");
						System.out
								.println("Account Balance after deposit :" + currentBalance + " for account id :" + id);
					} else
						System.out.println("Amount should be greater than zero.");
				}
			}

			catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else
			System.out.println("Pin do not match");
		return d;
	}

	@Override
	public int withdrawAmount(int id, double withdrawnAmount,int pin) {
		int withDraw = 0;
		boolean flag = securityCheck.securityCheck(id,pin);
		if (flag) {
		try (PreparedStatement pa = DbUtil.getConnection().prepareStatement(GET_BALANCE);) {
			pa.setInt(1, id);
			ResultSet rs = pa.executeQuery();
			double currentBalance = 0;
			if (rs.next()) {
				currentBalance = rs.getDouble("balance");

				System.out.println("Balance before withdrawing :" + currentBalance + " for account id :" + id);
				System.out.println("Withdrwal Amount :" + withdrawnAmount);
				if (withdrawnAmount < currentBalance && withdrawnAmount > 0) {
					currentBalance = currentBalance - withdrawnAmount;
					withDraw = pa.executeUpdate("update transaction_tbl set balance ='" + currentBalance
							+ "',withdrawn_amount='" + withdrawnAmount + "' where id ='" + id + "'");

					System.out
							.println("Account Balance after withdrawing :" + currentBalance + " for account id :" + id);
				} else {
					System.out.println("Not enough balance");
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		}else
			System.out.println("Pin does not match");
		return withDraw;
	}

	@Override
	public boolean checkBalance(int id,int pin) {
		boolean flag = false;
		boolean check = securityCheck.securityCheck(id, pin);
		if(check) {
		try (PreparedStatement pa = DbUtil.getConnection().prepareStatement(GET_BALANCE);) {
			pa.setInt(1, id);
			ResultSet rs = pa.executeQuery();
			double currentBalance = 0;
			if (rs.next()) {
				currentBalance = rs.getDouble("balance");
				System.out.println("Balance is "+currentBalance + " for account id :" + id);
				flag = true;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		}else
			System.out.println("Pin does not match/Enter correct pin");
		return flag;
	}

	@Override
	public List<AccountInfo> listOfCustomers() {
		List<AccountInfo> list = new ArrayList<>();
		ResultSet rs;
		try (PreparedStatement pa = DbUtil.getConnection().prepareStatement(GET_CUSTOMERS_LIST);) {

			rs = pa.executeQuery();

			while (rs.next()) {
				AccountInfo accountInfo = new AccountInfo();
				accountInfo.setAccountId(rs.getInt("id"));
				accountInfo.setAccountNo(rs.getLong("account_no"));
				accountInfo.setAccountName(rs.getString("account_name"));
				accountInfo.setMobileNo(rs.getLong("mobile_no"));
				accountInfo.setEmail(rs.getString("email"));
				accountInfo.setBalance(rs.getLong("balance"));

				list.add(accountInfo);

			}
			rs.close();
			pa.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public AccountInfo findCustomer(int id) {
		AccountInfo accountInfo = new AccountInfo();
		ResultSet rs;
		try (PreparedStatement pa = DbUtil.getConnection().prepareStatement(GET_CUSTOMER_BYID);) {
			pa.setInt(1, id);
			rs = pa.executeQuery();
			if (rs.next()) {
				accountInfo.setAccountId(rs.getInt("id"));
				accountInfo.setAccountNo(rs.getLong("account_no"));
				accountInfo.setAccountName(rs.getString("account_name"));
				accountInfo.setMobileNo(rs.getLong("mobile_no"));
				accountInfo.setEmail(rs.getString("email"));
				accountInfo.setBalance(rs.getLong("balance"));
			}
			rs.close();
			pa.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return accountInfo;
	}

	@Override
	public int deleteCustomer(int id) {
		int deleteNum = 0;
		try (PreparedStatement pa = DbUtil.getConnection().prepareStatement(DELETE_QUERY);) {
			pa.setInt(1, id);
			deleteNum = pa.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteNum;
	}

	public int sumAndCountCustomers() {
		int count = 0;
		try (PreparedStatement pa = DbUtil.getConnection().prepareStatement(GET_SUM_AND_COUNT);) {
			ResultSet rs = pa.executeQuery();
			if (rs.next()) {

				System.out.println("Total balance in the bank :" + rs.getDouble(2));
				System.out.println("Total customers :" + rs.getInt(1));
				count = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

}
