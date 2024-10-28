package com.hly.dao;

import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class InsertUpdateDelete {
	
	public static void setData(String query, String msg	) {
		
		try {
			Connection connection = ConnectionProvider.getConnection();
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			if(!msg.equals(""))
				JOptionPane.showMessageDialog(null, msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
