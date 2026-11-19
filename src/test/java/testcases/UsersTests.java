package testcases;



import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.Users;
import routes.Routes;


public class UsersTests extends BaseClass{

	
	// 1) Get all users
	@Test(priority = 1)
	public void getAllUsers()
	{
		given()
		
		.when()
		    .get(Routes.GET_ALL_USERS)
		.then()
		    .statusCode(200)
		    .contentType(ContentType.JSON)
		    .body("size()", greaterThan(0))
		    .log().body();
	}
	
	
	// 2) Get user by ID
	@Test(priority = 2)
	public void getUserByID()
	{
		int userID = configreader.getIntProperties("userID");
		given()
		     .pathParam("id", userID)
		.when()
		    .get(Routes.GET_USER_BY_ID)
		.then()
		    .statusCode(200)
		    .contentType(ContentType.JSON)
		    .body("size()", greaterThan(0))
		    .log().body();
		    //.body(JsonSchemaValidator.matchesJsonSchemaInClasspath(null), null)
	}
		
	
	// 3) Get users with limit
	@Test(priority = 3)
	public void getUsersWithLimit()
	{
		int limit = configreader.getIntProperties("limit");
		given()
		     .pathParam("limit", limit)
		.when()
		    .get(Routes.GET_USERS_LIMIT)
		.then()
		    .statusCode(200)
		    .contentType(ContentType.JSON)
		    .body("size()", equalTo(limit)).and().log().body();
	}
		
	
	// 4) Verify users are in the sorted list or not (in descending order)   
	@Test(priority = 4)
	public void verifyUsersAreInDescOrder()
	{
		Response response = given()
			    .pathParam("order", "desc")
		.when()
		    .get(Routes.GET_USERS_SORTED)
		.then()
		    .statusCode(200)
		    .contentType(ContentType.JSON)
		    .extract().response();
		
		List<Integer> listOfUserIDs = response.jsonPath().getList("id", Integer.class);
	    assertThat(isSortedDescending(listOfUserIDs), is(true));
	}	
	
	
	
	// 5) Verify users are in the sorted list or not (in ascending order)   
	@Test(priority = 5)
	public void verifyUsersAreInAscOrder()
	{
		Response response = given()
			    .pathParam("order", "asc")
		.when()
		    .get(Routes.GET_USERS_SORTED)
		.then()
		    .statusCode(200)
		    .contentType(ContentType.JSON)
		    .extract().response();
		
		List<Integer> listOfUserIDs = response.jsonPath().getList("id", Integer.class);
	    assertThat(isSortedAscending(listOfUserIDs), is(true));
	}		
	
	
	
	// 6) Verify to create a new user
	@Test(priority = 6)
	public void verifyToCreateNewUser()
	{
		Users newUser = Payload.userPayload();
		
		int generatedUserID = given()
				.contentType(ContentType.JSON)
				.body(newUser)
		.when()
		    .post(Routes.CREATE_NEW_USER)
		.then()
		    .statusCode(201)
		    .contentType(ContentType.JSON)
		    .log().body()
		    .extract().response().jsonPath().getInt("id");
		System.out.println("Generated user ID = "+generatedUserID);
	}		
	
	
	
	// 7) Verify to update a new user
		@Test(priority = 7)
		public void verifyToUpdateUser()
		{
			int userID = configreader.getIntProperties("userID");
			Users updateUser = Payload.userPayload();
			
			given()
				.contentType(ContentType.JSON)
				.pathParam("id", userID)
				.body(updateUser)
			.when()
			    .put(Routes.UPDATE_A_USER)
			.then()
			    .statusCode(200)
			    .body("username", equalTo(updateUser.getUsername()));
		}		
		
		
		
		// 8) Verify to Delete a user
		@Test(priority = 8)
		public void verifyToDeleteUser()
		{
			int userID = configreader.getIntProperties("userID");
			
			given()
				.pathParam("id", userID)
			.when()
			    .delete(Routes.DELETE_A_USER)
			.then()
			    .statusCode(200);
		}				
		
	
	
	
	
	
	
	
	
	
	
	
	
	
}
