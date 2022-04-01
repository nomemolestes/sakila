package vo;

public class Staff {
	private int staffId;
	private int storeId;
	private String staffName;
	private int addressId;
	private String staffAddress;
	private String email;
	private String username;
	private String lastUpdate;
	
	public int getStaffId() {
		return staffId;
	}
	public int getStoreId() {
		return storeId;
	}
	public String getStaffName() {
		return staffName;
	}
	public int getAddressId() {
		return addressId;
	}
	public String getStaffAddress() {
		return staffAddress;
	}
	public String getEmail() {
		return email;
	}
	public String getUsername() {
		return username;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public void setStaffAddress(String staffAddress) {
		this.staffAddress = staffAddress;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
