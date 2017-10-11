import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import java.awt.Color;


public class Orders extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Orders frame = new Orders();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	Connection conn=null;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textShopping;
	private JTextField textProducts;
	private JTextField textDate;
	private JButton btnEnterOrder;
	private JLabel lblName;
	private JLabel lblSruname;
	private JLabel lblShoppingOfRupees;
	private JLabel lblNoOfProducts;
	private JLabel lblDateyyyymmdd;
	private JButton btnBack;
		/**
	 * Create the frame.
	 */
	public void refreshTable()
	{
		String  query="select * from order_list";
		PreparedStatement pst;
		try {
			pst = (PreparedStatement) conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Orders() {
		conn=(Connection) SQLConnect.dbConnector();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		getContentPane().setBackground(Color.black);
		
		JLabel lblOrdersPlaced = new JLabel("Orders Placed");
		lblOrdersPlaced.setForeground(Color.ORANGE);
		lblOrdersPlaced.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblOrdersPlaced.setBounds(253, 36, 200, 50);
		contentPane.add(lblOrdersPlaced);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 95, 594, 190);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textName = new JTextField();
		textName.setBounds(173, 291, 120, 31);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textSurname = new JTextField();
		textSurname.setBounds(173, 333, 120, 31);
		contentPane.add(textSurname);
		textSurname.setColumns(10);
		
		textShopping = new JTextField();
		textShopping.setColumns(10);
		textShopping.setBounds(173, 380, 120, 31);
		contentPane.add(textShopping);
		
		textProducts = new JTextField();
		textProducts.setColumns(10);
		textProducts.setBounds(438, 291, 120, 31);
		contentPane.add(textProducts);
		
		textDate = new JTextField();
		textDate.setColumns(10);
		textDate.setBounds(438, 333, 120, 31);
		contentPane.add(textDate);
	
		
		btnEnterOrder = new JButton();
		Image img1=new ImageIcon(this.getClass().getResource("/add1.png")).getImage();
		btnEnterOrder.setIcon(new ImageIcon(img1));
	
		btnEnterOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				try 
				{
				
					String  query="INSERT INTO order_list(Name,Surname,Shopping,Products,Date) values(?,?,?,?,?)";
					PreparedStatement ps=(PreparedStatement) conn.prepareStatement(query);
					ps.setString(1,textName.getText());
					ps.setString(2,textSurname.getText());
					ps.setString(3,textShopping.getText());
					ps.setString(4,textProducts.getText());
					ps.setString(5,textDate.getText());

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
		btnEnterOrder.setBounds(458, 380, 85, 31);
		contentPane.add(btnEnterOrder);
		
		lblName = new JLabel("Name : ");
		lblName.setForeground(Color.ORANGE);
		lblName.setBounds(103, 281, 200, 50);
		contentPane.add(lblName);
		
		lblSruname = new JLabel("Surname :");
		lblSruname.setForeground(Color.ORANGE);
		lblSruname.setBounds(103, 323, 200, 50);
		contentPane.add(lblSruname);
		
		lblShoppingOfRupees = new JLabel("Shopping Of Rupees :");
		lblShoppingOfRupees.setForeground(Color.ORANGE);
		lblShoppingOfRupees.setBounds(44, 370, 200, 50);
		contentPane.add(lblShoppingOfRupees);
		
		lblNoOfProducts = new JLabel("No. of Products :");
		lblNoOfProducts.setForeground(Color.ORANGE);
		lblNoOfProducts.setBounds(333, 281, 200, 50);
		contentPane.add(lblNoOfProducts);
		
		lblDateyyyymmdd = new JLabel("Date (yyyy-mm-dd) :");
		lblDateyyyymmdd.setForeground(Color.ORANGE);
		lblDateyyyymmdd.setBounds(313, 323, 200, 50);
		contentPane.add(lblDateyyyymmdd);
		
		btnBack = new JButton();
		Image img=new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		btnBack.setIcon(new ImageIcon(img));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				ShopKeepar.main(null);
			}
		});
		btnBack.setBounds(10, 9, 41, 41);
		contentPane.add(btnBack);
		
		refreshTable();
	}
}
