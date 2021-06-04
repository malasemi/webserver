package edu.cscc;

import java.io.*;
import java.net.Socket;

/**
 * RequestHandler - handle HTTP GET Requests
 * (ignore anything else)
 * @author Majed Alasemi
 */
public class RequestHandler {
    private Socket connection;

    /**
     * Constructor
     */
    public RequestHandler(Socket connection) {
        this.connection = connection;
    }

    /**
     * Process an HTTP request
     */
    public void processRequest() {
        // This method calls the readRequest method and creates HTTPRequest with a given URI
    	// then calls ResponseHandler with the request then sends the response (socket)
    	String str = null;
    	try {
    		str = readRequest();
    		HTTPRequest req = new HTTPRequest(str);
    		ResponseHandler rHanlr = new ResponseHandler(req);
    		rHanlr.sendResponse(connection);
		} catch (IOException e) {			
			TinyWS.fatalError(e);
		} finally {	
			try {
				connection.close();
			} catch (IOException ex) {				
				TinyWS.fatalError(ex);
			}
			TinyWS.log("The connection was closed");
		}
    }

    // Read an HTTP Request
    private String readRequest() throws IOException {
        // Set socket timeout to 500 milliseconds
        connection.setSoTimeout(500);
        int recbufsize = connection.getReceiveBufferSize();
        InputStream in = connection.getInputStream();
        InputStreamReader rdr = new InputStreamReader(in);
        BufferedReader brdr = new BufferedReader(rdr, recbufsize);
        StringBuilder reqBuf = new StringBuilder();
        char[] cbuf = new char[recbufsize];
        
        // checking if brdr.read(cbuf) is not null (-1) and appending char[] to the StringBuilder
        // returns a string containing the request
        try {
        	if(brdr.read(cbuf) != -1 ) {
        		reqBuf.append(cbuf);
        	}              
        
        	} catch(IOException ex) {
        	ex.getMessage();
        	}
        return reqBuf.toString();
    }       
    
}