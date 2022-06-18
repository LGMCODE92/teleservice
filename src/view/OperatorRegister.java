package view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.PersonController;

import domain.Person;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JSeparator;
import javax.swing.JToolTip;

public class OperatorRegister extends JFrame {

	//declaración variables
	private JPanel contentPane;
	private JTextField txtNameOperator;
	private JTextField txtSurnameOperator;
	private JTextField txtDniOperator;
	private JTextField txtEmailOperator;
	private JTextField txtUsername;
	private JTextField txtPw;
	
	private PersonController personController;





	/**
	 * Create the frame.
	 */
	public OperatorRegister(Person userLogin) {
		personController = new PersonController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(244,247,255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		// centrar el frame en la pantalla
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int height = pantalla.height;
		int width = pantalla.width;
		setBounds(width / 4, height / 4, 800, 700);
		setLocationRelativeTo(null);
		
		
		
		
		setTitle("Operator register");
		Image img = new ImageIcon(getClass().getResource("../images/login.png")).getImage();
		setIconImage(img);
		contentPane.setLayout(null);
		
		
		JLabel nameOperator = new JLabel("Nombre");
		nameOperator.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameOperator.setBounds(289, 140, 86, 31);
		contentPane.add(nameOperator);
		
		JLabel surnameOperator = new JLabel("Apellidos");
		surnameOperator.setFont(new Font("Tahoma", Font.PLAIN, 20));
		surnameOperator.setBounds(282, 199, 93, 31);
		contentPane.add(surnameOperator);
		
		JLabel dniOperator = new JLabel("DNI");
		dniOperator.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dniOperator.setBounds(323, 260, 50, 31);
		contentPane.add(dniOperator);
		
		String tlfn="Teléfono";
		JLabel tfOperator = new JLabel(tlfn);
		tfOperator.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfOperator.setBounds(282, 316, 86, 38);
		contentPane.add(tfOperator);
		
		JLabel userOperator = new JLabel("Nombre de usuario");
		userOperator.setFont(new Font("Tahoma", Font.PLAIN, 20));
		userOperator.setBounds(189, 379, 190, 39);
		contentPane.add(userOperator);
		
		String pw= "Contraseña";
		JLabel pwOperator = new JLabel(pw);
		pwOperator.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pwOperator.setBounds(257, 439, 111, 38);
		contentPane.add(pwOperator);
			
		
		
		txtNameOperator = new JTextField();
		txtNameOperator.setBounds(378, 141, 145, 31);
		txtNameOperator.setBorder(null);
		contentPane.add(txtNameOperator);
		txtNameOperator.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNameOperator.setBackground(Color.WHITE);
		txtNameOperator.setColumns(10);
		
		txtSurnameOperator = new JTextField();
		txtSurnameOperator.setColumns(10);
		txtSurnameOperator.setBorder(null);
		txtSurnameOperator.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSurnameOperator.setBackground(Color.WHITE);
		txtSurnameOperator.setBounds(378, 200, 145, 31);
		contentPane.add(txtSurnameOperator);
		
		txtDniOperator = new JTextField();
		txtDniOperator.setColumns(10);
		txtDniOperator.setBorder(null);
		txtDniOperator.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDniOperator.setBackground(Color.WHITE);
		txtDniOperator.setBounds(378, 261, 145, 31);
		contentPane.add(txtDniOperator);
		
		txtEmailOperator = new JTextField();
		txtEmailOperator.setColumns(10);
		txtEmailOperator.setBorder(null);
		txtEmailOperator.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtEmailOperator.setBackground(Color.WHITE);
		txtEmailOperator.setBounds(378, 321, 145, 31);
		contentPane.add(txtEmailOperator);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBorder(null);
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtUsername.setBackground(Color.WHITE);
		txtUsername.setBounds(378, 384, 145, 31);
		contentPane.add(txtUsername);
		
		txtPw = new JTextField();
		txtPw.setColumns(10);
		txtPw.setBorder(null);
		txtPw.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPw.setBackground(Color.WHITE);
		txtPw.setBounds(378, 444, 145, 31);
		contentPane.add(txtPw);
		//txtPw.addKeyListener(redirectEvent);
		
		JLabel searchLabel = new JLabel("Introduzca los datos del operador");
		searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		searchLabel.setBounds(231, 49, 324, 66);
		contentPane.add(searchLabel);
		
		JButton saveButton = new JButton(" Guardar operador");
		saveButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		saveButton.setFocusable(false);
		saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveButton.setBorder(null);
		saveButton.setIcon(new ImageIcon(getClass().getResource("../images/save.png")));
		saveButton.setBackground(new Color(244,247,255));
		saveButton.setBounds(268, 535, 250, 38);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personController = new PersonController();
				Person request = new Person();
				request.setPassword(txtPw.getText());
				request.setUserSurname(txtSurnameOperator.getText());
				request.setUserName(txtNameOperator.getText());
				request.setTf(txtEmailOperator.getText());
				request.setDocument(txtDniOperator.getText());
				request.setOperator(txtUsername.getText());
				request.setTypeUser("O");
				
				String response = "KO";
                response = personController.savePerson(request);
				

				JOptionPane.showMessageDialog(null, response);

				

			}
		});
		contentPane.add(saveButton);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(new Color(0, 169, 176));
		separator.setBounds(378, 172, 145, 2);
		contentPane.add(separator);
		
		JSeparator separator2 = new JSeparator();
		separator2.setForeground(Color.WHITE);
		separator2.setBackground(new Color(0, 169, 176));
		separator2.setBounds(378, 231, 145, 2);
		contentPane.add(separator2);
		
		JSeparator separator3 = new JSeparator();
		separator3.setForeground(Color.WHITE);
		separator3.setBackground(new Color(0, 169, 176));
		separator3.setBounds(378, 292, 145, 2);
		contentPane.add(separator3);
		
		JSeparator separator4 = new JSeparator();
		separator4.setForeground(Color.WHITE);
		separator4.setBackground(new Color(0, 169, 176));
		separator4.setBounds(378, 352, 145, 2);
		contentPane.add(separator4);
		
		JSeparator separator5 = new JSeparator();
		separator5.setForeground(Color.WHITE);
		separator5.setBackground(new Color(0, 169, 176));
		separator5.setBounds(378, 415, 145, 2);
		contentPane.add(separator5);
		
		JSeparator separator6 = new JSeparator();
		separator6.setForeground(Color.WHITE);
		separator6.setBackground(new Color(0, 169, 176));
		separator6.setBounds(378, 475, 145, 2);
		contentPane.add(separator6);
		
		JButton returnButton = new JButton("") {
			@Override
			public JToolTip createToolTip() {
				JToolTip toolTip = super.createToolTip();
				toolTip.setBackground(Color.LIGHT_GRAY);
				toolTip.setFont(new Font("Tahoma", Font.PLAIN, 16));
				toolTip.setBorder(null);
				return toolTip;
			}
		};
		returnButton.setBorder(null);
		returnButton.setToolTipText("Volver");
		returnButton.setBackground(new Color(244, 247, 255));
		returnButton.setBounds(10, 11, 43, 38);
		returnButton.setFocusable(false);
		returnButton.setIcon(new ImageIcon(getClass().getResource("../images/return.png")));
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search frame = new Search(userLogin);
				frame.setVisible(true);
				dispose();
			}
		});
		contentPane.add(returnButton);

	}
}
