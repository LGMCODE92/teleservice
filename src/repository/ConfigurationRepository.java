package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Configuration;

public class ConfigurationRepository {

	public ConfigurationRepository() {

	}

	public void insert(Configuration entity, Connection conn) throws SQLException {

		try {
			String sql = "INSERT INTO CONFIGURATION (" + "KEY," + "VALUE," + "DELETED)" + "VALUES(?,?,?);";
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, entity.getKey());
				ps.setString(2, entity.getValue());
				ps.setBoolean(3, false);
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
			String sql = "CREATE TABLE  CONFIGURATION (" + " ID INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ " KEY TEXT NOT NULL, " + " VALUE TEXT NOT NULL," + " DELETED BOOLEAN NOT NULL)";

			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table configuration created!!!");
	}
	public void dropTable(Connection conn) throws SQLException {

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = "DROP TABLE CONFIGURATION";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Tabled configuration drop!!!");
	}

	public List<Configuration> find(String code, Connection conn) throws SQLException {
		List<Configuration> responseList = new ArrayList<Configuration>();

		String sql = "SELECT * FROM CONFIGURATION WHERE KEY = ? AND DELETED = false";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, code);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			Configuration res = new Configuration();
			res.setId(rs.getInt("ID"));
			res.setKey(rs.getString("KEY"));
			res.setValue(rs.getString("VALUE"));
			res.setDeleted(Boolean.valueOf(rs.getString("DELETED")));
			responseList.add(res);
		}

		return responseList;
	}
	
	public List<Configuration> findAll(Connection conn) throws SQLException {
		List<Configuration> responseList = new ArrayList<Configuration>();

		String sql = "SELECT * FROM CONFIGURATION WHERE DELETED = false";

		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			Configuration res = new Configuration();
			res.setId(rs.getInt("ID"));
			res.setKey(rs.getString("KEY"));
			res.setValue(rs.getString("VALUE"));
			res.setDeleted(Boolean.valueOf(rs.getString("DELETED")));
			responseList.add(res);
		}

		return responseList;
	}
	
	public void deleteById(int id,Connection conn) throws SQLException {

		// SQL Query
		String sqlUpdate = "UPDATE CONFIGURATION SET DELETED=?" + "WHERE ID = ?";

		try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
			// Fills query params
			psUpdate.setBoolean(1, true);
			psUpdate.setInt(2, id);
			// Execute update
			psUpdate.execute();
		} catch (SQLException e) {
			throw e;
		}
	}

}
