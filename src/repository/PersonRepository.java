package repository;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.ResultSetMetaData;
import domain.Person;

public class PersonRepository  {


	
   /**
   * PersonRepository
   */
	public PersonRepository() {
	}

	/**
	 * inserta una fila en la tabla contline 
	 * @param entity Person
	 * @param conn Connection
	 * @throws SQLException SQLException
	 */
	public void insert(Person entity, Connection conn) throws SQLException {				
		
		
		try {
			String sql = "INSERT INTO PERSONS ( " + 
					"OPERATOR, " + //1
					"PASSWORD, " +  
					"USERNAME, "
					+ "DOCUMENT," +  
					"TF, " +  
					"ADDRESS, " +  
					"HEALTH_STATUS, " +  
					"HELP_HOME, " +  
					"CIVIL_STATUS, " +
					"DATE_BIRTH, " +
					"SEX, " +
					"WARNING, " +
					"USER_TYPE, " +
					"DELETED) " +
  				    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";

				try (PreparedStatement ps = conn.prepareStatement(sql)) {
					ps.setString(1, entity.getOperator());
					ps.setString(2, entity.getPassword());
					ps.setString(3, entity.getUserName());
					ps.setString(4, entity.getDocument());
					ps.setString(5, entity.getTf());
					ps.setString(6, entity.getAddress());
					ps.setString(7, entity.getHealthStatus());
					ps.setString(8, entity.getHelpHome());
					ps.setString(9, entity.getCivilStatus());
					ps.setString(10, entity.getDateBirth());
					ps.setString(11, entity.getSex());
					ps.setString(12, entity.getWarning());
					ps.setString(13, entity.getUserName());
					ps.setObject(14, false);
					ps.execute();			
				}

		}catch (SQLException e) {
			throw e;
		}		
	}
	public void createTable( Connection conn) throws SQLException {				
		
		
		Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql = "CREATE TABLE  PERSONS (" +
                   " OPERATOR  TEXT NOT NULL, " + 
                   " PASSWORD TEXT NOT NULL, " +
                   " DELETED        BOOLEAN)"; 
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created!!!");
    }	

	public void dropTable( Connection conn) throws SQLException {				
		
		
		Statement stmt = null;
        try{
            stmt = conn.createStatement();
            String sql = "DROP TABLE PERSONS"; 
            stmt.executeUpdate(sql);
            stmt.close();
        }
        catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Tabled drop!!!");
    }	
	

	
	

	/**
	 * Update person
	 * @param entity entity
	 * @param conn Connection
	 * @throws SQLException SQLException
	 */
	public void update(Person entity, Connection conn) throws SQLException{



		// SQL Query
		String sqlUpdate = "UPDATE PERSONS SET PASSWORD=?"+ 
		"WHERE USER_NAME = ?";

		try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
			// Fills query params
			psUpdate.setString(1, entity.getPassword());
			psUpdate.setString(2, entity.getUserName());
			// Execute update
			psUpdate.execute();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	
	
	/**
	 * Logic delete
	 * @param entity entity
	 * @param conn Connection
	 * @throws SQLException SQLException
	 */
	public void delete(Person entity,  Connection conn) throws SQLException{


	
		// SQL Query
		String sqlUpdate = "UPDATE PERSONS SET DELETED=?"+ 
		"WHERE USER_NAME = ?";

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
	
	
	
	/**
	 * Find a person
	 * @param user user 
	 * @param conn Connection
	 * @return Person
	 * @throws SQLException SQLException
	 */
	public Person findPerson(Person user, Connection conn) throws SQLException {
		Person res = null;
		int cont = 1;
		//List<Map<String, String>> response = new ArrayList<>();
		String sql = "SELECT * FROM PERSON WHERE USER_NAME = ? ";
		if (null != user.getPassword()){
			sql = sql + "AND PASSWORD = ? ";
        }
		/*
		if (null != user.getTf()){
			sql = sql + "WHERE TF = ?";
         }else if (null != user.getUserName()){
			sql = sql + "WHERE USER_NAME = ? AND";
        }else if(null != user.getDni()){
			sql = sql + "WHERE DNI = ?";
		}
		*/
		
		System.out.println(sql);
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			// Fills query parameters 
			ps.setString(1, user.getUserName());
			if (null != user.getPassword()){
				int aux = cont + 1;
				ps.setString(aux, user.getPassword());
			}
			try(ResultSet rs = ps.executeQuery()){
//				ResultSetMetaData md = rs.getMetaData();
//			    int columns = md.getColumnCount();
//				Map<String, String> responseItem = new HashMap<>();
//					for (int i = 1; i <= columns; ++i) {
//						responseItem.put(md.getColumnName(i), rs.getObject(i).toString());
//					}
	
//					response.add(responseItem);
//				}
				if(rs.next()) {
					res = new Person();
					res.setUserName(rs.getString("USER_NAME"));
					res.setPassword(rs.getString("PASSWORD"));
					res.setDeleted(Boolean.valueOf(rs.getString("DELETED")));
				}
			}
			return res;
		} catch (SQLException e) {
			throw e;
		} 
	}
	
	


	

}
