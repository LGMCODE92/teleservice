package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import controller.ConfigurationController;
import controller.PersonController;
import domain.CallLog;
import domain.ConfigurationEnum;
import domain.Medicament;
import domain.Person;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class UserRegister extends JFrame {
	private PersonController personController;
	private ConfigurationController configurationController;
	// declaración variables
	private JPanel contentPane;
	private JTextField txtCivilStatus;
	private JTextField txtCivilStatusUser;
	private JTextField txtNameUser;
	private JTextField txtSurnameUser;
	private JTextField txtSexUser;
	private JTextField txtDateBirthUser;
	private JTextField txtHealthStatusUser;
	private JLabel searchLabel;
	private JTextField txtDniUser;
	private JTextField txtTfUser;
	private JTextField txtAdressUser;
	private JTextField txtHelpHomeUser;
	private JTextField txtWarningUser;

	private JComboBox<String> comboSex;
	private JComboBox<String> helpHome;
	private JComboBox<String> civilStatus;

	/**
	 * Create the frame.
	 */
	public UserRegister(Person person, Person userLogin) {
		configurationController = new ConfigurationController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(244, 247, 255));
		setContentPane(contentPane);

		// centrar el frame en la pantalla
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int height = pantalla.height;
		int width = pantalla.width;
		setBounds(width / 4, height / 4, 800, 700);
		setLocationRelativeTo(null);

		setTitle("User register");
		Image img = new ImageIcon(getClass().getResource("../images/login.png")).getImage();
		setIconImage(img);
		contentPane.setLayout(null);

		searchLabel = new JLabel("Introduzca los datos del usuario");
		searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		searchLabel.setBounds(241, 38, 304, 66);
		contentPane.add(searchLabel);

		JLabel nameUser = new JLabel("Nombre");
		nameUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameUser.setBounds(145, 140, 86, 31);
		contentPane.add(nameUser);

		JLabel surnameUser = new JLabel("Apellidos");
		surnameUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		surnameUser.setBounds(141, 199, 93, 31);
		contentPane.add(surnameUser);

		String tfUser = "Teléfono";

		JLabel healthStatusUser = new JLabel("Estado de salud");
		healthStatusUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		healthStatusUser.setBounds(75, 379, 154, 39);
		contentPane.add(healthStatusUser);

		JLabel sexUser = new JLabel("Sexo");
		sexUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		sexUser.setBounds(175, 260, 56, 31);
		contentPane.add(sexUser);

		JLabel civilStatusUser = new JLabel("Estado civil");
		civilStatusUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		civilStatusUser.setBounds(461, 260, 111, 31);
		contentPane.add(civilStatusUser);
		Object estadoCivil[] =  configurationController
				.getConfigurationByCode(ConfigurationEnum.CIVIL_STATUS.name()).getConfigList().stream()
				.map(x -> x.getValue()).toArray();
		// String estadoCivil[] = { "Soltero/a", "Casado/a", "Divorciado/a", "Viudo/a"
		// };
		civilStatus = new JComboBox<String>();
		civilStatus.setBounds(578, 261, 145, 31);
		getContentPane().add(civilStatus);
		for (int i = 0; i < estadoCivil.length; i++) {
			civilStatus.addItem(String.valueOf(estadoCivil[i]).toString());
		}

		civilStatus.setBorder(null);
		civilStatus.setBackground(Color.WHITE);
		civilStatus.setFocusable(false);
		civilStatus.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtSurnameUser = new JTextField();
		txtSurnameUser.setColumns(10);
		txtSurnameUser.setBorder(null);
		txtSurnameUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtSurnameUser.setBackground(Color.WHITE);
		txtSurnameUser.setBounds(230, 200, 145, 31);
		contentPane.add(txtSurnameUser);
		Object sexo[] = configurationController.getConfigurationByCode(ConfigurationEnum.SEX.name())
				.getConfigList().stream().map(x -> x.getValue()).toArray();
		// String sexo[] = { "H", "M" };
		comboSex = new JComboBox<String>();
		comboSex.setBounds(230, 261, 145, 31);
		getContentPane().add(comboSex);
		for (int i = 0; i < sexo.length; i++) {
			comboSex.addItem(String.valueOf(sexo[i]).toString());
		}
		comboSex.setBorder(null);
		comboSex.setBackground(Color.WHITE);
		comboSex.setFocusable(false);

		comboSex.setFont(new Font("Tahoma", Font.PLAIN, 18));

		txtDateBirthUser = new JTextField();
		txtDateBirthUser.setColumns(10);
		txtDateBirthUser.setBorder(null);
		txtDateBirthUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDateBirthUser.setBackground(Color.WHITE);
		txtDateBirthUser.setBounds(230, 321, 145, 31);
		contentPane.add(txtDateBirthUser);

		txtHealthStatusUser = new JTextField();
		txtHealthStatusUser.setColumns(10);
		txtHealthStatusUser.setBorder(null);
		txtHealthStatusUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtHealthStatusUser.setBackground(Color.WHITE);
		txtHealthStatusUser.setBounds(230, 384, 145, 31);
		contentPane.add(txtHealthStatusUser);

		txtDniUser = new JTextField();
		txtDniUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDniUser.setColumns(10);
		txtDniUser.setBorder(null);
		txtDniUser.setBackground(Color.WHITE);
		txtDniUser.setBounds(578, 141, 145, 31);
		contentPane.add(txtDniUser);

		txtTfUser = new JTextField();
		txtTfUser.addKeyListener(new KeyAdapter() {

		});
		txtTfUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtTfUser.setColumns(10);
		txtTfUser.setBorder(null);
		txtTfUser.setBackground(Color.WHITE);
		txtTfUser.setBounds(578, 200, 145, 31);
		contentPane.add(txtTfUser);

		txtNameUser = new JTextField();
		txtNameUser.setBounds(230, 141, 145, 31);
		txtNameUser.setBorder(null);
		contentPane.add(txtNameUser);
		txtNameUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNameUser.setBackground(Color.WHITE);
		txtNameUser.setColumns(10);

		JButton saveButton = new JButton(" Guardar usuario");
		saveButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveButton.setBorder(null);
		saveButton.setFocusable(false);
		saveButton.setIcon(new ImageIcon(getClass().getResource("../images/save.png")));
		saveButton.setBackground(new Color(244, 247, 255));
		saveButton.setBounds(268, 553, 250, 38);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				personController = new PersonController();
				Person request = new Person();
				request.setAddress(txtAdressUser.getText());
				request.setSex(null != comboSex.getSelectedItem() ? comboSex.getSelectedItem().toString() : null);
				request.setCivilStatus(
						null != civilStatus.getSelectedItem() ? civilStatus.getSelectedItem().toString() : null);
				request.setHelpHome(null != helpHome.getSelectedItem() ? helpHome.getSelectedItem().toString() : null);
				request.setUserName(txtNameUser.getText());
				request.setUserSurname(txtSurnameUser.getText());
				request.setDateBirth(txtDateBirthUser.getText());
				request.setHealthStatus(txtHealthStatusUser.getText());
				request.setTf(txtTfUser.getText());
				request.setDocument(txtDniUser.getText());
				request.setWarning(txtWarningUser.getText());
				request.setTypeUser("P");
				String response = "KO";
				if (person != null) {
					response = personController.updatePerson(request);
				} else {
					response = personController.savePerson(request);
				}

				JOptionPane.showMessageDialog(null, response);
				if (response.equals("Persona guardada correctamente")
						|| response.equals("Persona actualizada correctamente")) {
					request.setMedicamentList(new ArrayList<Medicament>());
					request.setContactsList(new ArrayList<Person>());
					request.setCallLogList(new ArrayList<CallLog>());
					User frame = new User(request, userLogin);
					frame.setVisible(true);
					dispose();
				}

			}
		});
		contentPane.add(saveButton);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(new Color(0, 169, 176));
		separator.setBounds(230, 172, 145, 2);
		contentPane.add(separator);

		JSeparator separator2 = new JSeparator();
		separator2.setForeground(Color.WHITE);
		separator2.setBackground(new Color(0, 169, 176));
		separator2.setBounds(230, 231, 145, 2);
		contentPane.add(separator2);

		JSeparator separator3 = new JSeparator();
		separator3.setForeground(Color.WHITE);
		separator3.setBackground(new Color(0, 169, 176));
		separator3.setBounds(230, 292, 145, 2);
		contentPane.add(separator3);

		JSeparator separator4 = new JSeparator();
		separator4.setForeground(Color.WHITE);
		separator4.setBackground(new Color(0, 169, 176));
		separator4.setBounds(230, 352, 145, 2);
		contentPane.add(separator4);

		JSeparator separator5 = new JSeparator();
		separator5.setForeground(Color.WHITE);
		separator5.setBackground(new Color(0, 169, 176));
		separator5.setBounds(230, 415, 145, 2);
		contentPane.add(separator5);

		txtAdressUser = new JTextField();
		txtAdressUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtAdressUser.setColumns(10);
		txtAdressUser.setBorder(null);
		txtAdressUser.setBackground(Color.WHITE);
		txtAdressUser.setBounds(578, 321, 145, 31);
		contentPane.add(txtAdressUser);

		JLabel helpHomeUser = new JLabel("Ayuda a domicilio");
		helpHomeUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		helpHomeUser.setBounds(409, 384, 190, 31);
		contentPane.add(helpHomeUser);
		Object ayudaDomicilio[] = configurationController.getConfigurationByCode(ConfigurationEnum.HELP_HOME.name())
				.getConfigList().stream().map(x -> x.getValue()).toArray();
		// String ayudaDomicilio[] = { "Sí", "No" };
		helpHome = new JComboBox<String>();
		helpHome.setBounds(578, 384, 145, 31);
		getContentPane().add(helpHome);
		for (int i = 0; i < ayudaDomicilio.length; i++) {
			helpHome.addItem(String.valueOf(ayudaDomicilio[i]).toString());
		}
		helpHome.setBorder(null);
		helpHome.setBackground(Color.WHITE);
		helpHome.setFocusable(false);

		helpHome.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JLabel warningUser = new JLabel("Avisos");
		warningUser.setFont(new Font("Tahoma", Font.BOLD, 20));
		warningUser.setBounds(235, 468, 71, 39);
		contentPane.add(warningUser);

		txtWarningUser = new JTextField();
		txtWarningUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtWarningUser.setColumns(10);
		txtWarningUser.setBorder(null);
		txtWarningUser.setBackground(Color.WHITE);
		txtWarningUser.setBounds(313, 473, 238, 31);
		contentPane.add(txtWarningUser);

		JLabel dateBirthUser = new JLabel("Fecha de nacimiento");
		dateBirthUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dateBirthUser.setBounds(39, 320, 190, 31);
		contentPane.add(dateBirthUser);

		JLabel tlfnUser = new JLabel("Tel\u00E9fono");
		tlfnUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tlfnUser.setBounds(486, 195, 86, 38);
		contentPane.add(tlfnUser);

		JLabel adressUser = new JLabel("Direcci\u00F3n actual");
		adressUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		adressUser.setBounds(419, 320, 145, 31);
		contentPane.add(adressUser);

		JLabel dniUser = new JLabel("DNI");
		dniUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dniUser.setBounds(526, 140, 50, 31);
		contentPane.add(dniUser);

		JSeparator separator6 = new JSeparator();
		separator6.setForeground(Color.WHITE);
		separator6.setBackground(new Color(0, 169, 176));
		separator6.setBounds(578, 172, 145, 2);
		contentPane.add(separator6);

		JSeparator separator7 = new JSeparator();
		separator7.setForeground(Color.WHITE);
		separator7.setBackground(new Color(0, 169, 176));
		separator7.setBounds(578, 231, 145, 2);
		contentPane.add(separator7);

		JSeparator separator8 = new JSeparator();
		separator8.setForeground(Color.WHITE);
		separator8.setBackground(new Color(0, 169, 176));
		separator8.setBounds(578, 292, 145, 2);
		contentPane.add(separator8);

		JSeparator separator4_1 = new JSeparator();
		separator4_1.setForeground(Color.WHITE);
		separator4_1.setBackground(new Color(0, 169, 176));
		separator4_1.setBounds(578, 352, 145, 2);
		contentPane.add(separator4_1);

		JSeparator separator4_2 = new JSeparator();
		separator4_2.setForeground(Color.WHITE);
		separator4_2.setBackground(new Color(0, 169, 176));
		separator4_2.setBounds(578, 415, 145, 2);
		contentPane.add(separator4_2);

		JSeparator separator4_3 = new JSeparator();
		separator4_3.setForeground(Color.WHITE);
		separator4_3.setBackground(new Color(0, 169, 176));
		separator4_3.setBounds(313, 505, 238, 2);
		contentPane.add(separator4_3);

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

		if (person != null) {
			comboSex.setSelectedItem(person.getSex().toString());
			civilStatus.setSelectedItem(person.getCivilStatus());
			helpHome.setSelectedItem(person.getHelpHome());
			txtSurnameUser.setText(person.getUserSurname());
			txtDateBirthUser.setText(person.getDateBirth());
			txtHealthStatusUser.setText(person.getHealthStatus());
			txtTfUser.setText(person.getTf());
			txtDniUser.setText(person.getDocument());
			txtNameUser.setText(person.getUserName());
			txtAdressUser.setText(person.getAddress());
			txtWarningUser.setText(person.getWarning());
		}

	}

}
