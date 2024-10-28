package com.hly.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

public class Tables {

	public static void main(String[] args) {
		try {
			Connection connection = ConnectionProvider.getConnection();
			Statement statement = connection.createStatement();

			// statement.executeUpdate("create table users(name varchar(200), email
			// varchar(200), password varchar(200), securityQuestion varchar(500), answer
			// varchar(200), address varchar(200), status varchar(20))");

			// statement.executeUpdate("create table room(roomNo varchar(10), roomType
			// varchar(200), bed varchar(200), price int, status varchar(20))");

			statement.executeUpdate(
					"create table customers(id int, name varchar(200), mobileNumber varchar(20), nationality varchar(100), gender varchar(50), email varchar(100), idProof varchar(200), address varchar(500), checkIn varchar(50), roomNo varchar(10), bed varchar(200), roomType varchar(200), pricePerDay int(10), numberOfDaysStay int(10), totalAmount varchar(200), checkOut varchar(50))");

			JOptionPane.showMessageDialog(null, "Table created successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
