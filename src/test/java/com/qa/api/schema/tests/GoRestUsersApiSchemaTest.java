package com.qa.api.schema.tests;

import com.qa.api.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.configManager;
import com.qa.api.manager.configManager;
import com.qa.api.pojo.User;
import com.qa.api.Utils.SchemaValidator;
import com.qa.api.Utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class GoRestUsersApiSchemaTest extends BaseTest{
	@Test
	public void getUsersAPISchemaTest() {	
		configManager.set("bearertoken", "e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6");
		
		Response response = restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.ANY);
	
		Assert.assertTrue(SchemaValidator.validateSchema(response, "schema/getuserschema.json"));
	
	}
	
	
	@Test
	public void createUserAPISchemaTest() {	
		configManager.set("bearertoken", "e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6");
		
		User user = User.builder()
		.name("api")
		.status("active")
		.email(StringUtils.getRandomEmailId())
		.gender("female")
		.build();
		
		Response response = restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
			
		Assert.assertTrue(SchemaValidator.validateSchema(response, "schema/createuserschema.json"));
	
	}
}
