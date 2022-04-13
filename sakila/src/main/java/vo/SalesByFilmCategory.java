package vo;

public class SalesByFilmCategory {
	private String category;
	private double totalSales;
	@Override
	public String toString() {
		return "SalesByStore [category=" + this.category + ", total_sales=" + this.totalSales + "]";
	}
	public String getCategory() {
		return category;
	}
	public double getTotalSales() {
		return totalSales;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}
	

}
