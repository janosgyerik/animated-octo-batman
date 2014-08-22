package com.janosgyerik.stackoverflow.junk;

import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UrlCheckTest {
	public static String urlCheck(String url) {
		URL urlToCheck = null;
		try {
			urlToCheck = new URL(url);
		} catch (MalformedURLException e) {
			return "Skipping URL (malformed)" + e.getMessage();
		}
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) urlToCheck.openConnection();
		} catch (IOException e) {
			return "Skipping URL (could not open connection to URL)";
		}
		try {
			connection.setRequestMethod("GET");
		} catch (ProtocolException e) {
			return "Skipping URL (protocol exception when setting request method to GET)";
		}
		Long start = System.currentTimeMillis();
		try {
			connection.connect();
		} catch (IOException e) {
			return "Skipping URL (could not connect to resource)" + e.getMessage();
		}
		int urlStatus = 0;
		try {
			urlStatus = connection.getResponseCode();
		} catch (IOException e) {
			return "Skipping URL (could not get response code)";
		}
		Long stop = System.currentTimeMillis();
		Long urlCheckTime = stop - start;
		return (connection.getRequestMethod() + " " + urlToCheck + " "
				+ urlStatus + " " + urlCheckTime + "ms");
	}

	public static String urlCheck2(String url) {
		try {
			URL urlToCheck = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlToCheck.openConnection();
			connection.setRequestMethod("GET");
			Long start = System.currentTimeMillis();
			connection.connect();
			int urlStatus = connection.getResponseCode();
			Long stop = System.currentTimeMillis();
			Long urlCheckTime = stop - start;
			return (connection.getRequestMethod() + " " + urlToCheck + " "
					+ urlStatus + " " + urlCheckTime + "ms");
		} catch (IOException err) {
			return "Skipping URL";
		}
	}

	@Test
	public void testInvalidUrl() {
//		assertEquals("Skipping URL", urlCheck("hello"));
		assertEquals("Skipping URL (malformed)", urlCheck("hello"));
	}

	@Test
	public void testNoSuchSite() {
//		assertEquals("Skipping URL", urlCheck("hello"));
		assertEquals("Skipping URL (malformed)", urlCheck("http://nonexistent.example.com"));
	}

	@Test
	public void testGoogle() {
		assertTrue(urlCheck("http://google.com").startsWith("GET http://google.com 200 "));
	}
}
