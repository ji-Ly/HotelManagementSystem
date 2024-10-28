package com.hly.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hly.dao.InsertUpdateDelete;
import com.hly.dao.Select;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class CustomerCheckIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtMobileNumber;
	private JTextField txtNationality;
	private JTextField txtEmail;
	private JTextField txtIDProof;
	private JTextField txtAddress;
	private JTextField txtDateCheckIn;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerCheckIn frame = new CustomerCheckIn();
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
	
	String roomNo;
	String roomType;
	String bed;
	String price;
	
	JComboBox comboBoxRoomNumber;
	JComboBox comboBoxRoomType;
	JComboBox comboBoxBed;
	
	public CustomerCheckIn() {
		setUndecorated(true);
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1266, 600);
		contentPane = new JPanel();
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer Check In");
		lblNewLabel.setFont(new Font("Impact", Font.BOLD, 31));
		lblNewLabel.setIcon(new ImageIcon(CustomerCheckIn.class.getResource("/com/hly/images/Customer Registration & Check IN.png")));
		lblNewLabel.setBounds(74, 27, 328, 50);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setIcon(new ImageIcon(CustomerCheckIn.class.getResource("/com/hly/images/close.png")));
		btnNewButton.setBounds(1146, 21, 45, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_1.setBounds(50, 138, 71, 13);
		contentPane.add(lblNewLabel_1);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtName.setBounds(52, 161, 231, 30);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Mobile Number");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_2.setBounds(52, 201, 108, 13);
		contentPane.add(lblNewLabel_2);
		
		txtMobileNumber = new JTextField();
		txtMobileNumber.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtMobileNumber.setBounds(50, 224, 231, 31);
		contentPane.add(txtMobileNumber);
		txtMobileNumber.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Nationally");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_3.setBounds(52, 268, 69, 13);
		contentPane.add(lblNewLabel_3);
		
		JComboBox comboBoxGender = new JComboBox();
		comboBoxGender.setFont(new Font("Segoe UI", Font.BOLD, 13));
		comboBoxGender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Others"}));
		comboBoxGender.setBounds(52, 355, 108, 30);
		contentPane.add(comboBoxGender);
		
		JLabel lblNewLabel_4 = new JLabel("Gender");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_4.setBounds(50, 332, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		txtNationality = new JTextField();
		txtNationality.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtNationality.setBounds(52, 293, 231, 31);
		contentPane.add(txtNationality);
		txtNationality.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Email");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_5.setBounds(50, 395, 45, 13);
		contentPane.add(lblNewLabel_5);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtEmail.setBounds(50, 418, 231, 33);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("ID Proof");
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_6.setBounds(491, 138, 85, 13);
		contentPane.add(lblNewLabel_6);
		
		txtIDProof = new JTextField();
		txtIDProof.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtIDProof.setBounds(491, 161, 273, 30);
		contentPane.add(txtIDProof);
		txtIDProof.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Address");
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_7.setBounds(491, 201, 96, 13);
		contentPane.add(lblNewLabel_7);
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtAddress.setBounds(491, 224, 273, 31);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Check IN Date(To day)");
		lblNewLabel_8.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_8.setBounds(491, 268, 193, 13);
		contentPane.add(lblNewLabel_8);
		
		txtDateCheckIn = new JTextField();
		txtDateCheckIn.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtDateCheckIn.setBounds(491, 293, 273, 31);
		contentPane.add(txtDateCheckIn);
		txtDateCheckIn.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Bed");
		lblNewLabel_9.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_9.setBounds(991, 138, 45, 13);
		contentPane.add(lblNewLabel_9);
		
		comboBoxBed = new JComboBox();
		comboBoxBed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomDetails();
			}
		});
		comboBoxBed.setFont(new Font("Segoe UI", Font.BOLD, 13));
		comboBoxBed.setModel(new DefaultComboBoxModel(new String[] {"Single", "Double", "Triple"}));
		comboBoxBed.setBounds(991, 160, 200, 31);
		contentPane.add(comboBoxBed);
		
		JLabel lblNewLabel_10 = new JLabel("Room Type");
		lblNewLabel_10.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_10.setBounds(991, 201, 85, 13);
		contentPane.add(lblNewLabel_10);
		
		comboBoxRoomType = new JComboBox();
		comboBoxRoomType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomDetails();
			}
		});
		comboBoxRoomType.setFont(new Font("Segoe UI", Font.BOLD, 13));
		comboBoxRoomType.setModel(new DefaultComboBoxModel(new String[] {"AC", "non-AC"}));
		comboBoxRoomType.setBounds(991, 223, 200, 32);
		contentPane.add(comboBoxRoomType);
		
		JLabel lblNewLabel_11 = new JLabel("Room Number");
		lblNewLabel_11.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_11.setBounds(991, 268, 96, 13);
		contentPane.add(lblNewLabel_11);
		
		comboBoxRoomNumber = new JComboBox();
		comboBoxRoomNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				roomNo = (String) comboBoxRoomNumber.getSelectedItem();
				ResultSet rs = Select.getData("select * from room where roomNo = '"+roomNo+"'");
				try {
					while(rs.next()) {
						txtPrice.setText(rs.getString(4));
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		comboBoxRoomNumber.setFont(new Font("Segoe UI", Font.BOLD, 13));
		comboBoxRoomNumber.setBounds(991, 292, 200, 32);
		contentPane.add(comboBoxRoomNumber);
		
		JLabel lblNewLabel_12 = new JLabel("Price");
		lblNewLabel_12.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_12.setBounds(991, 332, 45, 13);
		contentPane.add(lblNewLabel_12);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtPrice.setBounds(991, 356, 200, 31);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Allotate");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = 1;
				String name = txtName.getText();
				String mobileNumber = txtMobileNumber.getText();
				String nationality = txtNationality.getText();
				String gender = (String) comboBoxGender.getSelectedItem();
				String email = txtEmail.getText();
				String idProof = txtIDProof.getText();
				String address = txtAddress.getText();
				String dateCheckIn = txtDateCheckIn.getText();
				String bed = (String) comboBoxBed.getSelectedItem();
				String roomType = (String) comboBoxRoomType.getSelectedItem();
				String roomNo = (String) comboBoxRoomNumber.getSelectedItem();
				String price = txtPrice.getText();
				
				String query = "select max(id) from customers";
				
				ResultSet rs = Select.getData(query);
				try {
					while(rs.next()) {
						rs.getInt(1);
						id = id + 1;
					}
					if(!price.equals("")) {
						query = "update room set status = 'Booked' where roomNo = '"+roomNo+"'";
						InsertUpdateDelete.setData(query, "");
						query = "insert into customers(id, name, mobileNumber, nationality, gender, email, idProof, address, checkIn, roomNo, bed, roomType, pricePerDay) values ("+id+",'"+name+"','"+mobileNumber+"','"+nationality+"','"+gender+"','"+email+"','"+idProof+"','"+address+"','"+dateCheckIn+"','"+roomNo+"','"+bed+"','"+roomType+"', '"+price+"')";
						InsertUpdateDelete.setData(query, "Customer Check In Successfully");
						
						dispose();
						new CustomerCheckIn().setVisible(true);
					
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
					
				}
				
			}
		});
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNewButton_1.setBounds(991, 418, 108, 34);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CustomerCheckIn().setVisible(true);
			}
		});
		btnNewButton_2.setBackground(new Color(255, 0, 0));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		btnNewButton_2.setBounds(991, 472, 108, 31);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setIcon(new ImageIcon(CustomerCheckIn.class.getResource("/com/hly/images/all pages background.png")));
		lblNewLabel_13.setBounds(0, 0, 1266, 600);
		contentPane.add(lblNewLabel_13);
		
		txtDateCheckIn.setEditable(false);
		txtPrice.setEditable(false);
		SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		txtDateCheckIn.setText(myFormat.format(cal.getTime()));
		
		
	}
	// check xem còn phòng không
	public void roomDetails() {
		// xóa hết item của số phòng đi
		comboBoxRoomNumber.removeAllItems();
		txtPrice.setText("");
		bed = (String) comboBoxBed.getSelectedItem();
		roomType = (String) comboBoxRoomType.getSelectedItem();
		
		try {
			ResultSet rs = Select.getData("select * from room where bed = '"+bed+"' and roomType = '"+roomType+"'");
			if(rs.next()) {
				comboBoxRoomNumber.addItem(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
