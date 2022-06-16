package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import domain.Person;

public class ContactInformation extends JFrame {

	//declaración variables
	private Person person;


	private JPanel contentPane;
	private JTextField txtNameOperator;
	private JTextField txtSurnameOperator;
	private JTextField txtDniOperator;
	private JTextField txtEmailOperator;
	private JTextField txtRelationshipContactInfo;


	/**
	 * Create the frame.
	 */
	public ContactInformation(Person contract, Person person) {
		this.person = contract;
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
		
		
		setTitle("Contact information");
		Image img = new ImageIcon(getClass().getResource("../images/login.png")).getImage();
		setIconImage(img);
		contentPane.setLayout(null);
		
		
		JLabel nameOperator = new JLabel("Nombre");
		nameOperator.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameOperator.setBounds(288, 172, 86, 31);

		contentPane.add(nameOperator);
		
		JLabel surnameOperator = new JLabel("Apellidos");
		surnameOperator.setFont(new Font("Tahoma", Font.PLAIN, 20));
		surnameOperator.setBounds(282, 231, 93, 31);
		contentPane.add(surnameOperator);
		
		JLabel dniOperator = new JLabel("DNI");
		dniOperator.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dniOperator.setBounds(318, 292, 50, 31);
		contentPane.add(dniOperator);
		
		String tlfn="Teléfono";
		JLabel tfOperator = new JLabel(tlfn);
		tfOperator.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tfOperator.setBounds(282, 346, 86, 38);
		contentPane.add(tfOperator);
		
		JLabel relationshipContactInfo = new JLabel("Parentesco");
		relationshipContactInfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		relationshipContactInfo.setBounds(263, 404, 105, 39);
		contentPane.add(relationshipContactInfo);
		
		String pw= "Contraseña";
			
		
		
		txtNameOperator = new JTextField();
		txtNameOperator.setBounds(378, 172, 145, 31);
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
		txtSurnameOperator.setBounds(378, 231, 145, 31);
		contentPane.add(txtSurnameOperator);
		
		txtDniOperator = new JTextField();
		txtDniOperator.setColumns(10);
		txtDniOperator.setBorder(null);
		txtDniOperator.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDniOperator.setBackground(Color.WHITE);
		txtDniOperator.setBounds(378, 292, 145, 31);
		contentPane.add(txtDniOperator);
		
		txtEmailOperator = new JTextField();
		txtEmailOperator.setColumns(10);
		txtEmailOperator.setBorder(null);
		txtEmailOperator.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtEmailOperator.setBackground(Color.WHITE);
		txtEmailOperator.setBounds(378, 346, 145, 31);
		contentPane.add(txtEmailOperator);
		
		txtRelationshipContactInfo = new JTextField();
		txtRelationshipContactInfo.setColumns(10);
		txtRelationshipContactInfo.setBorder(null);
		txtRelationshipContactInfo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtRelationshipContactInfo.setBackground(Color.WHITE);
		txtRelationshipContactInfo.setBounds(378, 404, 145, 31);
		contentPane.add(txtRelationshipContactInfo);
		if (contract != null) {
			txtNameOperator.setText(contract.getUserName());
			txtSurnameOperator.setText(contract.getUserSurname());
			txtDniOperator.setText(contract.getDocument());
			txtEmailOperator.setText(contract.getTf());
		}
		//txtPw.addKeyListener(redirectEvent);
		
		JLabel searchLabel = new JLabel("Introduzca los datos de la persona de contacto");
		searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		searchLabel.setBounds(168, 79, 450, 66);
		contentPane.add(searchLabel);
		
		JButton saveButton = new JButton(" Guardar contacto");
		saveButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveButton.setBorder(null);
		saveButton.setFocusable(false);
		saveButton.setIcon(new ImageIcon(getClass().getResource("../images/save.png")));
		saveButton.setBackground(new Color(244,247,255));
		saveButton.setBounds(225, 518, 335, 38);
		contentPane.add(saveButton);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(new Color(0, 169, 176));
		separator.setBounds(378, 203, 145, 2);
		contentPane.add(separator);
		
		JSeparator separator2 = new JSeparator();
		separator2.setForeground(Color.WHITE);
		separator2.setBackground(new Color(0, 169, 176));
		separator2.setBounds(378, 262, 145, 2);
		contentPane.add(separator2);
		
		JSeparator separator3 = new JSeparator();
		separator3.setForeground(Color.WHITE);
		separator3.setBackground(new Color(0, 169, 176));
		separator3.setBounds(378, 323, 145, 2);
		contentPane.add(separator3);
		
		JSeparator separator4 = new JSeparator();
		separator4.setForeground(Color.WHITE);
		separator4.setBackground(new Color(0, 169, 176));
		separator4.setBounds(378, 377, 145, 2);
		contentPane.add(separator4);
		
		JSeparator separator5 = new JSeparator();
		separator5.setForeground(Color.WHITE);
		separator5.setBackground(new Color(0, 169, 176));
		separator5.setBounds(378, 435, 145, 2);
		contentPane.add(separator5);
		
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
				User frame = new User(person);
				frame.setVisible(true);
				dispose();
			}
		});
		contentPane.add(returnButton);

	}

}
