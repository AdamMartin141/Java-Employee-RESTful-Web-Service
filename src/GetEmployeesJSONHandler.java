
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


/**
 * The GetEmployeesJSONHandler public class specifies the content of the "/get-json" server context created in the 
 * ControllerHttpServer class. The GetEmployeesJSONHandler page displays the contents of 'employees' database table, in 
 * JSON format. 
 * 
 * @author Adam Martin 11021206
 * @version 1.0
 * 
 */

public class GetEmployeesJSONHandler implements HttpHandler {
	
	// Creates an instance of the EmployeeDAO class for calling DAO CRUD methods
	final EmployeeDAO DAO = new EmployeeDAO();
	
	
	/**
	 * The handle public method uses HttpExchange to output specified content to the browser, on the specified server 
	 * context ("/get-json"). This is achieved by calling the DAO 'getAllEmployees' method. All records contained within 
	 * the 'employees' database table are outputted in JSON format.
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
		
        // Console output - tells users when the employee list has been printed to the browser in JSON format
		System.out.println("JSON Operation - All employee records printed to browser in JSON format using getAllEmployees method");
		System.out.println();
		
		// Sets a 'head' title for the web page
		final String head = "Employee Database - All Records (JSON Format):";
				
		// Buffered writer for outputting content to the browser
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));

		// Executes the getAllEmployees DAO method for printing records to the browser
		ArrayList<Employee> employees = null;
		try {
			employees = DAO.getAllEmployees();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Uses GSON to convert the employee ArrayList to a JSON string and output to browser
		Gson gson = new Gson();
		he.sendResponseHeaders(200, 0); // Sends '200 okay' HTTP response header to display the content

		String employeesJson = gson.toJson(employees); // Puts the employees in a JSON string
		
		// Outputs the 'head' web page title to the browser
		out.write(head);
		
		// Prints the JSON string of employees to the browser
		out.write(employeesJson);
		out.close();
		System.out.println(employeesJson); // Prints the JSON string of employees to the console for user convenience
		System.out.println();

	}
}



