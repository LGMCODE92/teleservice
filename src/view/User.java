package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolTip;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import domain.Medicament;
import domain.Person;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JSpinner;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;

public class User extends JFrame {
	
	//declaración variables
	private Person detalle;
	private JPanel contentPane;
	private JTable historialTable;
	private JTable contactTable;
	private JTable medicalTable;
	private DefaultTableModel modelo = null;
	private JScrollPane desplazamiento = null;
	
	private JLabel lblContact;
	private JLabel lblPersonData;
	private JLabel lblMedicacion;
	private JLabel histLabel;
	
	private JButton modifyContact;
	private JButton modifyMedication;
	private JButton btnModifyPersonalData;
	private JButton btnAddContact;
	private JButton btnAddMedication;
	private JButton btnNewCall;

	private String[] columnasMedical = { "Nombre", "Cantidad (mg)", "Tomas diarias" };
	private String[] columnasContact = { "Nombre", "DNI", "Teléfono", "Parentesco" };
	private String[] columnasHistorialCall = { "Destinatario", "Fecha", "Motivo", "Operador", "Acciones" };

	private Object[][] datosMedical;
	private Object[][] datosHistorialCall;
	private Object[][] datosContact;
	
	private Medicament medicament;
	private Person contact;
	
	private String modificar="Modificar";
	private String llamar="Llamar";
	private String añadir="Añadir";
	private String volver="Volver";
	private String datosContacto="Datos de contacto";
	private String datosPersonales="Datos Personales";
	private String medicacion="Medicación";
	private String historialLlamadas="Historial de llamadas";
	



	/**
	 * Create the frame.
	 */
	public User(Person detalle) {
		this.detalle = detalle;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// titulo e icono de la ventana
		setTitle("Ficha / Detalle");
		Image img = new ImageIcon(getClass().getResource("../images/login.png")).getImage();
		setIconImage(img);

		// centrar el frame en la pantalla
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int height = pantalla.height;
		int width = pantalla.width;
		setBounds(width / 4, height / 4, 800, 700);
		setLocationRelativeTo(null);
		// no maximizar
		setResizable(false);
		// absolute layout
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(244,247,255));
		// Cargar datos de tablas
		loadMedicamentData();
		loadCalLogData();
		loadContactData();
       
		
		// crea tabla historial de llamadas, la coloca y la añade al panel junto al scroll
		historialTable = new JTable(datosHistorialCall, columnasHistorialCall);
		JScrollPane scrollPaneHist = new JScrollPane(historialTable);
		scrollPaneHist.setBounds(353, 390, 414, 170);
		historialTable.setSelectionBackground(new Color(216,247,248));
		getContentPane().add(scrollPaneHist);
		
		
		// crea tabla contactos, la coloca y la añade al panel junto al scroll
		contactTable = new JTable(datosContact, columnasContact);
		contactTable.addMouseListener(getMouseEvent(datosContact, "contacts"));
		JScrollPane scrollPaneContact = new JScrollPane(contactTable);
		scrollPaneContact.setBounds(353, 64, 403, 200);
		contactTable.setSelectionBackground(new Color(216, 247, 248));
		//scrollPaneContact.setBackground(Color.WHITE);
		getContentPane().add(scrollPaneContact);
		
		
		
		// crea tabla medicinas, la coloca y la añade al panel junto al scroll
		medicalTable = new JTable(datosMedical, columnasMedical);
		medicalTable.addMouseListener(getMouseEvent(datosMedical, "medical"));
		JScrollPane scrollPaneMedication = new JScrollPane(medicalTable);
		scrollPaneMedication.setBounds(30, 390, 299, 170);
		medicalTable.setSelectionBackground(new Color(216, 247, 248));
		getContentPane().add(scrollPaneMedication);
		
		
		
		// crea componente lista de detalle de usuario
		String[] data = { "one", "two", "three", "four" };
		JList list = new JList(data);
		list.setBorder(UIManager.getBorder("Table.scrollPaneBorder"));
		list.setForeground(SystemColor.desktop);
		list.setFont(new Font("Tahoma", Font.PLAIN, 11));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "Nombre: " + detalle.getSex() + " " + detalle.getUserName(), "DNI: " + detalle.getDocument(),
					"TF: " + detalle.getTf(), "Dirección: " + detalle.getAddress() ,"Estado de salud: " + detalle.getHealthStatus(), "Ayuda a domicilio: " + detalle.getHelpHome(),
					"Estado civil: " + detalle.getCivilStatus(), "Fecha nacimiento: " + detalle.getDateBirth().toString(), "Avisos: " + detalle.getWarning() };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		
		// coloca la lista en el panel y lo añade
		list.setBounds(30, 64, 299, 200);
		list.setBackground(SystemColor.menu);
		//list.setBorder();
		list.setSelectionBackground(new Color(216, 247, 248));
		getContentPane().add(list);
		
		
        // label historial de llamadas colocar en jpanel y añadir
		histLabel = new JLabel(historialLlamadas);
		histLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		histLabel.setBounds(353, 360, 140, 31);
		getContentPane().add(histLabel);
		
		
		 // label medicacion colocar en jpanel y añadir
		lblMedicacion = new JLabel(medicacion);
		lblMedicacion.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMedicacion.setBounds(30, 360, 140, 31);
		getContentPane().add(lblMedicacion);
		
		
		 // label persona colocar en jpanel y añadir
		lblPersonData = new JLabel(datosPersonales);
		lblPersonData.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPersonData.setBounds(30, 35, 140, 31);
		getContentPane().add(lblPersonData);
		
		
		 // label contactos colocar en jpanel y añadir
		lblContact = new JLabel(datosContacto);
		lblContact.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblContact.setBounds(353, 35, 140, 31);
		getContentPane().add(lblContact);
		
		
		
		//Boton nueva llamada y su escuchador
		btnNewCall = new JButton(llamar);
		btnNewCall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// llamar alta contacto
				CallRecord frame = new CallRecord();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewCall.setBounds(353, 582, 110, 36);
		btnNewCall.setFocusable(false);
		btnNewCall.setBackground(new Color(216, 247, 248));
		getContentPane().add(btnNewCall);
		
		
		
		// Boton nueva medicacion y su escuchador
		btnAddMedication = new JButton(añadir);
		btnAddMedication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// llamar alta contacto
				Medication frame = new Medication();
				frame.setVisible(true);
				dispose();
			}
		});
		btnAddMedication.setBounds(30, 582, 110, 36);
		btnAddMedication.setFocusable(false);
		btnAddMedication.setBackground(new Color(216, 247, 248));
		getContentPane().add(btnAddMedication);
		
		
		// Boton añadir contacto y su escuchador
		btnAddContact = new JButton(añadir);
		btnAddContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// llamar alta contacto
				
				ContactInformation frame = new ContactInformation();
				frame.setVisible(true);
				dispose();
			}
		});
		btnAddContact.setBounds(353, 287, 110, 36);
		btnAddContact.setFocusable(false);
		btnAddContact.setBackground(new Color(216, 247, 248));
		getContentPane().add(btnAddContact);
		
		
		//Botón modificar en contactos y su escuchador
		modifyContact = new JButton(modificar);
		modifyContact.setEnabled(false);
		modifyContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// llamara a modificar contacto y pasar el objeto contacto
			}
		});
		modifyContact.setBounds(646, 287, 110, 36);
		modifyContact.setBackground(new Color(216, 247, 248));
		getContentPane().add(modifyContact);

		
		//Botón modificar en medicación y su escuchador
		modifyMedication = new JButton(modificar);
		modifyMedication.setEnabled(false);
		modifyMedication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// llamara a modificar medicacion y pasar el objeto medicacion
			}
		});
		modifyMedication.setBounds(219, 582, 110, 36);
		modifyMedication.setFocusable(false);
		modifyMedication.setBackground(new Color(216, 247, 248));
		getContentPane().add(modifyMedication);

		
		//Botón modificar en datos personales y su escuchador
		btnModifyPersonalData = new JButton(modificar);
		btnModifyPersonalData.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModifyPersonalData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// llamar a modificar personal data

				if (null == detalle.getError()) {
					UserRegister frame = new UserRegister();
					frame.setVisible(true);
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, detalle.getError());
				}
			}
		});
		btnModifyPersonalData.setBounds(30, 287, 110, 36);
		btnModifyPersonalData.setFocusable(false);
		btnModifyPersonalData.setBackground(new Color(216, 247, 248));
		getContentPane().add(btnModifyPersonalData);
		
		
		//Botón volver y su escuchador
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
		returnButton.setBounds(8, 8, 29, 28);
		returnButton.setToolTipText(volver);
		returnButton.setBorder(null);
		returnButton.setFocusable(false);
		returnButton.setBackground(new Color(244,247,255));
		returnButton.setIcon(new ImageIcon(getClass().getResource("../images/return.png")));
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Search frame = new Search();
				frame.setVisible(true);
				dispose();
			}
		});
		getContentPane().add(returnButton);


	}
	
	
	 // carga los datos para la tabla medicamentos instanciando un array bidimensional
	private void loadMedicamentData() {
		datosMedical = new Object[detalle.getMedicamentList().size()][columnasMedical.length];
		for (int i = 0; i < detalle.getMedicamentList().size(); i++) {
			datosMedical[i][0] = detalle.getMedicamentList().get(i).getName();
			datosMedical[i][1] = detalle.getMedicamentList().get(i).getBaseMedicine();
			//datosMedical[i][2] = detalle.getMedicamentList().get(i).getLaboratory();
			//datosMedical[i][3] = detalle.getMedicamentList().get(i).getAmount();
		};
	}
	
	
	 // carga los datos para la tabla historico de llamadas instanciando un array bidimensional
	private void loadCalLogData() {
		datosHistorialCall = new Object[detalle.getCallLogList().size()][columnasHistorialCall.length];
		for (int i = 0; i < detalle.getCallLogList().size(); i++) {
			datosHistorialCall[i][0] = detalle.getCallLogList().get(i).getContactPerson();
			datosHistorialCall[i][1] = detalle.getCallLogList().get(i).getDate().toString();
			datosHistorialCall[i][2] = detalle.getCallLogList().get(i).getCallReason();
			datosHistorialCall[i][3] = detalle.getCallLogList().get(i).getOperator();
			datosHistorialCall[i][4] = detalle.getCallLogList().get(i).getDocument();
		};
	}
	
	
    // carga los datos para la tabla contactos instanciando un array bidimensional
	private void loadContactData() {

		datosContact = new Object[detalle.getContactsList().size()][columnasContact.length];
		for (int i = 0; i < detalle.getContactsList().size(); i++) {
			datosContact[i][0] = detalle.getContactsList().get(i).getUserName();
			datosContact[i][1] = detalle.getContactsList().get(i).getDocument();
			datosMedical[i][2] = detalle.getContactsList().get(i).getTf();
			datosContact[i][3] = detalle.getContactsList().get(i).getAddress();
		};
	}
	
	
    // evento modificar
	private java.awt.event.MouseAdapter getMouseEvent(Object[][] data, String typeTable) {
		return new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = medicalTable.rowAtPoint(evt.getPoint());
				int col = medicalTable.columnAtPoint(evt.getPoint());
				if (row >= 0 && col >= 0) {
					if (typeTable.equals("medical")) {
						modifyMedication.setEnabled(true);
						medicament = new Medicament ();
						
						// monta objeto medicina
						// llamar pantalla de moficar
					} else {
						modifyContact.setEnabled(true);
						contact = new Person();
						contact.setUserName(data[row][0].toString());
						contact.setDocument(data[row][1].toString());
						contact.setTf(data[row][2].toString());
						// llamar pantalla modficar
					}
//					for (int i = 0; i < data[row].length; i++) {
//						System.out.println(data[row][i]);
//					}

				}
			}
		};
	}
	
	
	//getters y setters
	public Person getDetalle() {
		return detalle;
	}

	public void setDetalle(Person detalle) {
		this.detalle = detalle;
	}
}
