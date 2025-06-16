package com.qa.api.products.tests;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.Utils.jsonUtils;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.product;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITest extends BaseTest {

	@Test
	public void getproduct() {
		
		Response response = restClient.get(BASE_URL_PRODUCTS, PRODUCTS_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
		Assert.assertEquals(response.statusCode(), 200);
		
		product[] Product = jsonUtils.deserialize(response, product[].class);
		
		for (product p : Product) {
			System.out.println("id: " + p.getId());
			System.out.println("title: " + p.getTitle());
			System.out.println("price: " + p.getPrice());
			System.out.println("description: " + p.getDescription());
			System.out.println("image: " + p.getImage());
			System.out.println("category: " + p.getCategory());


			System.out.println("rate: " + p.getRating().getRate());
			System.out.println("count: " + p.getRating().getCount());

	}
}
}
