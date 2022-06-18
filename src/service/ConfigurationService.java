package service;


import domain.Configuration;
import repository.ConfigurationRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import config.Connect;

public class ConfigurationService {

	private ConfigurationRepository configRepository;
    /**
     * Contructor
     */
	public ConfigurationService() {
		configRepository = new ConfigurationRepository();
	}
	/**
	 * getConfigurationByCode
	 * @param code code
	 * @return List<Configuration> List<Configuration>
	 * @throws SQLException
	 */
	public List<Configuration> getConfigurationByCode(String code) throws SQLException{
		
		Connection conn = Connect.connect();
		List<Configuration> response = configRepository.find(code, conn);
		conn.close();
		return response;
	}
	
	/**
	 * getAllConfiguration
	 * @return List<Configuration> List<Configuration>
	 * @throws SQLException
	 */
	public List<Configuration> getAllConfiguration() throws SQLException{
		
		Connection conn = Connect.connect();
		List<Configuration> response = configRepository.findAll(conn);
		conn.close();
		return response;
	}
	
    /**
     * Save a config
     * @param config
     * @throws SQLException 
     */
	public void saveConfig(Configuration config) throws SQLException {

		Connection conn = Connect.connect();
		configRepository.insert(config, conn);
        conn.close();

	}

	
    /**
     * delete a config
     * @param id
     * @throws SQLException 
     */
	public void deleteConfig(int id) throws SQLException {

		Connection conn = Connect.connect();
		configRepository.deleteById(id, conn);
        conn.close();

	}

}
