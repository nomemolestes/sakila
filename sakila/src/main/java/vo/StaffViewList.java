package vo;

public class StaffViewList {
	private int  id;
	private String name;
	private int zipCode;
	private int phone;
	private String city;
	private String country;
	private int sid;
	@Override
	public String toString() {
		return "StaffViewList [id=" + this.id + ", name=" + this.name + ", zipCode=" + this.zipCode + ", phone=" + this.phone + ", city="
				+ this.city + ", country=" + this.country + ", sid=" + this.sid + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getZipCode() {
		return zipCode;
	}
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
}
