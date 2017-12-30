
/**
 * The Employee public base class 'extends' the person class, and therefore inherits from the Person class. This 
 * inheritance relationship states that an Employee 'is a' Person. The class establishes the attributes of an employee
 * (employee number, department, start date, salary, email), and sets and gets their values. All of the attributes are
 * of type String, apart from employee number (int) and salary (float).  
 * 
 * @author Adam Martin 11021206
 * @version 1.0
 * 
 */

public class Employee extends Person {
		
	// Employee attributes (employee number, department, start date, salary, email)
	private int employeeNumber; 
	private String department; 
	private String startDate; 
	private float salary; 
	private String email;
	
	
	/** 
	 * The Employee public constructor constructs a person using the attributes established above, and the attributes of
	 * the 'Person' class, which this class inherits from. The 'super' class is used to obtain the Person values and establish 
	 * them as 'Employee' values also. An employee has a name, gender, date of birth, address, postcode, employee number, 
	 * department, start date, salary and email address. 
	 * 
	 * @param name, gender, dob, address, postcode, newEmployeeNumber, newDepartment, newStartDate, newSalary, newEmail
	 * 
	 * @author Adam Martin
	 * @version 1.0
	 */
		
	// Employee 'Constructor' - Constructs an Employee using 'Person' + 'Employee' values (name, gender,  date of birth, address, postcode, employee number, department, start date, salary, email)
	public Employee(String name, String gender, String dob, String address, String postcode, 
			int newEmployeeNumber, String newDepartment, String newStartDate, float newSalary, String newEmail)
	{
		// 'super' class is called to tell java to use the 'Person' attributes in the 'Employee'
		super(name, gender, dob, address, postcode);

		// Initialization of Employee attributes 
		this.employeeNumber = newEmployeeNumber; 
		this.department = newDepartment;
		this.startDate = newStartDate;
		this.salary = newSalary;
		this.email = newEmail;		
	}
	
	
	// 'Setter and Getter' methods to get and return values
	
	
	/**
	 * This employee 'setter' sets the employee's employee number number.
	 * 
	 * @param newEmployeeNumber
	 *  
	 * @author Adam Martin
	 * @version 1.0
	 */
	
    // Sets the employee number
	public void setEmployeeNumber (int newEmployeeNumber)
	{
		this.employeeNumber = newEmployeeNumber;
	}
	
	
	/** 
	 * This employee 'getter' gets the employee number of the employee 
	 * 
	 * @return the employee's employee number
	 * 
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Gets the employee number
	public int getEmployeeNumber()
	{
		return this.employeeNumber;
	}
	
	
	/**
	 * This employee 'setter' sets the employee's department
	 * 
	 * @param newDepartment
	 *  
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Sets the department	
	public void setDepartment(String newDepartment)
	{
		this.department = newDepartment;
	}
	
	
	/** 
	 * This employee 'getter' gets the employee's department 
	 * 
	 * @return the employee's department
	 * 
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Gets the department
	public String getDepartment()
	{
		return this.department; 
	}
	
	
	/**
	 * This employee 'setter' sets the employee's start date
	 * 
	 * @param newStartDate
	 *  
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Sets the start date
	public void setStartDate(String newStartDate)
	{
		this.startDate = newStartDate;
	}
	
	
	/** 
	 * This employee 'getter' gets the employee's start date 
	 * 
	 * @return the employee's start date
	 * 
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Gets the start date
	public String getStartDate()
	{
		return this.startDate;
	}
	
	
	/**
	 * This employee 'setter' sets the employee's salary
	 * 
	 * @param newSalary
	 *  
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Sets the salary
	public void setSalary(float newSalary)
	{
		this.salary = newSalary;
	}
	
	
	/** 
	 * This employee 'getter' gets the employee's salary
	 * 
	 * @return the employee's salary
	 * 
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Gets the salary
	public float getSalary()
	{
		return this.salary;
	}
	
	
	/**
	 * This employee 'setter' sets the employee's email address
	 * 
	 * @param newEmail
	 *  
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Sets the email
	public void setEmail(String newEmail) 
	{
		this.email = newEmail;
	}
	
	
	/** 
	 * This employee 'getter' gets the employee's email address
	 * 
	 * @return the employee's email address
	 * 
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Gets the email
	public String getEmail()
	{
		return this.email;
	}
	
		
	
	
	/** 
	 * This toString() method puts all of the 'Employee' attributes in a string, to avoid the Employee being outputted
	 * as byte code. The 'super' class is used when getting Person attributes to call them from the 'Person' class.
	 * 
	 * @author Adam Martin
	 * @version 1.0 
	 * 
	 * 
	 */
	
	public String toString()
	{
		return "Employee Name: "+super.getName()+" Gender: "+super.getGender()+
				" Date of Birth: "+super.getDOB()+" Address: "+super.getAddress()+
				" Postcode: "+super.getPostcode()+" Employee Number "+this.getEmployeeNumber()+
				" Department "+this.getDepartment()+" Start Date: "+this.getStartDate()+
				" Salary: "+this.getSalary()+" Email: "+this.getEmail();				
	}

	
}

		