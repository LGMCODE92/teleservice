/**
 * 
 */
package repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Medicament;
import domain.Person;

/**
 * @author David
 *
 */
public class MedicamentRepository {

	public MedicamentRepository() {

	}

	public void insert(Medicament entity, Connection conn) throws SQLException {

		try {
			String sql = "INSERT INTO MEDICAMENTS (" + "NAME," + "DIARYINGEST," + "BASEMEDICINE," + "AMOUNT,"
					+ "USERDOCUMENT," + "DELETED)" + "VALUES(?,?,?,?,?,?);";
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, entity.getName());
				ps.setString(2, entity.getDiaryIngest());
				ps.setString(3, entity.getBaseMedicine());
				ps.setInt(4, entity.getAmount());
				ps.setString(5, entity.getUserDocument());
				ps.setBoolean(6, false);
				ps.execute();
				// ps.close();
			}
		} catch (SQLException e) {
			throw e;
		}
	}

	public void createTable(Connection conn) throws SQLException {

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS MEDICAMENTS (" + " ID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " NAME TEXT NOT NULL, " + " DIARYINGEST TEXT NOT NULL," + " BASEMEDICINE TEXT NOT NULL,"
					+ " AMOUNT INT NOT NULL," + " USERDOCUMENT TEXT NOT NULL," // FOREIGN KEY
					+ " DELETED BOOLEAN NOT NULL, FOREIGN KEY (USERDOCUMENT) REFERENCES PERSONS (DOCUMENT));";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println("MR60"+e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table Medicaments created!!!");
	}

	public void update(Medicament entity, Connection conn) throws SQLException {

		// SQL Query
		String sqlUpdate = "UPDATE MEDICAMENTS SET" + " NAME = ?,"
				+ "DIARYINGEST=?, "
				+ "BASEMEDICINE=?, "
				+ "AMOUNT=?, "
				+ "USERDOCUMENT=?, "
				+ "DELETED=? "
				+ "WHERE ID = ?;";

		try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
			// Fills query params
			psUpdate.setString(1, entity.getName());
			psUpdate.setString(2, entity.getDiaryIngest());
			psUpdate.setString(3, entity.getBaseMedicine());
			psUpdate.setInt(4,entity.getAmount());
			psUpdate.setString(5, entity.getUserDocument());
			psUpdate.setBoolean(6, entity.isDeleted());
			psUpdate.setInt(7, entity.getId());
			// Execute update
			psUpdate.execute();
		} catch (SQLException e) {
			throw e;
		}
	}

	public void dropTable(Connection conn) throws SQLException {

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = "DROP TABLE MEDICAMENTS;";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Tabled drop!!!");
	}

	public void delete(Person entity, Connection conn) throws SQLException {

		// SQL Query
		String sqlUpdate = "UPDATE MEDICAMENTS SET DELETED=?" + "WHERE USERDOCUMENT = ?";

		try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
			// Fills query params
			psUpdate.setBoolean(1, true);
			psUpdate.setString(2, entity.getUserName());
			// Execute update
			psUpdate.execute();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void deleteById(Medicament entity, Connection conn) throws SQLException {

		// SQL Query
		String sqlUpdate = "UPDATE MEDICAMENTS SET DELETED=?" + "WHERE ID = ?";

		try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
			// Fills query params
			psUpdate.setBoolean(1, true);
			psUpdate.setInt(2, entity.getId());
			// Execute update
			psUpdate.execute();
		} catch (SQLException e) {
			throw e;
		}
	}

	public List<Medicament> findMedicaments(Person entity, Connection conn) throws SQLException {
        List<Medicament> medicaments = new ArrayList<Medicament>();
		String sql = "SELECT * FROM MEDICAMENTS WHERE USERDOCUMENT = ?  AND DELETED = false";

		System.out.println(sql);
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			// Fills query parameters
			ps.setString(1, entity.getDocument());

			try (ResultSet rs = ps.executeQuery()) {
//				ResultSetMetaData md = rs.getMetaData();
//				int columns = md.getColumnCount();
//				Map<String, String> responseItem = new HashMap<>();
//				for (int i = 1; i <= columns; ++i) {
//					responseItem.put(md.getColumnName(i), rs.getObject(i).toString());
//				}
				while( rs.next()) {

					 
					Medicament aux = new Medicament();
					aux.setAmount(rs.getString("AMOUNT") != null ? Integer.parseInt(rs.getString("AMOUNT")): 0);
					aux.setBaseMedicine(rs.getString("BASEMEDICINE"));
					aux.setDeleted(Boolean.valueOf(rs.getString("DELETED")));
					aux.setDiaryIngest(rs.getString("DIARYINGEST"));
					aux.setName(rs.getString("NAME"));
					aux.setUserDocument(rs.getString("USERDOCUMENT"));
					aux.setId(rs.getInt("ID"));
					medicaments.add(aux);
				}

			}
			return medicaments;
		} catch (SQLException e) {
			throw e;
		}
	}

}
