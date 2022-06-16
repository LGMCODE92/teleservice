package service;

import domain.CallLog;
import domain.Medicament;
import domain.Person;
import repository.CallLogRepository;
import repository.MedicamentRepository;
import repository.PersonRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import config.Connect;

public class PersonService {

	private PersonRepository personRepository;
	private MedicamentRepository medicamentRepository;
	private CallLogRepository callLogRepository;

	public PersonService() {
		personRepository = new PersonRepository();
		medicamentRepository = new MedicamentRepository();
		callLogRepository = new CallLogRepository();
	}
    /**
     * Recupera el detalle de una persona
     * @param user user
     * @return Person
     * @throws SQLException
     */
	public Person getPerson(Person user) throws SQLException {

		Connection conn = Connect.connect();
		Person response = null;

		response = personRepository.findPerson(user, conn);
		if (response != null) {
			if (response.getUserRef() != null && response.getUserRef().length() > 0) {
				user.setDocument(response.getUserRef());
				response = personRepository.findPerson(user, conn);
			}
			List<Medicament> medicamentList = medicamentRepository.findMedicaments(response, conn);
			List<CallLog> callList = callLogRepository.findCalls(response, conn);
			response.setMedicamentList(medicamentList);
			response.setCallLogList(callList);
			response.setContactsList(personRepository.findContracts(response, conn));

		} else {
			response = new Person();
			response.setError("Usuario no encontrado");
		}

		conn.close();

		return response;

	}
	/**
	 * savePerson
	 * @param user user
	 * @throws SQLException SQLException
	 */
	public void savePerson(Person user) throws SQLException {

		Connection conn = Connect.connect();
		personRepository.insert(user, conn);

		conn.close();

	}
	
	/**
	 * updatePerson
	 * @param user user
	 * @throws SQLException SQLException
	 */
	public void updatePerson(Person user) throws SQLException {

		Connection conn = Connect.connect();
		personRepository.update(user, conn);

		conn.close();

	}
	
	/**
	 * deletePerson
	 * @param user user
	 * @throws SQLException SQLException
	 */
	public void deletePerson(Person user) throws SQLException {

		Connection conn = Connect.connect();
//		callLogRepository.delete(user, conn);
//		medicamentRepository.delete(user, conn);
		personRepository.delete(user, conn);

		conn.close();

	}
	


}
