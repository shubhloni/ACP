import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.Color;


public class OrderStock extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textVendor;
	private JTextField textPrice;
	private JTextField textDate;
	private ButtonGroup buttonGroup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderStock frame = new OrderStock();
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
	
	public void refreshTable()
	{
		try {
			String  query="select * from vendors";
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
	
	Connection conn=null;
	public OrderStock() {
		
		conn=(Connection) SQLConnect.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		getContentPane().setBackground(Color.black);
		
		JButton btnBack = new JButton();
		Image img=new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		btnBack.setIcon(new ImageIcon(img));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ShopKeepar.main(null);
			}
		});
		btnBack.setBounds(10, 9, 41, 41);
		contentPane.add(btnBack);
		
		JLabel lblStockOrders = new JLabel("Stock Orders");
		lblStockOrders.setForeground(Color.GREEN);
		lblStockOrders.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblStockOrders.setBounds(277, 23, 200, 50);
		contentPane.add(lblStockOrders);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 76, 516, 208);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textVendor = new JTextField();
		textVendor.setBounds(155, 295, 145, 31);
		contentPane.add(textVendor);
		textVendor.setColumns(10);
		
		textPrice = new JTextField();
		textPrice.setBounds(416, 295, 145, 31);
		contentPane.add(textPrice);
		textPrice.setColumns(10);
		
		textDate = new JTextField();
		textDate.setBounds(155, 340, 145, 23);
		contentPane.add(textDate);
		textDate.setColumns(10);
		
		JRadioButton rdbtnDone = new JRadioButton("Done");
		rdbtnDone.setForeground(Color.BLACK);
		rdbtnDone.setBounds(416, 340, 61, 23);
		rdbtnDone.setActionCommand(rdbtnDone.getText() );

		
		
		JRadioButton rdbtnPending = new JRadioButton("Pending");
		rdbtnPending.setForeground(Color.BLACK);
		rdbtnPending.setBounds(489, 340, 72, 23);
		rdbtnPending.setActionCommand(rdbtnPending.getText() );

		
		buttonGroup=new ButtonGroup();
		buttonGroup.add(rdbtnDone);
		buttonGroup.add(rdbtnPending);

		contentPane.add(rdbtnDone);
		contentPane.add(rdbtnPending);
		
		JLabel lblOrderedTo = new JLabel("Ordered to :");
		lblOrderedTo.setForeground(Color.ORANGE);
		lblOrderedTo.setBounds(73, 296, 72, 26);
		contentPane.add(lblOrderedTo);
		
		JLabel lblTotalPrice = new JLabel("Total Price :");
		lblTotalPrice.setForeground(Color.ORANGE);
		lblTotalPrice.setBounds(337, 284, 200, 50);
		contentPane.add(lblTotalPrice);
		
		JLabel lblDateyyyymmdd = new JLabel("Date (yyyy-mm-dd) :");
		lblDateyyyymmdd.setForeground(Color.ORANGE);
		lblDateyyyymmdd.setBounds(31, 332, 200, 50);
		contentPane.add(lblDateyyyymmdd);
		
		JLabel lblDelivery = new JLabel("Delivery : ");
		lblDelivery.setForeground(Color.ORANGE);
		lblDelivery.setBounds(337, 334, 200, 34);
		contentPane.add(lblDelivery);
		
		JButton btnAddEntry = new JButton();
		Image imga=new ImageIcon(this.getClass().getResource("/add1.png")).getImage();
		btnAddEntry.setIcon(new ImageIcon(imga));
			
		btnAddEntry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try 
				{
				
					String  query="INSERT INTO vendors(VendorName,Price,Date,Delivery) values(?,?,?,?)";
					PreparedStatement ps=(PreparedStatement) conn.prepareStatement(query);
					ps.setString(1,textVendor.getText());
					ps.setString(2,textPrice.getText());
					ps.setString(3,textDate.getText());
					ps.setString(4,buttonGroup.getSelection().getActionCommand());

					ps.execute();
					JOptionPane.showMessageDialog(null,"\tEntry Added...");
					ps.close();

					refreshTable();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	
			
			}
		});
		btnAddEntry.setBounds(278, 376, 94, 34);
		contentPane.add(btnAddEntry);
		
		refreshTable();
	}
}
