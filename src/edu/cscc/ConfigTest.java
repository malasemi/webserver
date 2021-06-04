package edu.cscc;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ConfigTest {

    @Test
    public void testConfig() {
        Config config = new Config();
        config.dumpProperties();
        assertNotNull("Missing port", config.getProperty(Config.PORT));
        assertNotNull("Missing HTML folder",config.getProperty(Config.DEFAULTFOLDER));
        assertNotNull("Missing default page",config.getProperty(Config.DEFAULTPAGE));
    }
}