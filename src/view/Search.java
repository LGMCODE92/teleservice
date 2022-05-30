package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class Search extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;


	//comentario DOBLE, doble es lo que yo quiero
	
	
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
		
		txtBuscar = new JTextField();
		txtBuscar.setText("BUSCAR");
		txtBuscar.setBounds(147, 105, 96, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
	}
}
