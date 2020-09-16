package configs;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

	public static String GetProperty(String propName){
		String propValue = "";
		try {
			java.io.InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("properties-from-pom.properties");
			java.util.Properties properties = new Properties();
			properties.load(inputStream);
			String  env = properties.getProperty("env");

			File configFile = null;
			if(env.equalsIgnoreCase("local"))
			{
				System.out.println("local props");
				configFile = new File("config.local.properties");
			}
			else {
				System.out.println("server props");
				configFile = new File("config.properties");
			}

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
