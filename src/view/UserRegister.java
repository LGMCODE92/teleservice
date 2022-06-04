package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserRegister extends JFrame {

	private JPanel contentPane;
	private JTextField txtNameOperator;
	private JTextField txtSurnameOperator;
	private JTextField txtDniOperator;
	private JTextField txtEmailOperator;
	private JTextField txtUsername;
	private JTextField txtPw;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegister frame = new UserRegister();
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
	public UserRegister() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setTitle("User register");
		Image img = new ImageIcon(getClass().getResource("../images/login.png")).getImage();
		setIconImage(img);
		contentPane.setLayout(null);
		
		JLabel nameOperator = new JLabel("Nombre:");
		nameOperator.setBounds(34, 44, 100, 14);
		contentPane.add(nameOperator);
		
		JLabel surnameOperator = new JLabel("Apellidos:");
		surnameOperator.setBounds(34, 70, 100, 14);
		contentPane.add(surnameOperator);
		
		JLabel dniOperator = new JLabel("DNI:");
		dniOperator.setBounds(85, 112, 100, 14);
		contentPane.add(dniOperator);
		
		JLabel emailOperator = new JLabel("Correo electr\u00F3nico:");
		emailOperator.setBounds(85, 137, 100, 14);
		contentPane.add(emailOperator);
		
		JLabel userOperator = new JLabel("Nombre de usuario:");
		userOperator.setBounds(85, 164, 100, 14);
		contentPane.add(userOperator);
		
		JLabel pwOperator = new JLabel("Contrase\u00F1a:");
		pwOperator.setBounds(85, 189, 100, 14);
		contentPane.add(pwOperator);
		
		txtNameOperator = new JTextField();
		txtNameOperator.setBounds(144, 41, 96, 20);
		contentPane.add(txtNameOperator);
		txtNameOperator.setColumns(10);
		
		txtSurnameOperator = new JTextField();
		txtSurnameOperator.setColumns(10);
		txtSurnameOperator.setBounds(199, 84, 96, 20);
		contentPane.add(txtSurnameOperator);
		
		txtDniOperator = new JTextField();
		txtDniOperator.setColumns(10);
		txtDniOperator.setBounds(199, 112, 96, 20);
		contentPane.add(txtDniOperator);
		
		txtEmailOperator = new JTextField();
		txtEmailOperator.setColumns(10);
		txtEmailOperator.setBounds(199, 186, 96, 20);
		contentPane.add(txtEmailOperator);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(199, 161, 96, 20);
		contentPane.add(txtUsername);
		
		txtPw = new JTextField();
		txtPw.setColumns(10);
		txtPw.setBounds(195, 137, 171, 19);
		contentPane.add(txtPw);
	}

}
