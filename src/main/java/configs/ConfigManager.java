package configs;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

	public static String GetProperty(String propName) {
		File configFile = new File("config.properties");
		String propValue = "";
		try {
		    FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		 
		    propValue = props.getProperty(propName);
		 
		    System.out.println("PropValue: " + propValue);
		    reader.close();
		    
		    return  propValue;
		} catch (FileNotFoundException ex) {
		    System.out.println(" file does not exist");
		} catch (IOException ex) {
			System.out.println(" io exception");
		}
		return propValue;
	}
}
