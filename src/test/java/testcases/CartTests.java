package testcases;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.Cart;
import routes.Routes;

public class CartTests extends BaseClass{
	
	
	//1) Get all carts
	@Test(priority = 1)
	public void getAllCarts()
	{
		given()
		   
		.when()
		   .get(Routes.GET_ALL_CART)
		
		.then()
		   .statusCode(200)
		   .body("size()", greaterThan(0));
	}

	
	//2) Get carts by ID
	@Test(priority = 2)
	public void getCartByCartID()
	{
		int cartID = configreader.getIntProperties("cartID");
		given()
		   .pathParam("id", cartID)
		.when()
		   .get(Routes.GET_CART_BY_ID)
		
		.then()
		   .statusCode(200)
		   .body("id", equalTo(cartID));
	}
	
	
	//3) Get carts by date Range
		@Test(priority = 3)
		public void getCartByDateRange()
		{
			String startDate = configreader.getProperties("startdate");
            String endDate = configreader.getProperties("enddate");
					
		    Response response = given()
			   .pathParam("startdate", startDate)
			   .pathParam("enddate", endDate)
			.when()
			   .get(Routes.GET_CART_BETWEEN_DATE)
			.then()
			   .statusCode(200)
			   .body("size()", greaterThan(0))
			   .extract().response();
		
		//extract the list of cart dates
		List<String> cartDates = response.jsonPath().getList("date");
		
		//validate cart dates
		validateCartDatesWithinRange(cartDates, startDate, endDate);
		assertThat(validateCartDatesWithinRange(cartDates, startDate, endDate) , is(true));		
		}
		
		
		//4) Get carts by date Range
		@Test(priority = 4)
		public void getGetUserCart()
		{
			int UserID = configreader.getIntProperties("userID");
							
		        given()
				   .pathParam("userId", UserID)
				.when()
				   .get(Routes.GET_USER_BY_CART)
				.then()
				   .statusCode(200)
				   .body("userId", everyItem(equalTo(UserID)));
		}	
		
		
		//5) Get carts by limit
		@Test(priority = 5)
		public void getGetCartByLimit()
		{
			String limit = configreader.getProperties("limit");
							
		        given()
				   .pathParam("limit", limit)
				.when()
				   .get(Routes.GET_CARTS_BY_LIMIT)
				.then()
				   .statusCode(200)
				   .body("size()", lessThanOrEqualTo(3));
		}	
		
		
		//6) Get carts in descending order
		@Test(priority = 6)
		public void getGetCartInDesendingOrder()
		{
		    Response response = given()
				   .pathParam("order", "desc")
				.when()
				   .get(Routes.GET_CARTS_SORTED)
				.then()
				   .statusCode(200)
				   .body("size()", greaterThan(0))
		           .extract().response();
		    
		    //Parse response to get product IDs
		    List<Integer> cartIds = response.jsonPath().getList("id", Integer.class);
		    
		    //validate IDs are sorted in ascending otder
		    assertThat(isSortedDescending(cartIds), is(true));
		}	
		
		
		//7) Get carts in descending order
		@Test(priority = 7)
		public void getGetCartInAscendingOrder()
		{
		    Response response = given()
				   .pathParam("order", "asc")
				.when()
				   .get(Routes.GET_CARTS_SORTED)
				.then()
				   .statusCode(200)
				   .body("size()", greaterThan(0))
		           .extract().response();
		    
		    //Parse response to get product IDs
		    List<Integer> cartIds = response.jsonPath().getList("id", Integer.class);
		    
		    //validate IDs are sorted in ascending otder
		    assertThat(isSortedAscending(cartIds), is(true));
		}	

		
		//8) Verify to crate a cart
		@Test(priority = 8)
		public void createCart()
		{
			int UserID = configreader.getIntProperties("userID");
			Cart newcart = Payload.cartPayload(UserID); 
			
			given()
				.contentType(ContentType.JSON)
				.body(newcart)
			.when()
			    .post(Routes.ADD_A_NEW_CART) 
			.then()
			    .statusCode(201)
			    .log().body()
			    .body("id", notNullValue())
			    .body("userId", notNullValue())
			    .body("products.size()", greaterThan(0));
		}
		
		
		
		//9) Verify to update a cart
		@Test(priority = 8)
		public void testToUpdateCart()
		{
			int UserID = configreader.getIntProperties("userID");
			int cartID = configreader.getIntProperties("cartID");
			
			Cart updatecart = Payload.cartPayload(UserID); 
			
			given()
			    .pathParam("id", cartID)
				.contentType(ContentType.JSON)
				.body(updatecart)
			.when()
			    .put(Routes.UPDATE_A_CART)
			.then()
			    .statusCode(200)
		        .body("id", notNullValue())
			    .body("userId", notNullValue())
			    .body("products.size()", equalTo(1));
		}

		
		//10) Verify to delete a cart
		@Test(priority = 8)
		public void testToDeleteCart()
		{
			int cartID = configreader.getIntProperties("cartID");
			
			given()
			    .pathParam("id", cartID)
			.when()
			    .delete(Routes.DELETE_A_CART)
			.then()
			    .statusCode(200);
		}
}
