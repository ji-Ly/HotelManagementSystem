package com.hly.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hly.dao.InsertUpdateDelete;
import com.hly.dao.Select;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearch;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminHome frame = new AdminHome();
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
	public AdminHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome Admin");
		lblNewLabel.setFont(new Font("Impact", Font.BOLD, 42));
		lblNewLabel.setBounds(37, 28, 306, 71);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login().setVisible(true);
			}
			
		});
		btnNewButton.setIcon(new ImageIcon(AdminHome.class.getResource("/com/hly/images/logout.png")));
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNewButton.setBounds(924, 66, 144, 50);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login().setVisible(true);
				
			}
			
			
		});
		btnNewButton_1.setIcon(new ImageIcon(AdminHome.class.getResource("/com/hly/images/exit.png")));
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNewButton_1.setBounds(1132, 66, 115, 50);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Search By Name Or Email");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_1.setBounds(322, 147, 169, 13);
		contentPane.add(lblNewLabel_1);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Segoe UI", Font.BOLD, 13));
		txtSearch.setBounds(521, 143, 185, 19);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameOrEmail = txtSearch.getText();
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				
				ResultSet rs = Select.getData("select * from users where name like '%"+nameOrEmail+"%' or email like '%"+nameOrEmail+"'");
				
				try {
					while(rs.next()) {
						model.addRow(new Object[] {
							rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(6), rs.getString(7)	
						});
					}
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnSearch.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnSearch.setBounds(754, 142, 85, 21);
		contentPane.add(btnSearch);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AdminHome().setVisible(true);
			}
		});
		btnClear.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnClear.setBounds(1067, 142, 85, 21);
		contentPane.add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(107, 237, 1004, 354);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int index = table.getSelectedRow();
				TableModel model = table.getModel();
				
				String email = model.getValueAt(index, 1).toString();
				String status = model.getValueAt(index, 4).toString();
				
				if(status.equals("true")) {
					status = "false";
				}else {
					status = "true";
				}
				
				try {
					int response = JOptionPane.showConfirmDialog(null, "Do you want to change status of email: "+email+"", "Select", JOptionPane.YES_NO_OPTION);
					if(response == 0) {
						InsertUpdateDelete.setData("update users set status = '"+status+"' where email = '"+email+"'", "Status Changed Successfully");
		
					}
					dispose();
					new AdminHome().setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
				
				
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Email", "Security Question", "Address", "Status"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(117);
		scrollPane.setViewportView(table);
		
		addWindowListener( new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				ResultSet rs = Select.getData("select * from users");
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				try {
					while(rs.next()) {
						model.addRow(new Object[] {
							rs.getString(1), rs.getString(2), rs.getString(4), rs.getString(6), rs.getString(7)	
						});
					}
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			
				
			}
		});
	}
}
