package edu.asupoly.ser422.phone.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import edu.asupoly.ser422.phone.model.PhoneBook;

public class PhoneBookListFactory {
	private static PhoneBookList _phoneService;
	
	/**
	 * Intentionally left blank. Not used.
	 */
	private PhoneBookListFactory() {};
	
	/**
	 * Singleton factory for PhoneBookList
	 * 
	 * @return
	 */
	public static PhoneBookList getInstance() {
		if(_phoneService == null) {
			try {
				Properties properties = new Properties();
				properties.load(PhoneBookListFactory.class.getClassLoader().getResourceAsStream("/phone.properties"));
				String folderPath = properties.getProperty("folderPath");
				//is = new FileInputStream("/Users/nolanbruce/Documents/ASU/SER422/Week3/lab3/lab3-nmbruce/resources/phonebook.txt");
			    _phoneService = new PhoneBookList(folderPath);
			} catch (FileNotFoundException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			} catch (IOException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
		}
		return _phoneService;
	}

}
