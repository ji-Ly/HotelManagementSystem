package com.hly.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Select {

	
	public static ResultSet getData(String query) {
		try {
			Connection connection = ConnectionProvider.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
