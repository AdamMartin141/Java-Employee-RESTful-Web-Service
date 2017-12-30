import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


/**
 * The ProcessEmployeeRetrieveHandler public class specifies the content of the "/process_search" server context created in the 
 * ControllerHttpServer class. The ProcessEmployeeRetrieveHandler calls the DAO 'getEmployee' method, and is set up to 
 * take in an input request from the user. This input request takes the form of an employee number. When this request 
 * is processed, the employee record attached to the employee number is retrieved and sent to the console. 
 * This functionality can be tested using the Firefox RESTclient.  
 * 
 * @author Adam Martin 11021206
 * @version 1.0
 * 
 */

public class ProcessEmployeeRetrieveHandler implements HttpHandler {

	// Creates an instance of the EmployeeDAO class for calling DAO CRUD methods
	final EmployeeDAO DAO = new EmployeeDAO();
	
	
	/**
	 * The handle public method uses HttpExchange to process a request  on the specified server context ("/process_search"). 
	 * In this case, an input request, which takes the form of an employee number, is taken. When this request is processed, 
	 * When this request is processed, the DAO 'getEmployee' method is called to retrieve the employee record attached to the 
	 * employee number from the database. This employee record is then sent to the console. This functionality can be tested
	 * using the Firefox RESTclient.  
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
		}

		// Buffered writer for outputting content to the browser
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));

		try {
			he.sendResponseHeaders(200, 0); // Sends '200 okay' HTTP response header to display the content

			// Calls the DAO 'getEmployee' method to retrieve an employee based on the request (an employee number)
			Employee emp = DAO.getEmployee(request);
			
			// Converts the employee object to a String using the 'toString' method for readable output
			String employee = emp.toString();

			// Outputs the requested employee number and employee to the user
			out.write("Successfully Retrieved Employee " + request + ": " + employee);
		}

		catch (SQLException se) {
			
			// Sends a HTTP 500 (Internal Server Error) error to the user if there is an error during the process 
			he.sendResponseHeaders(500, 0); 
			out.write("Error retrieving Employee");
		}

		finally {
			out.close();
		}

	}

}
