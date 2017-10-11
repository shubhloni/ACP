import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;


public class ProcessOrder extends JFrame {

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
					ProcessOrder frame = new ProcessOrder();
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
	public ProcessOrder() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		getContentPane().setBackground(Color.black);
		
		JLabel lblNewLabel = new JLabel("THANK YOU...");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(253, 113, 178, 38);
		contentPane.add(lblNewLabel);
		
		JLabel lblYourOrderHas = new JLabel("Your Order Has Been Successfully Placed... And Delivered Soon");
		lblYourOrderHas.setForeground(Color.CYAN);
		lblYourOrderHas.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblYourOrderHas.setBounds(57, 180, 497, 30);
		contentPane.add(lblYourOrderHas);
		
		JButton btnFeedback = new JButton();
		Image imgf=new ImageIcon(this.getClass().getResource("/feedback1.png")).getImage();
		btnFeedback.setIcon(new ImageIcon(imgf));
			
		btnFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFeedback.setBounds(270, 251, 90, 30);
		contentPane.add(btnFeedback);
		
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
	}
}
