import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;


public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUser;
	private JPasswordField passwordField;
	public String pass;
	public String uname;
	public String u="admin";
	public String p="admin";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	
	Connection conn=null;
	
	public Login() {
		
		conn=(Connection) SQLConnect.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 235);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		getContentPane().setBackground(Color.lightGray);
		
		textUser = new JTextField();
		textUser.setBounds(149, 64, 118, 30);
		contentPane.add(textUser);
		textUser.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name : ");
		lblUserName.setBounds(58, 72, 81, 14);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(58, 113, 81, 14);
		contentPane.add(lblPassword);
		
		JLabel lblShopekeeperPanel = new JLabel("ShopeKeeper Panel");
		lblShopekeeperPanel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblShopekeeperPanel.setBounds(102, 11, 135, 14);
		contentPane.add(lblShopekeeperPanel);
		
		JLabel lblPleaseLoginTo = new JLabel("Please log-in to your rights ");
		lblPleaseLoginTo.setBounds(87, 36, 190, 14);
		contentPane.add(lblPleaseLoginTo);
		
		JButton btnSubmit = new JButton();
		Image img=new ImageIcon(this.getClass().getResource("/submit.png")).getImage();
		btnSubmit.setIcon(new ImageIcon(img));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try {
					String  query="select * from login where acc=? and pass=?";

					java.sql.PreparedStatement pst= conn.prepareStatement(query);
					pst.setString(1,textUser.getText());
					pst.setString(2,passwordField.getText());
					
					ResultSet rs=pst.executeQuery();
					int count=0;
					while(rs.next())
					{
						count++;
					}
					pst.close();
					
					if(count!=0)
					{
						
						ShopKeepar.main(null);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Invalid User Name \n Please Try again...");
					}
					rs.close();
					pst.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		
		btnSubmit.setBounds(60, 150, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton();
		Image img1=new ImageIcon(this.getClass().getResource("/cancel_2.png")).getImage();
		btnCancel.setIcon(new ImageIcon(img1));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				IndexFrame.main(null);
			}
		});
		btnCancel.setBounds(178, 150, 89, 23);
		contentPane.add(btnCancel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(149, 105, 118, 30);
		contentPane.add(passwordField);
	}
	
		
}
