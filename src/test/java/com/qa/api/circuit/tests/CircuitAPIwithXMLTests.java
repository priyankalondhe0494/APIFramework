package com.qa.api.circuit.tests;

import com.qa.api.Utils.XmlpathUtils;
import com.qa.api.base.BaseTest;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;


import com.qa.api.constants.AuthType;


import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
public class CircuitAPIwithXMLTests extends BaseTest{
	@Test
	public void getCircuitInfoTest() {
		Response response = 
					restClient.get(BASE_URL_ERGAST_CIRCUIT, ERGAST_CIRCUIT_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
	
		List<String> circuitNames = XmlpathUtils.readList(response, "MRData.CircuitTable.Circuit.CircuitName");
		System.out.println(circuitNames);
		
		for(String e : circuitNames) {
			Assert.assertNotNull(e);
		}
		
		
		String americaLoc = XmlpathUtils.read(response, "**.find{ it.@circuitId == 'americas' }.Location.Locality");
		System.out.println("americas location--->"+ americaLoc);
		Assert.assertEquals(americaLoc, "Austin");
	
	}
}
