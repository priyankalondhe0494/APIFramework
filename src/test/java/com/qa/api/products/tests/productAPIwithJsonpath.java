package com.qa.api.products.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.Utils.JsonPathvalidatorUtils;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class productAPIwithJsonpath extends BaseTest {
	@Test
	public void getProductTest() {
		
		Response response = restClient.get(BASE_URL_PRODUCTS, PRODUCTS_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(response.statusCode(), 200);
		
		List<Number> prices = JsonPathvalidatorUtils.readList(response, "$[?(@.price > 50)].price");
		System.out.println(prices);

		List<Number> ids = JsonPathvalidatorUtils.readList(response, "$[?(@.price > 50)].id");
		System.out.println(ids);

		List<Number> rates = JsonPathvalidatorUtils.readList(response, "$[?(@.price > 50)].rating.rate");
		System.out.println(rates);
		
		List<Integer> counts = JsonPathvalidatorUtils.readList(response, "$[?(@.price > 50)].rating.count");
		System.out.println(counts);

		
		List<Map<String, Object>> idTitleList = JsonPathvalidatorUtils.readListOfMaps(response, "$.[*].['id', 'title']");
		System.out.println(idTitleList);
		
		List<Map<String, Object>> idTitleCatList = JsonPathvalidatorUtils.readListOfMaps(response, "$.[*].['id', 'title', 'category']");
		System.out.println(idTitleCatList);
		
		List<Map<String, Object>> jewlIDTitleList = JsonPathvalidatorUtils.readListOfMaps(response, "$[?(@.category == 'jewelery' )].['id', 'title']");
		System.out.println(jewlIDTitleList);
		
		Double price = JsonPathvalidatorUtils.read(response, "min($[*].price)");
		System.out.println("min price -->"+ price);
	}
	
}
