package StepDefinations;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import Resourses.ApiResourses;
import Resourses.TestDataBuild;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class NewStepDefinations extends Resourses.Utilities {

	RequestSpecification resspec;
	ResponseSpecification respp;
	Response resppp;
	TestDataBuild data = new TestDataBuild();
	String placeid;

	static String place_id;

	@Given("Add place payload {string} {string} {string}")
	public void add_place_payload(String name, String phoneno, String lang) throws IOException {

		// add place specification

		resspec = given().spec(requestSpecifications()).body(data.addPlace(name, phoneno, lang));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String req, String method) {

		ApiResourses resourseAPI = ApiResourses.valueOf(req);
		System.out.println(resourseAPI.getResourse());

		respp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST")) {
			resppp = resspec.when().post(resourseAPI.getResourse());
		} else if (method.equalsIgnoreCase("GET"))
			resppp = resspec.when().get(resourseAPI.getResourse());

	}

	@Then("The api call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		resppp.then().spec(respp).extract().response();
		assertEquals(resppp.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String Expectedvalue) {

		assertEquals(getJsonPath(resppp, keyvalue), Expectedvalue);
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String method) throws IOException {
		place_id = getJsonPath(resppp, "place_id");
		resspec = given().spec(requestSpecifications()).queryParam("place_id", place_id);
		user_calls_with_http_request(method, "GET");
		String actualname = getJsonPath(resppp, "name");
		assertEquals(actualname, expectedname);
	}
	@Given("verify delete request payload")
	public void verify_delete_request_payload() throws IOException {
		resspec=given().spec(requestSpecifications()).body(data.deletePlacePayload(place_id));
	}

}
