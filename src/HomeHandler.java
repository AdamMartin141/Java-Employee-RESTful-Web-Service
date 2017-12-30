import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


/**
 * The HomeHandler public class specifies the content of the "/" server context created in the ControllerHttpServer class. 
 * The HomeHandler serves as a homepage/ welcome screen for the RESTful web service, containing instructions on how to
 * use the service.
 * 
 * @author Adam Martin 11021206
 * @version 1.0
 * 
 */

public class HomeHandler implements HttpHandler {
	

	/**
	 * The handle public method uses HttpExchange to output specified content to the browser, on the specified server 
	 * context ("/"). In this case, a home page for the RESTful web service is being outputted using HTML.
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
		
		// Buffered writer for outputting content to the browser
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody()));
		
		// Sends '200 okay' HTTP response header to display the content
		he.sendResponseHeaders(200, 0);
		
		// Uses Buffered Writer to writes instructions for using the RESTful web service to the browser
		out.write("<html><head></head><body><h1> Welcome to the Employee database service, running on server port 8014! </h1>");
		out.write("\n");
		out.write("<h3> Instructions for using the online Employee Database System (Use the 'POST' method in the RESTClient for all requests): </h3>");
		out.write("<ul> <li> Go to 'http://localhost:8014/get-employees' to open a list of all Employees in the database in text format. </li>");
		out.write("<li> Go to 'http://localhost:8014/get-json' to open a list of all Employees in the database in JSON format. </li>");
		out.write("<li> To post an Employee object to the database in JSON format, write a JSON string in the RESTClient Body. (Use 'http://localhost:8014/process_post' as the URL). </li>");	
		out.write("<li> To retrieve a single Employee object from the database, write an existing Employee Number in the RESTClient Body. (Use 'http://localhost:8014/process_search' as the URL). </li>");
		out.write("<li> To update an Employee record based on its Employee Number, place the full record in the RESTClient Body in JSON format, and alter the attributes. (Use 'http://localhost:8014/process_update' as the URL). </li>");
		out.write("<li> To delete a single Employee record from the database, write its Employee Number in the RESTClient Body. (Use 'http://localhost:8014/process_delete' as the URL). </li>");

		out.close();
	}
}