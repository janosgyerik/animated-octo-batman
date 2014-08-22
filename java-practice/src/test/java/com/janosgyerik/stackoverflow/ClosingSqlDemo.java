package com.janosgyerik.stackoverflow;

import org.apache.log4j.Logger;

import java.sql.*;

public class ClosingSqlDemo {
    private static final Logger LOGGER = Logger.getLogger(ClosingSqlDemo.class.getSimpleName());

    private String _driver;
    private String _url;
    private User _user;

    private static class Helper {
        public static void println(String s) {
        }

        public static void printErrln(String s) {
        }
    }

    private static class User {
        private String _username;
        private String _password;
    }

    private static final String VERSION_QUERY = "Select VERSION()";
    private static final String VERSION_NOT_FOUND = "";

    public String getVersion() {
        Helper.println("Getting driver...");
        try {
            Class.forName(_driver);
        } catch (ClassNotFoundException e) {
            Helper.printErrln("Driver Error: " + e.getMessage());
            return VERSION_NOT_FOUND;
        }

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Helper.println("Connecting to database...");
            con = DriverManager.getConnection(_url, _user._username, _user._password);
            stmt = con.createStatement();

            Helper.println("Executing query...");
            rs = stmt.executeQuery(VERSION_QUERY);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException se) {
            Helper.printErrln("SQL Error: " + se.getErrorCode() + ' ' + se.getMessage());
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) {}
            try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
            try { if (con != null) con.close(); } catch (SQLException e) {}
        }
        return VERSION_NOT_FOUND;
    }

    private void closeQuietly(ResultSet resource) {
        try {
            resource.close();
        } catch (SQLException e) {
            LOGGER.warn("Could not close resource", e);
        }
    }
}
