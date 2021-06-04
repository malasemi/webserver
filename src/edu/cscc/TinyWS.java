// Majed Alasemi 11/12/2020
package edu.cscc;

import java.io.IOException;
import java.net.*;

/**
 * TinyWS a simplistic Tiny Web Server
 * @author Majed Alasemi
 */
public class TinyWS {

    private static int port;
    private static String defaultFolder;
    private static String defaultPage;

    /**
     * Main routine - instantiate tiny web server, start listening for browser requests
     */
    public static void main(String[] args) {
        TinyWS tiny = new TinyWS();
        tiny.listen();
    }

    /**
     * Constructor - read and set configuration
     */
    public TinyWS() {
    	// This method initializes the configuration fields and displays the values
        Config config = new Config();        
        port =Integer.parseInt(config.getProperty(Config.PORT));
        defaultFolder = config.getProperty(Config.DEFAULTFOLDER);
        defaultPage = config.getProperty(Config.DEFAULTPAGE);        
        
        config.dumpProperties();
    }

    /**
     * Listen on server socket
     */
    public void listen() {
    	// This method creates a socket with a specific port number
    	// listens for a connection and accepts it and returns a socket connection object
    	Socket connection = null;
    	try (ServerSocket ssk = new ServerSocket(port)) {
    			
    	ssk.setSoTimeout(0);
    	System.out.println("The server is started and is listening to " + ssk);
    	System.out.println("Timeout value is set to: " + ssk.getSoTimeout());
    		while (true) {
				connection = ssk.accept();				
				log("Request From "+ connection.getInetAddress().getCanonicalHostName());				
				RequestHandler rh = new RequestHandler(connection);
				System.out.println("Got a request ");
				rh.processRequest();				
			}    		
    	} catch (Exception e) {
    		fatalError(e);
    	} finally {
    		try {				
				connection.close();
			} catch (IOException e) {
				fatalError(e);
			}
    	}
    }

    /**
     * Log web server requests
     * @param s - message to log
     */
    public static void log(String s) {
        System.out.println(s);
    }

    /**
     * Handle fatal error - print info and die
     */
    public static void fatalError(String s) {
        handleError(s, null, true);
    }

    /**
     * Handle fatal error - print info and die
     */
    public static void fatalError(Exception e) {
        handleError(null, e, true);
    }

    /**
     * Handle fatal / non-fatal errors
     */
    public static void handleError(String s, Exception e, boolean isFatal) {
        if (s != null) {
            System.out.println(s);
        }
        if (e != null) {
            e.printStackTrace();
        }
        if (isFatal) System.exit(-1);
    }

    /**
     * Get port configuration value
     * @return port number
     */
    public static int getPort() {
        return port;
    }

    /**
     * Get default HTML folder
     * @return folder
     */
    public static String getDefaultFolder() {
        return defaultFolder;
    }

    /**
     * Get name of default web page
     * @return default page
     */
    public static String getDefaultPage() {
        return defaultPage;
    }
}