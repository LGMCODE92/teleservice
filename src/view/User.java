package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class User extends JFrame {
	private Person detalle;

	public Person getDetalle() {
		return detalle;
	}

	public void setDetalle(Person detalle) {
		this.detalle = detalle;
	}

	private JPanel contentPane;
	private JTable historialTable;
	private JTable contactTable;
	private JTable medicalTable;
	DefaultTableModel modelo = null;
	JScrollPane desplazamiento = null;
	JButton modifyContact;
	JButton modifyMedication;

	String[] columnasMedical = { "Medicamento", "P. Activo", "Laboratorio", "Importe" };
	String[] columnasContact = { "Nombre", "DNI", "TF", "Parentesco" };
	String[] columnasHistorialCall = { "Destinatario", "Fecha", "Motivo", "Operator", "DNI" };

	Object[][] datosMedical;
	Object[][] datosHistorialCall;
	Object[][] datosContact;
	Medicament medicament;
	Person contact;



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
		
		// Cargar datos de tablas
		loadMedicamentData();
		loadCalLogData();
		loadContactData();
       
		// crea tabla historial de llamadas, la coloca y la añade al panel junto al scroll
		historialTable = new JTable(datosHistorialCall, columnasHistorialCall);
		JScrollPane scrollPaneHist = new JScrollPane(historialTable);
		scrollPaneHist.setBounds(362, 421, 414, 170);
		getContentPane().add(scrollPaneHist);
		// crea tabla contactos, la coloca y la añade al panel junto al scroll
		contactTable = new JTable(datosContact, columnasContact);
		contactTable.addMouseListener(getMouseEvent(datosContact, "contacts"));
		JScrollPane scrollPaneContact = new JScrollPane(contactTable);
		scrollPaneContact.setBounds(373, 64, 403, 200);
		getContentPane().add(scrollPaneContact);
		// crea tabla medicinas, la coloca y la añade al panel junto al scroll
		medicalTable = new JTable(datosMedical, columnasMedical);
		medicalTable.addMouseListener(getMouseEvent(datosMedical, "medical"));
		JScrollPane scrollPaneMedication = new JScrollPane(medicalTable);
		scrollPaneMedication.setBounds(10, 421, 299, 170);
		getContentPane().add(scrollPaneMedication);
		// crea componente lista de detalle de usuario
		String[] data = { "one", "two", "three", "four" };
		JList list = new JList(data);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] { "Nombre: " + detalle.getSex() + " " + detalle.getUserName(), "DNI: " + detalle.getDocument(),
					"TF: " + detalle.getTf(), "Dirección: " + detalle.getAddress() ,"Estado de salud: " + detalle.getHealthStatus(), "Ayuda a domicilio: " + detalle.getHelpHome(),
					"client","Situacion personal: " + detalle.getCivilStatus(), "Fecha nacimiento: " + detalle.getDateBirth().toString(), "Avisos: " + detalle.getWarning() };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		// coloca la lista en el panel y lo añade
		list.setBounds(10, 64, 299, 200);
		getContentPane().add(list);
        // label historial de llamadas colocar en jpanel y añadir
		JLabel histLabel = new JLabel("Historial de llamadas: ");
		histLabel.setBounds(362, 389, 140, 31);
		getContentPane().add(histLabel);
		 // label medicacion colocar en jpanel y añadir
		JLabel lblMedicacion = new JLabel("Medicaci\u00F3n: ");
		lblMedicacion.setBounds(10, 389, 140, 31);
		getContentPane().add(lblMedicacion);
		 // label persona colocar en jpanel y añadir
		JLabel lblPersonData = new JLabel("Datos personales:");
		lblPersonData.setBounds(10, 35, 140, 31);
		getContentPane().add(lblPersonData);
		 // label contactps colocar en jpanel y añadir
		JLabel lblContact = new JLabel("Datos de contacto: ");
		lblContact.setBounds(373, 35, 140, 31);
		getContentPane().add(lblContact);
		 // Boton llamar y su escuchador
		JButton btnNewCall = new JButton("Llamar");
		btnNewCall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// llamar alta historico
			}
		});
		btnNewCall.setBounds(362, 602, 110, 36);
		getContentPane().add(btnNewCall);
		// Boton nueva medicacion y su escuchador
		JButton btnAddMedication = new JButton("A\u00F1adir");
		btnAddMedication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// llamar alta medicacion
			}
		});
		btnAddMedication.setBounds(10, 602, 110, 36);
		getContentPane().add(btnAddMedication);
		// Boton añadir contrato y su escuchador
		JButton btnAddContact = new JButton("A\u00F1adir");
		btnAddContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// llamar alta contacto
			}
		});
		btnAddContact.setBounds(373, 287, 110, 36);
		getContentPane().add(btnAddContact);
    
		modifyContact = new JButton("Modificar");
		modifyContact.setEnabled(false);
		modifyContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// llamara a modificar contacto y pasar el objeto contacto
			}
		});
		modifyContact.setBounds(666, 287, 110, 36);
		getContentPane().add(modifyContact);

		modifyMedication = new JButton("Modificar");
		modifyMedication.setEnabled(false);
		modifyMedication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// llamara a modificar medicacion y pasar el objeto medicacion
			}
		});
		modifyMedication.setBounds(199, 602, 110, 36);
		getContentPane().add(modifyMedication);

		JButton btnModifyPersonalData = new JButton("Modificar");
		btnModifyPersonalData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// llamar a modificar personal data
			}
		});
		btnModifyPersonalData.setBounds(10, 275, 110, 36);
		getContentPane().add(btnModifyPersonalData);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(982, 743, 60, -720);
		getContentPane().add(scrollPane_2);

	}
	 // carga los datos para la tabla medicamentos instanciando un array bidimensional
	private void loadMedicamentData() {
		datosMedical = new Object[detalle.getMedicamentList().size()][columnasMedical.length];
		for (int i = 0; i < detalle.getMedicamentList().size(); i++) {
			datosMedical[i][0] = detalle.getMedicamentList().get(i).getName();
			datosMedical[i][1] = detalle.getMedicamentList().get(i).getBaseMedicine();
			datosMedical[i][2] = detalle.getMedicamentList().get(i).getDiaryIngest();
			datosMedical[i][3] = detalle.getMedicamentList().get(i).getAmount();
		}
		;
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
		}
		;
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
}
