package vo;

public class Film {
	private int FID;
	private String title;
	private String description;
	private String category;
	private String price;
	private int length;
	private String rating;
	private String actors;
	@Override
	public String toString() {
		return "Film [FID=" + this.FID + ", title=" + this.title + ", description=" + this.description + ", category=" + this.category
				+ ", price=" + this.price + ", length=" + this.length + ", rating=" + this.rating + ", actors=" + this.actors + "]";
	}
	
	public int getFID() {
		return FID;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getCategory() {
		return category;
	}
	public String getPrice() {
		return price;
	}
	public int getLength() {
		return length;
	}
	public String getRating() {
		return rating;
	}
	public String getActors() {
		return actors;
	}
	public void setFID(int fID) {
		FID = fID;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	
	
	

}
