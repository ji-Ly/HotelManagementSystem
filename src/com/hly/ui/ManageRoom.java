package com.hly.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import com.hly.dao.InsertUpdateDelete;
import com.hly.dao.Select;

import javax.swing.DefaultComboBoxModel;

public class ManageRoom extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRoomNumber;
	private JTextField txtPrice;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageRoom frame = new ManageRoom();
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
	public ManageRoom() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1266, 600);
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Manage Room");
		lblNewLabel.setFont(new Font("Impact", Font.BOLD, 33));
		lblNewLabel.setIcon(new ImageIcon(ManageRoom.class.getResource("/com/hly/images/manage room.png")));
		lblNewLabel.setBounds(62, 25, 278, 84);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(144, 238, 144));
		btnNewButton.setIcon(new ImageIcon(ManageRoom.class.getResource("/com/hly/images/close.png")));
		btnNewButton.setBounds(1210, 10, 46, 33);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(77, 170, 573, 343);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Room Number", "Room Type", "Bed", "Price", "Status"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("Room Number");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_1.setBounds(701, 170, 117, 13);
		contentPane.add(lblNewLabel_1);
		
		txtRoomNumber = new JTextField();
		txtRoomNumber.setFont(new Font("Segoe UI", Font.BOLD, 14));
		txtRoomNumber.setBounds(701, 194, 355, 30);
		contentPane.add(txtRoomNumber);
		txtRoomNumber.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Room Type");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_2.setBounds(701, 234, 117, 13);
		contentPane.add(lblNewLabel_2);
		
		JComboBox comboBoxRoomType = new JComboBox();
		comboBoxRoomType.setModel(new DefaultComboBoxModel(new String[] {"AC", "non-AC"}));
		comboBoxRoomType.setFont(new Font("Segoe UI", Font.BOLD, 14));
		comboBoxRoomType.setBounds(701, 257, 355, 33);
		contentPane.add(comboBoxRoomType);
		
		JLabel lblNewLabel_3 = new JLabel("Bed");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_3.setBounds(701, 299, 60, 13);
		contentPane.add(lblNewLabel_3);
		
		JComboBox comboBoxBed = new JComboBox();
		comboBoxBed.setModel(new DefaultComboBoxModel(new String[] {"Single", "Double", "Triple"}));
		comboBoxBed.setFont(new Font("Segoe UI", Font.BOLD, 14));
		comboBoxBed.setBounds(701, 322, 355, 33);
		contentPane.add(comboBoxBed);
		
		JLabel lblNewLabel_4 = new JLabel("Price");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 14));
		lblNewLabel_4.setBounds(701, 365, 60, 13);
		contentPane.add(lblNewLabel_4);
		
		txtPrice = new JTextField();
		txtPrice.setFont(new Font("Segoe UI", Font.BOLD, 14));
		txtPrice.setBounds(701, 388, 355, 30);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Add Room");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String roomNo = txtRoomNumber.getText();
				String roomType = (String) comboBoxRoomType.getSelectedItem();
				String bed = (String) comboBoxBed.getSelectedItem();
				String price = txtPrice.getText();
				
				String query = "insert into room values('"+roomNo+"','"+roomType+"', '"+bed+"','"+price+"','Not Booked')";
				InsertUpdateDelete.setData(query, "Room Added Successfully");
				dispose();
				new ManageRoom().setVisible(true);
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnNewButton_1.setBounds(701, 448, 117, 33);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(ManageRoom.class.getResource("/com/hly/images/all pages background.png")));
		lblNewLabel_5.setBounds(0, 0, 1266, 600);
		contentPane.add(lblNewLabel_5);
		
		addWindowListener(new WindowAdapter() {
			 public void windowOpened(WindowEvent e) {
				 
				 ResultSet rs = Select.getData("select * from room");
				 DefaultTableModel model = (DefaultTableModel) table.getModel();
				 
				 try {
					while(rs.next()) {
						model.addRow(new Object[] {
							rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)	
						});
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				 
			 }
			
		}) ;
			
		
	}
	
}
