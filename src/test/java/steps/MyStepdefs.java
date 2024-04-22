package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.Context;
import utils.ScenarioContext;

import java.util.Map;

public class MyStepdefs extends ScenarioContext {
    private ScenarioContext context;

    public MyStepdefs(ScenarioContext context){
        this.context=context;
    }

    @Given("I hit the api url {string} successfully")
    public void iHitTheApiUrl(String url) {
        RestAssured.baseURI = url;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "");
        String statuslineresponse = response.getStatusLine();
        System.out.println("Response received is ->" + response.getStatusLine());
        System.out.println(response.prettyPrint());
        Assert.assertEquals("HTTP/1.1 200 OK", statuslineresponse);
        context.setContext(Context.RESPONSE, response);
        System.out.println(context.getContext(Context.RESPONSE));

    }

    @Then("I verify if json schema matches the expected json")
    public void iVerifyIfJsonSchemaMatchesTheExpectedJson() {
        Response response = (Response) context.getContext(Context.RESPONSE);
        response.then()
                .assertThat()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("exchange_rate_schema.json"));
    }

    @Then("I verify if response received is {string}")
    public void iVerifyIfResponseReceivedIs(String expectedString) {
        Response response = (Response) context.getContext(Context.RESPONSE);
        JsonPath jsonPath = response.jsonPath();
        String result_value = jsonPath.get("result").toString();
        Assert.assertEquals(expectedString, result_value);
    }

    @Then("I verify number of distinct currencies received is {int}")
    public void iVerifyNumberOfDistinctCurrenciesReceived(int numberOfCurrenciesExpected) {
        Response response = (Response) context.getContext(Context.RESPONSE);
        JsonPath jsonPath = response.jsonPath();
        Map<String, Float> ratesMap = jsonPath.getMap("rates");
        int numberOfCurrencies = ratesMap.size();
        Assert.assertEquals(numberOfCurrenciesExpected, numberOfCurrencies);
    }

    @Then("I verify if USD price against AED is within range")
    public void iVerifyIfUSDPriceAgainstAEDIsWithinRange() {
        boolean expectedRange = false;
        Response response = (Response) context.getContext(Context.RESPONSE);
        JsonPath jsonPath = response.jsonPath();
        Map<String, Float> ratesMap = jsonPath.getMap("rates");
        float usdagainstAedPrice = ratesMap.get("AED");
        if (usdagainstAedPrice >=3.6 && usdagainstAedPrice<3.7)
            expectedRange = true;
        Assert.assertTrue(expectedRange);
    }
}
