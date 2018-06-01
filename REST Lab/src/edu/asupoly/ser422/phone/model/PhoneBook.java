package edu.asupoly.ser422.phone.model;

import java.io.*;
import java.util.*;

import edu.asupoly.ser422.phone.exceptions.EntryNotFoundException;

public class PhoneBook {
	private String id;

	private Map<String, PhoneEntry> _pbook = new HashMap<String, PhoneEntry>();

	/**
	 * Generates a PhoneBook from the given id, or if none are found, creates a new
	 * file for that
	 * 
	 * @param id				id for PhoneBook
	 * @throws IOException	Thrown in the case of any IO exception
	 */
	public PhoneBook(String id) throws IOException {
		this.id = id;
		
		String folderPath = getFolderPath();
		String fname = folderPath + id + ".txt";
		
		//create file if file doesn't exist
		File file = new File(fname);
		file.createNewFile();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
		
		String name = null;
		String lname = null;
		String phone = null;

		try {
			String nextLine = null;
			while ( (nextLine=br.readLine()) != null)
			{
				name  = nextLine;
				lname = br.readLine();
				phone = br.readLine();
				addEntry(name, lname, phone);
			}
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error process phonebook");
			throw new IOException("Could not process phonebook file");
		}
		
	}
	/**
	 * Generates a PhoneBook from a specific InputStream
	 * 
	 * @param is				InputStream from which to read in PhoneBook
	 * @throws IOException	Thrown in the case of any IO exception
	 */
	public PhoneBook(InputStream is) throws IOException {
		this(new BufferedReader(new InputStreamReader(is)));
	}
	private PhoneBook(BufferedReader br) throws IOException {	
		String name = null;
		String lname = null;
		String phone = null;

		try {
			String nextLine = null;
			while ( (nextLine=br.readLine()) != null)
			{
				name  = nextLine;
				lname = br.readLine();
				phone = br.readLine();
				addEntry(name, lname, phone);
			}
			br.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Error process phonebook");
			throw new IOException("Could not process phonebook file");
		}
	}

	/**
	 * Saves PhoneBook to persistent store
	 * 
	 * @throws IOException	Thrown in the case of any IO exception
	 */
	public void savePhoneBook() throws IOException {
		String fname = getFolderPath() + id  + ".txt";
		try {
			System.out.println("Opening " + fname);
			PrintWriter pw = new PrintWriter(new FileOutputStream(fname));
			System.out.println("...done");
			String[] entries = listEntries();
			for (int i = 0; i < entries.length; i++)
				pw.println(entries[i]);

			pw.close();
		}
		catch (Exception exc)
		{ 
			exc.printStackTrace(); 
			System.out.println("Error saving phone book");
		}
	}

	/**
	 * Edits entry to have a new firstName and lastName
	 * 
	 * @param phone	Phone number for entry to edit
	 * @param fname	New firstName
	 * @param lname	New lastName
	 */
	public void editEntry(String phone, String fname, String lname) {
		PhoneEntry pentry = _pbook.get(phone);
		pentry.changeName(fname, lname);
	}
	
	/**
	 * Adds entry to PhoneBook by Strings
	 * 
	 * @param fname	firstName for new entry
	 * @param lname	lastName for new entry
	 * @param phone	phone number for new entry
	 */
	public void addEntry(String fname, String lname, String phone)
	{ 
		addEntry(phone, new PhoneEntry(fname, lname, phone));
	}

	/**
	 * Adds entry based as a PhoneEntry object
	 * 
	 * @param number		Phone number for new entry
	 * @param entry		PhoneEntry object to be added
	 */
	public boolean addEntry(String number, PhoneEntry entry) {
		_pbook.put(number, entry); 
		try {
			this.savePhoneBook();
			return true;
		} catch(IOException exc) {
			System.out.println("Error saving PhoneBook " + id);
			exc.printStackTrace();
		}
		return false;
	}
	
	/**
	 * Removes entry by phone number
	 * 
	 * @param number		PhoneNumber of entry to be removed
	 * @return			true if number has been successfully removed
	 */
	public boolean removeEntry(String number) {
		if(_pbook.containsKey(number)) {
			_pbook.remove(number);
			try {
				this.savePhoneBook();
			} catch(IOException exc) {
				System.out.println("Error saving PhoneBook " + id);
				exc.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the PhoneBook contains an entry with the given number
	 * 
	 * @param number		Number for entry to search for
	 * @return			true if entry present in PhoneBook, false otherwise
	 */
	public boolean contains(String number) {
		if(_pbook.containsKey(number)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns entries as a String array
	 * 
	 * @return	entries in PhoneBook as String[]
	 */
	public String[] listEntries()
	{
		String[] rval = new String[_pbook.size()];
		int i = 0;
		PhoneEntry nextEntry = null;
		for (Iterator<PhoneEntry> iter = _pbook.values().iterator(); iter.hasNext();) {
			nextEntry = iter.next();
			rval[i++] = nextEntry.toString();
		}
		return rval;
	}
	
	/**
	 * Returns entries as List<PhoneEntry>
	 * 
	 * @return	entries in PhoneBook as List<PhoneEntry>
	 */
	public List<PhoneEntry> getEntries() {
		List<PhoneEntry> deepClone = new ArrayList<PhoneEntry>();
		PhoneEntry nextEntry = null;
		for (Iterator<PhoneEntry> iter = _pbook.values().iterator(); iter.hasNext();) {
			nextEntry = iter.next();
			deepClone.add(new PhoneEntry(nextEntry.getFirstName(), nextEntry.getLastName(), nextEntry.getPhone()));
		}
		return deepClone;
	}
	
	/**
	 * gets subset of PhoneBook based on firstName or lastName
	 * 
	 * @param name		name to search for
	 * @param fname		if true, search for firstName. If false, search for lastName
	 * @return
	 */
	public List<PhoneEntry> getSubset(String name, boolean fname) {
		List<PhoneEntry> entries = this.getEntries();
		List<PhoneEntry> subset = new ArrayList<PhoneEntry>();
		for(int i=0;i<entries.size();i++) {
			PhoneEntry ent = entries.get(i);
			if(fname) {
				if(ent.getFirstName().toLowerCase().contains(name.toLowerCase())) {
					subset.add(ent);
				}
			} else {
				if(ent.getLastName().toLowerCase().contains(name.toLowerCase())) {
					subset.add(ent);
				}
			}
		}
		return subset;
	}
	
	/**
	 * gets subset of PhoneBook based on firstName and lastName
	 * 
	 * @param fname	firstName to search for
	 * @param lname	lastName to search for
	 * @return
	 */
	public List<PhoneEntry> getSubset(String fname, String lname) {
		List<PhoneEntry> entries = this.getEntries();
		List<PhoneEntry> subset = new ArrayList<PhoneEntry>();
		for(int i=0;i<entries.size();i++) {
			PhoneEntry ent = entries.get(i);
			if(ent.getFirstName().toLowerCase().contains(fname.toLowerCase())) {
				if(ent.getLastName().toLowerCase().contains(lname.toLowerCase())) {
					subset.add(ent);
				}
			}
		}
		return subset;
	}
	
	/**
	 * Returns a PhoneEntry based on number
	 * 
	 * @param phone	number to search by
	 * @return	PhoneEntry(firstName "Error" if number not found)
	 */
	public PhoneEntry getEntry(String phone) throws EntryNotFoundException {
		List<PhoneEntry> list = this.getEntries();
		
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getPhone().equals(phone)) {
				return list.get(i);
			}
		}
		
		throw new EntryNotFoundException("No entry with number: " + phone);
	}
	
	/**
	 * Returns id of PhoneBook
	 * 
	 * @return	id of PhoneBook
	 */
	public String getID() {
		return this.id;
	}
	
	/**
	 * Sets id of PhoneBook
	 * 
	 * @param id		id of PhoneBook
	 */
	public void setID(String id) {
		this.id = id;
	}
	
	/**
	 * generates id for a new PhoneBook based on files in persistent store
	 * 
	 * @return	if for PhoneBook
	 */
	public static String generateID() {
		int id = 0;
		
		File folder = new File(getFolderPath());
		File[] files = folder.listFiles();
		
		for(int i=0; i<files.length; i++) {
			if(!files[i].isHidden()) {
				id++;
			}
		}
		
		return Integer.toString(id);

		/* No need to fill in gaps. PhoneBooks can't be deleted by any methods in the API.
	    for (int i = 0; i < files.length; i++) {
	    		boolean flag = true;
	    		String tempID = Integer.toString(i).concat(".txt");
	    		System.out.println("Temp ID: " + tempID);
	    		System.out.println("File: " + files[i].getName());
	    		if (files[i].isFile()) {
	    			if(tempID.equals(files[i].getName())) {
	    				flag = false;
	    			}
	    			listOfFiles[i].getName();
	    		}
	    		if(flag) {
    				return tempID;
    			}
	    	}
	    	*/
	}
	
	/**
	 * gets the size of the PhoneBook
	 * 
	 * @return	number of entries in PhoneBook as int
	 */
	public int getSize() {
		return _pbook.size();
	} 
	
	public static String getFolderPath() {
		try {
			Properties properties = new Properties();
			properties.load(PhoneBookListFactory.class.getClassLoader().getResourceAsStream("/phone.properties"));
			return properties.getProperty("folderPath");
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		return "";
	}
}
