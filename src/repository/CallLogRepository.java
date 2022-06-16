/**
 * 
 */
package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.CallLog;
import domain.Person;

/**
 * @author David
 *
 */
public class CallLogRepository {

	public CallLogRepository() {

	}

	public void insert(CallLog entity, Connection conn) throws SQLException {

		try {
			String sql = "INSERT INTO CALLS (" + "DATE," + "OPERATOR," + "DOCUMENT," + "CALLREASON," + "CONTACTPERSON,"
					+ "DELETED)" + "VALUES(?,?,?,?,?,?);";
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setObject(1, entity.getDate());
				ps.setString(2, entity.getOperator());
				ps.setString(3, entity.getDocument());
				ps.setString(4, entity.getCallReason());
				ps.setString(5, entity.getContactPerson());
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
			String sql = "CREATE TABLE IF NOT EXISTS CALLS (" + " ID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " DATE DATE NOT NULL, " + "OPERATOR TEXT NOT NULL," + "DOCUMENT TEXT NOT NULL,"
					+ "CONTACTPERSON TEXT NOT NULL," + "CALLREASON TEXT NOT NULL,"
					+ " DELETED BOOLEAN NOT NULL , FOREIGN KEY (DOCUMENT) REFERENCES PERSONS (DOCUMENT) ON DELETE CASCADE )";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created!!!");
	}

	public void dropTable(Connection conn) throws SQLException {

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = "DROP TABLE CALLS";
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
		String sqlUpdate = "UPDATE CALLS SET DELETED=? WHERE DOCUMENT = ?";

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

	public List<CallLog> findCalls(Person entity, Connection conn) throws SQLException {

		List<CallLog> callList = new ArrayList<CallLog>();
		String sql = "SELECT * FROM CALLS WHERE DOCUMENT = ?  AND DELETED = false";
//		if (null != user.getPassword()){
//			sql = sql + "AND PASSWORD = ? ";
//        }
		/*
		 * if (null != user.getTf()){ sql = sql + "WHERE TF = ?"; }else if (null !=
		 * user.getUserName()){ sql = sql + "WHERE USER_NAME = ? AND"; }else if(null !=
		 * user.getDni()){ sql = sql + "WHERE DNI = ?"; }
		 */

		System.out.println(sql);
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			// Fills query parameters
			ps.setString(1, entity.getDocument());
//			if (null != user.getPassword()){
//				int aux = cont + 1;
//				ps.setString(aux, user.getPassword());
//			}
			try (ResultSet rs = ps.executeQuery()) {
//				ResultSetMetaData md = rs.getMetaData();
//				int columns = md.getColumnCount();
//				
//				Map<String, String> responseItem = new HashMap<>();
//				for (int i = 1; i <= columns; ++i) {
//					responseItem.put(md.getColumnName(i), rs.getObject(i).toString());
//				}
	

				while( rs.next()) {

 
					CallLog call = new CallLog();
					call.setCallReason(rs.getString("CALLREASON"));
					call.setContactPerson(rs.getString("CONTACTPERSON"));
					call.setDate(new Date(Long.parseLong(rs.getString("DATE"))));
					call.setDeleted(Boolean.valueOf(rs.getString("DELETED")));
					call.setDocument(rs.getString("DOCUMENT"));
					call.setOperator(rs.getString("OPERATOR"));
					callList.add(call);
				}
	
			}
//				if(rs.next()) {//No iría un while?
//					res = new Person();
//					res.setUserName(rs.getString("USER_NAME"));
//					res.setPassword(rs.getString("PASSWORD"));
//					res.setDeleted(Boolean.valueOf(rs.getString("DELETED")));
//				}


			return callList;
		} catch (SQLException e) {
			throw e;
		}
	}

}
