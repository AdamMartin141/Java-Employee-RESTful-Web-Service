
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * The DatabaseTester public class calls the EmployeeDAO class and its methods, and prints the results to the 
 * console screen for testing purposes. This class prints all employees, gets a single employee, inserts a new 
 * employee, updates an existing employee, and deletes an existing employee.   
 * 
 * @author Adam Martin 11021206
 * @version 1.0
 * 
 */

public class DatabaseTester {
	
	
	/**
	 * The main method within this class calls all methods from the EmployeeDAO class, and prints their results to the
	 * console screen. The parameters from the EmployeeDAO method are passed here, in order to test their 
	 * functionality.      
	 * 
	 * @author Adam Martin
	 * @version 1.0
	 * 
	 */

	public static void main(String[] args) {

		// Calls getAllEmployees method from EmployeeDAO and prints results to the console screen
		EmployeeDAO dao = new EmployeeDAO();
		ArrayList<Employee> allEmployees = new ArrayList<>();

		try {
			allEmployees = dao.getAllEmployees(); // Calls the method from the EmployeeDAO

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Iterates through all employees using toString() method
		for (Employee e : allEmployees) {
			System.out.println(e); // Prints the array list of employees to the console screen
		}
		
		
		
		// Calls getEmployee method from EmployeeDAO and prints results to the console screen
		EmployeeDAO dao2 = new EmployeeDAO();
		Employee tempEmp = new Employee(null, null, null, null, null, 0, null, null, 0, null); // Constructs a temporary employee to hold values
		String id1 = "5"; // Tells the program which employee number record to retrieve
		{

			try {
				tempEmp = dao2.getEmployee(id1); // Retrieves the employee with the 'id' (or 'Employee Number') set above

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		

		
		// Calls insertEmployee method from EmployeeDAO
		EmployeeDAO dao3 = new EmployeeDAO();
		Boolean insertResult = false;
		
		// Creates a new employee object to insert into the database
		Employee e = new Employee("Sam James", "M", "14-04-1960", "Stoke", "S1 2EQ", 11, "Economics",
				"01-09-2012", 49000, "sam@mail.com");

		try {
			insertResult = dao3.insertEmployee(e); // Inserts the above-created employee object into the database

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		

		
		// Calls updateEmployee method from EmployeeDAO
		EmployeeDAO dao5 = new EmployeeDAO();
		boolean updateResult = false;
		
		// Creates a temporary employee record to hold values, then updates the values of the given employee number record 
		Employee David = new Employee("David Thompson", "M", "09-04-1990", "Sheffield", "S3 2QW", 7, "Media Studies", "23-07-2015", 35000, "david@mail.com");

		try {
        dao.updateEmployee(David); // Executes the update
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		
		
		// Calls deleteEmployee method from EmployeeDAO
		EmployeeDAO dao4 = new EmployeeDAO();
		boolean deleteResult = false;
		String id = "9"; // Tells the program which employee number record to delete

		try {
			deleteResult = dao4.deleteEmployee(id); // Executes the deletion

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

}
