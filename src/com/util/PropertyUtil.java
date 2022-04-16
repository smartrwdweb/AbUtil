package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyUtil {

	public static String getAppPropValue(String key) throws IOException {
		String value = null;
		Properties prop = readPropertiesFile(
				Paths.get(Constants.RESOURCE_PATH + Constants.SLASH + Constants.APP_PROP).toString());
		if (null != key && !"".equalsIgnoreCase(key)) {
			value = prop.getProperty(key);
		}
		return value;
	}

	public static String getDBPropValue(String key) throws IOException {
		String value = null;
		Properties prop = readPropertiesFile(
				Paths.get(Constants.RESOURCE_PATH + Constants.SLASH + Constants.DB_PROP).toString());
		if (null != key && !"".equalsIgnoreCase(key)) {
			value = prop.getProperty(key);
		}
		return value;
	}

	public static Properties readPropertiesFile(String fileName) throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}

}
