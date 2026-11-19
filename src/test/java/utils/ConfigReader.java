package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static final String CONFIG_FILE_PATH = ".\\src\\test\\resources\\config.properties";
	Properties prop;
	
	public ConfigReader()
	{
		prop= new Properties();
		try (FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH))
		{
			prop.load(fis);
		} catch (IOException e) 
		   {
			e.printStackTrace();
			throw new RuntimeException("Failed to load config.properties file.");
	       }
	}	
	
	public String getProperties(String key)
	{
		return prop.getProperty(key);
	}
	
	
	public int getIntProperties(String key)
	{
		return Integer.parseInt(prop.getProperty(key));
	}
	
	
}
