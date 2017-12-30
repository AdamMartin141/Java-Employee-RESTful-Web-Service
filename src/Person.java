
/**
 * The Person public base class establishes the attributes of a person (name, gender, date of birth, address, postcode), and 
 * sets and gets their values. All of the attributes are of type String. These attributes are used in the 'Employee' 
 * class.  
 * 
 * @author Adam Martin 11021206
 * @version 1.0
 * 
 */

public class Person {
		
	// Person attributes (name, gender, date of birth, address, and postcode) 
	private String name; 
	private String gender;
	private String dob; 
	private String address; 
	private String postcode;
	
	
	/** 
	 * The Person public constructor constructs a person using the attributes established above. A person has a name, 
	 * gender, date of birth, address and postcode. 
	 * 
	 * @param newName, newGender, newDOB, newAddress, newPostcode
	 * 
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Person 'Constructor' - constructs a Person using attributes established above 
	public Person(String newName, String newGender, String newDOB, String newAddress, String newPostcode)
	{
		// Initialization of Person attributes 
		this.name = newName; 
		this.gender = newGender;
		this.dob = newDOB;
		this.address = newAddress; 
		this.postcode = newPostcode;	
	}
	
	
	// 'Setter and Getter' methods to 'set' and 'get' person attributes

	
	/**
	 * This person 'setter' sets the name of the person
	 * 
	 * @param newName
	 *  
	 * @author Adam Martin
	 * @version 1.0
	 */
			
	// Sets the name
	public void setName(String newName)
	{
		this.name = newName;
	}
	
	
	/** 
	 * This person 'getter' gets the name of the person 
	 * 
	 * @return the person's name
	 * 
	 * @author Adam Martin
	 * @version 1.0
	 */
		
	// Gets the name
	public String getName()
	{
		return this.name;
	}
	
	
	/**
	 * This person 'setter' sets the gender of the person
	 * 
	 * @param newGender
	 *  
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Sets the gender
	public void setGender(String newGender)
	{
		this.gender = newGender;
	}
	
	
	/** 
	 * This person 'getter' gets the gender of the person 
	 * 
	 * @return the person's name
	 * 
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Gets the gender
	public String getGender()
	{
		return this.gender;
	}
	
	
	/**
	 * This person 'setter' sets the date of birth of the person
	 * 
	 * @param newDOB
	 *  
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Sets the date of birth
	public void setDOB(String newDOB)
	{
		this.dob = newDOB;
	}
	
	
	/** 
	 * This person 'getter' gets the date of birth of the person 
	 * 
	 * @return the person's name
	 * 
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Gets the date of birth
	public String getDOB() 
	{
		return this.dob;
	}
	
	
	/**
	 * This person 'setter' sets the address of the person
	 * 
	 * @param newAddress
	 *  
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Sets the address
	public void setAddress(String newAddress)
	{
		this.address = newAddress;
	}
	
	
	/** 
	 * This person 'getter' gets the address of the person
	 *  
	 * @return the person's name
	 * 
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Gets the address
	public String getAddress()
	{
		return this.address; 
	}
	
	
	/**
	 * This person 'setter' sets the postcode of the person
	 * 
	 * @param newPostcode
	 *  
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Sets the postcode
	public void setPostcode(String newPostcode)
	{
		this.postcode = newPostcode;
	}
	
	
	/** 
	 * This person 'getter' gets the postcode of the person 
	 * 
	 * @return the person's name
	 * 
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Gets the postcode
	public String getPostcode()
	{
		return this.postcode;
	}

}
