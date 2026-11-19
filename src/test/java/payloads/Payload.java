package payloads;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

import pojo.Address;
import pojo.Cart;
import pojo.CartProducts;
import pojo.Geolocation;
import pojo.Login;
import pojo.Name;
import pojo.Product;
import pojo.Users;


public class Payload {

	private static final Faker faker = new Faker();
	private static String categories[]= {"men's clothing","jewelery","electronics","women's clothing"};	
	private static Random random = new Random();
	
	// product
	public static Product productPayload()
	{
		String name=faker.commerce().productName();  // to generate fake product name for ecommerce website.
	    double price = Double.parseDouble(faker.commerce().price()); 
		String description=faker.lorem().sentence(); 
		String imageUrl="https://i.pravatar.cc/100";
		String category = categories[random.nextInt(categories.length)];  // we do not have any category method in fakes so we created our own array for category
		
		return new Product(name, price, description, category, imageUrl);
	}
	
	
	// user
	public static Users userPayload()
	{
		// Create data using faker for Name POJO
		String firstname=faker.name().firstName();  
		String lastname=faker.name().lastName();
		Name name = new Name(firstname, lastname);
		
		// Create data using faker for Geolocation POJO
		String lat = faker.address().latitude();
		String Long = faker.address().longitude();
		Geolocation geolocation = new Geolocation(lat, Long);
		
		// Create data using faker for Address POJO
		String city = faker.address().city();
		String steet= faker.address().streetName();
		int number = random.nextInt(100);
		String zipcode = faker.address().zipCode();
		Address address = new Address(city, steet, number, zipcode, geolocation) ;
		
		// Create data using faker for Users POJO
		String email = faker.internet().emailAddress();
		String username = faker.name().username();
		String password = faker.internet().password();
		String phone = faker.phoneNumber().cellPhone();
        Users users = new Users(email, username, password, name, address, phone);
	
		return users;
	}
	
	
	// cart
	public static Cart cartPayload(int userID)
	{
		List<CartProducts> products = new ArrayList<>();
		
		int productID = random.nextInt(100);
		int quantity = random.nextInt(10)+1;
		
		CartProducts cartproducts = new CartProducts(productID, quantity);
		products.add(cartproducts);
		
		//new Date()  ---> Returns date like Wed Feb 19 13:17:45 IST 202
		// we need to convert this to "yyyy-MM-dd" formatin string
		
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); //Define output date in expected formt
		String date = outputFormat.format(new Date());		
		
		return new Cart(userID, date, products);
	}
	
	
	//Login
	public static Login loginPayload()
	{
		String username = faker.name().username();
		String password = faker.internet().password();
		return new Login(username, password);
	}
	
	
}
