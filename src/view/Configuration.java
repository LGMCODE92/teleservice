package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class Configuration extends JFrame {

	private JPanel contentPane;

	private JComboBox<String> comboSex;
	private JComboBox<String> tomas;
	private JTextField txtTomasDiarias;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Configuration frame = new Configuration();
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
	public Configuration() {
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
		
		setTitle("Configuration");
		Image img = new ImageIcon(getClass().getResource("../images/login.png")).getImage();
		setIconImage(img);
		contentPane.setLayout(null);
		
		JLabel reasonCall_1 = new JLabel("Configuraci\u00F3n tomas diarias");
		reasonCall_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		reasonCall_1.setBounds(213, 88, 262, 31);
		contentPane.add(reasonCall_1);
		
		JLabel reasonCall_1_1 = new JLabel("Configuraci\u00F3n sexo");
		reasonCall_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		reasonCall_1_1.setBounds(213, 216, 190, 31);
		contentPane.add(reasonCall_1_1);
		
		JButton btnDeleteUser = new JButton("Borrar");
		btnDeleteUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDeleteUser.setFocusable(false);
		btnDeleteUser.setBackground(new Color(216, 247, 248));
		btnDeleteUser.setBounds(599, 132, 90, 36);
		contentPane.add(btnDeleteUser);
		
		
		String sexo[]={"H", "M"};
		comboSex=new JComboBox<String>();
		comboSex.setBounds(396, 217, 145, 31);
        getContentPane().add(comboSex);
        for(int i=0;i<sexo.length;i++) {
        	comboSex.addItem(String.valueOf(sexo[i]).toString());
        }
		comboSex.setBorder(null);
		comboSex.setBackground(Color.WHITE);
		comboSex.setFocusable(false);
		
		comboSex.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		

		String tomasDiarias[]={"1", "2", "3", "4", "5"};
		tomas=new JComboBox<String>();
		tomas.setBounds(474, 89, 145, 31);
       
        for(int i=0;i<tomasDiarias.length;i++) {
        	tomas.addItem(String.valueOf(tomasDiarias[i]).toString());
        }
		tomas.setBorder(null);
		tomas.setBackground(Color.WHITE);
		tomas.setFocusable(false);
		
		tomas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(tomas);
		
		
		

		String estadoCivil[]={"Soltero/a", "Casado/a", "Divorciado/a", "Viudo/a"};
		comboSex=new JComboBox<String>();
		comboSex.setBounds(455, 346, 145, 31);
        getContentPane().add(comboSex);
        for(int i=0;i<estadoCivil.length;i++) {
        	comboSex.addItem(String.valueOf(estadoCivil[i]).toString());
        }
        
		
		
    	
		String ayudaDomicilio[]={"Sí", "No"};
		comboSex=new JComboBox<String>();
		comboSex.setBounds(491, 484, 145, 31);
        getContentPane().add(comboSex);
        for(int i=0;i<ayudaDomicilio.length;i++) {
        	comboSex.addItem(String.valueOf(ayudaDomicilio[i]).toString());
        }
        
        
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(new Color(0, 169, 176));
		separator.setBounds(302, 165, 145, 2);
		contentPane.add(separator);
		
		txtTomasDiarias = new JTextField();
		txtTomasDiarias.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTomasDiarias.setColumns(10);
		txtTomasDiarias.setBorder(null);
		txtTomasDiarias.setBackground(Color.WHITE);
		txtTomasDiarias.setBounds(302, 134, 145, 31);
		contentPane.add(txtTomasDiarias);
		
		JSeparator separator2 = new JSeparator();
		separator2.setForeground(Color.WHITE);
		separator2.setBackground(new Color(0, 169, 176));
		separator2.setBounds(302, 295, 145, 2);
		contentPane.add(separator2);
		
		JButton btnAadir = new JButton("A\u00F1adir");
		btnAadir.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAadir.setFocusable(false);
		btnAadir.setBackground(new Color(216, 247, 248));
		btnAadir.setBounds(480, 132, 90, 36);
		contentPane.add(btnAadir);
		
		JLabel reasonCall_1_2 = new JLabel("Elemento a configurar");
		reasonCall_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		reasonCall_1_2.setBounds(77, 133, 215, 31);
		contentPane.add(reasonCall_1_2);
		
		JLabel reasonCall_1_1_1 = new JLabel("Configuraci\u00F3n estado civil");
		reasonCall_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		reasonCall_1_1_1.setBounds(210, 346, 237, 31);
		contentPane.add(reasonCall_1_1_1);
		
		JLabel reasonCall_1_1_2 = new JLabel("Configuraci\u00F3n ayuda domicilio");
		reasonCall_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		reasonCall_1_1_2.setBounds(213, 480, 280, 31);
		contentPane.add(reasonCall_1_1_2);
		
		JLabel reasonCall_1_2_1 = new JLabel("Elemento a configurar");
		reasonCall_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		reasonCall_1_2_1.setBounds(77, 265, 215, 31);
		contentPane.add(reasonCall_1_2_1);
		
		JLabel reasonCall_1_2_2 = new JLabel("Elemento a configurar");
		reasonCall_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		reasonCall_1_2_2.setBounds(77, 388, 215, 31);
		contentPane.add(reasonCall_1_2_2);
		
		JLabel reasonCall_1_2_3 = new JLabel("Elemento a configurar");
		reasonCall_1_2_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		reasonCall_1_2_3.setBounds(77, 524, 215, 31);
		contentPane.add(reasonCall_1_2_3);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setBorder(null);
		textField.setBackground(Color.WHITE);
		textField.setBounds(302, 266, 145, 31);
		contentPane.add(textField);
		
		JButton btnAadir_1 = new JButton("A\u00F1adir");
		btnAadir_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAadir_1.setFocusable(false);
		btnAadir_1.setBackground(new Color(216, 247, 248));
		btnAadir_1.setBounds(480, 264, 90, 36);
		contentPane.add(btnAadir_1);
		
		JButton btnDeleteUser_1 = new JButton("Borrar");
		btnDeleteUser_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDeleteUser_1.setFocusable(false);
		btnDeleteUser_1.setBackground(new Color(216, 247, 248));
		btnDeleteUser_1.setBounds(599, 264, 90, 36);
		contentPane.add(btnDeleteUser_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBorder(null);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(302, 389, 145, 31);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_2.setColumns(10);
		textField_2.setBorder(null);
		textField_2.setBackground(Color.WHITE);
		textField_2.setBounds(302, 525, 145, 31);
		contentPane.add(textField_2);
		
		JButton btnAadir_2 = new JButton("A\u00F1adir");
		btnAadir_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAadir_2.setFocusable(false);
		btnAadir_2.setBackground(new Color(216, 247, 248));
		btnAadir_2.setBounds(480, 387, 90, 36);
		contentPane.add(btnAadir_2);
		
		JButton btnAadir_3 = new JButton("A\u00F1adir");
		btnAadir_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAadir_3.setFocusable(false);
		btnAadir_3.setBackground(new Color(216, 247, 248));
		btnAadir_3.setBounds(480, 524, 90, 36);
		contentPane.add(btnAadir_3);
		
		JButton btnDeleteUser_2 = new JButton("Borrar");
		btnDeleteUser_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDeleteUser_2.setFocusable(false);
		btnDeleteUser_2.setBackground(new Color(216, 247, 248));
		btnDeleteUser_2.setBounds(599, 387, 90, 36);
		contentPane.add(btnDeleteUser_2);
		
		JButton btnDeleteUser_3 = new JButton("Borrar");
		btnDeleteUser_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDeleteUser_3.setFocusable(false);
		btnDeleteUser_3.setBackground(new Color(216, 247, 248));
		btnDeleteUser_3.setBounds(599, 524, 90, 36);
		contentPane.add(btnDeleteUser_3);
		
		JSeparator separator3 = new JSeparator();
		separator3.setForeground(Color.WHITE);
		separator3.setBackground(new Color(0, 169, 176));
		separator3.setBounds(302, 420, 145, 2);
		contentPane.add(separator3);
		
		JSeparator separator4 = new JSeparator();
		separator4.setForeground(Color.WHITE);
		separator4.setBackground(new Color(0, 169, 176));
		separator4.setBounds(302, 556, 145, 2);
		contentPane.add(separator4);
		
	}
}
