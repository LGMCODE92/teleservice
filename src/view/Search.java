package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import domain.CallLog;
import domain.Medicament;
import domain.Person;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Search extends JFrame {

	// declaración de variables
	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTextField txtSearch;
	private JButton btnNewButton_1;

	/**
	 * Create the frame.
	 */
	public Search() {

		setTitle("Search");
		Image img = new ImageIcon(getClass().getResource("../images/login.png")).getImage();
		setIconImage(img);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// centrar el frame en la pantalla
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int height = pantalla.height;
		int width = pantalla.width;
		setBounds(width / 4, height / 4, 800, 700);
		setLocationRelativeTo(null);

		// no maximizar
		setResizable(false);

		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		// para limitar la longitud a 10 caracteres
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (txtSearch.getText().length() >= 10) {
					e.consume();
				}
			}
		});
		txtSearch.setBounds(256, 255, 223, 50);
		contentPane.add(txtSearch);
		// txtSearch.setColumns(10);

		JLabel searchLabel = new JLabel("Introduzca el dni o teléfono del paciente o de algún dato de contacto:");
		searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		searchLabel.setBounds(76, 178, 634, 66);
		contentPane.add(searchLabel);

		JButton searchButton = new JButton("") {
			@Override
			public JToolTip createToolTip() {
				JToolTip toolTip = super.createToolTip();
				toolTip.setBackground(Color.LIGHT_GRAY);
				toolTip.setFont(new Font("Tahoma", Font.PLAIN, 16));
				toolTip.setBorder(null);
				return toolTip;
			}
		};
		searchButton.setBorder(null);
		searchButton.setBackground(new Color(240, 240, 240));
		searchButton.setToolTipText("Buscar");
		searchButton.setIcon(new ImageIcon(getClass().getResource("../images/search.png")));
		searchButton.setToolTipText("Buscar");
		searchButton.setBounds(489, 255, 53, 50);

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtSearch.getText().length() == 9) {
					//
					//
					if (txtSearch.getText().toUpperCase().matches("[0-9]{9}")) {
						// seteamos TF
						System.out.println("TF");
					} else if (txtSearch.getText().substring(0, 8).matches("[0-9]{8}")
							&& txtSearch.getText().substring(8, 9).matches("[A-Z]*")) {
						// seteamos DNI
						System.out.println("DNI");
					}
					Person detalle = null; // llamamos al controlador que nos busca el detalle
					detalle = getPersonMock();

					if (null == detalle.getError()) {
						User frame = new User(detalle);
						frame.setVisible(true);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, detalle.getError());
					}

				} else {
					JOptionPane.showMessageDialog(null, "DNI o TF incorrecto");
				}

			}

		});
		contentPane.add(searchButton);
		
		
		
		
		String config=" ALTA OPERADOR";
		JButton userRegisterButton = new JButton(config);
		userRegisterButton.setBorder(null);
		userRegisterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		userRegisterButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		userRegisterButton.setForeground(new Color(0, 0, 0));
		userRegisterButton.setBackground(new Color(245, 245, 245));
		userRegisterButton.setIcon(new ImageIcon(getClass().getResource("../images/config.png")));
		userRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ir al alta y cerrar
				JOptionPane.showMessageDialog(null, "ir a registrarse");
			}
		});
		userRegisterButton.setBounds(104, 411, 215, 38);
		contentPane.add(userRegisterButton);
		
		
		JButton operatorRegisterButton = new JButton(" ALTA USUARIO");
		operatorRegisterButton.setForeground(Color.BLACK);
		operatorRegisterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		operatorRegisterButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		operatorRegisterButton.setBorder(null);
		operatorRegisterButton.setBackground(new Color(245, 245, 245));
		userRegisterButton.setIcon(new ImageIcon(getClass().getResource("../images/config.png")));
		userRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ir al alta y cerrar
				JOptionPane.showMessageDialog(null, "ir a registrarse");
			}
		});

		operatorRegisterButton.setBounds(398, 411, 215, 38);
		contentPane.add(operatorRegisterButton);
	}
	
	private Person getPersonMock() {
		Person response = new Person();
		response.setAddress("C/ Pantoja");
		response.setCivilStatus("Soltero");
		response.setDateBirth("25/08/1991");
		response.setDocument("12345678K");
		response.setHealthStatus("Enfermo");
		response.setHelpHome("SI");
		response.setTypeUser("P");
		response.setTf("678987676");
		response.setSex("Sr");
		
		Person contact = new Person();
		contact.setTypeUser("C");
		contact.setUserName("Geremias");
		contact.setTf("654654654");
		contact.setDocument("12345678G");
		List<Person> contactlist = new ArrayList();
		contactlist.add(contact);
		Medicament medicament = new Medicament();
		medicament.setName("Ibuprofeno");
		medicament.setBaseMedicine("P. Activo 1");
		medicament.setLaboratory("Cinfa");
		medicament.setAmount(3);
		
		Medicament medicament2 = new Medicament();
		medicament2.setName("Paracetamol");
		medicament2.setBaseMedicine("P. Activo 1");
		medicament2.setLaboratory("Pfizer");
		medicament2.setAmount(5);
		
		List<Medicament> medicamentlist = new ArrayList();
		medicamentlist.add(medicament);
		medicamentlist.add(medicament2);
		CallLog callLog = new CallLog();
		callLog.setCallReason("Emergencia");
		callLog.setContactPerson(getName());
		callLog.setDate(new Date(01/01/2022));
		callLog.setDocument("12345678G");
		List<CallLog> callLoglist = new ArrayList();
		callLoglist.add(callLog);
		
		response.setCallLogList(callLoglist);
		response.setMedicamentList(medicamentlist);
		response.setContactsList(contactlist);
		return response;
	}
}
