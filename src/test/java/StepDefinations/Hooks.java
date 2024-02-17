package StepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before("@Deleteplace")
	public void beforescenario() throws IOException {
		NewStepDefinations m=new NewStepDefinations();
		m.add_place_payload("ramagiri", "9876", "sdfhdhf");
		m.user_calls_with_http_request("addPlaceAPI", "post");
		m.verify_place_id_created_maps_to_using("ramagiri", "getPlaceAPI");
		
	}
	

}	
