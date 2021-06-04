package edu.cscc;


/**
 * HTTPRequest - parse HTTP Requests
 * (actually parse a small part of a GET Request: GET [filepath])
 * @author Majed Alasemi
 */
public class HTTPRequest {
    private String path;            // path to file
    private boolean validRequest;   // is request valid?

    /**
     * Constructor
     * @param r - HTTP request string to be parsed
     */
    public HTTPRequest(String r) {
        validRequest = parse(r);
    }

    /**
     * Is the request valid
     */
    public boolean isValidRequest() {
        return (validRequest);
    }

    /**
     * Return file path for request
     */
    public String getPath() {
        return (path);
    }

    /**
     * Parse an HTTP request
     */
    private boolean parse(String r) {
    	// This method return false if the HTTPRequest(String r) is null or empty
    	// otherwise it returns true and assigns the path
    	if(r == null || r.isEmpty()) {    		
    		return false;
    	}
    	String[] arr = r.split("[ \\t\\n?]");
    	 
    	if (arr.length < 2 || !"GET".equals(arr[0])) {	    		
			return false;
		} else {			
			path = arr[1];			
			return true;
		}
    }
}