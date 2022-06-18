package domain;

import java.util.List;

public class ConfigurationResponse {
	public List<Configuration> getConfigList() {
		return configList;
	}
	public void setConfigList(List<Configuration> configList) {
		this.configList = configList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	List<Configuration> configList;
	String message;

}
