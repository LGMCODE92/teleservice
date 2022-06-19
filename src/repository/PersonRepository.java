package repository;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import domain.Person;

public class PersonRepository {

	// comentario

	/**
	 * PersonRepository
	 */
	public PersonRepository() {
	}

	/**
	 * inserta una fila en la tabla contline
	 * 
	 * @param entity Person
	 * @param conn   Connection
	 * @throws SQLException SQLException
	 */
	public void insert(Person entity, Connection conn) throws SQLException {

		String sql = "INSERT INTO PERSONS ( " + "OPERATOR, " + // 1
				"PASSWORD, " + "USER_NAME, " + "USER_SURNAME, " + "DOCUMENT, " + "TF, " + "ADRESS, " + "HEALTH_STATUS, "
				+ "HELP_HOME, " + "CIVIL_STATUS, " + "DATE_BIRTH, " + "SEX, " + "WARNING, " + "USER_TYPE,"
				+ " USER_REF, " + "DELETED) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, entity.getOperator());
			ps.setString(2, entity.getPassword());
			ps.setString(3, entity.getUserName());
			ps.setString(4, entity.getUserSurname());
			ps.setString(5, entity.getDocument());
			ps.setString(6, entity.getTf());
			ps.setString(7, entity.getAddress());
			ps.setString(8, entity.getHealthStatus());
			ps.setString(9, entity.getHelpHome());
			ps.setString(10, entity.getCivilStatus());
			ps.setString(11, entity.getDateBirth());
			ps.setString(12, entity.getSex());
			ps.setString(13, entity.getWarning());
			ps.setString(14, entity.getTypeUser());
			ps.setString(15, entity.getUserRef());
			ps.setObject(16, false);
			ps.execute();

		} catch (SQLException e) {
			throw e;
		}
	}

	public void createTable(Connection conn) throws SQLException {

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS PERSONS  ( OPERATOR  TEXT, " + " PASSWORD TEXT, "
					+ " USER_NAME TEXT," + " USER_SURNAME TEXT," + " DOCUMENT TEXT PRIMARY KEY  NOT NULL,"
					+ " TF TEXT NOT NULL UNIQUE," + " ADRESS TEXT," + " HEALTH_STATUS TEXT," + " HELP_HOME TEXT,"
					+ " CIVIL_STATUS TEXT," + " DATE_BIRTH TEXT," + " SEX TEXT," + " WARNING TEXT," + " USER_TYPE TEXT,"
					+ " USER_REF TEXT," + " DELETED BOOLEAN);";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table person created!!!");
	}

	public void dropTable(Connection conn) throws SQLException {

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = "DROP TABLE PERSONS";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Tabled person drop!!!");
	}

	/**
	 * Update person
	 * 
	 * @param entity entity
	 * @param conn   Connection
	 * @throws SQLException SQLException
	 */
	public void update(Person entity, Connection conn) throws SQLException {

		String sqlUpdate = "UPDATE PERSONS SET "
				+ "USER_NAME = ?, USER_SURNAME = ?,TF = ?,ADRESS = ?, OPERATOR = ?,PASSWORD = ?,"
				+ "HEALTH_STATUS = ?,HELP_HOME = ?, CIVIL_STATUS = ?, DATE_BIRTH= ?, SEX = ?, WARNING = ?, USER_TYPE = ?   WHERE DOCUMENT = ?;";

		try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
			// Fills query params
			psUpdate.setString(1, entity.getUserName());
			psUpdate.setString(2, entity.getUserSurname());
			psUpdate.setString(3, entity.getTf());
			psUpdate.setString(4, entity.getAddress());
			psUpdate.setString(5, entity.getOperator());
			psUpdate.setString(6, entity.getPassword());
			psUpdate.setString(7, entity.getHealthStatus());
			psUpdate.setString(8, entity.getHelpHome());
			psUpdate.setString(8, entity.getCivilStatus());
			psUpdate.setString(10, entity.getDateBirth());
			psUpdate.setString(11, entity.getSex());
			psUpdate.setString(12, entity.getWarning());
			psUpdate.setString(13, entity.getTypeUser());
			psUpdate.setString(14, entity.getDocument());
			// Execute update
			psUpdate.execute();
		} catch (SQLException e) {
			throw e;
		}
	}

	/**
	 * Logic delete
	 * 
	 * @param entity entity
	 * @param conn   Connection
	 * @throws SQLException SQLException
	 */
	public void delete(Person entity, Connection conn) throws SQLException {

		// SQL Query
		String sqlUpdate = "UPDATE PERSONS SET DELETED=?" + "WHERE DOCUMENT = ?;";

		try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
			// Fills query params
			psUpdate.setBoolean(1, true);
			psUpdate.setString(2, entity.getDocument());
			// Execute update
			psUpdate.execute();
		} catch (SQLException e) {
			throw e;
		}
	}

	/**
	 * Find a person
	 * 
	 * @param user user
	 * @param conn Connection
	 * @return Person
	 * @throws SQLException SQLException
	 */

	public Person findOperator(Person user, Connection conn) throws SQLException {
		Person res = null;
		int cont = 1;
		String sql = "SELECT * FROM PERSONS WHERE OPERATOR = ? AND PASSWORD = ?  AND DELETED = false; ";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			// Fills query parameters
			ps.setString(1, user.getOperator());
			if (null != user.getPassword()) {
				int aux = cont + 1;
				ps.setString(aux, user.getPassword());
			}
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					res = new Person();
					res.setOperator(rs.getString("OPERATOR"));
					res.setPassword(rs.getString("PASSWORD"));
					res.setDocument(rs.getString("DOCUMENT"));
					res.setTypeUser(rs.getString("USER_TYPE"));
					res.setTf(rs.getString("TF"));
					res.setDeleted(Boolean.valueOf(rs.getString("DELETED")));
				}
			}
			return res;
		}
	}

	public Person findPerson(Person entity, Connection conn) throws SQLException {
		Person res = null;
		int cont = 1;
		boolean control;
		String sql;
		// List<Map<String, String>> response = new ArrayList<>();
		if (null != entity.getDocument() && entity.getDocument().length() > 0) {
			sql = "SELECT * FROM PERSONS WHERE DOCUMENT = ? AND USER_TYPE <> 'O' AND DELETED = false;";
			control = true;
		} else {
			sql = "SELECT * FROM PERSONS WHERE TF = ? AND USER_TYPE <> 'O' AND DELETED = false;";
			control = false;
		}

		System.out.println(sql);
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			// Fills query parameters
			if (control)
				ps.setString(1, entity.getDocument());
			else {
				ps.setString(1, entity.getTf());
			}

			if (null != entity.getPassword() && entity.getPassword().length() > 0) {
				int aux = cont + 1;
				ps.setString(aux, entity.getPassword());
			}
			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) { // ESTO NO SE CONTROLA EN EL MAIN?

					res = new Person();
					res.setUserName(rs.getString("USER_NAME"));
					res.setUserSurname(rs.getString("USER_SURNAME"));
					res.setDocument(rs.getString("DOCUMENT"));
					res.setTf(rs.getString("TF"));
					res.setAddress(rs.getString("ADRESS"));
					res.setHealthStatus(rs.getString("HEALTH_STATUS"));
					res.setHelpHome(rs.getString("HELP_HOME"));
					res.setCivilStatus(rs.getString("CIVIL_STATUS"));
					res.setDateBirth(rs.getString("DATE_BIRTH"));
					res.setSex(rs.getString("SEX"));
					res.setWarning(rs.getString("WARNING"));
					res.setTypeUser(rs.getString("USER_TYPE"));
					res.setUserRef(rs.getString("USER_REF"));
					res.setDeleted(Boolean.valueOf(rs.getString("DELETED")));
				}
			}
			return res;
		} catch (SQLException e) {
			throw e;
		}
	}

	public List<Person> findContracts(Person entity, Connection conn) throws SQLException {
		List<Person> response = new ArrayList();
		Person res = null;
		String sql;
		// List<Map<String, String>> response = new ArrayList<>();
		sql = "SELECT * FROM PERSONS WHERE USER_REF = ? AND DELETED = false;";

		System.out.println(sql);
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			// Fills query parameters
			ps.setString(1, entity.getDocument());

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) { // ESTO NO SE CONTROLA EN EL MAIN?

					res = new Person();
					res.setUserName(rs.getString("USER_NAME"));
					res.setUserSurname(rs.getString("USER_SURNAME"));
					res.setDocument(rs.getString("DOCUMENT"));
					res.setTf(rs.getString("TF"));
					res.setAddress(rs.getString("ADRESS"));
					res.setHealthStatus(rs.getString("HEALTH_STATUS"));
					res.setHelpHome(rs.getString("HELP_HOME"));
					res.setCivilStatus(rs.getString("CIVIL_STATUS"));
					res.setDateBirth(rs.getString("DATE_BIRTH"));
					res.setSex(rs.getString("SEX"));
					res.setWarning(rs.getString("WARNING"));
					res.setTypeUser(rs.getString("USER_TYPE"));
					res.setDeleted(Boolean.valueOf(rs.getString("DELETED")));
					response.add(res);
				}
			}
			return response;
		} catch (SQLException e) {
			throw e;
		}
	}
}
