package com.qa.api.gorest.tests;
import com.qa.api.Utils.ExcelUtils;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AppConstants;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.configManager;
import com.qa.api.pojo.User;
import com.qa.api.Utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.qa.api.base.BaseTest;
//every test class will extends basetest
public class createUserTest extends BaseTest{

private String tokenId;
	
	@BeforeClass
	public void setUpToken() {
		tokenId = "7e67c6115f1b1b4b79dbd8b16ac3c1b3b758e563bfd015112bee182d80324373";
		configManager.set("bearertoken", tokenId);
	}
	//1st approach
	@DataProvider
       public Object[][] getUserData(){
		return new Object[][] {
			{"Snehal","Female","Active"},
			{"Vaibhav","Male","Active"},
			{"Aniket","Male","Inctive"}
		};
		
		}
	//2nd approach
	@DataProvider
	public Object[][] getUserExcelData() {
		return ExcelUtils.readData(AppConstants.CREATE_USER_SHEET_NAME);
	}
	
	
	@Test (dataProvider = "getUserData")
	public void createAUserTest(String name, String gender,String status ) {
		//User user = new User(null, "name", StringUtils.getRandomEmailId(), "female", "active");			
		User user = new User(null, "name", StringUtils.getRandomEmailId(), "gender", "status");
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("name"), name);
		Assert.assertEquals(response.jsonPath().getString("gender"), gender);
		Assert.assertEquals(response.jsonPath().getString("status"), status);
		Assert.assertNotNull(response.jsonPath().getString("id"));	
	}
	
	
	@Test(enabled = false)
	public void createAUserTestWithJsonString() {

		String userJson = "{\n"
				+ "\"name\": \"naveen\",\n"
				+ "\"email\": \"naveen8990@gmail.com\",\n"
				+ "\"gender\": \"male\",\n"
				+ "\"status\": \"active\"\n"
				+ "}";
		
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, userJson, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("name"), "naveen");
		Assert.assertNotNull(response.jsonPath().getString("id"));	
	}
	
	
	@Test(enabled = false)
	public void createAUserTestWithJsonfile() {

		File userFile = new File("./src/test/resources/jsons/user.json");
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, userFile, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("name"), "Tom");
		Assert.assertNotNull(response.jsonPath().getString("id"));	
	}
}
