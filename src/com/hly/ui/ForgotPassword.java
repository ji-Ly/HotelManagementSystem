package com.hly.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hly.dao.InsertUpdateDelete;
import com.hly.dao.Select;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class ForgotPassword extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtSecurity;
	private JTextField txtAnswer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPassword frame = new ForgotPassword();
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
	private String email;
	private JPasswordField txtPassword;
	
	public ForgotPassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Forgot Password");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNewLabel.setBounds(625, 198, 163, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_1.setBounds(447, 253, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Security Question");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_2.setBounds(447, 300, 148, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Answer");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_3.setBounds(447, 354, 45, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("New Password");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_4.setBounds(447, 403, 109, 13);
		contentPane.add(lblNewLabel_4);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtEmail.setBounds(575, 249, 280, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtSecurity = new JTextField();
		txtSecurity.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtSecurity.setBounds(575, 296, 280, 19);
		contentPane.add(txtSecurity);
		txtSecurity.setColumns(10);
		
		txtAnswer = new JTextField();
		txtAnswer.setFont(new Font("Segoe UI", Font.BOLD, 12));
		txtAnswer.setBounds(575, 350, 280, 19);
		contentPane.add(txtAnswer);
		txtAnswer.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(0, 0, 1366, 13);
		contentPane.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int check = 0;
				email = txtEmail.getText();
				
				if(email.equals("")) {
					check = 1;
					JOptionPane.showMessageDialog(null, "Email field is required");
				}
				else {
					try {
						ResultSet rs = Select.getData("select * from users where email = '"+email+"'");
						if(rs.next()) {
							check = 1;
			
							txtSecurity.setEditable(false);
							txtSecurity.setText(rs.getString(4));
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				if(check == 0) {
					JOptionPane.showMessageDialog(null, "Incorect email");
				}
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(882, 249, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String securityQuestion = txtSecurity.getText();
				String answer= txtAnswer.getText();
				String newPassword = new String(txtPassword.getPassword());
				
				int check = 0;
				
				if(answer.equals("") || newPassword.equals("")) {
					check = 1;
					JOptionPane.showMessageDialog(null, "All fields are required");
				}else {
					ResultSet rs = Select.getData("select * from users where email = '"+email+"' and securityQuestion = '"+securityQuestion+"' and answer = '"+answer+"'");
					try {
						if(rs.next()) {
							check = 1;
							InsertUpdateDelete.setData("update users set password = '"+newPassword+"' where email = '"+email+"'", "Password changed successfully");
							setVisible(false);
							new ForgotPassword().setVisible(true);
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				if(check == 0) {
					JOptionPane.showMessageDialog(null, "Incorrect answer");
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(575, 448, 75, 21);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Sign Up");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SignUp().setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNewButton_2.setBackground(new Color(255, 0, 0));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBounds(674, 448, 85, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Login");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login().setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNewButton_3.setBackground(new Color(255, 0, 0));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBounds(780, 448, 75, 21);
		contentPane.add(btnNewButton_3);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(573, 401, 282, 19);
		contentPane.add(txtPassword);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(ForgotPassword.class.getResource("/com/hly/images/forgot password.PNG")));
		lblNewLabel_6.setBounds(0, 0, 1366, 768);
		contentPane.add(lblNewLabel_6);
	}

}
