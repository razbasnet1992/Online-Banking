package com.onlinebanking.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.onlinebanking.util.DbUtil;

public class SecurityCheck {
	public  boolean securityCheck(int id,int securityPin) {
		final  String SECURITY_CHECK_QUERY = "select pin_no from transaction_tbl where id =?";
		boolean flag = false;
		try (PreparedStatement pa = DbUtil.getConnection().prepareStatement(SECURITY_CHECK_QUERY);) {
			pa.setInt(1, id);
			ResultSet rs = pa.executeQuery();
			if(rs.next()) {
			int pin = rs.getInt("pin_no");
			//System.out.println(pin);
			//double currentBalance = 0;
				if(securityPin==pin) {
				flag = true;
				}
		}
			else {
				flag = false;
			}
			}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return flag;
	}


}
