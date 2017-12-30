import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



/**
 * The EmployeeDAO public class contains a range of public methods, which will be called by other methods for CRUD. When called, 
 * these methods will connect to the database, get all employee records from the database, get a single employee record from the 
 * database, insert an employee record into the database, update all attributes of an existing employee record, and delete an 
 * employee record from the database.  
 * 
 * @author Adam Martin 11021206
 * @version 1.0
 * 
 */

public class EmployeeDAO {
	
	
	/**
	 * The public getDBConnection class connects to the JDBC driver, and then to the 'empdb' SQLite database file. This enables
	 * the program to manipulate the data in this database. If the connection is successful, the method returns the 
	 * database connection. Otherwise, the method throws an SQLException.
	 * 
	 * @return dbConnection
	 * @throws SQLException
	 * 
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	// Method for getting the database connection
	public Connection getDBConnection() {
		
		Connection dbConnection = null;
		
		try {
			Class.forName("org.sqlite.JDBC"); // Links to the JDBC driver file
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			String dbURL = ("jdbc:sqlite:empdb.sqlite"); // Links the program to the 'empdb' database file
			dbConnection = DriverManager.getConnection(dbURL);
			return dbConnection; 
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return dbConnection; 	 	
	}
	
	
	
	/**
	 * The public getAllEmployees method connects to the database using the getDBConnection method, and then uses an SQL 
	 * statement to obtain all records from the 'employees' database table. These methods are then placed in array list. 
	 * When the method has been called successfully, it prints confirmation to the console screen. 
	 * Otherwise, the method throws an SQLException. 
	 * 
	 * @return allEmployees
	 * @throws SQLException
	 * 
	 * @author Adam Martin
	 * @version 1.0
	 */
	
	    // Method for getting a list of all employees in the database
    	public ArrayList<Employee> getAllEmployees() throws SQLException {
		
		Connection dbConnection = null; 
		Statement statement = null;
		ResultSet resultset = null; 
		String query = "SELECT * FROM employees;"; // Selects everything from the 'employees' database table
		Employee tempEmp = null; 
		
		ArrayList<Employee> allEmployees = new ArrayList<>(); // Creates an 'Array List' (an array with an undefined length) for the database records 
		
		try {
			// Opens the database connection by calling the getDBConnection method
			dbConnection = getDBConnection(); 
			System.out.println("---------------------------------------------------");
			System.out.println("Get All Employees - Database successfully opened");
			
			// Executes the SQL statement
			statement = dbConnection.createStatement(); 
			System.out.println("SQL Statement: "+query);
			resultset = statement.executeQuery(query); 
			
			// Adds the table columns to the result set
			while (resultset.next()) {
			String name = resultset.getString("Name"); 
			String gender = resultset.getString("Gender"); 
			String dob = resultset.getString("DOB"); 
			String address = resultset.getString("Address"); 
			String postcode = resultset.getString("Postcode"); 
			
			int employeeNumber = resultset.getInt("EmployeeNumber"); 
			String department = resultset.getString("Department"); 
			String startDate = resultset.getString("StartDate");
			float salary = resultset.getFloat("Salary");
			String email = resultset.getString("Email");
				
			tempEmp = new Employee(name, gender, dob, address, postcode, employeeNumber,
					department, startDate, salary, email);
				
			allEmployees.add(tempEmp);				
			}
		
			// Catches any SQL exceptions and closes the result set
		} catch (SQLException e) { 
			System.out.println(e.getMessage());
		} finally {
			if (resultset != null) {
				resultset.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (dbConnection != null) {
				dbConnection.close();
			}
		}
		
		// Console output for users
		System.out.println("Get All Employees - Records successfully located");
		System.out.println("---------------------------------------------------");
		System.out.println();
		System.out.println("Get All Employees - All Employee Records:");
		System.out.println();
		
		return allEmployees;
		
	}
    
    	
    	
    	/**
    	 * The public getEmployee method connects to the database using the getDBConnection method, and then uses an SQL 
    	 * statement to get an employee with a specific 'id' from the 'employees' database table. When the method has been called 
    	 * successfully, it prints confirmation to the console screen. Otherwise, the method throws an SQLException. 
    	 * 
    	 * @param id
    	 * @return tempEmp
    	 * @throws SQLException
    	 * 
    	 * @author Adam Martin
    	 * @version 1.0
    	 */	
	
    	// Method for getting a single employee record from the database based on a given id (or 'Employee Number')
    	public Employee getEmployee(String id) throws SQLException {
    		
    		Connection connection = null;
    		Statement statement = null;
    		Employee tempEmp = null;
	        String query = "SELECT * FROM employees WHERE EmployeeNumber = '" + id + "'"; // Gets an employee based a given Employee Number (or 'id') 
    		ResultSet resultset = null; 

    			
    	    try { // Opens the database connection by calling the getDBConnection method
    			connection = getDBConnection();	
    			connection.setAutoCommit(false);
    			
    			// Console output for users
    			System.out.println();
    			System.out.println("-------------------------------------------------------------------------------");
    			System.out.println("Get Employee - Database successfully opened");

    			// Executes the SQL statement
    			statement = connection.createStatement();
    			System.out.println("SQL Statement: "+query);
    			System.out.println("Get Employee - Record successfully located");
    			System.out.println("-------------------------------------------------------------------------------");
    			System.out.println();
    			System.out.println("Get Employee by ID - Record:");
    			System.out.println();
    			resultset=statement.executeQuery(query);
    			
    			// Adds the table columns to the result set
    			String name = resultset.getString("Name");
    			String gender = resultset.getString("Gender");
    			String dob = resultset.getString("DOB");
    			String address = resultset.getString("Address");
    			String postcode = resultset.getString("Postcode");
    			int employeeNumber = resultset.getInt("EmployeeNumber");
    			String department = resultset.getString("Department");
    			String startDate = resultset.getString("StartDate");
    			float salary = resultset.getFloat("Salary");
    			String email = resultset.getString("Email");
   			
    			tempEmp = new Employee(name, gender, dob, address, postcode, employeeNumber,
    					department, startDate, salary, email);
    			
    			// Prints the chosen Employee record to the console
    			System.out.println("Employee Record: " + tempEmp);
    				
    			// Closes the result set and catches any exceptions
    			resultset.close();
    			statement.close();
    			connection.commit();
    			connection.close();
    				
    			} catch ( Exception e ) {
    				System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    				System.exit(0);
    			}
    		
    		return tempEmp;
    		}
    	
    	
    	
    	/**
    	 * The public insertEmployee method connects to the database using the getDBConnection method, and then uses an SQL 
    	 * statement to insert an employee record into the 'employees' database table. When the method has been called 
    	 * successfully, it prints confirmation to the console screen. Otherwise, the method throws an SQLException. 
    	 * 
    	 * @param emp
    	 * @return insertResult
    	 * @throws SQLException
    	 * 
    	 * @author Adam Martin
    	 * @version 1.0
    	 */
   	 	
    	// Method for inserting an employee record into the database
    	public Boolean insertEmployee(Employee emp) throws SQLException {
    		{
    		
    		Boolean insertResult = false;
    		Connection connection = null;
    		Statement statement = null;
    		String query = "INSERT INTO employees (Name, Gender, DOB, Address, Postcode, EmployeeNumber, Department, StartDate, Salary, Email) "
    				+ "VALUES ('"+emp.getName() + "','" +   emp.getGender() + "','" + emp.getDOB() + "','"   + emp.getAddress() + "','" +
    				emp.getPostcode() + "','" + emp.getEmployeeNumber() + "','" + emp.getDepartment() + "','" + emp.getStartDate() + "','" +
    				emp.getSalary() + "','" + emp.getEmail() + "')"; // Inserts an Employee by calling 'getters' created in Person and Employee classes
    		
    		try {
    			// Opens the database connection by calling the getDBConnection method
    			connection = getDBConnection();	
    			connection.setAutoCommit(false);  
    		    System.out.println("");
    		    System.out.println("-----------------------------------------------------------------------------------------------");
    			System.out.println("Insert Employee - Database successfully opened");
    			
    			// Executes the SQL statement
    			statement = connection.createStatement(); 
    			System.out.println("SQL Statement: "+query);
    			statement.executeUpdate(query);
    			
    			// Closes the result set and catches any exceptions
    			statement.close();
    			connection.commit();
    			connection.close();
    			   				
    			} catch ( Exception ex ) {
    			    System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
    				System.exit(0);
    			}
    		
                // Console output for users    		
    			System.out.println("Insert Employee - Records successfully created");
    			System.out.println("-----------------------------------------------------------------------------------------------");
    		   		    
    		return insertResult;
    		}
    	}
    	
  
    	
    	/**
    	 * The public updateEmployee method connects to the database using the getDBConnection method, and then uses an SQL 
    	 * statement to update an employee record in the 'employees' database table. When the method has been called 
    	 * successfully, it prints confirmation to the console screen. Otherwise, the method throws an SQLException. 
    	 * 
    	 * @param emp
    	 * @return updateResult
    	 * @throws SQLException
    	 * 
    	 * @author Adam Martin
    	 * @version 1.0
    	 */	
    	
    	// Method for updating an employee record in the database
    	public Boolean updateEmployee(Employee emp) throws SQLException {
    		{
    			
        Boolean updateResult = false;	
	    Connection connection = null;
	    Statement statement = null;
		String query = "UPDATE employees SET Name = '"+emp.getName()+"', Gender = '"+emp.getGender()+"', "
				+ "DOB = '"+emp.getDOB()+"', Address = '"+emp.getAddress()+"', Postcode = '"+emp.getPostcode()+"', "
				+ "Department = '"+emp.getDepartment()+"', StartDate = '"+emp.getStartDate()+"', "
				+ "Salary = '"+emp.getSalary()+"', Email = '"+emp.getEmail()+"' "
				+ "WHERE EmployeeNumber = '" + emp.getEmployeeNumber() + "'"; // Updates an employee assigned to a certain employee number. Calls 'getters' from Person and Employee classes to assign values
	    
	    try {
			// Opens the database connection by calling the getDBConnection method
			connection = getDBConnection();	
			connection.setAutoCommit(false);
			
			// Console output for users
			System.out.println();	
			System.out.println("--------------------------------------------------------------------------------------------------------------");
			System.out.println("Update Employee - Database successfully opened");
			    
			// Executes the SQL statement
			statement = connection.createStatement();
			System.out.println("SQL Statement: "+query);
			statement.executeUpdate(query);
			
			// Closes the result set and catches any exceptions
			connection.commit();
			statement.close();
			connection.close();
			
			} catch ( Exception e ) {
			    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			    System.exit(0);
		    }
	    
	            // Console output for users
			    System.out.println("Update Employee - Records successfully updated");
			    System.out.println("--------------------------------------------------------------------------------------------------------------");
			    
		    return updateResult;
		    }
	     }
    	
    	
    	
    	/**
    	 * The public deleteEmployee method connects to the database using the getDBConnection method, and then uses an SQL 
    	 * statement to delete an employee with a specific 'id' from the 'employees' database table. When the method has been called 
    	 * successfully, it prints confirmation to the console screen. Otherwise, the method throws an SQLException. 
    	 * 
    	 * @param id
    	 * @return deleteResult
    	 * @throws SQLException
    	 * 
    	 * @author Adam Martin
    	 * @version 1.0
    	 */
    	    	
    	// Method for deleting an employee record from the database
    	public Boolean deleteEmployee(String id) throws SQLException {
    		{
    			
        Boolean deleteResult = false;
		Connection connection = null;
        Statement statement = null;
        String query = "DELETE FROM employees WHERE EmployeeNumber = '" + id + "'"; // Deletes an employee with a given employee number (or 'id')
        
        try {
			// Opens the database connection by calling the getDBConnection method
			connection = getDBConnection();	
			connection.setAutoCommit(false);
			
			// Console output for users
			System.out.println();
			System.out.println("--------------------------------------------------------------------");
			System.out.println("Delete Employee - Database successfully opened");
			
			// Executes the SQL statement
			statement = connection.createStatement();
			System.out.println("SQL Statement: "+query);
			statement.executeUpdate(query);
			
			// Closes the result set and catches any exceptions
			connection.commit();
			statement.close();
			connection.close();
			
			} catch ( Exception e ) {
			    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			    System.exit(0);
			}
                // Console output for users
			    System.out.println("Delete Employee - Records successfully deleted");
			    System.out.println("--------------------------------------------------------------------");
			    
			return deleteResult;
		    }
      }
    	   	     		
}
   			
    			
 		
   