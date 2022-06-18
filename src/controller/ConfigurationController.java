package controller;
import service.ConfigurationService;


import domain.Configuration;
import domain.ConfigurationResponse;
public class ConfigurationController {
	
    private ConfigurationService configService;

    public ConfigurationController(){
    	configService = new ConfigurationService();
    }
    /**
     * saveConfiguration
     * @param config config
     * @return String with status response
     */
    public String saveConfiguration ( Configuration config){
    	String response = "Configuracion guardada correctamente";
        try{
        	config.configurationValid();
        	configService.saveConfig(config);

        }catch (Exception e){
        	e.printStackTrace();
        	System.out.println("ErrorConfigurationControlerInsert");
        	response = e.getMessage();
        }
        return response;
    }
    

    
    /**
     * deleteConfiguration
     * @param id id
     * @return String with status response
     */
    public String deleteConfiguration ( int id){
    	String response = "Configuracion eliminada correctamente";
        try{
        	configService.deleteConfig(id);
        }catch (Exception e){
        	e.printStackTrace();
        	System.out.println("ErrorConfiguracionControlerDelete");
        	response = e.getMessage();
        }
        return response;
    }
    /**
     * getConfigurationByCode
     * @param code code
     * @return ConfigurationResponse
     */
    public ConfigurationResponse getConfigurationByCode ( String code){
    	ConfigurationResponse response = new ConfigurationResponse();
        try{
        	response.setConfigList(configService.getConfigurationByCode(code));

        }catch (Exception e){
        	e.printStackTrace();
        	response.setMessage(e.getMessage());
        	System.out.println("ErrorConfigurationControler");
        }
        return response;
    }
    
    /**
     * getConfigurationByCode
     * @param code code
     * @return ConfigurationResponse
     */
    public ConfigurationResponse getAllConfiguration(){
    	ConfigurationResponse response = new ConfigurationResponse();
        try{
        	response.setConfigList(configService.getAllConfiguration());

        }catch (Exception e){
        	e.printStackTrace();
        	response.setMessage(e.getMessage());
        	System.out.println("ErrorConfigurationControler");
        }
        return response;
    }
        
}
