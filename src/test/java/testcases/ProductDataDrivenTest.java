package testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.Map;

import org.testng.annotations.Test;

import pojo.Product;
import routes.Routes;

public class ProductDataDrivenTest extends BaseClass {
	
	
	@Test(dataProvider = "jsonDataProvider", dataProviderClass = utils.DataProviders.class)
	public void testAddNewProduct(Map<String, String> data)
	{
	    String title=data.get("title");
	    double price=Double.parseDouble(data.get("price"));
	    String category=data.get("category");
	    String description=data.get("description");
	    String image=data.get("image");	    
	    
	    Product newProduct = new Product(title, price,description,image,category);		
		
	    int prodID = given()
	      .contentType("application/json")
          .body(newProduct)
		.when()
		   .post(Routes.CREATE_PRODUCT)
		.then()
		   .log().body()
	       .statusCode(201)
	       .body("id", notNullValue())
	       .body("title", equalTo(newProduct.getTitle()))
	       .extract().jsonPath().getInt("id");
	    
	    System.out.println("Generated product id = "+prodID);
	    
	
	}
	
	

}
