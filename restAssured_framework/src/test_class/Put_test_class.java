package test_class;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.Test;

import common_method.Common_method_utilities;
import common_method.Put_common_method;
import io.restassured.path.json.JsonPath;
import request_repository.Put_request_repository;

public class Put_test_class {
	@Test
		public static void orchestrator() throws IOException 
		{    
			
			String responseBody = "" ;
			int responsestatuscode = 0;
			String baseUri = Put_request_repository.baseuri();
			String resource = Put_request_repository.resource();
			String requestBody = Put_request_repository.Put_request_tc1();
			
			System.out.println(requestBody);
			for(int i=0 ; i<5 ; i++) 
	        {
			 responsestatuscode =Put_common_method.responsestatuscode_extractor(baseUri, resource, requestBody);	
	          if (responsestatuscode == 200)
			  {
				responseBody = Put_common_method.responsebody_extractor(baseUri, resource, requestBody);
				responseBodyValidator(responseBody,requestBody);
				
				System.out.println(responseBody);
			    break;
		      }
	          else
	          {
	        	  System.out.println("correct status code is not found in the iteration " +i);
	          }
	        } 
			Common_method_utilities.evidenceFileCreator("Put_test_class", requestBody, responseBody);
			Assert.assertEquals(responsestatuscode, 200);
			
	     }

	    public static void responseBodyValidator(String responseBody,String requestBody)
		{
			// create jsonPath object to extract response body parameters
			JsonPath jsp = new JsonPath(responseBody);

			// extract response body parameters
			String res_name = jsp.getString("name");
			String res_job = jsp.getString("job");
			String res_updatedAt = jsp.getString("updatedAt");
			String res_date = res_updatedAt.substring(0,10);

			System.out.println("name : " + res_name + "\njob : " + res_job + "\nupdatedAt : " + res_date);
			JsonPath jsp1 = new JsonPath(requestBody);

			
			String req_name = jsp1.getString("name");
			String req_job = jsp1.getString("job");
			

			// validate response body parameter
			Assert.assertEquals(res_name, req_name);
			Assert.assertEquals(res_job, req_job);

			// extract date from updatedAt parameter
			String current_date = LocalDate.now().toString();
			Assert.assertEquals(res_date,current_date);
			System.out.println( "\nCurrent date : " +current_date);
			

		}
	
	    
	    
	    
	}



