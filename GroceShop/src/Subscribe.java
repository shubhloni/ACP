import java.awt.EventQueue;
import java.awt.Font;
import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;


public class Subscribe extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCn;  
	private JLabel lblMo;
	private JLabel lblDa;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Subscribe frame = new Subscribe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void refreshAll()
	{
		try {
			String  query="select * from contacts";
			PreparedStatement pst=(PreparedStatement) conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery(query);
			String name = null,addr=null,mob,date;
			while(rs.last())
			{
				lblCn.setText(rs.getString("name")+" "+rs.getString("surname"));
				lblMo.setText(rs.getString("phone"));
				lblDa.setText(rs.getString("address"));
				//date=rs.getString("");
			}
					
			pst.close();
			rs.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 */
	Connection conn=null;
	
	public Subscribe() {
	
		conn=(Connection) SQLConnect.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOrderSummary = new JLabel("ORDER SUMMARY");
		lblOrderSummary.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblOrderSummary.setBounds(210, 11, 200, 28);
		contentPane.add(lblOrderSummary);
		
		JLabel lblCustomerName = new JLabel("Customer Name :");
		lblCustomerName.setBounds(57, 54, 102, 14);
		contentPane.add(lblCustomerName);
		
		lblCn = new JLabel();
		lblCn.setBounds(169, 54, 200, 14);
		contentPane.add(lblCn);
		
		JLabel lblDiliveryAddress = new JLabel("Dilivery Address : ");
		lblDiliveryAddress.setBounds(57, 79, 102, 14);
		contentPane.add(lblDiliveryAddress);
		
		lblDa = new JLabel("da");
		lblDa.setBounds(169, 79, 200, 28);
		contentPane.add(lblDa);
		
		JLabel lblMobileNo = new JLabel("Mobile No. : ");
		lblMobileNo.setBounds(57, 118, 79, 14);
		contentPane.add(lblMobileNo);
		
		lblMo = new JLabel();
		lblMo.setBounds(169, 118, 145, 14);
		contentPane.add(lblMo);
		
		JLabel lblOrderList = new JLabel("Order List");
		lblOrderList.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblOrderList.setBounds(236, 143, 102, 28);
		contentPane.add(lblOrderList);
		
		JLabel lblOl = new JLabel("ol");
		lblOl.setBounds(57, 169, 380, 202);
		contentPane.add(lblOl);
		
		JLabel lblDate = new JLabel("Date : ");
		lblDate.setBounds(334, 118, 51, 17);
		contentPane.add(lblDate);
		
		JLabel lblD = new JLabel("d");
		lblD.setBounds(401, 117, 145, 17);
		contentPane.add(lblD);
		
		JLabel lblTotalbill = new JLabel("Total Bill :  ");
		lblTotalbill.setBounds(442, 205, 65, 14);
		contentPane.add(lblTotalbill);
		
		JLabel lblT = new JLabel("t");
		lblT.setBounds(525, 205, 79, 14);
		contentPane.add(lblT);
		
		JLabel lblPayment = new JLabel("Payment : ");
		lblPayment.setBounds(442, 247, 65, 17);
		contentPane.add(lblPayment);
		
		JLabel lblP = new JLabel("p");
		lblP.setBounds(525, 247, 79, 17);
		contentPane.add(lblP);
		
		JLabel lblComputerGeneratedReciept = new JLabel("Computer Generated Reciept");
		lblComputerGeneratedReciept.setBounds(443, 292, 161, 28);
		contentPane.add(lblComputerGeneratedReciept);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(249, 387, 89, 23);
		contentPane.add(btnOk);
		
		refreshAll();
	}
}
