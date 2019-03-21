import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestClass {
	
	private String token;
	
	@Before
    public void before() throws Exception {
        token =
                given()
                .auth().preemptive().basic("singh.sneha2688@gmail.com","Sneha1234").
                        
                when().post("https://api-ssl.bitly.com/oauth/access_token").
                        then().contentType(ContentType.TEXT).extract().response().asString();
        //System.out.println(token);
    }
	
	@Test
    public void inValidUserInfo() {

        Response response = given()
                .when()
                .get("https://api-ssl.bitly.com/v3/user/info?access_token=trttrr" + token)
                .then()
                .statusCode(200)
                .extract()
                .response();

        String jsonBody = response.getBody().asString();
        //System.out.println(jsonBody);

        try {
        	JSONObject obj = new JSONObject(jsonBody);  
        	assertEquals(403, obj.get("status_code"));
        } catch (JSONException ex) {
            fail(ex.getLocalizedMessage());
        }

    }
	
    @Test
    public void validUserInfo() {

        Response response = given()
                .when()
                .get("https://api-ssl.bitly.com/v3/user/info?access_token=" + token)
                .then()
                .statusCode(200)
                .extract()
                .response();

        String jsonBody = response.getBody().asString();
        //System.out.printlntem.out.println(jsonBody);

        try {
        	JSONObject obj = new JSONObject(jsonBody);  
        	assertEquals(200, obj.get("status_code"));
        } catch (JSONException ex) {
            fail(ex.getLocalizedMessage());
        }

    }
	
	@Test
    public void linkHistory() {

        Response response = given()
                .when()
                .get("https://api-ssl.bitly.com/v3/user/link_history?access_token=" + token)
                .then()
                .statusCode(200)
                .extract()
                .response();

        String jsonBody = response.getBody().asString();
        //System.out.println(jsonBody);

        try {
        	JSONObject obj = new JSONObject(jsonBody);  
        	assertEquals(200, obj.get("status_code"));
        } catch (JSONException ex) {
            fail(ex.getLocalizedMessage());
        }

    }
	
	@Test
    public void inValindLinkHistory() {

        Response response = given()
                .when()
                .get("https://api-ssl.bitly.com/v3/user/link_history?access_token=ete" + token)
                .then()
                .statusCode(200)
                .extract()
                .response();

        String jsonBody = response.getBody().asString();
        //System.out.println(jsonBody);

        try {
        	JSONObject obj = new JSONObject(jsonBody);  
        	assertEquals(403, obj.get("status_code"));
        } catch (JSONException ex) {
            fail(ex.getLocalizedMessage());
        }

    }
	
	@Test
    public void linkShorten() {

        Response response = given()
                .when()
                .get("https://api-ssl.bitly.com/v3/shorten?access_token=" + token+ "&longUrl=http://example.com/page?parameter=value#anchor&format=json")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String jsonBody = response.getBody().asString();
        //System.out.println(jsonBody);

        try {
        	JSONObject obj = new JSONObject(jsonBody);  
        	assertEquals(200, obj.get("status_code"));
        } catch (JSONException ex) {
            fail(ex.getLocalizedMessage());
        }

    }
	
	@Test
    public void invalidLinkShorten() {

        Response response = given()
                .when()
                .get("https://api-ssl.bitly.com/v3/shorten?access_token=invalid" + token + "&longUrl=http://example.com/page?parameter=value#anchor&format=json")
                .then()
                .statusCode(200)
                .extract()
                .response();

        String jsonBody = response.getBody().asString();
        //System.out.println(jsonBody);

        try {
        	JSONObject obj = new JSONObject(jsonBody);  
        	assertEquals(403, obj.get("status_code"));
        } catch (JSONException ex) {
            fail(ex.getLocalizedMessage());
        }

    }
}