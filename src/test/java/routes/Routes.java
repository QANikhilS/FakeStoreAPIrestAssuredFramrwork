package routes;

public class Routes {

	// This class will contains only variables, no method. We will be going to access then in the test cases directly.
	
	public static final String BASE_URL = "https://fakestoreapi.com";
	
	//product module related end points as per technical document
	public static final String GET_ALL_PRODUCTS = "/products";	
    public static final String GET_PRODUCT_BY_ID = "/products/{id}";
    public static final String GET_PRODUCTS_WITH_LIMIT = "/products?limit={limit}";
    public static final String GET_PRODUCTS_SORTED = "/products?sort={order}";
    public static final String GET_ALL_CATEGORIES = "/products/categories";
    public static final String GET_PRODUCTS_BY_CATEGORY = "/products/category/{category}";
    public static final String CREATE_PRODUCT = "/products";
    public static final String UPDATE_PRODUCT = "/products/{id}";
    public static final String DELETE_PRODUCT = "/products/{id}";
    
    //Users module related end points as per technical document
    public static final String GET_ALL_USERS = "/users";
    public static final String GET_USER_BY_ID = "/users/{id}";
    public static final String GET_USERS_LIMIT = "/users?limit={limit}";
    public static final String GET_USERS_SORTED = "/users?sort={order}";
    public static final String CREATE_NEW_USER = "/users";
    public static final String UPDATE_A_USER = "/users/{id}";
    public static final String DELETE_A_USER = "/users/{id}";
    
    //Cart module related end points as per technical document
    public static final String GET_ALL_CART = "/carts";
    public static final String GET_CART_BY_ID = "/carts/{id}";
    public static final String GET_CART_BETWEEN_DATE = "/carts?startdate={startdate}&enddate={enddate}";
    public static final String GET_USER_BY_CART = "/carts/user/{userId}";
    public static final String GET_CARTS_BY_LIMIT = "/carts?limit={limit}";
    public static final String GET_CARTS_SORTED = "/carts?sort={order}";
    public static final String ADD_A_NEW_CART = "/carts";
    public static final String UPDATE_A_CART = "/carts/{id}";
    public static final String DELETE_A_CART = "/carts/{id}";
    
    
    //Login module related end points as per technical document
    public static final String Login = "/auth/login";


/*    // Proper sequence is - 
1. Routes - First we list out all routes 
2. POJO classes  Then we create all main as well as Nested POJO classes (first check schema for it based on get all resources response.
3. Payload - Create a payload based on pojo class
4. Test cases - Then start  writting actual test cases and pass payload to it as input that is a body in the given() method


*/
}
