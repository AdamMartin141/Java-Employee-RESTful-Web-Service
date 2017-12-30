
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * The WebServiceTester public class tests the functionality of each server side CRUD operation/ handler class, at the
 * console. The class tests the following functions: outputting all employees in JSON format, posting a JSON employee 
 * object to the database, retrieving a single employee record, updating an employee record, and deleting an employee
 * record. The functions are tested using a range of private static methods, which use HttpURLConnection to connect 
 * to the server. These private static methods are then called in the main method, using test data, with output printed 
 * to the console.   
 * 
 * @author Adam Martin 11021206
 * @version 1.0
 * 
 */

    public class WebServiceTester {
    	
    
    	/**
    	 * The main method within this class calls the private static methods of the 'WebServiceTester' class, in order 
    	 * to test the server side CRUD operations, and print the results to the console screen. The methods are tested 
    	 * using test data and their relevant URL, if appropriate. 
    	 * 
    	 * @author Adam Martin
    	 * @version 1.0
    	 * 
    	 */	
    	
    public static void main(String[] args) throws IOException { 
    	
    // Console title		    	
    System.out.println("---Console Testing of RESTful URL Output:---");     		
    System.out.println();
    
    // Console RESTful testing outputs
  
    // Testing of the output of the 'get-json' URL by printing its contents to the console 
    System.out.println("-----Output of 'Employees in JSON format' page:-----"); 
    System.out.println(getAllEmployees());
    System.out.println();
    
    // Testing of the RESTful route to post a JSON Employee object to the database 
    System.out.println("-----Test JSON Employee Sent to Database:-----");
  	System.out.println(postJSONEmp("{\"employeeNumber\":13,\"department\":\"Design\",\"startDate\":\"08-03-2008\",\"salary\":32000.0,\"email\":\"jack@mail.com\",\"name\":\"Jack Morris\",\"gender\":\"M\",\"dob\":\"08-06-1979\",\"address\":\"Stockport\",\"postcode\":\"S2 9JA\"}", 
  			"http://localhost:8014/process_post"));
    System.out.println();
  	
    // Testing of the RESTful route to retrieve an Employee record based on a given Employee id/ Number
    System.out.println("-----Test Retrieving of Single Employee Record:-----");
    System.out.println(getEmp("6", "http://localhost:8014/process_search"));
    System.out.println();
    
    // Testing of the RESTful route to update the attributes of an employee record with a particular Employee Number
  	System.out.println(updateEmp("{\"employeeNumber\":5,\"department\":\"Physics\",\"startDate\":\"24-11-1998\",\"salary\":47000.0,\"email\":\"jane2@mail.com\",\"name\":\"Jane Nolan\",\"gender\":\"F\",\"dob\":\"01-07-1970\",\"address\":\"High Lane\",\"postcode\":\"H2 6HS\"}", 
  			"http://localhost:8014/process_update"));
    System.out.println();
    
    // Testing of the RESTful route to delete an Employee record based on a given Employee id/ Number
  	System.out.println(deleteEmp("8", "http://localhost:8014/process_delete"));
       
}
    
    
   
    // Static methods for printing the contents of the RESTful URL pages to the console 
    
    // Creates a StringBuffer for storing and returning the response for testing (the list of employees in JSON format)
    // 'getAllEmployees' StringBuffer is called in the main method for testing
    private static StringBuffer getAllEmployees() { 
    	
       StringBuffer response = new StringBuffer();
    
       try {
    	   
       // Reads in the 'get-json' URL for testing	
       URL url = new URL("http://localhost:8014/get-json"); BufferedReader reader = new BufferedReader
    		   (new InputStreamReader(url.openStream()));
       
       String output;
       
	   // Adds/ appends to the response line String when the line is not null 
       while ((output = reader.readLine()) != null) {
            response.append(output);
       }
       
       reader.close();
       
       } catch (Exception e) { 
    	   System.out.println(e.getMessage()); 
       }
       
       // Returns the response using the StringBuffer
       return response;
    }
    
    
    
    // Creates a StringBuffer for storing and returning the response for testing (the posting of an employee object in JSON format) 
    // 'postJSONEmp' StringBuffer is called in the main method using test data, for testing 
    private static StringBuffer postJSONEmp(String request, String url) {
    	
		StringBuffer response = new StringBuffer();
		
		try {
			
			// Uses HttpURLConnection to connect to Http Server for testing the post request
			URL newURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) newURL.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			// Output request is sent to the server, and a response is obtained       
			DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			output.writeBytes(request); // Writes out the request string 			
			InputStream input = connection.getInputStream(); // Allows for an input stream
			
			// Allows for the reading in of a request using BufferedReader
			BufferedReader in = new BufferedReader(new InputStreamReader(input)); 
			
			// Adds/ appends to the response line String when the line is not null 
			String line;
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	    // Returns the response using the StringBuffer
		return response;
	}
    
    
    
    // Creates a StringBuffer for storing and returning the response for testing (the retrieving of an Employee with a given Employee Number)
    // 'postJSONEmp' StringBuffer is called in the main method using an Employee Number, for testing 
    private static StringBuffer getEmp(String request, String url) {
    	
		StringBuffer response = new StringBuffer();
		
		try {
			
			// Uses HttpURLConnection to connect to Http Server for testing the request
			URL newURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) newURL.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			// Output request is sent to the server, and a response is obtained       
			DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			output.writeBytes(request);			
			InputStream input = connection.getInputStream();
			
			// Allows for the reading in of a request using BufferedReader
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			
			// Adds/ appends to the response line String when the line is not null 
			String line;
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	    // Returns the response using the StringBuffer
		return response;
	}
    
    
    
    // Creates a StringBuffer for storing and returning the response for testing (the updating of an Employee's attributes based on Employee Number)
    // 'updateEmp' StringBuffer is called in the main method using test data, for testing 
    private static StringBuffer updateEmp(String request, String url) {
    	
		StringBuffer response = new StringBuffer();
		
		try {
			
			// Uses HttpURLConnection to connect to Http Server for testing the request
			URL newURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) newURL.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			// Output request is sent to the server, and a response is obtained       
			DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			output.writeBytes(request);			
			InputStream input = connection.getInputStream();
			
			// Allows for the reading in of a request using BufferedReader
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			
			// Adds/ appends to the response line String when the line is not null 
			String line;
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	    // Returns the response using the StringBuffer
		return response;
	}
    
    
    
    // Creates a StringBuffer for storing and returning the response for testing (the deleting of an Employee with a given Employee Number)
    // 'deleteEmp' StringBuffer is called in the main method using an Employee Number, for testing 
    private static StringBuffer deleteEmp(String request, String url) {
    	
		StringBuffer response = new StringBuffer();
		
		try {
			
			// Uses HttpURLConnection to connect to Http Server for testing the request
			URL newURL = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) newURL.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			// Output request is sent to the server, and a response is obtained       
			DataOutputStream output = new DataOutputStream(connection.getOutputStream());
			output.writeBytes(request);			
			InputStream input = connection.getInputStream();
			
			// Allows for the reading in of a request using BufferedReader
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			
			// Adds/ appends to the response line String when the line is not null 
			String line;
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	    // Returns the response using the StringBuffer
		return response;
	}
    
}   

    
    
   