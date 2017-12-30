import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


/**
 * The GetEmployeesTextHandler public class specifies the content of the "/get-employees" server context created in the 
 * ControllerHttpServer class. The GetEmployeesTextHandler page displays the complete 'employees' database table for 
 * the user. 
 * 
 * @author Adam Martin 11021206
 * @version 1.0
 * 
 */

public class GetEmployeesTextHandler implements HttpHandler {
	
	// Creates an instance of the EmployeeDAO class for calling DAO CRUD methods
	final EmployeeDAO DAO = new EmployeeDAO();
	
	
	/**
	 * The handle public method uses HttpExchange to output specified content to the browser, on the specified server 
	 * context ("/get-employees"). This is achieved by calling the DAO 'getAllEmployees' method. All records contained 
	 * within the 'employees' database table are outputted in a HTML table.
	 * 
	 * @param he
	 * @throws IOException
	 * 
	 * @author Adam Martin
	 * @version 1.0
	 * 
	 */
	
	// Handle method for browser output
	public void handle(HttpExchange he) throws IOException {
		
        // Console output - tells users when the employee list has been printed to the browser
		System.out.println("Print Operation - All employee records printed to browser");
		System.out.println();

		// HTML table for easily-readable browser display of the 'employees' database table
		final String head = "<html><head></head><body><h1>Employee Database - All Records: </h1><table><tr>"
				+ "<th>Employee Number</th>"
				+ "<th>Name</th>"
				+ "<th>Gender</th>"
				+ "<th>DOB</th>"
				+ "<th>Address</th>"
				+ "<th>Postcode</th>"
				+ "<th>Department</th>"
				+ "<th>Start Date</th>"
				+ "<th>Salary</th>"
				+ "<th>Email</th>"
				+ "</tr>";
		
		
		// Buffered writer for outputting content to the browser
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));

		// Executes the getAllEmployees DAO method for printing records to the browser
		ArrayList<Employee> employees = null;
		try {
			employees = DAO.getAllEmployees();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Sends '200 okay' HTTP response header to display the content
		he.sendResponseHeaders(200, 0); 

		// Outputs the 'head' HTML table to the browser
		out.write(head);
		for (Iterator<Employee> iterator = employees.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			
		// Outputs the list of employees to the browser (inside the 'head' HTML table)
		out.write(
				"<tr><td>" 
				+ employee.getEmployeeNumber() + "</td><td>"
			    + employee.getName() + "</td><td>" 
				+ employee.getGender() + "</td><td>"
				+ employee.getDOB() + "</td><td>" 
				+ employee.getAddress() + "</td><td>"
				+ employee.getPostcode() + "</td><td>" 
				+ employee.getDepartment() + "</td><td>" 
				+ employee.getStartDate() + "</td><td>"
				+ employee.getSalary() + "</td><td>" 
				+ employee.getEmail() + 
				"</tr>");
			
		
         // Prints the records to the browser simultaneously for user convenience 
		 System.out.println(employee.getName()
				+ " " + employee.getGender()
				+ " " + employee.getDOB()
				+ " " + employee.getAddress()
				+ " " + employee.getPostcode() 
				+ " " + employee.getEmployeeNumber()
				+ " " + employee.getDepartment()
				+ " " + employee.getStartDate()
				+ " " + employee.getSalary() 
				+ " " + employee.getEmail());
		}

		out.close();
	}

}


