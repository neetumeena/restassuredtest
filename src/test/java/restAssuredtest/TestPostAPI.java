package restAssuredtest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestPostAPI {

/*

{
“FirstName” : “value”

“LastName” : “value”,

“UserName” : “value”,

“Password” : “value”,

“Email”        : “Value”

}
 */

    @Test
    public void methodToSetReqBody_1()
    {
        RestAssured.baseURI="https://restapi.demoqa.com/customer";
        RequestSpecification req=RestAssured.given();
        JSONObject requestParams = new JSONObject();

//        Map<String,String> requestParams = new HashMap<>();
        requestParams.put("FirstName", "Virender");
        requestParams.put("LastName", "Singh");

        requestParams.put("UserName", "simpleuser001");
        requestParams.put("Password", "password1");
        requestParams.put("Email",  "someuser@gmail.com");
        req.header("Content-Type", "application/json");
        req.contentType(ContentType.JSON);
        req.body(requestParams);
        Response response = req.post("/register", Method.POST);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals( "Correct Success code was returned", successCode, "OPERATION_SUCCESS");



    }


//    @Test
    public void passBodyAsMap()
    {
        Map<String,String> authPayload = new HashMap<String,String>();
        authPayload.put("username", "admin");
        authPayload.put("password", "password123");


        //GIVEN
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com/auth")
                .contentType(ContentType.JSON)
                .body(authPayload)
                .log()
                .all()
                // WHEN
                .when()
                .post()
                // THEN
                .then()
                .assertThat()
                .statusCode(200)
                .log()
                .all();

    }
}
