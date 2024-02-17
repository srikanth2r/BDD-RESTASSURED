Feature: Validating the place Apis
@Addplace
Scenario Outline: Verify if place is being succesfully added through Place APIS

Given Add place payload "<Addname>" "<PhoneNumber>" "<language>"
When User calls "addPlaceAPI" with "Post" http request	
Then The api call is success with status code 200 
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created maps to "<Addname>" using "getPlaceAPI"

Examples:
|Addname|PhoneNumber|language|
|Srikanram|123434|English|
|Anusri|232424333|Malay|

@Deleteplace
Scenario: Verify the delete Place functionality is working

Given verify delete request payload
When User calls "deletePlaceAPI" with "POST" http request	 
Then The api call is success with status code 200 
And "status" in response body is "OK"