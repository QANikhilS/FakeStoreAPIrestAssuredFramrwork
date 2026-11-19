package testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import payloads.Payload;
import pojo.Product;
import routes.Routes;



public class ProductTests extends BaseClass
{
	
	//1) Test to retrieve all the products
 	@Test(priority = 1)
	public void testGetAllProducts()
	{
		given()
		.when()
		    .get(Routes.GET_ALL_PRODUCTS)
		.then()
		    .statusCode(200)
		    .body("size()", greaterThan(0));
	}
	
	

	//2) Test to get single product based on ID
	@Test(priority = 2)
	public void testGetSingleProductByID()
	{
		given()
		   .pathParam("id", configreader.getIntProperties("productID"))
		.when()
		    .get(Routes.GET_PRODUCT_BY_ID)
		.then()
		    .statusCode(200)
		    .body("size()", greaterThan(0));
	}
	
	
	
	//3) Test to retrieve a limited number of products
	@Test(priority = 3)
	public void testGetLimitedProducts()
	{
	    given()
	         .pathParam("limit", 5) // we pass limit as path paratmeter bcoz we already have limit and query parameter syntax in Route so we are passing it as Path param.
	     .when()
	         .get(Routes.GET_PRODUCTS_WITH_LIMIT)
	    .then()
	         .statusCode(200);
	}
	
	
	//4) Test to retrieve products in descending order
	@Test(priority = 4)
	public void testGetProductsInDescendingOrder()
	{
		
		Response resopnse = given()
		    .pathParam("order", "desc")
		.when()
		    .get(Routes.GET_PRODUCTS_SORTED)
		.then()
		    .statusCode(200)
		    .extract().response();
		    
		  List<Integer> productIDs = resopnse.jsonPath().getList("id", Integer.class);    // to take anything from response we use JSONPath    
	      assertThat(isSortedDescending(productIDs), is(true));	
	}
	
	
	//5) Test to retrieve products in ascending order
	@Test(priority = 5)
	public void testGetProductsInAscendingOrder()
	{
		
		Response resopnse = given()
		    .pathParam("order", "asc")
		.when()
		    .get(Routes.GET_PRODUCTS_SORTED)
		.then()
		    .statusCode(200)
		    .extract().response();
		    
		  List<Integer> productIDs = resopnse.jsonPath().getList("id", Integer.class);    // to take anything from response we use JSONPath    
	      assertThat(isSortedAscending(productIDs), is(true));
	}
	
	
	//6) Test to get all product  
	@Test(priority = 6)
	public void testGetAllCategories()
	{
		given()
		
		.when()
		    .get(Routes.GET_ALL_CATEGORIES)
		.then()
		   .statusCode(200)
		   .body("size()", greaterThan(0));
	}
	
	
	//7) Test to get product based categories 
	@Test(priority = 7)
	public void testGetProductBasedOnCategory()
	{
		given()
		   .pathParam("category", "electronics")
		.when()
		   .get(Routes.GET_PRODUCTS_BY_CATEGORY)
		.then()
	       .statusCode(200)
	       .body("size()", greaterThan(0))
		   .body("category", everyItem(notNullValue()))
		   .body("category", everyItem(equalTo("electronics")));
	}	
		

	//8) Test to add new product 
	@Test(priority = 8)
	public void testAddNewProduct()
	{
		Product newProduct = Payload.productPayload();
		
		int ProductID = given()
	      .contentType("application/json")
          .body(newProduct)
		.when()
		   .post(Routes.CREATE_PRODUCT)
		.then()
	       .statusCode(201)
	       .body("id", notNullValue())
	       .body("title", equalTo(newProduct.getTitle()))
	       .extract().jsonPath().getInt("id");
	}
		
		
	//9) Test to update new product 
	@Test(priority = 9)
	public void testUpdateTheProduct()
	{
		Product updatedProduct = Payload.productPayload();
	  
		given()
		  .pathParam("id", configreader.getIntProperties("productID"))	 	
	      .contentType("application/json")
          .body(updatedProduct)
		.when()
		   .put(Routes.UPDATE_PRODUCT)
		.then()
	       .statusCode(200)
	       .body("title", equalTo(updatedProduct.getTitle()));
	}		
		
	
	//10) Test to delete new product 
	@Test(priority = 10)
	public void testDeleteTheProduct()
	{
		given()
		  .pathParam("id", configreader.getIntProperties("productID"))	 	
		.when()
		   .delete(Routes.DELETE_PRODUCT)
		.then()
	       .statusCode(200);
	}		
		
		
	
}
