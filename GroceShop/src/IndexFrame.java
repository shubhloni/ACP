import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class IndexFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IndexFrame window = new IndexFrame();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection conn=null;
	public void refreshTable()
	{
		try {
			String  query="select Stock from products";
			PreparedStatement pst=(PreparedStatement) conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			pst.close();
			rs.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public IndexFrame() {

		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		conn=(Connection) SQLConnect.dbConnector();
	
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.black);
		frame.setBounds(100, 100, 630, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblGroceryShopManagement = new JLabel("Grocery Shop Management");
		lblGroceryShopManagement.setForeground(Color.GREEN);
		lblGroceryShopManagement.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
		lblGroceryShopManagement.setBounds(147, 22, 281, 37);
		frame.getContentPane().add(lblGroceryShopManagement);
		
		JButton btnAdmin = new JButton();
		Image img=new ImageIcon(this.getClass().getResource("/admin.png")).getImage();
		btnAdmin.setIcon(new ImageIcon(img));
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				Login.main(null);
			}
		});
		btnAdmin.setBounds(515, 387, 89, 23);
		frame.getContentPane().add(btnAdmin);
		
		JButton btnFeedback = new JButton();
		Image img1=new ImageIcon(this.getClass().getResource("/feedback1.png")).getImage();
		btnFeedback.setIcon(new ImageIcon(img1));
		btnFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			Feedback.main(null);
			
			}
		});
		btnFeedback.setBounds(416, 387, 89, 23);
		frame.getContentPane().add(btnFeedback);
		
		JLabel lblAllRightsReserved = new JLabel("All rights reserved @Grocery Shop       T&C");
		lblAllRightsReserved.setForeground(Color.ORANGE);
		lblAllRightsReserved.setBounds(25, 391, 300, 14);
		frame.getContentPane().add(lblAllRightsReserved);
		
		JLabel home = new JLabel();
		Image img2=new ImageIcon(this.getClass().getResource("/shop1.jpg")).getImage();
		home.setIcon(new ImageIcon(img2));
		home.setBounds(25, 81, 565, 284);
		frame.getContentPane().add(home);
		
		JButton btnOrder = new JButton();
		Image img3=new ImageIcon(this.getClass().getResource("/orderNowButton1.png")).getImage();
		btnOrder.setIcon(new ImageIcon(img3));
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				OrderFrame.main(null);
			}
		});
		btnOrder.setBounds(515, 11, 89, 37);
		frame.getContentPane().add(btnOrder);
		
		JLabel lblA = new JLabel();
		Image imgl=new ImageIcon(this.getClass().getResource("/arrow.png")).getImage();
		lblA.setIcon(new ImageIcon(imgl));
		
		lblA.setBounds(476, 11, 29, 37);
		frame.getContentPane().add(lblA);
	
		refreshTable();
	}
}
