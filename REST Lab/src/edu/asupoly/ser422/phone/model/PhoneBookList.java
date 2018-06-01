package edu.asupoly.ser422.phone.model;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import edu.asupoly.ser422.phone.exceptions.EntryNotFoundException;
import edu.asupoly.ser422.phone.exceptions.PhoneBookNotFoundException;
import edu.asupoly.ser422.phone.model.*;

public class PhoneBookList {
	public static ArrayList<PhoneBook> list;
	
	/**
	 * Generates a new PhoneBookList containing all PhoneBooks in the persistent store
	 * 
	 * @throws FileNotFoundException		thrown in the case of a missing file
	 * @throws IOException				thrown in the case of any other IO exceptions
	 */
	public PhoneBookList(String folderPath) throws FileNotFoundException, IOException {
		list = new ArrayList<PhoneBook>();
		FileInputStream is;
		PhoneBook pBook = null;
		File folder = new File(folderPath);
		File[] files = folder.listFiles();
		int num = 0;
		
		for(int i=0; i<files.length; i++) {
			if(!files[i].isHidden()) {
				num++;
			}
		}
		
		for(int i=0;i<num-1;i++) {
			try {
				String id = Integer.toString(i);
				System.out.println("Adding phonebook to list from: " + id);
			    //is = new FileInputStream(filename);
			    pBook = new PhoneBook(id);
				this.addPhoneBook(pBook);
			} catch (FileNotFoundException e) {
			    e.printStackTrace();
			} catch (IOException e) {
			    e.printStackTrace();
			}
		}
		
		//add Unlisted numbers
		try {
			String id = "Unlisted";
			System.out.println("Adding phonebook to list from: " + id);
		    //is = new FileInputStream(filename);
		    pBook = new PhoneBook(id);
			this.addPhoneBook(pBook);
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
		
	/**
	 * Adds given PhoneBook to the list
	 * 
	 * @param pBook	PhoneBook as object to be added
	 */
	public void addPhoneBook(PhoneBook pBook) {
		list.add(pBook);
	}
	
	/**
	 * Returns PhoneBook at index i in list
	 * 
	 * @param i	index of PhoneBook to return
	 * @return	PhoneBook at index i
	 */
	public PhoneBook get(int i) {
		return list.get(i);
	}
	
	/**
	 * Returns PhoneBook with given id
	 * 
	 * @param id		id for PhoneBook to search for
	 * @return		PhoneBook with passed id
	 */
	public PhoneBook get(String id) {
		PhoneBook pBook = null;
		//search for PhoneBook matching id
		for(int i=0;i<this.getSize();i++) {
			if(this.get(i).getID().equals(id)) {
				System.out.println("Found by ID");
				return this.get(i);
			}
		}
		//if no phonebook matches, create new phonebook with id, add to list, and write to file
		try {
			System.out.println("Creating new pBook");
			pBook = new PhoneBook(id);
			this.addPhoneBook(pBook);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return pBook;
	}
	
	/**
	 * gets size of list
	 *
	 * @return	number of PhoneBooks in list as int
	 */
	public int getSize() {
		return list.size();
	}
	
	/**
	 * Returns true if entry with passed number is in the list
	 * 
	 * @param phone	number to search for
	 * @return	true if number is in list, false otherwise
	 */
	public boolean contains(String phone) {
		for(int i=0;i<this.getSize();i++) {
			if(this.get(i).contains(phone)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * finds entry based on passed number in PhoneBooks (not Unlisted
	 * 
	 * @param phone	number to search for
	 * @return		PhoneEntry with given number
	 */
	public PhoneEntry findEntry(String phone) throws EntryNotFoundException {
		for(int i=0;i<this.getSize();i++) {
			PhoneBook pBook = this.get(i);
			if(!pBook.getID().equals("Unlisted")) {
				List<PhoneEntry> list = pBook.getEntries();
				for(int j=0;j<list.size();j++) {
					if(list.get(j).getPhone().equals(phone)) {
						return list.get(j);
					}
				}
			}
		}
		throw new EntryNotFoundException("No entry with number: " + phone);
	}
	
	/**
	 * Adds entry to a PhoneBook
	 * 
	 * @param ent	PhoneEntry as object to add
	 * @param id		id of PhoneBook to add entry to
	 * @return
	 */
	public boolean addEntry(PhoneEntry ent, String id) {
		try {
			this.findEntry(ent.getPhone());
			return false;
		} catch (EntryNotFoundException exc) {
			exc.getMessage();
		}
		PhoneBook pBook = this.get(id);
		pBook.addEntry(ent.getPhone(), ent);
		pBook = this.get("Unlisted");
		pBook.removeEntry(ent.getPhone());
		return true;
	}
	
	/**
	 * Removes entry with given number
	 * 
	 * @param 	phone	number of entry to remove
	 * @return	true if entry successfully removed, false otherwise
	 */
	public boolean removeEntry(String phone) {
		try {
			return this.removeEntry(this.findEntry(phone));
		} catch(EntryNotFoundException exc) {
			exc.getMessage();
			return false;
		}
	}
	
	/**
	 * Removes passed PhoneEntry from list
	 * 
	 * @param ent	PhoneEntry as object to be removed
	 * @return		true if entry successfully removed
	 */
	public boolean removeEntry(PhoneEntry ent) {
		for(int i=0;i<this.getSize();i++) {
			PhoneBook pBook = this.get(i);
			if(pBook.contains(ent.getPhone())) {
				pBook.removeEntry(ent.getPhone());
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns the id of the PhoneBook containing the given entry
	 * 
	 * @param phone						number to search for
	 * @return							id of PhoneBook containing entry with passed number
	 * @throws EntryNotFoundException	thrown if entry not found in any PhoneBooks in list
	 */
	public String locateEntry(String phone) throws EntryNotFoundException {
		for(int i=0;i<this.getSize();i++) {
			PhoneBook pBook = this.get(i);
			if(pBook.contains(phone)) {
				return pBook.getID();
			}
		}
		throw new EntryNotFoundException("Entry not found with number: " + phone);
	}
}
