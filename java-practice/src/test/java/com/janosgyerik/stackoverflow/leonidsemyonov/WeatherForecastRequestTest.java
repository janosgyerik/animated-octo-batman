package com.janosgyerik.stackoverflow.leonidsemyonov;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

class HttpRequestor {

	private static final HttpClient httpClient = new DefaultHttpClient();

	private final String url;
	private final List<? extends NameValuePair> arguments;

	public static interface ArgumentsProvider {
		List<? extends NameValuePair> getArguments();
	}

	public HttpRequestor(String url, ArgumentsProvider argumentsProvider) {
		this.url = url;
		this.arguments = argumentsProvider.getArguments();
	}

	public String request() throws IOException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new UrlEncodedFormEntity(arguments));
		HttpResponse httpResponse = httpClient.execute(httpPost);
		return EntityUtils.toString(httpResponse.getEntity());
	}
}

class WeatherForecastRequestArgumentsProvider implements HttpRequestor.ArgumentsProvider {

	enum QueryParam {
		QUERY("q"),
		MODE("mode"),
		UNITS("units"),
		COUNT("cnt");

		private final String name;

		private QueryParam(String name) {
			this.name = name;
		}
	}

	private final List<NameValuePair> arguments;

	private WeatherForecastRequestArgumentsProvider(Builder builder) {
		arguments = new ArrayList<NameValuePair>(builder.getArguments());
	}

	@Override
	public List<? extends NameValuePair> getArguments() {
		return arguments;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		Map<QueryParam, String> arguments = new HashMap<>();

		public Builder query(String query) {
			arguments.put(QueryParam.QUERY, query);
			return this;
		}

		public Builder units(String units) {
			arguments.put(QueryParam.UNITS, units);
			return this;
		}

		public Builder mode(String mode) {
			arguments.put(QueryParam.MODE, mode);
			return this;
		}

		public Builder numberOfDays(String numberOfDays) {
			arguments.put(QueryParam.COUNT, numberOfDays);
			return this;
		}

		public List<NameValuePair> getArguments() {
			List<NameValuePair> nonNullArguments = new ArrayList<NameValuePair>();
			for (Map.Entry<QueryParam, String> entry : arguments.entrySet()) {
				String value = entry.getValue();
				if (value != null) {
					nonNullArguments.add(new BasicNameValuePair(entry.getKey().name, value));
				}
			}
			return Collections.unmodifiableList(nonNullArguments);
		}

		public WeatherForecastRequestArgumentsProvider build() {
			return new WeatherForecastRequestArgumentsProvider(this);
		}
	}
}

public class WeatherForecastRequestTest {
	@Test
	public void test() {
		new HttpRequestor("", WeatherForecastRequestArgumentsProvider.builder().build());
	}
}
