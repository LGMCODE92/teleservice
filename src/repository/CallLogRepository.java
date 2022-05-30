/**
 * 
 */
package repository;

import java.sql.Connection;
import java.sql.SQLException;

import domain.CallLog;

/**
 * @author David
 *
 */
public class CallLogRepository {

	public CallLogRepository (){
		
	}
	
	public void insert ( CallLog entity, Connection conn) throws SQLException {
		
		try {
			String sql = "INSERT INTO CALLS ("
					+ "OPERATOR,"
					+ "DOCUMENT"
					+ "CALLREASON"
					+ "CONTACTPERSON"
					+ "HOUR"
					+ "DATE"
					+ "DELETED"
					+ "VALUES(?,?,?,?,?,?,?);";
			try (PreparedStatement ps=conn.prepareStatement(sql)){
				
			}
			
		}catch (SQLException e) {
			throw e;
		}	
	}
}
