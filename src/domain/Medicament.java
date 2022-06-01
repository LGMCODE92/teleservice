/**
 * 
 */
package domain;

/**
 * @author David
 *
 */
public class Medicament {
	
	private String name;
	private String laboratory;
	private String baseMedicine;
	private int amount;
	private String userDocument;
	private boolean deleted;
	private String error;
	
	/**
	 * @return the deleted
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
	
	/**
	 * @return the userDocument
	 */
	public String getUserDocument() {
		return userDocument;
	}
	/**
	 * @param userDocument the userDocument to set
	 */
	public void setUserDocument(String userDocument) {
		this.userDocument = userDocument;
	}
	//falta la medida que podríamos hacer que seleccione de un array.
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the laboratory
	 */
	public String getLaboratory() {
		return laboratory;
	}
	/**
	 * @param laboratory the laboratory to set
	 */
	public void setLaboratory(String laboratory) {
		this.laboratory = laboratory;
	}
	/**
	 * @return the baseMedicine
	 */
	public String getBaseMedicine() {
		return baseMedicine;
	}
	/**
	 * @param baseMedicine the baseMedicine to set
	 */
	public void setBaseMedicine(String baseMedicine) {
		this.baseMedicine = baseMedicine;
	}
	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

}
