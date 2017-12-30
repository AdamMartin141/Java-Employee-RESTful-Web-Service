import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


/**
 * The ProcessEmployeeDeleteHandler public class specifies the content of the "/process_delete" server context 
 * created in the ControllerHttpServer class. The ProcessEmployeeDeleteHandler is set up to take in an input request 
 * from the user. This input request takes the form of an employee number. When this request is processed, 
 * the employee record attached to the employee number is deleted from the database. This functionality can be tested 
 * using the Firefox RESTclient.  
 * 
 * @author Adam Martin 11021206
 * @version 1.0
 * 
 */

public class ProcessEmployeeDeleteHandler implements HttpHandler {
	
	// Creates an instance of the EmployeeDAO class for calling DAO CRUD methods
	final EmployeeDAO DAO = new EmployeeDAO();

	
	/**
	 * The handle public method uses HttpExchange to process a request  on the specified server context ("/process_delete"). 
	 * In this case, an input request, which takes the form of an employee number, is taken. When this request is processed, 
	 * the DAO 'deleteEmployee' method is called to delete the employee record attached to the employee number from the database. 
	 * This functionality can be tested using the Firefox RESTclient.  
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
		
		// Buffered reader for reading in data requests
		BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
		
		// Read in a request while the request line is not null
		while ((line = in.readLine()) != null) {
			request = request + line;
			
		// Outputs the data request to the console for user convenience
			System.out.println(request);
		}
		
		// Buffered writer for outputting content to the browser
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));

				
		try {
			he.sendResponseHeaders(200, 0); // Sends '200 okay' HTTP response header to display the content
            
			// Calls the DAO 'deleteEmployee' method to delete an employee based on the request (an employee number)
			DAO.deleteEmployee(request);
			
			// Confirms the deletion to the user
			out.write("Employee successfully deleted" + " ");
			out.write("Employee Number of deleted employee: " + request + " ");

		}
				
		catch (SQLException se) {
			
			// Sends a HTTP 500 (Internal Server Error) error to the user if there is an error during the process 
			he.sendResponseHeaders(500, 0); 
			out.write("Error deleting Employee");
		}
		
		finally {
			out.close();
		}
		
	}
	
}
	
