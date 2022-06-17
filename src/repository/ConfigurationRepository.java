package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Configuration;
import domain.Medicament;
import domain.Person;

public class ConfigurationRepository {
	
	public ConfigurationRepository() {
		
	}

	
	
	public void insert(Configuration entity, Connection conn) throws SQLException {

		try {
			String sql = "INSERT INTO CONFIGURATION (" 
					+ "KEY," 
					+ "VALUE)" 
					+ "VALUES(?,?);";
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setString(1, entity.getKey());
				ps.setString(2, entity.getValue());
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
			String sql = "CREATE TABLE  CONFIGURATION (" 
					+ " ID PRIMARY KEY  NOT NULL AUTOINCREMENT, " 
					+ " KEY TEXT NOT NULL, "
					+ " VALUE TEXT NOT NULL)";
			
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created!!!");
	}
	
	
	
	
	public List<Configuration> find(Person user, Connection conn) throws SQLException {
		List<Configuration> res = new ArrayList<Configuration>();
		
		String sql = "SELECT * FROM CONFIGURATION";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			
			try(ResultSet rs = ps.executeQuery()){
				ResultSetMetaData md = rs.getMetaData();
			    int columns = md.getColumnCount();
				Configuration responseItem = new Configuration();
					for (int i = 1; i <= columns; i++) {
						responseItem.put(md.getColumnName(i), rs.getObject(i).toString());
						responseItem.setKey(rs.getObject(i).toString());
					}
	
					res.add(responseItem);
				}
				if(rs.next()) {//No iría un while?
					
					res.setUserName(rs.getString("KEY"));
					res.setPassword(rs.getString("VALUE"));
					res.setDeleted(Boolean.valueOf(rs.getString("DELETED")));
				}
			}
			return res;
		} catch (SQLException e) {
			throw e;
		} 
	}
	
	
}

