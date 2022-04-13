package vo;

public class RewardsReport {
	private int customerId;
	private int storeId;
	private String firstName;
	private String lastName;
	private String email;
	private int addressId;
	private int active;
	private String createDate;
	private String lastUpdate;
	@Override
	public String toString() {
		return "RewardsReport [customerId=" + this.customerId + ", storeId=" + this.storeId + ", firstName=" + this.firstName
				+ ", lastName=" + this.lastName + ", email=" + this.email + ", addressId=" + this.addressId + ", active=" + this.active
				+ ", createDate=" + this.createDate + ", lastUpdate=" + this.lastUpdate + "]";
	}
	public int getCustomerId() {
		return customerId;
	}
	public int getStoreId() {
		return storeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public int getAddressId() {
		return addressId;
	}
	public int getActive() {
		return active;
	}
	public String getCreateDate() {
		return createDate;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
