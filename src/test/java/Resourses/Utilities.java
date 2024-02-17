package Resourses;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utilities {
	public static RequestSpecification req;

	public RequestSpecification requestSpecifications() throws IOException {
		if (req == null) {
			PrintStream streamm = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseurl"))
					.addFilter(RequestLoggingFilter.logRequestTo(streamm))
					.addFilter(ResponseLoggingFilter.logResponseTo(streamm)).addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}

	public static String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\SRIKANTH RAMAGIRI\\eclipse-workspace\\CucumberBddProject\\src\\test\\java\\Resourses\\Globaldata.properties");
		prop.load(fis);
		return prop.getProperty(key);

	}
	
	public String getJsonPath(Response resppp,String key) {
		String str = resppp.asString();
		JsonPath js= new JsonPath(str);
		return js.get(key).toString();
		
	}

}
