package com.bestbuy.testSuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }
    @Test
    public void test001(){
        response.body("total", equalTo(1561));
    }
    @Test
    public void test002 () {
        response.body("limit", Matchers.equalTo(10));
    }

    @Test
    public void test003 (){
        response.body("data[1].name", equalTo("Inver Grove Heights"));

    }

    @Test
    public void test004(){
        response.body("data.name", hasItems("Roseville","Burnsville","Maplewood"));
    }
    @Test
    public void test005(){
        response.body("data[2].services[2].storeservices",hasKey("storeId"));
    }

    @Test
    public void test006(){
        response.body("data.findAll{it.name=='Roseville'}", hasItem(hasEntry("createdAt", "2016-11-17T17:57:05.853Z")));
    }
    @Test
    public void test007(){
        response.body("data[3].state",equalTo("MN"));
    }
    @Test
    public void test008(){
        response.body("data[8].name",equalTo("Rochester"));
    }
    @Test
    public void test009(){
        response.body("data[5].id",equalTo(11));
    }
    public void test010(){
        response.body("data[6].services[3].storeservices.serviceId",equalTo(4));
    }
}
