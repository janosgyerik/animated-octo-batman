package com.janosgyerik.stackoverflow.junk;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadPropertiesTest {

    private static final String PROPERTIES_FILENAME = "player.properties";
    private static final String PATHS_PROPERTY = "paths";
    private static final String ITEM_SEPARATOR = "|";

    private String[] getPaths(String filename, String propName) throws IOException {
        InputStream input = this.getClass().getClassLoader().getResourceAsStream(filename);
        if (input == null) {
            return new String[0];
        }
        Properties prop = new Properties();
        prop.load(input);
        input.close();
        return prop.getProperty(propName, "").split(ITEM_SEPARATOR);
    }

    private String[] getPaths(String filename) throws IOException {
        return getPaths(filename, PATHS_PROPERTY);
    }

    String[] getPaths() throws IOException {
        return getPaths(PROPERTIES_FILENAME, PATHS_PROPERTY);
    }

    @Test
    public void testGetPaths() throws IOException {
        Assert.assertTrue(getPaths().length > 1);
    }

    @Test
    public void testGetPaths_noSuchProp() throws IOException {
        Assert.assertArrayEquals(new String[]{""}, getPaths(PROPERTIES_FILENAME, "nonexistentprop"));
    }

    @Test
    public void testGetPaths_noSuchFile() throws IOException {
        Assert.assertArrayEquals(new String[0], getPaths("nonexistentfile"));
    }

    @Test(expected = NullPointerException.class)
    public void testGetPaths_nullFilename() throws IOException {
        getPaths(null);
    }
}
