package pojo;

public class Users {
	
	
	    private int id;
	    private String username;
	    private String email;
	    private String password;
	    private Name name;
	    private Address address;
	    private Geolocation geolocation;
	    private String phone;

	    // Default Constructor
	    public Users() {
	    }


		 public Users(String email, String username, String password, Name name, Address address, String phone) 
		{
		this.username = username;
		this.email = email;
		this.password = password;
		this.name = name;
		this.address = address;
		this.phone = phone;
		} 
	    
	    
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Name getName() {
			return name;
		}

		public void setName(Name name) {
			this.name = name;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public Geolocation getGeolocation() {
			return geolocation;
		}

		public void setGeolocation(Geolocation geolocation) {
			this.geolocation = geolocation;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}
}
