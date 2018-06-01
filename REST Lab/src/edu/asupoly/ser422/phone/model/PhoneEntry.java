package edu.asupoly.ser422.phone.model;

public class PhoneEntry {
    private String firstname;
    private String lastname;
    private String phone;

    /**
     * Creates a new PhoneEntry with passed parameters
     * 
     * @param fname	firstName for entry
     * @param lname	lastName for entry
     * @param phone	phone number for entry
     */
    public PhoneEntry(String fname, String lname, String phone)
    {
	this.firstname  = fname;
	this.lastname  = lname;
	this.phone = phone;
    }

    /**
     * Changes name of PhoneEntry
     * 
     * @param newfname	new firstName for entry
     * @param newlname	new lastName for entry
     */
    public void changeName(String newfname, String newlname) {
    	firstname = newfname;
    	// Nonsensical, to prove a point
    	try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	lastname  = newlname;
    }
    
    /**
     * Returns PhoneEntry as String
     */
    public String toString()
    { return firstname + "\n" + lastname + "\n" + phone; }
    
    /**
     * gets firstName of entry
     * 
     * @return	firstName of Entry
     */
    public String getFirstName() {
    		return this.firstname;
    }
    
    /**
     * gets lastName of entry
     * 
     * @return	lastName of Entry
     */
    public String getLastName() {
    		return this.lastname;
    }
    
    /**
     * gets phone of entry
     * 
     * @return	phone number of Entry
     */
    public String getPhone() {
    		return this.phone;
    }
}



