package testcases;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;
import routes.Routes;

public class SchemaTests extends BaseClass
 {
	
	@Test(priority = 1)
	public void testProductSchema()
	{
		given()
		   .pathParam("id", configreader.getIntProperties("productID"))
		.when()
		    .get(Routes.GET_PRODUCT_BY_ID)
		.then()
		    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ProductSchema.json"));
	}

	@Test(priority = 2)
	public void testCartSchema()
	{
		int cartID = configreader.getIntProperties("cartID");
		given()
		   .pathParam("id", cartID)
		.when()
		   .get(Routes.GET_CART_BY_ID)
		.then()
	      .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("CartSchema.json"));
	}
	
	
	@Test(priority = 3)
	public void testUserSchema()
	{
		int userID = configreader.getIntProperties("userID");
		given()
		     .pathParam("id", userID)
		.when()
		    .get(Routes.GET_USER_BY_ID)
		.then()
	      .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("UserSchema.json"));
	}
	
	
}
