package pojo;

import java.util.Date;
import java.util.List;

public class Cart {

	
    private int userId;
    private String date;
    private List<CartProducts> products;

    // Default Constructor
    public Cart() {
    }

    // Parameterized Constructor
    public Cart(int userId, String date, List<CartProducts> products) 
    {
        this.userId = userId;
        this.date= date;
        this.products = products;
    }

    // Getters and Setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
   
    
    public List<CartProducts> getProducts() {
        return products;
    }

    public void setProducts(List<CartProducts> products) {
        this.products = products;
    }
}
	
