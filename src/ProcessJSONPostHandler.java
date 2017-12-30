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
 * The ProcessJSONPostHandler public class specifies the content of the "/process_post" server context created in the 
 * ControllerHttpServer class. The ProcessJSONPostHandler is set up to take in a request, which takes the form of an 
 * employee object, in JSON format. This JSON employee record is then converted to an employee object, and posted to 
 * the database. This functionality can be tested using the Firefox RESTclient.  
 * 
 * @author Adam Martin 11021206
 * @version 1.0
 * 
 */

public class ProcessJSONPostHandler implements HttpHandler {

	
	// Creates an instance of the EmployeeDAO class for calling DAO CRUD methods
	final EmployeeDAO DAO = new EmployeeDAO();

	
	/**
	 * The handle public method uses HttpExchange to process a request  on the specified server context ("/process_post"). 
	 * In this case, an input request, which takes the form of an employee object (in JSON format) is taken. The JSON string 
	 * is then converted to an employee object, and posted to the database, using the DAO 'insertEmployee' method. 
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
		
		// Read in a request while the request line is not null		
		BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));
		while ((line = in.readLine()) != null) {
			request = request + line;
			System.out.println(request);
		}
		
		// Buffered writer for outputting content to the browser
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		
		// Converts the user's JSON Employee String 'from JSON' to a regular Employee object, so it can be added to the database
		Gson gson = new Gson(); 
		Employee JSONemp = gson.fromJson(request, Employee.class); 
		
		out.write(request); 
		
		
		try {
			he.sendResponseHeaders(200, 0); // Sends '200 okay' HTTP response header to display the content
			
			// Calls the DAO 'insertEmployee' method to insert the Employee object into the database
			DAO.insertEmployee(JSONemp);
			out.write("Employee successfully posted in JSON format");
		}
				
		catch (SQLException se) {
			
			// Sends a HTTP 500 (Internal Server Error) error to the user if there is an error during the process
			he.sendResponseHeaders(500, 0); 
			out.write("Error posting Employee in JSON format");
		}
		
		finally {
			out.close();
		}
		
	}
	
}
		
		
		