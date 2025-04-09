package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(dataProvider="BooksData")
	public void addBook(String isbn, String aisle) {
		//Add Book
		RestAssured.baseURI="http://216.10.245.166";
		String response=given().header("Content-Type", "application/json").
		body(payload.Addbook(isbn, aisle))
		.when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=new JsonPath(response);
		String id=js.get("ID");
		System.out.println(id);
		
		//deleteBook
		
		
		
		
		
	}
	@DataProvider(name="BooksData")
	public  Object[][]  getData() {
		//Multidimensional array - collection of arrays
		
		 return new Object[][] {{"qwerty", "9876"}, {"asdfgh", "5467"},{"hjklgf", "8975"}};
		
	}
	

}
