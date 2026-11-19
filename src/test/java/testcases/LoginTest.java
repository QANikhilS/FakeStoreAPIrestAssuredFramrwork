package testcases;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.Payload;
import pojo.Login;
import routes.Routes;


public class LoginTest extends BaseClass {
	
	
	@Test
	public void loginwithInvalidUsernameAndPassword()
	{
		Login newlogin = Payload.loginPayload();
		
		
	    given()
	       .contentType(ContentType.JSON)
	       .body(newlogin)
	    .when()
	        .post(Routes.Login)
	    .then()
	       .statusCode(401)   // for unauthorized access
	       .log().body()
	       .body(equalTo("username or password is incorrect"));
	}
	
	
	@Test
	public void loginwithValidUsernameAndPassword()
	{
		String uname = configreader.getProperties("username");
		String pwd = configreader.getProperties("password");

		Login newlogin = new Login(uname, pwd);

	 Response res = given()
	       .contentType(ContentType.JSON)
	       .body(newlogin)
	    .when()
	        .post(Routes.Login)
	    .then()
	       .statusCode(201)   // for unauthorized access
	       .log().body()
	       .body("token", notNullValue()).extract().response();
	 
	 String token = res.jsonPath().getString("token");
	 System.out.println("================== "+token);
	 
	}

}
