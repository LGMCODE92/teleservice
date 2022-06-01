package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Search extends JFrame {

	private JPanel contentPane;
<<<<<<< HEAD
	private JTextField txtBuscar;	
=======
	private JTextField txtUser;
	private JButton btnNewButton_1;


	//comentario DOBLE
	
>>>>>>> origin/Sofia
	
	/**
	 * Create the frame.
	 */
	public Search() {
		setTitle("Search");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUser = new JTextField();
		txtUser.setBounds(96, 83, 96, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Introduzca el dni o teléfono del paciente o de algún dato de contacto:");
		lblNewLabel.setBounds(52, 58, 362, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("BUSCAR");
		btnNewButton.setBounds(228, 82, 89, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Configuraci\u00F3n");
		btnNewButton_1.setBounds(155, 193, 127, 23);
		contentPane.add(btnNewButton_1);
	}
}
