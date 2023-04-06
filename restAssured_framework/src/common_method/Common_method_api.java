package common_method;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
public class Common_method_api {
	public static int responeStatuscode_extractor(String baseURI, String resource, String req_body)
	{
		RestAssured.baseURI=baseURI;
		int responeStatuscode=given().header("Content-Type", "application/json").body(req_body)
				.when().post(resource).then().extract().statusCode();
		return responeStatuscode;
	}
	public static String responeBody_extractor(String baseURI, String resource, String req_body)
	{
		RestAssured.baseURI=baseURI;
		String responeBody=given().header("Content-Type", "application/json").body(req_body)
				.when().post(resource).then().extract().response().asString();
		return responeBody;
    }
}
