import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;


public class Feedback extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textName;
	private JTextField textEmail;
	private JTextField textQuality;
	private JTextField textRequire;
	private JTextField textImprove;
	private JTextField textRate;
	private JTextField textCell;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Feedback frame = new Feedback();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection conn=null;
	/**
	 * Create the frame.
	 */
	public Feedback() {
		conn=(Connection) SQLConnect.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		getContentPane().setBackground(Color.lightGray);
		
		textName = new JTextField();
		textName.setBounds(299, 86, 180, 29);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textEmail = new JTextField();
		textEmail.setBounds(299, 126, 180, 29);
		contentPane.add(textEmail);
		textEmail.setColumns(10);
		
		textQuality = new JTextField();
		textQuality.setBounds(299, 206, 180, 29);
		contentPane.add(textQuality);
		textQuality.setColumns(10);
		
		textRequire = new JTextField();
		textRequire.setBounds(299, 246, 180, 29);
		contentPane.add(textRequire);
		textRequire.setColumns(10);
		
		textImprove = new JTextField();
		textImprove.setBounds(299, 286, 180, 29);
		contentPane.add(textImprove);
		textImprove.setColumns(10);
		
		textRate = new JTextField();
		textRate.setBounds(299, 326, 180, 29);
		contentPane.add(textRate);
		textRate.setColumns(10);
		
		textCell = new JTextField();
		textCell.setBounds(299, 166, 180, 29);
		contentPane.add(textCell);
		textCell.setColumns(10);
		
		JLabel lblYourFeedbackWill = new JLabel("Your Feedback will Help us to Improve our Service and Quality...");
		lblYourFeedbackWill.setBounds(147, 50, 367, 14);
		contentPane.add(lblYourFeedbackWill);
		
		JLabel lblName = new JLabel("Name : ");
		lblName.setBounds(170, 93, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblEmailId = new JLabel("Email ID :");
		lblEmailId.setBounds(170, 133, 119, 14);
		contentPane.add(lblEmailId);
		
		JLabel lblCellNo = new JLabel("Cell No :  ");
		lblCellNo.setBounds(170, 173, 119, 14);
		contentPane.add(lblCellNo);
		
		JLabel lblQuality = new JLabel("Quality :");
		lblQuality.setBounds(170, 213, 119, 14);
		contentPane.add(lblQuality);
		
		JLabel lblNewLabel = new JLabel("Requirements : ");
		lblNewLabel.setBounds(170, 253, 119, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblImprovements = new JLabel("Improvements : ");
		lblImprovements.setBounds(170, 293, 119, 14);
		contentPane.add(lblImprovements);
		
		JLabel lblRating = new JLabel("Rating (1-5) : ");
		lblRating.setBounds(170, 333, 119, 14);
		contentPane.add(lblRating);
		
		JButton btnSubmit = new JButton();
		Image img=new ImageIcon(this.getClass().getResource("/submit.png")).getImage();
		btnSubmit.setIcon(new ImageIcon(img));
		btnSubmit.setBounds(226, 376, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton();
		Image img2=new ImageIcon(this.getClass().getResource("/cancel_2.png")).getImage();
		btnCancel.setIcon(new ImageIcon(img2));
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			IndexFrame.main(null);
			}
		});
		btnCancel.setBounds(342, 376, 89, 23);
		contentPane.add(btnCancel);
		
		JButton btnHome = new JButton();
		Image img1=new ImageIcon(this.getClass().getResource("/home.png")).getImage();
		btnHome.setIcon(new ImageIcon(img1));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				IndexFrame.main(null);
			}
		});
		btnHome.setBounds(10, 9, 41, 41);
		contentPane.add(btnHome);
	}
}
