package Resourses;

public enum ApiResourses {

	addPlaceAPI("/maps/api/place/add/json"), getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");

	private String resourse;

	ApiResourses(String resourse) {
		this.resourse=resourse;
	}

	public String getResourse() {
		return resourse;
	}

}
