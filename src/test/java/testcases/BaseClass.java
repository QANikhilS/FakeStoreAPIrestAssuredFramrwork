package testcases;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import routes.Routes;
import utils.ConfigReader;


public class BaseClass {

	ConfigReader configreader;
	RequestLoggingFilter requestLoggingFilter;
	ResponseLoggingFilter responseLoggingFilter;
	
	@BeforeClass
	public void setup() throws Exception
	{
		RestAssured.baseURI=Routes.BASE_URL;		
		configreader=new ConfigReader();
		
	    //Setup filters for logging	
		FileOutputStream fos = new FileOutputStream(".\\logs\\test_logging.txt");
		PrintStream log = new PrintStream(fos, true);
		requestLoggingFilter = new RequestLoggingFilter(log);
		responseLoggingFilter = new ResponseLoggingFilter(log);
		RestAssured.filters(requestLoggingFilter,responseLoggingFilter);
		
		
	}
	
	

	// helper method to check if a list is sorted in Descending order
	boolean isSortedDescending(List<Integer> list)
	{
		for (int i=0 ; i<list.size()-1 ; i++)
		{
			if(list.get(i)<list.get(i+1))
			{
				return false;
			}
		}
		return true ;        }
	
	
	// helper method to check if a list is sorted in Ascending order
	boolean isSortedAscending(List<Integer> list)
	{
		for (int i=0 ; i<list.size()-1 ; i++)
		{
			if(list.get(i)>list.get(i+1))
			{
				return false;
			}
		}
		return true ;        }	
	
	
	public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	//helper method to check dates fall within the specified range
	boolean validateCartDatesWithinRange(List<String> cartDates, String startDate, String endDate)
	{
		LocalDate start = LocalDate.parse(startDate, FORMATTER);
		LocalDate end = LocalDate.parse(endDate, FORMATTER);
		
		for (String dateTime : cartDates)
		{
			LocalDate cartDate = LocalDate.parse(dateTime.substring(0,10), FORMATTER);
			if (cartDate.isBefore(start) || cartDate.isAfter(end))
			{
				return false;  //Immediately return false if any cart date is out of range
			}
		}
		return true ; //all dates are within range		
	}
	
}
