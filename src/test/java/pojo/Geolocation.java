package pojo;

public class Geolocation {
	
	private String Lat;
	private String Long;

	public Geolocation(String lat, String l) {
		this.Lat = lat;
		this.Long = l;
	}

	public String getLat() {
		return Lat;
	}

	public void setLat(String lat) {
		Lat = lat;
	}

	public String getLong() {
		return Long;
	}

	public void setLong(String l) {
		Long = l;
	}

	
	
}
