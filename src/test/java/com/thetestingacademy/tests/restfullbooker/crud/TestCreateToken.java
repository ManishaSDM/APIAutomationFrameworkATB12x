package com.thetestingacademy.tests.restfullbooker.crud;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.TmsLink;
import org.testng.annotations.Test;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class TestCreateToken extends BaseTest {
    @Test(groups = "reg", priority = 1)
    @TmsLink("https://bugz.atlassian.net/browse/BUG-19")
    @Owner("Promode")
    @Description("TC#1  - Create Token and Verify")
    public void testTokenPOST() {

        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification)
                .when()
                .body(payloadManager.setAuthPayload()).post();


        // Extraction ( JSON String response to Java Object)
        String token = payloadManager.getTokenFromJSON(response.asString());
        System.out.println(token);


        // Validation of the request.
        assertActions.verifyStringKeyNotNull(token);


    }

    @Test(groups = "reg", priority = 1)
    @TmsLink("https://bugz.atlassian.net/browse/BUG-19")
    @Owner("Promode")
    @Description("TC#2  - Create Invalid Token and Verify")
    public void testTokenPOST_Negative() {

        requestSpecification.basePath(APIConstants.AUTH_URL);
        response = RestAssured.given(requestSpecification)
                .when()
                .body("{}").post();


        // Extraction ( JSON String response to Java Object)
        String invalid_reason = payloadManager.getInvalidResponse(response.asString());
        System.out.println(invalid_reason);


        // Validation of the request.
        assertActions.verifyStringKey(invalid_reason,"Bad credentials");



    }
}
