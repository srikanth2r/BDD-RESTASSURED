package Pojo;

import java.util.ArrayList;
import java.util.List;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

public class AddPlace {

	public static void main(String[] args) {

		FullLocation loc = new FullLocation();
		loc.setAccuracy(20);
		loc.setAddress("Hyderabad,Secunderbad");
		loc.setLanguage("English");
		loc.setName("New House");
		loc.setPhone_number("87443434");
		loc.setWebsite("http://www.google.com");

		Location ll = new Location();
		ll.setLat(12324.7666);
		ll.setLng(32344343.544);
		loc.setLocation(ll);

		List<String> lst = new ArrayList<String>();
		lst.add("New show park");
		lst.add("payal shop");

		loc.setTypes(lst);

		// add place specification

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

		ResponseSpecification respp = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();

		RequestSpecification resspec = given().spec(req).body(loc);

		Response resppp = resspec.when().post("/maps/api/place/add/json").then().spec(respp).extract().response();
		
		
	String responsestring	=resppp.asString();
	System.out.println(responsestring);	

	}

}
