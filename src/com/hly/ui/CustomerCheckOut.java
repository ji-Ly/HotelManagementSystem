package com.hly.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.hly.dao.InsertUpdateDelete;
import com.hly.dao.Select;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;

public class CustomerCheckOut extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRoomNumberSearch;
	private JTextField txtCustomerName;
	private JTextField txtCheckInDate;
	private JTextField txtCheckOutDate;
	private JTextField txtCustomerMobileNumber;
	private JTable table;
	private JTextField txtNumberOfDaysStay;
	private JTextField txtTotal;
	private JTextField txtEmail;
	private JTextField txtPricePerDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerCheckOut frame = new CustomerCheckOut();
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

	int id = 0;
	String query;
	String bed;
	String roomNo;
	String roomType;

	public CustomerCheckOut() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1266, 600);
		contentPane = new JPanel();
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Customer Check Out");
		lblNewLabel
				.setIcon(new ImageIcon(CustomerCheckOut.class.getResource("/com/hly/images/Customer Check Out.png")));
		lblNewLabel.setBounds(60, 28, 181, 41);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Room Number");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(475, 106, 112, 13);
		contentPane.add(lblNewLabel_1);

		txtRoomNumberSearch = new JTextField();
		txtRoomNumberSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtRoomNumberSearch.setBounds(631, 102, 96, 19);
		contentPane.add(txtRoomNumberSearch);
		txtRoomNumberSearch.setColumns(10);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String roomNo = txtRoomNumberSearch.getText();

				try {
					ResultSet rs = Select
							.getData("select * from customers where roomNo = '" + roomNo + "' and checkOut is NULL");

					if (rs.next()) {
						txtRoomNumberSearch.setEditable(false);
						id = rs.getInt(1);
						txtCustomerName.setText(rs.getString(2));
						txtCheckInDate.setText(rs.getString(9));
						txtCustomerMobileNumber.setText(rs.getString(3));
						txtPricePerDate.setText(rs.getString(13));

						SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
						Calendar cal = Calendar.getInstance();
						txtCheckOutDate.setText(myFormat.format(cal.getTime()));

						String dateBeforeString = rs.getString(9);
						Date dateBefore = myFormat.parse(dateBeforeString);
						String dateAfterString = myFormat.format(cal.getTime());
						Date dateAfter = myFormat.parse(dateAfterString);

						long different = dateAfter.getTime() - dateBefore.getTime();
						int noOfDaysStay = (int) (different / (1000 * 60 * 60 * 24));
						if (noOfDaysStay == 0) {
							noOfDaysStay = 1;
						}
						txtNumberOfDaysStay.setText(String.valueOf(noOfDaysStay));

						float price = Float.parseFloat(txtPricePerDate.getText());

						txtTotal.setText(String.valueOf(noOfDaysStay * price));
						txtEmail.setText(rs.getString(6));

						roomType = rs.getString(12);
						bed = rs.getString(11);

					} else {
						JOptionPane.showMessageDialog(null, "Room number is not Booked or does not exist");
					}

				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});
		btnSearch.setBackground(new Color(255, 0, 0));
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSearch.setBounds(758, 96, 85, 31);
		contentPane.add(btnSearch);

		JLabel lblNewLabel_2 = new JLabel("Customer Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(65, 186, 136, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Check IN date");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(310, 186, 118, 13);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Check OUT date(to day)");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(582, 186, 171, 13);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Customer Mobile Number");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(972, 186, 199, 13);
		contentPane.add(lblNewLabel_5);

		txtCustomerName = new JTextField();
		txtCustomerName.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCustomerName.setBounds(58, 222, 156, 19);
		contentPane.add(txtCustomerName);
		txtCustomerName.setColumns(10);

		txtCheckInDate = new JTextField();
		txtCheckInDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCheckInDate.setBounds(297, 222, 199, 19);
		contentPane.add(txtCheckInDate);
		txtCheckInDate.setColumns(10);

		txtCheckOutDate = new JTextField();
		txtCheckOutDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCheckOutDate.setBounds(582, 222, 220, 19);
		contentPane.add(txtCheckOutDate);
		txtCheckOutDate.setColumns(10);

		txtCustomerMobileNumber = new JTextField();
		txtCustomerMobileNumber.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCustomerMobileNumber.setBounds(972, 232, 199, 19);
		contentPane.add(txtCustomerMobileNumber);
		txtCustomerMobileNumber.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Price Per Date");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(60, 263, 141, 13);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Number Of Days  Stay");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setBounds(310, 263, 148, 13);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Total Amount to collect From Customer");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_8.setBounds(582, 263, 280, 13);
		contentPane.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Email");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_9.setBounds(972, 263, 45, 13);
		contentPane.add(lblNewLabel_9);

		JButton btnCheckOut = new JButton("Check Out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtCustomerName.getText();
				String mobileNumber = txtCustomerMobileNumber.getText();
				String email = txtEmail.getText();

				String checkOut = txtCheckOutDate.getText();
				String numberOfDaysStay = txtNumberOfDaysStay.getText();
				String totalAmount = txtTotal.getText();
				roomNo = txtRoomNumberSearch.getText();

				query = "update customers set numberOfDaysStay ='" + numberOfDaysStay + "', totalAmount = '"
						+ totalAmount + "', checkOut = '" + checkOut + "' where id = '" + id + "'";
				InsertUpdateDelete.setData(query, "");
				query = "update room set status = 'Not Booked' where roomNo = '"+roomNo+"'";
				InsertUpdateDelete.setData(query, "");
				
				String path ="D:\\";
				
				com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
				
				try {
					PdfWriter.getInstance(doc, new FileOutputStream(path+""+id+".pdf"));
					doc.open();
					
					Paragraph paragraph1 = new Paragraph("                              HUULY HOTEL                         ");
					doc.add(paragraph1);
					
					Paragraph paragraph2 = new Paragraph("\n******************************************************************\n");
					doc.add(paragraph2);
					
					Paragraph paragraph3 = new Paragraph("\tBill ID: "+id+"\nCustomer Details:\nName: "+name+"\nMobile Number: "+mobileNumber+"\nEmail: "+email+"");
					doc.add(paragraph3);
					doc.add(paragraph2);
					
					Paragraph paragraph4 = new Paragraph("\tRoom Details:\nNumber :"+roomNo+"\nType: "+roomType+"\nBed: "+bed+"\nPrice Per Day: "+txtPricePerDate.getText()+"");
					doc.add(paragraph4);
					doc.add(paragraph2);
					
					PdfPTable tabel = new PdfPTable(4);
					tabel.addCell("Check In Date: " + txtCheckInDate.getText());
					tabel.addCell("Check Out Date: " + checkOut);
					tabel.addCell("No Of Days Stay: " + numberOfDaysStay);
					tabel.addCell("Total Amount Paid: " + totalAmount);
					doc.add(tabel);
					doc.add(paragraph2);
					
					Paragraph paragraph5 = new Paragraph("Thank You. Please Visit Again.");
					doc.add(paragraph5);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				doc.close();
				
				if(JOptionPane.showConfirmDialog(null, "Do you want to print Bill", "Select", JOptionPane.YES_NO_OPTION) == 0) {
					if((new File("D:\\"+id+".pdf")).exists()) {
						try {
							Process process = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler D:\\"+id+".pdf");
							
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}else {
						System.out.println("File is not exist");
					}
				}
				dispose();
				new CustomerCheckOut().setVisible(true);
			}
		});
		btnCheckOut.setBackground(new Color(255, 0, 0));
		btnCheckOut.setForeground(new Color(255, 255, 255));
		btnCheckOut.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCheckOut.setBounds(60, 324, 115, 31);
		contentPane.add(btnCheckOut);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new CustomerCheckOut().setVisible(true);
			}
		});
		btnClear.setBackground(new Color(255, 0, 0));
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setBounds(223, 324, 85, 31);
		contentPane.add(btnClear);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 376, 1134, 198);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Name", "Mobile Number", "Nationality", "Gender", "Email", "ID Proof", "Address",
						"Check In Date", "Room Number", "Bed", "Room Type", "Price Per Date" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(56);
		table.getColumnModel().getColumn(1).setPreferredWidth(78);
		scrollPane.setViewportView(table);

		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(CustomerCheckOut.class.getResource("/com/hly/images/close.png")));
		btnNewButton_2.setBounds(1158, 28, 36, 31);
		contentPane.add(btnNewButton_2);

		txtNumberOfDaysStay = new JTextField();
		txtNumberOfDaysStay.setBounds(297, 299, 199, 19);
		contentPane.add(txtNumberOfDaysStay);
		txtNumberOfDaysStay.setColumns(10);

		txtTotal = new JTextField();
		txtTotal.setBounds(582, 299, 220, 19);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(972, 299, 199, 19);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		txtPricePerDate = new JTextField();
		txtPricePerDate.setBounds(60, 295, 154, 19);
		contentPane.add(txtPricePerDate);
		txtPricePerDate.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10
				.setIcon(new ImageIcon(CustomerCheckOut.class.getResource("/com/hly/images/all pages background.png")));
		lblNewLabel_10.setBounds(0, 0, 1266, 600);
		contentPane.add(lblNewLabel_10);

		txtCustomerName.setEditable(false);
		txtCheckInDate.setEditable(false);
		txtCheckOutDate.setEditable(false);
		txtCustomerMobileNumber.setEditable(false);
		txtPricePerDate.setEditable(false);
		txtPricePerDate.setEditable(false);
		txtTotal.setEditable(false);
		txtEmail.setEditable(false);
		txtNumberOfDaysStay.setEditable(false);

		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {

				DefaultTableModel model = (DefaultTableModel) table.getModel();

				ResultSet rs = Select.getData("select * from customers where checkOut is NULL");

				try {
					while (rs.next()) {
						model.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
								rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13) });
					}
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
	}
}
