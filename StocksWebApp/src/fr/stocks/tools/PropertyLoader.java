package fr.stocks.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class PropertyLoader {

	public static final String DATABASE 			= "db.properties";
	public static final String API 					= "api.properties";
	public static final String FAV				 	= "fav.properties";
	
	public static String[] getInfo(String filePath) {
		Properties prop 	= new Properties();
		InputStream iStream = null;
		String[] r = null;
		try {
			iStream = PropertyLoader.class.getClassLoader().getResourceAsStream(filePath);
			if(iStream == null){
				throw new IOException("File not found");
			}
			prop.load(iStream);
			r = new String[prop.size()];
			Set<String> keys = prop.stringPropertyNames();
			
			for (String key : keys) {
				r[Integer.parseInt(key.substring(0, 1))] = prop.getProperty(key);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(iStream != null){
					iStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return r;
		
	}
	
}
