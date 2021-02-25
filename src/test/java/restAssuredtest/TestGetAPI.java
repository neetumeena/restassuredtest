package restAssuredtest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TestGetAPI {

    @Test
    public void getWeatherDetails()
    {
        RestAssured.baseURI="https://demoqa.com/utilities/weather/city";
        RequestSpecification httpReq=RestAssured.given();
        Response res =httpReq.request(Method.GET,"/Hyderabad");
        System.out.println(res.getStatusCode());
        String cityName=res.jsonPath().get("City");
        String temperature=res.jsonPath().get("Temperature");
        System.out.println(cityName);
        System.out.println(temperature);

    }

}
