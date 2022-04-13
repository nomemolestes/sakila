package vo;

public class StaffViewList {
	private int  id;
	private String name;
	private String address;
	private String zipCode;
	private long phone;
	private String city;
	private String country;
	private int sid;
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public long getPhone() {
		return phone;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public int getSid() {
		return sid;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	@Override
	public String toString() {
		return "StaffViewList [id=" + id + ", name=" + name + ", address=" + address + ", zipCode=" + zipCode
				+ ", phone=" + phone + ", city=" + city + ", country=" + country + ", sid=" + sid + "]";
	}
	
	
	
}
