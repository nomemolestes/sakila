package vo;

public class SalesByStore {
	private String store;
	private String manager;
	private double totalSales;
	@Override
	public String toString() {
		return "SalesByStore [store=" + this.store + ", manager=" + this.manager + ", total_sales=" + this.totalSales + "]";
	}
	public String getStore() {
		return store;
	}
	public String getManager() {
		return manager;
	}
	public double getTotalSales() {
		return totalSales;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}
	
}
