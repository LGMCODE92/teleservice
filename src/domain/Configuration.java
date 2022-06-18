package domain;

public class Configuration {
	
	//atributos
	private int id;
    private boolean deleted;
	private String key;
	private String value;
	/**
	 * configurationValid
	 * @throws Exception
	 */
	public void configurationValid() throws Exception{

		if (value == null || value.length() == 0) {
			throw new Exception("Valor obligatorio debe tener al menos un caracter");
		}
		
	}
	
	//getters y setters
	public String getKey() {
		return key;
	}
	public void setKey(String clave) {
		this.key = clave;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String valor) {
		this.value = valor;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
