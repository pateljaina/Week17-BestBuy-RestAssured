package com.bestbuy.testSuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

public class ProductsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    @Test
    public void test11() {
        response.body("total", equalTo(51957));
    }

    @Test
    public void test12() {
        response.body("limit", equalTo(10));
    }

    @Test
    public void test13() {
        response.body("data.name", hasItem("Duracell - AAA Batteries (4-Pack)"));
    }

    @Test
    public void test14() {
        response.body("data.name", Matchers.hasItem("Duracell - AA 1.5V CopperTop Batteries (4-Pack)"));
        response.body("data.name", Matchers.hasItem("Duracell - AA Batteries (8-Pack)"));
        response.body("data.name", Matchers.hasItem("Energizer - MAX Batteries AA (4-Pack)"));
    }

    @Test
    public void test15() {
        // response.body("data[3].categories[2].name", equalTo("Household Batteries"));
        response.body("data.findAll{it.id=150115}.categories[2].name", hasItem("Household Batteries"));
    }

    @Test
    public void test16() {
        //response.body("data[8].categories[1].name", equalTo("Housewares"));
        response.body("data[8].categories[1].name", equalTo("Housewares"));

    }

    @Test
    public void test17() {
        //  response.body("data[3].price", Matchers.equalTo("4.99"));
        // response.body("data[3].price", hasEntry("price", 4.99));
        // response.body(" data.findAll{it.price == '4.99'}", hasItem(hasEntry("")))
        //response.body("data[3].price", hasKey(4.99));
        response.body("data[3].price", equalTo("4.99"));
    }

    @Test
    public void test18() {
        response.body("data[5].name", Matchers.equalTo("Duracell - D Batteries (4-Pack)"));
    }

    @Test
    public void test19() {
        response.body("data[8].id", Matchers.equalTo(333179));
    }

    @Test
    public void test20() {
        response.body("data.find{it.id==346575}.categories.size", equalTo(5));
    }
}
