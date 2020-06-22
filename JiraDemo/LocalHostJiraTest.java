package JiraDemo;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;

public class LocalHostJiraTest {
	String createURI="/rest/api/2/issue";
	@Test
	public void TestCase_01() throws IOException {
		RestAssured.baseURI="http://localhost:8081";
		
		SessionFilter session=new SessionFilter();
		
		given().log().all().contentType(ContentType.JSON)
		.body(JiraMethodSupport.reqBody()).log().all().filter(session).when().post(createURI)
		.then().log().all().statusCode(200);
		
		String str=generateStringFromfile("D:\\Narayan\\Eclips_Program_RestAPI\\MyFirstAPIProject\\src\\main\\resources\\IssueData.json");
		
		given().log().all().contentType(ContentType.JSON)
		.body(str).log().all().filter(session).when().post(createURI)
		.then().log().all().assertThat().statusCode(200);
		
	}
	
	public static String generateStringFromfile(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));

	}
}
