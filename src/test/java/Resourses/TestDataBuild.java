package Resourses;

import java.util.ArrayList;
import java.util.List;

import Pojo.FullLocation;
import Pojo.Location;

public class TestDataBuild {
	
	
	
	public FullLocation addPlace(String name ,String phone,String lang) {
		FullLocation loc = new FullLocation();
		loc.setAccuracy(20);
		loc.setAddress("US,Cananda");
		loc.setLanguage(lang);
		loc.setName(name);
		loc.setPhone_number(phone);
		loc.setWebsite("http://www.google.com");

		Location ll = new Location();
		ll.setLat(12324.7666);
		ll.setLng(32344343.544);
		loc.setLocation(ll);

		List<String> lst = new ArrayList<String>();
		lst.add("New show park");
		lst.add("payal shop");

		loc.setTypes(lst);
		return loc;
	}

	public String deletePlacePayload(String placeid) {
		
		return "{\r\n    \"place_id\":\""+placeid+"\"\r\n}";
	}
	

}
