import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


/**
 * The ProcessEmployeeUpdateHandler public class specifies the content of the "/process_update" server context created in the 
 * ControllerHttpServer class. The ProcessEmployeeUpdateeHandler calls the DAO 'updateEmployee' method, and is set up to 
 * update attributes of an employee with a specified employee number. To achieve this, the program takes in an employee object, 
 * containing updated attributes (in JSON format). The JSON string is then converted to an employee object, with the updated attributes 
 * attached to the specified database record. This functionality can be tested using the Firefox RESTclient.  
 * 
 * @author Adam Martin 11021206
 * @version 1.0
 * 
 */

public class ProcessEmployeeUpdateHandler implements HttpHandler {

	
	// Creates an instance of the EmployeeDAO class for calling DAO CRUD methods
	final EmployeeDAO DAO = new EmployeeDAO();

	
	/**
	 * The handle public method uses HttpExchange to process a request  on the specified server context ("/process_update"). 
	 * In this case, an input request, which takes the form of an employee object (in JSON format) containing updated 
	 * attributes, is taken. The JSON string is then converted to an employee object, with the updated attributes attached to the 
     * specified database record, using the DAO 'updateEmployee' method. This functionality can be tested using the Firefox RESTclient.  
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

		// Sets a line and request to be read in
		String line = ""; 
		String request = ""; 
		
		// Read in a request while the request line is not null		
		BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
		while ((line = in.readLine()) != null) {
			request = request + line;
			System.out.println(request);
		}
		
		// Buffered writer for outputting content to the browser
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		
		// Converts the user's updated JSON Employee String 'from JSON' to a regular Employee object
		Gson gson = new Gson();
        Employee updatedEmp = gson.fromJson(request, Employee.class);	
        
				
		try {
			he.sendResponseHeaders(200, 0); // Sends '200 okay' HTTP response header to display the content
			
			// Calls the DAO 'updateEmployee' method to update the attributes of the given Employee object
			DAO.updateEmployee(updatedEmp);
			out.write("Employee successfully updated");
		}
				
		catch (SQLException se) {
			
			// Sends a HTTP 500 (Internal Server Error) error to the user if there is an error during the process
			he.sendResponseHeaders(500, 0); 
			out.write("Error updating Employee");
		}
		
		finally {
			out.close();
		}
		
	}
	
}

