package test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
     JsonPath js=new JsonPath(payload.courseData());
     //Print no of courses returned by API
     int count=js.getInt("courses.size()");
     System.out.println(count);
     
     //Print Purchase Amount
     int totalAmount=js.getInt("dashboard.purchaseAmount");
     System.out.println(totalAmount);
     
     //Print title of first course
     String title=js.getString("courses[0].title");
     System.out.println(title);
     
     //Print All courses with prices
     for(int i=0; i<count; i++) {
    	 String courseTitles=js.get("courses["+i+"].title");
    	 int coursePrices=js.get("courses["+i+"].price");
    	 System.out.println("courseTitle: " + courseTitles+" "+ "coursePrice: "+coursePrices);
    	 
     }
     
     
     //Print no of copies sold by RPA
     for(int i=0; i<count; i++) {
    	 String courseTitles=js.get("courses["+i+"].title");
    	 if(courseTitles.equalsIgnoreCase("RPA")) {
    		int copies= js.get("courses["+i+"].copies");
    		System.out.println(copies);
    		break;
    	 }
    	 
    	 
     }
     
    
     
	}

}
