package service;

import domain.CallLog;

import repository.CallLogRepository;

import java.sql.Connection;
import java.sql.SQLException;

import config.Connect;

public class CallLogService {


	private CallLogRepository callLogRepository;
    /**
     * Constructor
     */
	public CallLogService() {
		callLogRepository = new CallLogRepository();
	}
    /**
     * Inserta una nueva llamada en bbdd
     * @param callLog callLog
     * @throws SQLException SQLException
     */
	public void saveCall(CallLog callLog) throws SQLException {

		Connection conn = Connect.connect();
		callLogRepository.insert(callLog, conn);
		conn.close();

	}


}
