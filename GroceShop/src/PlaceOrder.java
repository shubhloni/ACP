import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

import java.awt.Font;


public class PlaceOrder extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textName;
	private JTextField textSurname;
	private JTextField textMobile;
	private JTextField textMail;
	private JTextField textAddress;
	private JTextField textLand;
	private JTextField textPIN;
	private JTextField textCity;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlaceOrder frame = new PlaceOrder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void refresh()
	{
		String query1="select Price from orders";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(query1);
		
		ResultSet r=ps.executeQuery();
		
		int price=0;
		int count=0;
		while(r.next())
		{
			count++;
			price=price+(r.getInt("Price"));
		}
		
		texttotPrice.setText(String.valueOf(price));
		textProNo.setText(String.valueOf(count));
		
		ps.close();
		r.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	Connection conn=null;
	private JTextField textProNo;
	private JTextField texttotPrice;
	private JTextField textDate;
	/**
	 * Create the frame.
	 */
	public PlaceOrder() {
		conn=(Connection) SQLConnect.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		getContentPane().setBackground(Color.black);

		JButton btnHome = new JButton();
		Image img=new ImageIcon(this.getClass().getResource("/home.png")).getImage();
		btnHome.setIcon(new ImageIcon(img));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				IndexFrame.main(null);

			}
		});
		btnHome.setBounds(10, 11, 41, 41);
		contentPane.add(btnHome);
		
		JButton btnBack = new JButton();
		Image img1=new ImageIcon(this.getClass().getResource("/back.png")).getImage();
		btnBack.setIcon(new ImageIcon(img1));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				OrderFrame.main(null);
				}
		});
		btnBack.setBounds(57, 11, 41, 41);
		contentPane.add(btnBack);
		
		textName = new JTextField();
		textName.setBounds(128, 220, 140, 20);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textSurname = new JTextField();
		textSurname.setBounds(411, 220, 140, 20);
		contentPane.add(textSurname);
		textSurname.setColumns(10);
		
		textMobile = new JTextField();
		textMobile.setBounds(128, 251, 140, 20);
		contentPane.add(textMobile);
		textMobile.setColumns(10);
		
		textMail = new JTextField();
		textMail.setBounds(411, 251, 140, 20);
		contentPane.add(textMail);
		textMail.setColumns(10);
		
		textAddress = new JTextField();
		textAddress.setBounds(128, 282, 140, 48);
		contentPane.add(textAddress);
		textAddress.setColumns(10);
		
		textArea = new JTextArea();
		
		JLabel lblName = new JLabel("Name* :");
		lblName.setForeground(Color.ORANGE);
		lblName.setBounds(52, 223, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname* :");
		lblSurname.setForeground(Color.ORANGE);
		lblSurname.setBounds(321, 223, 67, 14);
		contentPane.add(lblSurname);
		
		JLabel lblMobile = new JLabel("Mobile* :");
		lblMobile.setForeground(Color.ORANGE);
		lblMobile.setBounds(52, 254, 66, 14);
		contentPane.add(lblMobile);
		
		JLabel lblEmail = new JLabel("E-mail : ");
		lblEmail.setForeground(Color.ORANGE);
		lblEmail.setBounds(321, 254, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address* : ");
		lblAddress.setForeground(Color.ORANGE);
		lblAddress.setBounds(52, 299, 66, 14);
		contentPane.add(lblAddress);
		
		textLand = new JTextField();
		textLand.setBounds(411, 282, 140, 20);
		contentPane.add(textLand);
		textLand.setColumns(10);
		
		textPIN = new JTextField();
		textPIN.setBounds(411, 310, 140, 20);
		contentPane.add(textPIN);
		textPIN.setColumns(10);
		
		JLabel lblLandmark = new JLabel("Landmark :");
		lblLandmark.setForeground(Color.ORANGE);
		lblLandmark.setBounds(321, 285, 67, 14);
		contentPane.add(lblLandmark);
		
		JLabel lblPin = new JLabel("PIN* :");
		lblPin.setForeground(Color.ORANGE);
		lblPin.setBounds(321, 313, 46, 14);
		contentPane.add(lblPin);
		
		
		JButton btnCancel = new JButton();
		Image imgc=new ImageIcon(this.getClass().getResource("/cancel_2.png")).getImage();
		btnCancel.setIcon(new ImageIcon(imgc));
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				IndexFrame.main(null);				
			}
		});
		btnCancel.setBounds(334, 376, 86, 32);
		contentPane.add(btnCancel);
		
		JButton btnPlaceOrder = new JButton();
		Image img2=new ImageIcon(this.getClass().getResource("/place-order-button.png")).getImage();
		btnPlaceOrder.setIcon(new ImageIcon(img2));
		
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try 
				{
				
					String  query="INSERT INTO contacts(name,surname,phone,email,address,city,landmark,pin) values(?,?,?,?,?,?,?,?)";
					PreparedStatement ps=(PreparedStatement) conn.prepareStatement(query);
					ps.setString(1,textName.getText());
					ps.setString(2,textSurname.getText());
					ps.setString(3,textMobile.getText());
					ps.setString(4,textMail.getText());
					ps.setString(5,textAddress.getText());
					ps.setString(6,textCity.getText());
					ps.setString(7,textLand.getText());
					ps.setString(8,textPIN.getText());

					ps.execute();
					//JOptionPane.showMessageDialog(null,"\tThank You...\nYour Order Placed...");
					ps.close();
			
					String  query1="INSERT INTO order_list(Name,Surname,Shopping,Products,Date) values(?,?,?,?,?)";
					PreparedStatement pst=(PreparedStatement) conn.prepareStatement(query1);
					pst.setString(1,textName.getText());
					pst.setString(2,textSurname.getText());
					pst.setString(3,texttotPrice.getText());
					pst.setString(4,textProNo.getText());
					pst.setString(5,textDate.getText());

					pst.execute();
					//JOptionPane.showMessageDialog(null,"\tThank You...\nYour Order Placed...");
					pst.close();
					
					ProcessOrder d=new ProcessOrder();
					d.setVisible(true);
					
			}
			catch (Exception e) {
					e.printStackTrace();	
			}
				
			}});
		btnPlaceOrder.setBounds(229, 376, 86, 32);
		contentPane.add(btnPlaceOrder);
		
		textCity = new JTextField();
		textCity.setBounds(128, 341, 140, 20);
		contentPane.add(textCity);
		textCity.setColumns(10);
		
		JLabel lblCity = new JLabel("City :");
		lblCity.setForeground(Color.ORANGE);
		lblCity.setBounds(52, 344, 46, 14);
		contentPane.add(lblCity);
		
		JLabel lblTotalProducts = new JLabel("Total Products : ");
		lblTotalProducts.setForeground(Color.ORANGE);
		lblTotalProducts.setBounds(52, 145, 113, 14);
		contentPane.add(lblTotalProducts);
		
		textProNo = new JTextField();
		textProNo.setEditable(false);
		textProNo.setBounds(175, 142, 67, 20);
		contentPane.add(textProNo);
		textProNo.setColumns(10);
		
		texttotPrice = new JTextField();
		texttotPrice.setEditable(false);
		texttotPrice.setBounds(411, 142, 67, 20);
		contentPane.add(texttotPrice);
		texttotPrice.setColumns(10);
		
		JLabel lblTotalPrice = new JLabel("Total Price : ");
		lblTotalPrice.setForeground(Color.ORANGE);
		lblTotalPrice.setBounds(321, 145, 80, 14);
		contentPane.add(lblTotalPrice);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(Color.BLACK);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Cash On Delivery", "Paid at Counter"}));
		comboBox.setBounds(175, 173, 113, 20);
		contentPane.add(comboBox);
		
		JLabel lblPaymentMethod = new JLabel("Payment Method :");
		lblPaymentMethod.setForeground(Color.ORANGE);
		lblPaymentMethod.setBounds(52, 176, 113, 14);
		contentPane.add(lblPaymentMethod);
		
		JLabel lblFastDelivery = new JLabel("Fast Delivery : ");
		lblFastDelivery.setForeground(Color.ORANGE);
		lblFastDelivery.setBounds(321, 176, 84, 14);
		contentPane.add(lblFastDelivery);
		
		JCheckBox chckbxYes = new JCheckBox("YES");
		chckbxYes.setForeground(Color.BLACK);
		chckbxYes.setBounds(411, 172, 63, 23);
		contentPane.add(chckbxYes);
		
		JCheckBox chckbxNo = new JCheckBox("NO");
		chckbxNo.setForeground(Color.BLACK);
		chckbxNo.setBounds(476, 172, 46, 23);
		contentPane.add(chckbxNo);
		
		JLabel lblShippingAddress = new JLabel("Shipping Address");
		lblShippingAddress.setForeground(Color.GREEN);
		lblShippingAddress.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
		lblShippingAddress.setBounds(229, 53, 138, 32);
		contentPane.add(lblShippingAddress);
		
		JLabel lblDateyymmdd = new JLabel("Date (yy-mm-dd)* :");
		lblDateyymmdd.setForeground(Color.ORANGE);
		lblDateyymmdd.setBounds(321, 344, 125, 14);
		contentPane.add(lblDateyymmdd);
		
		textDate = new JTextField();
		textDate.setBounds(456, 341, 95, 20);
		contentPane.add(textDate);
		textDate.setColumns(10);
		
		JLabel lblR = new JLabel();
		Image imgr=new ImageIcon(this.getClass().getResource("/rups.png")).getImage();
		lblR.setIcon(new ImageIcon(imgr));
		
		lblR.setBounds(481, 145, 41, 23);
		contentPane.add(lblR);
		
		refresh();
	}
}
