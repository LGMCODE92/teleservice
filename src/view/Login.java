package view;

import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Cursor;
import controller.TeleserviceController;
import domain.Person;
import view.Search;
public class Login extends JFrame {
    public Person person;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField passwordField;
    private TeleserviceController teleserviceController;


    
    
    
	/**
	 * Create the frame.
	 */
	public Login() {
        teleserviceController = new TeleserviceController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Login");
		Image img = new ImageIcon(getClass().getResource("../images/login.png")).getImage();
		setIconImage(img);
		setBounds(100, 100, 830, 700);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel user = new JLabel("Usuario:");
		user.setFont(new Font("Times New Roman", Font.BOLD, 20));
		user.setBounds(222, 314, 365, 52);
		contentPane.add(user);
		
		txtUser = new JTextField();
		txtUser.setBorder(null);
		txtUser.setBackground(Color.WHITE);
		txtUser.setBounds(222, 363, 365, 38);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel password = new JLabel("Contrase\u00F1a:");
		password.setFont(new Font("Times New Roman", Font.BOLD, 20));
		password.setBounds(222, 428, 339, 58);
		contentPane.add(password);

		
		passwordField = new JPasswordField();
		passwordField.setBorder(null);
		passwordField.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			}
		});
		passwordField.setBounds(222, 475, 365, 38);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/images/user.png")));
		lblNewLabel.setBounds(284, 11, 256, 283);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.DARK_GRAY);
		separator.setBounds(222, 404, 365, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.DARK_GRAY);
		separator_1.setBounds(222, 513, 365, 2);
		contentPane.add(separator_1);
		
		JButton enterButton = new JButton("Entrar");
		enterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		enterButton.setBorder(null);
		enterButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		enterButton.setBackground(new Color(245, 245, 245));
		enterButton.setIcon(new ImageIcon(getClass().getResource("../images/enter.png")));
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                Person res = teleserviceController.login(txtUser.getText(),new String(passwordField.getPassword()));
                if ( null == res.getError()){
                    JOptionPane.showMessageDialog(null, "Go to search");
                   
					Search frame = new Search();
					frame.setVisible(true);
					dispose();
                }else {
                    JOptionPane.showMessageDialog(null, res.getError());
                }
				
			}
		});
		enterButton.setBounds(284, 554, 215, 38);
		contentPane.add(enterButton);
		// TODO: METERLO EN LA SIGUIENTE PANTALLA
		/*
		JButton newButton = new JButton("Registrarse");
		newButton.setBorder(null);
		newButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		newButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		newButton.setForeground(new Color(0, 0, 0));
		newButton.setBackground(new Color(245, 245, 245));
		newButton.setToolTipText("Registrarse");
		newButton.setIcon(new ImageIcon(getClass().getResource("../images/add.png")));
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ir al alta y cerrar
				JOptionPane.showMessageDialog(null, "ir a registrarse");
			}
		});
		newButton.setBounds(284, 613, 215, 38);
		contentPane.add(newButton);
		
 */

	}
}
