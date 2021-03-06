package domain;

import java.util.List;

public class Person {

	@Override
	public String toString() {
		return "Person [operator=" + operator + ", password=" + password + ", userName=" + userName + ", document="
				+ document + ", tf=" + tf + ", address=" + address + ", healthStatus=" + healthStatus + ", helpHome="
				+ helpHome + ", civilStatus=" + civilStatus + ", dateBirth=" + dateBirth + ", sex=" + sex + ", warning="
				+ warning + ", typeUser=" + typeUser + ", userRef=" + userRef + ", userSurname=" + userSurname
				+ ", deleted=" + deleted + ", error=" + error + ", medicamentList=" + medicamentList + ", contactsList="
				+ contactsList + ", callLogList=" + callLogList + "]";
	}

	private String operator="";
	private String password="";
	private String userName="";
	private String document="";
	private String tf="";
	private String address="";
	private String healthStatus="";
	private String helpHome="";
	private String civilStatus="";
	private String dateBirth="";
	private String sex="";
	private String warning="";
	private String typeUser="";
	private String userRef="";
	private String userSurname="";
    private boolean deleted=false;
	private String error;
	private List<Medicament> medicamentList;
	private List<Person> contactsList;
	private List<CallLog> callLogList;
	
	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public List<Medicament> getMedicamentList() {
		return medicamentList;
	}

	public void setMedicamentList(List<Medicament> medicamentList) {
		this.medicamentList = medicamentList;
	}

	public List<Person> getContactsList() {
		return contactsList;
	}

	public void setContactsList(List<Person> contactsList) {
		this.contactsList = contactsList;
	}

	public List<CallLog> getCallLogList() {
		return callLogList;
	}

	public void setCallLogList(List<CallLog> callLogList) {
		this.callLogList = callLogList;
	}

	public String getUserRef() {
		return userRef;
	}

	public void setUserRef(String userRef) {
		this.userRef = userRef;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @return the document
	 */
	public String getDocument() {
		return document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(String document) {
		this.document = document;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return String return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @return String return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return String return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return boolean return the deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public void loginValid() throws Exception {
		if (password == null || password.length() < 3) {
			throw new Exception("La contrase??a es obligatoria y tiene que tener como minimo 4 caracteres");
		}
		if (operator == null || operator.length() < 2) {
			throw new Exception("El nombre de usuario es obligatorio y tiene que tener como minimo 2 caracteres");
		}

	}


	public void insertOrUpdateContractValid() throws Exception {

		if (userName == null || userName.length() == 0) {
			throw new Exception("Nombre de usuario obligatorio");
		}
		if (document == null || document.length() == 0) {
			throw new Exception("DNI obligatorio");
		}
		if (document.length() != 9 || !(document.substring(0, 8).matches("[0-9]{8}")
				&& document.substring(8, 9).toUpperCase().matches("[A-Z]*"))) {
			throw new Exception("DNI Incorrecto");
		}

		if (tf == null || tf.length() == 0) {
			throw new Exception("Telefono obligatorio");
		}
		if ( tf.length() != 9 || !tf.toUpperCase().matches("[0-9]{9}")) {
			throw new Exception("Tf Incorrecto");
		}

		if (userSurname == null || userSurname.length() == 0) {
			throw new Exception("Apellidos obligatorios");
		}

	}
	public void insertOrUpdateValid() throws Exception {

		if (userName == null || userName.length() == 0) {
			throw new Exception("Nombre de usuario obligatorio");
		}
		if (document == null || document.length() == 0) {
			throw new Exception("DNI obligatorio");
		}
		if (document.length() != 9 || !(document.substring(0, 8).matches("[0-9]{8}")
				&& document.substring(8, 9).toUpperCase().matches("[A-Z]*"))) {
			throw new Exception("DNI Incorrecto");
		}

		if (tf == null || tf.length() == 0) {
			throw new Exception("Telefono obligatorio");
		}
		if ( tf.length() != 9 || !tf.toUpperCase().matches("[0-9]{9}")) {
			throw new Exception("Tf Incorrecto");
		}
		if (address == null || address.length() == 0) {
			throw new Exception("Direccion obligatoria");
		}
		if (healthStatus == null || healthStatus.length() == 0) {
			throw new Exception("Estado de salud obligatorio");
		}
		if (helpHome == null || helpHome.length() == 0) {
			throw new Exception("Ayuda al hogar obligatorio");
		}
		if (civilStatus == null|| civilStatus.length() == 0) {
			throw new Exception("Estado civil obligatorio");
		}
		if (dateBirth == null || dateBirth.length() == 0) {
			throw new Exception("Fecha de nacimiento obligatoria");
		}
		if (sex == null || sex.length() == 0) {
			throw new Exception("Sexo obligatorio");
		}
		if (userSurname == null || userSurname.length() == 0) {
			throw new Exception("Apellidos obligatorios");
		}

	}

	public void insertOperatorValid() throws Exception {

		if (userName == null || userName.length() == 0) {
			throw new Exception("Nombre de usuario obligatorio");
		}
		if (document == null || document.length() == 0) {
			throw new Exception("DNI obligatorio");
		}
		if (document.length() != 9 || !(document.substring(0, 8).matches("[0-9]{8}")
				&& document.substring(8, 9).toUpperCase().matches("[A-Z]*"))) {
			throw new Exception("DNI Incorrecto");
		}

		if (tf == null || tf.length() == 0) {
			throw new Exception("Telefono obligatorio");
		}

		if (userSurname == null || userSurname.length() == 0) {
			throw new Exception("Apellidos obligatorios");
		}
		if (password == null || password.length() < 3) {
			throw new Exception("La contrase??a es obligatoria y tiene que tener como minimo 4 caracteres");
		}
		if (operator == null || operator.length() < 2) {
			throw new Exception("El nombre de usuario es obligatorio y tiene que tener como minimo 2 caracteres");
		}

	}

	/**
	 * @return String return the tf
	 */
	public String getTf() {
		return tf;
	}

	/**
	 * @param tf the tf to set
	 */
	public void setTf(String tf) {
		this.tf = tf;
	}

	/**
	 * @return String return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return String return the healthStatus
	 */
	public String getHealthStatus() {
		return healthStatus;
	}

	/**
	 * @param healthStatus the healthStatus to set
	 */
	public void setHealthStatus(String healthStatus) {
		this.healthStatus = healthStatus;
	}

	/**
	 * @return String return the helpHome
	 */
	public String getHelpHome() {
		return helpHome;
	}

	/**
	 * @param helpHome the helpHome to set
	 */
	public void setHelpHome(String helpHome) {
		this.helpHome = helpHome;
	}

	/**
	 * @return String return the civilStatus
	 */
	public String getCivilStatus() {
		return civilStatus;
	}

	/**
	 * @param civilStatus the civilStatus to set
	 */
	public void setCivilStatus(String civilStatus) {
		this.civilStatus = civilStatus;
	}

	/**
	 * @return String return the dateBirth
	 */
	public String getDateBirth() {
		return dateBirth;
	}

	/**
	 * @param dateBirth the dateBirth to set
	 */
	public void setDateBirth(String dateBirth) {
		this.dateBirth = dateBirth;
	}

	/**
	 * @return String return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return String return the warning
	 */
	public String getWarning() {
		return warning;
	}

	/**
	 * @param warning the warning to set
	 */
	public void setWarning(String warning) {
		this.warning = warning;
	}

	/**
	 * @return String return the typeUser
	 */
	public String getTypeUser() {
		return typeUser;
	}

	/**
	 * @param typeUser the typeUser to set
	 */
	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}

}
