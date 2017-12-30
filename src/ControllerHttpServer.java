
import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;


/**
 * The ControllerHttpServer public class contains code for the creation of URL 'handlers' for each server task class. 
 * The class also contains code for starting the local host server, running on port 8014.    
 * 
 * @author Adam Martin 11021206
 * @version 1.0
 * 
 */

public class ControllerHttpServer {
	
	
	/**
	 * The main method within this class contains code for the creation of URL 'handlers' for each server task class. 
	 * The class also contains code for starting the local host server, running on port 8014. When the server is running
	 * successfully, a confirmation message is outputted to the console screen. Otherwise, an IOException error 
	 * message displays.    
	 * 
	 * @author Adam Martin
	 * @version 1.0
	 * 
	 */

	public static void main(String[] args) {

		try {
			// Create the server on port 8000
			HttpServer server = HttpServer.create(new InetSocketAddress(8014), 0);
			
			// Creates instances of each handler class, and maps them to RESTful URLs
			// Handlers allow user to: 
			// Get all employees in text and JSON format
			// Post an Employee object to the database in JSON format
			// Retrieve a single Employee record based on a given Employee Number
			// Update an Employee record with a given Employee Number 
			// Delete an Employee record with a given Employee Number 
			server.createContext("/", new HomeHandler());
			server.createContext("/get-employees", new GetEmployeesTextHandler());
			server.createContext("/get-json", new GetEmployeesJSONHandler());
			server.createContext("/process_post", new ProcessJSONPostHandler());
			server.createContext("/process_search", new ProcessEmployeeRetrieveHandler());
			server.createContext("/process_update", new ProcessEmployeeUpdateHandler());
			server.createContext("/process_delete", new ProcessEmployeeDeleteHandler());

			// Start the server on port 8014, notify user of this at the console
			System.out.println("Server running on port 8014");
			server.start();
			System.out.println();
						
		}
		
		// Catch any exceptions and display an error message in case of a server problem
		catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage() + "  " + ioe.getStackTrace());
		}

	}
}

