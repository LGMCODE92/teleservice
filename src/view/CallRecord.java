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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToolTip;
import javax.swing.border.EmptyBorder;

import controller.CallLogController;
import controller.PersonController;
import domain.CallLog;
import domain.Person;

public class CallRecord extends JFrame {

	//declaración variables
	private JPanel contentPane;
	private JTextField txtAdresseeCall;
	private JTextField txtDateCall;
	private JTextField txtReasonCall;
	private JTextField txtOperatorCall;
	private JTextField txtActionCall;
    private CallLogController callLogController;
    private PersonController personController;


	/**
	 * Create the frame.
	 */
	public CallRecord(Person person) {
		callLogController = new CallLogController();
		personController = new PersonController();
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
		
		
		JLabel adresseeCall = new JLabel("Destinatario");
		adresseeCall.setFont(new Font("Tahoma", Font.PLAIN, 20));
		adresseeCall.setBounds(256, 172, 118, 31);
		contentPane.add(adresseeCall);
		
		JLabel dateCall = new JLabel("Fecha");
		dateCall.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dateCall.setBounds(308, 231, 67, 31);
		contentPane.add(dateCall);
		
		JLabel reasonCall = new JLabel("Motivo");
		reasonCall.setFont(new Font("Tahoma", Font.PLAIN, 20));
		reasonCall.setBounds(301, 292, 67, 31);
		contentPane.add(reasonCall);
		
		String tlfn="Operador";
		JLabel operatorCall = new JLabel(tlfn);
		operatorCall.setFont(new Font("Tahoma", Font.PLAIN, 20));
		operatorCall.setBounds(282, 346, 86, 38);
		contentPane.add(operatorCall);
		
//		JLabel actionCall = new JLabel("Acciones");
//		actionCall.setFont(new Font("Tahoma", Font.PLAIN, 20));
//		actionCall.setBounds(282, 404, 86, 39);
//		contentPane.add(actionCall);
		
		String pw= "Contraseña";
			
		
		
		txtAdresseeCall = new JTextField();
		txtAdresseeCall.setBounds(378, 172, 145, 31);
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
		txtDateCall.setBounds(378, 231, 145, 31);
		contentPane.add(txtDateCall);
		
		txtReasonCall = new JTextField();
		txtReasonCall.setColumns(10);
		txtReasonCall.setBorder(null);
		txtReasonCall.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtReasonCall.setBackground(Color.WHITE);
		txtReasonCall.setBounds(378, 292, 145, 31);
		contentPane.add(txtReasonCall);
		
		txtOperatorCall = new JTextField();
		txtOperatorCall.setColumns(10);
		txtOperatorCall.setBorder(null);
		txtOperatorCall.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtOperatorCall.setBackground(Color.WHITE);
		txtOperatorCall.setBounds(378, 346, 145, 31);
		contentPane.add(txtOperatorCall);
		
//		txtActionCall = new JTextField();
//		txtActionCall.setColumns(10);
//		txtActionCall.setBorder(null);
//		txtActionCall.setFont(new Font("Tahoma", Font.PLAIN, 18));
//		txtActionCall.setBackground(Color.WHITE);
//		txtActionCall.setBounds(378, 404, 145, 31);
//		contentPane.add(txtActionCall);
		//txtPw.addKeyListener(redirectEvent);
		
		JLabel searchLabel = new JLabel("Introduzca los datos de la llamada");
		searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		searchLabel.setBounds(225, 79, 335, 66);
		contentPane.add(searchLabel);
		
		JButton saveButton = new JButton(" Guardar llamada");
		saveButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		saveButton.setFocusable(false);
		saveButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		saveButton.setBorder(null);
		saveButton.setIcon(new ImageIcon(getClass().getResource("../images/save.png")));
		saveButton.setBackground(new Color(244,247,255));
		saveButton.setBounds(225, 518, 335, 38);
		saveButton.setBounds(225, 518, 335, 38);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CallLog request = new CallLog();
				request.setCallReason(txtReasonCall.getText());
				request.setContactPerson(txtAdresseeCall.getText());
				request.setDocument(person.getDocument());
				request.setOperator(txtOperatorCall.getText());
				String response = "KO";
                response = callLogController.saveCall(request);
				JOptionPane.showMessageDialog(null, response);
				if (response.equals("Llamada realizada correctamente")) {
					User frame = new User(personController.getPerson(person));
					frame.setVisible(true);
					dispose();
				}
				

			}
		});
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
		
//		JSeparator separator5 = new JSeparator();
//		separator5.setForeground(Color.WHITE);
//		separator5.setBackground(new Color(0, 169, 176));
//		separator5.setBounds(378, 435, 145, 2);
//		contentPane.add(separator5);
		
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
