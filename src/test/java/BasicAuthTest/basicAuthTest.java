package BasicAuthTest;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

public class basicAuthTest extends BaseTest {

	@Test
	public void basicauthTest() {
		
		Response response = restClient.get(BASE_URL_BASIC_AUTH, BASIC_AUTH_ENDPOINT, null, null, AuthType.BASIC_AUTH, ContentType.ANY);
		Assert.assertTrue(response.getBody().asString().contains("Congratulations! You must have the proper credentials."));
	}
}
