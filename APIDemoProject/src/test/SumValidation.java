package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	@Test
	public void sumOfCourses() {
		int sum=0;
		JsonPath js=new JsonPath(payload.courseData());
	     int count=js.getInt("courses.size()");
	     for(int i=0; i<count; i++) {
	    	 int price=js.get("courses["+i+"].price");
	    	 int copies=js.get("courses["+i+"].copies");
	    	 int amount=price * copies;
	    	 System.out.println(amount);
	    	 sum=sum+amount;
	    	 
	    	 
	     }
	     int purchaseAmount=js.get("dashboard.purchaseAmount");
	     Assert.assertEquals(sum, purchaseAmount);
	     
	}

}
