
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.awt.Font;
import java.awt.Color;


public class ShopKeepar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textItem;
	private JTextField textPrice;
	private JComboBox comboBox;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShopKeepar frame = new ShopKeepar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection conn=null;
	private JTable table;
	/**
	 * Create the frame.
	 */

	public void refreshTable()
	{
		try {
			String  query="select * from products";
			PreparedStatement pst=(PreparedStatement) conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			//JOptionPane.showMessageDialog(null,"Data saved");
			pst.close();
			rs.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ShopKeepar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		getContentPane().setBackground(Color.black);
		
		conn=(Connection) SQLConnect.dbConnector();

		JButton btnCheckOrders = new JButton("Check Orders");
		btnCheckOrders.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnCheckOrders.setForeground(Color.ORANGE);
		btnCheckOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				Orders.main(null);
			}
		});
		btnCheckOrders.setBounds(49, 91, 134, 23);
		contentPane.add(btnCheckOrders);
		
		JButton btnAddStock = new JButton();
		Image img=new ImageIcon(this.getClass().getResource("/add1.png")).getImage();
		btnAddStock.setIcon(new ImageIcon(img));
			btnAddStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					String  query="INSERT INTO products(Stock,Brand,Price) values(?,?,?)";
					PreparedStatement pst=(PreparedStatement) conn.prepareStatement(query);
					pst.setString(1,textItem.getText());
					String combo=(String) comboBox.getEditor().getItem();
					pst.setString(2,combo);
					pst.setString(3,textPrice.getText());

					pst.execute();
					JOptionPane.showMessageDialog(null,"Data saved");
					pst.close();
					}
				catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnAddStock.setBounds(10, 237, 86, 30);
		contentPane.add(btnAddStock);
		
		JButton btnCheckStock = new JButton();
		Image img1=new ImageIcon(this.getClass().getResource("/show.png")).getImage();
		btnCheckStock.setIcon(new ImageIcon(img1));
		btnCheckStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String  query="select * from products";
					PreparedStatement pst=(PreparedStatement) conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					//JOptionPane.showMessageDialog(null,"Data saved");
					pst.close();
					rs.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
								
			}
		});
		btnCheckStock.setBounds(119, 237, 86, 30);
		contentPane.add(btnCheckStock);
		
		textItem = new JTextField();
		textItem.setBounds(101, 135, 86, 20);
		contentPane.add(textItem);
		textItem.setColumns(10);
		
		textPrice = new JTextField();
		textPrice.setBounds(101, 169, 86, 20);
		contentPane.add(textPrice);
		textPrice.setColumns(10);
		
		JLabel lblItem = new JLabel("Item :");
		lblItem.setForeground(Color.ORANGE);
		lblItem.setBounds(26, 138, 46, 14);
		contentPane.add(lblItem);
		
		JLabel lblPrice = new JLabel("Price :");
		lblPrice.setForeground(Color.ORANGE);
		lblPrice.setBounds(26, 172, 46, 14);
		contentPane.add(lblPrice);
		
		JLabel lblStockAvailable = new JLabel("Stock Available");
		lblStockAvailable.setForeground(Color.GREEN);
		lblStockAvailable.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblStockAvailable.setBounds(339, 67, 127, 41);
		contentPane.add(lblStockAvailable);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C", "D"}));
		comboBox.setBounds(101, 200, 86, 20);
		contentPane.add(comboBox);
		
		JLabel lblBrand = new JLabel("Brand :");
		lblBrand.setForeground(Color.ORANGE);
		lblBrand.setBounds(26, 203, 46, 14);
		contentPane.add(lblBrand);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(215, 135, 379, 214);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnHome = new JButton();
		Image imgh=new ImageIcon(this.getClass().getResource("/home.png")).getImage();
		btnHome.setIcon(new ImageIcon(imgh));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				IndexFrame.main(null);
			}
		});
		btnHome.setBounds(10, 9, 41, 41);
		contentPane.add(btnHome);
		
		JButton btnBilling = new JButton("Check Billings");
		btnBilling.setForeground(Color.ORANGE);
		btnBilling.setFont(new Font("Times New Roman", Font.BOLD, 12));
		btnBilling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			OrderStock.main(null);
			}
		});
		btnBilling.setBounds(31, 287, 158, 23);
		contentPane.add(btnBilling);

		refreshTable();
	}
}
