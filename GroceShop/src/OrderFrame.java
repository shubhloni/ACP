import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.Statement;







import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;


public class OrderFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textitem;
	private JTextField textquantity;
	private JTable table;
	private JComboBox boxBrand;
	private JComboBox boxunit;
	Connection conn=null;
	private JTextField textTotal;
	private JTextField textSearch;
	/**
	 * Create the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderFrame window = new OrderFrame();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public OrderFrame() {
		initialize();
		conn=(Connection) SQLConnect.dbConnector();

	}

	public void refreshTable()
	{
		try {
			String  query="select * from orders";
			PreparedStatement pst=(PreparedStatement) conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			pst.close();
			rs.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 630, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		frame.getContentPane().setBackground(Color.black);
		
		JLabel lblOrderFrame1 = new JLabel("             Order Form");
		lblOrderFrame1.setForeground(Color.GREEN);
		lblOrderFrame1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblOrderFrame1.setBounds(170, 11, 200, 50);
		frame.getContentPane().add(lblOrderFrame1);
		
		JLabel lblPleaseEnterYour = new JLabel("Please Enter Item Name(in small letters) and Quantity Below and Press 'ADD'..");
		lblPleaseEnterYour.setForeground(Color.YELLOW);
		lblPleaseEnterYour.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPleaseEnterYour.setBounds(20, 73, 509, 41);
		frame.getContentPane().add(lblPleaseEnterYour);
		
		textitem = new JTextField();
		textitem.setBounds(136, 133, 150, 30);
		frame.getContentPane().add(textitem);
		textitem.setColumns(10);
		
		textquantity = new JTextField();
		textquantity.setBounds(421, 133, 65, 30);
		frame.getContentPane().add(textquantity);
		textquantity.setColumns(10);
		
		JLabel lblItem = new JLabel("Item Name* :");
		lblItem.setForeground(Color.ORANGE);
		lblItem.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblItem.setBounds(25, 122, 101, 50);
		frame.getContentPane().add(lblItem);
		
		JLabel lblQuantity = new JLabel("Quantity* : ");
		lblQuantity.setForeground(Color.ORANGE);
		lblQuantity.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblQuantity.setBounds(319, 122, 90, 50);
		frame.getContentPane().add(lblQuantity);
		
		boxunit = new JComboBox();
		boxunit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		boxunit.setModel(new DefaultComboBoxModel(new String[] {"   KG", "   Litre", "   Number"}));
		boxunit.setToolTipText("");
		boxunit.setBounds(513, 132, 65, 30);
		frame.getContentPane().add(boxunit);
		
		JButton btnadd = new JButton();
		Image img1=new ImageIcon(this.getClass().getResource("/atc.png")).getImage();
		btnadd.setIcon(new ImageIcon(img1));
		
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					String  query="insert into orders (Product,Brand,Quantity,Unit,Price) values (?,?,?,?,?)";

					java.sql.PreparedStatement pst= conn.prepareStatement(query);
					pst.setString(1,textitem.getText());
					String brand=(String) boxBrand.getEditor().getItem();
					pst.setString(2,brand);
					String unit=(String) boxunit.getEditor().getItem();
					pst.setString(3,textquantity.getText());
					pst.setString(4,unit);
					
					String query1="select Price from Products where Stock=?";
					PreparedStatement ps=(PreparedStatement) conn.prepareStatement(query1);
					ps.setString(1,textitem.getText());
					ResultSet rs=ps.executeQuery();
					int price=0;
					while(rs.next())
					{
						price=rs.getInt("Price");
					}
					int total=price*(Integer.parseInt(textquantity.getText()));
					pst.setString(5,(String.valueOf(total)));
					
					pst.execute();
					JOptionPane.showMessageDialog(null,"Added to Cart");
					pst.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				refreshTable();
			}
		});
		btnadd.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnadd.setBounds(361, 176, 90, 30);
		frame.getContentPane().add(btnadd);
		
		JButton btnOrderList = new JButton();
		Image img5=new ImageIcon(this.getClass().getResource("/view.png")).getImage();
		btnOrderList.setIcon(new ImageIcon(img5));
		
		btnOrderList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String  query="select * from orders";
					PreparedStatement pst=(PreparedStatement) conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
					pst.close();
					rs.close();
					
					
					String query1="select Price from orders";
					PreparedStatement ps=(PreparedStatement) conn.prepareStatement(query1);
					ResultSet r=ps.executeQuery();
					
					int price=0;
					
					while(r.next())
					{
						price=price+(r.getInt("Price"));
					}
					
					textTotal.setText(String.valueOf(price));
					
					ps.close();
					r.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnOrderList.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnOrderList.setBounds(488, 176, 90, 30);
		frame.getContentPane().add(btnOrderList);
		
		boxBrand = new JComboBox();
		boxBrand.setFont(new Font("Times New Roman", Font.BOLD, 14));
		boxBrand.setModel(new DefaultComboBoxModel(new String[] {"NA", "Oil - Gemini", "Oil - Sunflower", "Oil - Swaraj"}));
		boxBrand.setBounds(136, 177, 150, 30);
		frame.getContentPane().add(boxBrand);
		
		JLabel lblProductBrand = new JLabel("Product Brand :");
		lblProductBrand.setForeground(Color.ORANGE);
		lblProductBrand.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblProductBrand.setBounds(25, 185, 101, 14);
		frame.getContentPane().add(lblProductBrand);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 218, 566, 153);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnPlaceOrder = new JButton();
		Image img4=new ImageIcon(this.getClass().getResource("/order.png")).getImage();
		btnPlaceOrder.setIcon(new ImageIcon(img4));
		
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				frame.dispose();
				PlaceOrder p=new PlaceOrder();
				p.setVisible(true);
			}
		});
		btnPlaceOrder.setBounds(313, 382, 90, 30);
		frame.getContentPane().add(btnPlaceOrder);
		
		JButton btnCancel = new JButton();
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IndexFrame.main(null);
			}
		});
		Image img3=new ImageIcon(this.getClass().getResource("/cancel_2.png")).getImage();
		btnCancel.setIcon(new ImageIcon(img3));
		
		btnCancel.setBounds(450, 382, 90, 30);
		frame.getContentPane().add(btnCancel);
		
		JLabel lblTotalPrice = new JLabel("Total Price :");
		lblTotalPrice.setForeground(Color.ORANGE);
		lblTotalPrice.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTotalPrice.setBounds(41, 385, 85, 22);
		frame.getContentPane().add(lblTotalPrice);
		
		textTotal = new JTextField();
		textTotal.setEditable(false);
		textTotal.setBounds(133, 382, 73, 30);
		frame.getContentPane().add(textTotal);
		textTotal.setColumns(10);
		
		textSearch = new JTextField();
		textSearch.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
			}
		});
		textSearch.setBounds(492, 23, 107, 30);
		frame.getContentPane().add(textSearch);
		textSearch.setColumns(10);
		
		JButton btnSearch = new JButton();
		Image img2=new ImageIcon(this.getClass().getResource("/search-btn.png")).getImage();
		btnSearch.setIcon(new ImageIcon(img2));
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try {
					String  query="select * from products where Stock=?";
					PreparedStatement pst=(PreparedStatement) conn.prepareStatement(query);
					pst.setString(1,textSearch.getText());
					ResultSet rs=pst.executeQuery();
					int count=0;
					while(rs.next())
					{
						count++;
					}
					
					if(count!=0)
					{
						JOptionPane.showMessageDialog(null,"Product Available");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Product Not Available");
					}
					pst.close();
					rs.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			
			}
		});
		btnSearch.setBounds(502, 57, 89, 23);
		frame.getContentPane().add(btnSearch);
		
		JButton btnHome = new JButton();
		Image img=new ImageIcon(this.getClass().getResource("/home.png")).getImage();
		btnHome.setIcon(new ImageIcon(img));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				IndexFrame.main(null);
			}
		});
		btnHome.setBounds(10, 9, 41, 41);
		frame.getContentPane().add(btnHome);
		
		JLabel lblR = new JLabel();
		Image imgr=new ImageIcon(this.getClass().getResource("/rups.png")).getImage();
		lblR.setIcon(new ImageIcon(imgr));
		
		lblR.setBounds(207, 382, 41, 30);
		frame.getContentPane().add(lblR);
	}
}
