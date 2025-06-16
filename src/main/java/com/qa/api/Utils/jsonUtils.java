package com.qa.api.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;
public class jsonUtils {
private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static <T> T deserialize(Response response, Class<T> targetClass) {
		try {
			return objectMapper.readValue(response.getBody().asString(), targetClass);
		}
		catch(Exception e) {
			throw new RuntimeException("deserilization is failed..."+ targetClass.getName());
		}		
	}
}
