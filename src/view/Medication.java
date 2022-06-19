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
import controller.MedicamentController;
import controller.PersonController;
import domain.ConfigurationEnum;
import domain.Medicament;
import domain.Person;

public class Medication extends JFrame {

	//declaración variables
	private JPanel contentPane;
	private JTextField txtAdresseeCall;
	private JTextField txtDateCall;
	private JTextField txtReasonCall;

	
	private JComboBox<String> tomas;
	private MedicamentController medicationController;
	private PersonController personController;
	private ConfigurationController configurationController;
	
	

	/**
	 * Create the frame.
	 */
	public Medication(Person person, Medicament medicament, Person userLogin) {
		configurationController = new ConfigurationController();
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
		Image img = new ImageIcon(getClass().getResource("/img/login.png")).getImage();
		setIconImage(img);
		contentPane.setLayout(null);
		
		
		JLabel adresseeCall = new JLabel("Medicamento");
		adresseeCall.setFont(new Font("Tahoma", Font.PLAIN, 20));
		adresseeCall.setBounds(246, 230, 131, 31);
		contentPane.add(adresseeCall);
		
		JLabel dateCall = new JLabel("Cantidad (mg)");
		dateCall.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dateCall.setBounds(238, 295, 130, 31);
		contentPane.add(dateCall);
		
		JLabel reasonCall = new JLabel("Tomas diarias");
		reasonCall.setFont(new Font("Tahoma", Font.PLAIN, 20));
		reasonCall.setBounds(238, 365, 130, 31);
		contentPane.add(reasonCall);
		
		String tlfn="Operador";
		
		String pw= "Contraseña";
			
		
		
		txtAdresseeCall = new JTextField();
		txtAdresseeCall.setBounds(378, 230, 145, 31);
		txtAdresseeCall.setBorder(null);
		contentPane.add(txtAdresseeCall);
		txtAdresseeCall.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtAdresseeCall.setBackground(Color.WHITE);
		txtAdresseeCall.setColumns(10);
		
		txtDateCall = new JTextField();
		txtDateCall.setColumns(10);
		txtDateCall.setBorder(null);
		txtDateCall.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtDateCall.setBackground(Color.WHITE);
		txtDateCall.setBounds(378, 295, 145, 31);
		contentPane.add(txtDateCall);

		
		
		Object tomasDiarias[] = configurationController.getConfigurationByCode(ConfigurationEnum.INGEST_NUMBER.name()).getConfigList().stream()
				.map(x -> x.getValue()).toArray();
		//String tomasDiarias[]={"1", "2", "3", "4", "5"};
		tomas=new JComboBox<String>();
		tomas.setBounds(378, 365, 145, 31);
       
        for(int i=0;i<tomasDiarias.length;i++) {
        	tomas.addItem(String.valueOf(tomasDiarias[i]).toString());
        }
		if (null != medicament) {
			txtAdresseeCall.setText(medicament.getName());
			txtDateCall.setText(medicament.getBaseMedicine());
			tomas.setSelectedItem(medicament.getDiaryIngest().toString());
		}
        ///comboSex.addItemListener();
		tomas.setBorder(null);
		tomas.setBackground(Color.WHITE);
		tomas.setFocusable(false);
		
		tomas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(tomas);
		
		
//		txtReasonCall = new JTextField();
//		txtReasonCall.setColumns(10);
//		txtReasonCall.setBorder(null);
//		txtReasonCall.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		txtReasonCall.setBackground(Color.WHITE);
//		txtReasonCall.setBounds(378, 365, 145, 31);
//		contentPane.add(txtReasonCall);
		//txtPw.addKeyListener(redirectEvent);
		
		JLabel searchLabel = new JLabel("Introduzca los datos de la medicación");
		searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		searchLabel.setBounds(209, 126, 367, 66);
		contentPane.add(searchLabel);
		
		JButton saveButton = new JButton(" Guardar medicación");
		saveButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveButton.setBorder(null);
		saveButton.setFocusable(false);
		saveButton.setIcon(new ImageIcon(getClass().getResource("/img/save.png")));
		saveButton.setBackground(new Color(244,247,255));
		saveButton.setBounds(209, 482, 367, 38);
		saveButton.setBounds(225, 518, 335, 38);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				medicationController = new MedicamentController();
				personController = new PersonController();
				Medicament request = new Medicament();
				request.setName(txtAdresseeCall.getText());
				request.setDiaryIngest(tomas.getSelectedItem().toString());
				request.setBaseMedicine(txtDateCall.getText());
				request.setUserDocument(person.getDocument());
				
				String response = "KO";
				if (medicament != null) {
					request.setId(medicament.getId());
					response = medicationController.updateMedicament(request);
				} else {
					response = medicationController.saveMedicament(request);
				}

				JOptionPane.showMessageDialog(null, response);
				if (response.equals("Medicamento actualizado correctamente") || response.equals("Medicamento guardado correctamente")) {
					
					User frame = new User(personController.getPerson(person), userLogin);
					frame.setVisible(true);
					dispose();
				}
				

			}
		});
		contentPane.add(saveButton);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(new Color(0, 169, 176));
		separator.setBounds(378, 261, 145, 2);
		contentPane.add(separator);
		
		JSeparator separator2 = new JSeparator();
		separator2.setForeground(Color.WHITE);
		separator2.setBackground(new Color(0, 169, 176));
		separator2.setBounds(378, 326, 145, 2);
		contentPane.add(separator2);
		
		JSeparator separator3 = new JSeparator();
		separator3.setForeground(Color.WHITE);
		separator3.setBackground(new Color(0, 169, 176));
		separator3.setBounds(378, 396, 145, 2);
		contentPane.add(separator3);
		
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
		returnButton.setFocusable(false);
		returnButton.setBackground(new Color(244, 247, 255));
		returnButton.setBounds(10, 11, 43, 38);
		returnButton.setIcon(new ImageIcon(getClass().getResource("/img/return.png")));
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User frame = new User(person, userLogin);
				frame.setVisible(true);
				dispose();
			}
		});
		contentPane.add(returnButton);
	}

}
