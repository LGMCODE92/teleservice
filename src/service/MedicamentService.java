package service;


import domain.Medicament;
import repository.MedicamentRepository;
import java.sql.Connection;
import java.sql.SQLException;

import config.Connect;

public class MedicamentService {

	private MedicamentRepository medicamentRepository;
    /**
     * Contructor
     */
	public MedicamentService() {
		medicamentRepository = new MedicamentRepository();
	}
	
    /**
     * Save a medicament
     * @param medicament
     * @throws SQLException 
     */
	public void saveMedicament(Medicament medicament) throws SQLException {

		Connection conn = Connect.connect();
		medicamentRepository.insert(medicament, conn);
        conn.close();

	}
	
    /**
     * Update a medicament
     * @param medicament
     * @throws SQLException 
     */
	public void updateMedicament(Medicament medicament) throws SQLException {

		Connection conn = Connect.connect();
		medicamentRepository.update(medicament, conn);
        conn.close();

	}
	
    /**
     * delete a medicament
     * @param medicament
     * @throws SQLException 
     */
	public void deleteMedicament(Medicament medicament) throws SQLException {

		Connection conn = Connect.connect();
		medicamentRepository.deleteById(medicament, conn);
        conn.close();

	}

}
