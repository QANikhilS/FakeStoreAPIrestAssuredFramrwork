package pojo;

public class Product {

	
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	
	public Product(String title, double price, String description, String category, String image)
	{
		this.title=title;
		this.price=price;
		this.description=description;
		this.category=category;
		this.image=image;
	}
		
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	/* How to create pojo class using AI tools - 
	Enter this promot in chatgpt - Create a pojo class based on below data also contain constructor 
	
	Data is a payload from tech specification. lets see the expected is to create a pojo class for Add new product end point.
	{
		"id": 0,
		"title": "string",
		"price": 0.1,
		"description": "string",
		"category": "string",
		"image": "http://example.com"
		}
	
	
	  review the response from LLM and copy - paster to your class.
	*/
	
}

