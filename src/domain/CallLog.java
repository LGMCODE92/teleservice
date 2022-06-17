/**
 * 
 */
package domain;

import java.sql.Date;

public class CallLog {

	private int id;
	private String operator;
	private String document;
	private String callReason;
	private String contactPerson; // Persona que llama
	private String date;
	// private String UserDocument;
	private boolean deleted;
	private String error;

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
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
	 * @return the callReason
	 */
	public String getCallReason() {
		return callReason;
	}

	/**
	 * @param callReason the callReason to set
	 */
	public void setCallReason(String callReason) {
		this.callReason = callReason;
	}

	/**
	 * @return the contactPerson
	 */
	public String getContactPerson() {
		return contactPerson;
	}

	/**
	 * @param contactPerson the contactPerson to set
	 */
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 
	 * @return the index
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param the id
	 */
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CallLog [operator=" + operator + ", document=" + document + ", callReason=" + callReason
				+ ", contactPerson=" + contactPerson + ", date=" + date + ", deleted=" + deleted + ", error=" + error
				+ "]";
	}

	public void loginValid() throws Exception {
		if (operator == null || operator.length() == 0) {
			throw new Exception("Operador obligatorio");
		}
		if (callReason == null || callReason.length() == 0) {
			throw new Exception("Motivo obligatorio");
		}
		if (contactPerson == null || contactPerson.length() == 0) {
			throw new Exception("Persona de contacto obligatoria");
		}

	}
}
