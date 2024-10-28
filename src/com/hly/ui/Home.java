package com.hly.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Home() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Customer Check In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerCheckIn().setVisible(true);
			}
		});
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setIcon(new ImageIcon(Home.class.getResource("/com/hly/images/Customer Registration & Check IN.png")));
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnNewButton.setBounds(249, 45, 247, 73);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Customer Check Out");
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.setIcon(new ImageIcon(Home.class.getResource("/com/hly/images/Customer Check Out.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerCheckOut().setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnNewButton_1.setBounds(506, 45, 249, 73);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Customer Details Bill");
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.setIcon(new ImageIcon(Home.class.getResource("/com/hly/images/Customer Details Bill.png")));
		btnNewButton_2.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnNewButton_2.setBounds(765, 44, 257, 75);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Logout");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you want to logout?", "Select", JOptionPane.YES_NO_OPTION) == 0) {
					dispose();
					new Login().setVisible(true);
				}
					
			}
		});
		btnNewButton_3.setBackground(new Color(192, 192, 192));
		btnNewButton_3.setIcon(new ImageIcon(Home.class.getResource("/com/hly/images/logout.png")));
		btnNewButton_3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnNewButton_3.setBounds(1032, 44, 147, 75);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Exit");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you want to exit the application", "Select", JOptionPane.YES_NO_OPTION) == 0) {
					System.exit(0);
				}
			}
		});
		btnNewButton_4.setBackground(new Color(192, 192, 192));
		btnNewButton_4.setIcon(new ImageIcon(Home.class.getResource("/com/hly/images/exit.png")));
		btnNewButton_4.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnNewButton_4.setBounds(1191, 44, 138, 73);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Manage Room");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new ManageRoom().setVisible(true);
			}
		});
		btnNewButton_5.setBackground(new Color(192, 192, 192));
		btnNewButton_5.setIcon(new ImageIcon(Home.class.getResource("/com/hly/images/manage room.png")));
		btnNewButton_5.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnNewButton_5.setBounds(23, 46, 216, 73);
		contentPane.add(btnNewButton_5);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/com/hly/images/home.png")));
		lblNewLabel.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel);
	}
}
