package view;

import java.awt.Color;


import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import controller.TeleserviceController;
import domain.Person;

public class Login extends JFrame {
	
	
	//declaración variables
	public Person person;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField passwordField;
	private TeleserviceController teleserviceController;

	/**
	 * Create the frame.
	 */
	public Login() {
		
		//evento para tecla enter
		KeyListener redirectEvent = new KeyListener() {

			public void keyTyped(KeyEvent e) {
				// Aqui no funcionara
			}

			public void keyPressed(KeyEvent e) {
				if (e.getExtendedKeyCode() == KeyEvent.VK_ENTER) {

					// enterButton.doClick();
					Person res = teleserviceController.login(txtUser.getText(),
							new String(passwordField.getPassword()));
					if (null == res.getError()) {

						Search frame = new Search(res);
						frame.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, res.getError());
					}

				}
			}

			public void keyReleased(KeyEvent e) {
				// Aqui no funcionará
			}
		};
		teleserviceController = new TeleserviceController();
		// cerrar ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// titulo e icono de la ventana
		setTitle("Login");
		Image img = new ImageIcon(getClass().getResource("/img/login.png")).getImage();
		setIconImage(img);

		
		// centrar el frame en la pantalla
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int height = pantalla.height;
		int width = pantalla.width;
		setBounds(width / 4, height / 4, 800, 700);
		setLocationRelativeTo(null);

		// no maximizar
		setResizable(false);

		// componentes
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(244,247,255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String usuario = "Usuario";
		JLabel user = new JLabel(usuario);
		user.setFont(new Font("Tahoma", Font.PLAIN, 20));
		user.setBounds(210, 315, 365, 52);
		contentPane.add(user);

		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtUser.setBorder(null);
		txtUser.setBackground(Color.WHITE);
		txtUser.setBounds(210, 363, 365, 40);
		txtUser.addKeyListener(redirectEvent);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		String contraseña = "Contraseña";
		JLabel password = new JLabel(contraseña);
		password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		password.setBounds(210, 425, 365, 58);
		contentPane.add(password);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField.setBorder(null);
		passwordField.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			}
		});
		passwordField.setBounds(210, 475, 365, 38);
		passwordField.addKeyListener(redirectEvent);
		contentPane.add(passwordField);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/img/user.png")));
		lblNewLabel.setBounds(250, 11, 285, 313);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(new Color(0, 169, 176));
		separator.setBounds(210, 404, 365, 2);
		contentPane.add(separator);

		JSeparator separator2 = new JSeparator();
		separator2.setForeground(Color.WHITE);
		separator2.setBackground(new Color(0, 169, 176));
		separator2.setBounds(210, 513, 365, 2);
		contentPane.add(separator2);

		String entrar = "Entrar";
		JButton enterButton = new JButton(entrar) {
			@Override
			public JToolTip createToolTip() {
				JToolTip toolTip = super.createToolTip();
				toolTip.setBackground(Color.LIGHT_GRAY);
				toolTip.setFont(new Font("Tahoma", Font.PLAIN, 16));
				toolTip.setBorder(null);
				return toolTip;
			}
		};
		enterButton.setToolTipText("Iniciar sesión");
		enterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		enterButton.setBorder(null);
		enterButton.setFocusable(false);
		enterButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		enterButton.setBackground(new Color(244,247,255));
		enterButton.setIcon(new ImageIcon(getClass().getResource("/img/enter.png")));
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Person res = teleserviceController.login(txtUser.getText(), new String(passwordField.getPassword()));
				if (null == res.getError()) {
					Search frame = new Search(res);
					frame.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, res.getError());
				}

			}

		});
		enterButton.addKeyListener(redirectEvent);
		enterButton.setBounds(210, 554, 365, 38);
		contentPane.add(enterButton);

	}
}
