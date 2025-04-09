package test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;


public class Basics {

	public static void main(String[] args) {
		
		//Add new place-->Update Address -->Get place to see if address is updated
		
		//Add a new Place in Google map API
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(payload.AddPlace())
		.when().post("/maps/api/place/add/json")
		.then().log().all().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		JsonPath js=new JsonPath(response); //for parsing json response
		String place_id=js.getString("place_id");
		
		//Update place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+place_id+"\",\r\n"
				+ "\"address\":\"70 winter walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		given().log().all().queryParam("key", "qaclick123").queryParam("place_id", place_id)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).body("address", equalTo("70 winter walk, USA"));
		
		

	}

}
