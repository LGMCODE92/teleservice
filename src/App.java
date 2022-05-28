import javax.swing.JOptionPane;

import config.Connect;
import repository.PersonRepository;
import domain.Person;
import java.sql.Connection;
import java.sql.SQLException;
import java.awt.EventQueue;
import view.Login;
public class App {
    public static void main(String[] args) {
        Connection conn = Connect.connect();

        EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

    }
}
