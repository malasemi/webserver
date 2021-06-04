package edu.cscc;

import org.junit.Test;

import static org.junit.Assert.*;

public class HTTPRequestTest {

    public static final String GOODREQUEST = "GET /path1/path2/index.html\nmore stuff\nmore stuff";
    public static final String BADREQUEST = "bogus";

    @Test
    public void testHTTPRequest1() {
        HTTPRequest request = new HTTPRequest(GOODREQUEST);
        assertTrue("Request processing failed","/path1/path2/index.html".equals(request.getPath()));
        assertTrue("isValidRequest() failed",request.isValidRequest());
    }

    @Test
    public void testHTTPRequest2() {
        HTTPRequest request = new HTTPRequest(BADREQUEST);
        assertFalse("isValidRequest() failed while processing invalid request", request.isValidRequest());
    }
}