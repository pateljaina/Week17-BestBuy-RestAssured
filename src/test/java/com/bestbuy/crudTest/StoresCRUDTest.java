package com.bestbuy.crudTest;

import com.bestbuy.model.StorePojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCRUDTest {
    @Before
    public void setUp(){
        RestAssured.basePath = "/stores";
    }
    @Test
    public void test001() {
        StorePojo storePojo=new StorePojo();
        storePojo.setName("Asda");
        storePojo.setType("Croydon");
        storePojo.setAddress("11");
        storePojo.setAddress2("Little Road");
        storePojo.setCity("");
        storePojo.setState("London");
        storePojo.setZip("CR2 7ST");
        storePojo.setLat(123345);
        storePojo.setLng(12345);
        storePojo.setHours("8:00");

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .post();
        response.then().log().all().statusCode(201);
    }
    @Test
    public void test002 () {

        Response response = given()
                .when()
                .get("/8922");
        response.then().log().all().statusCode(200);
    }
    @Test
    public void test003 () {
        StorePojo storePojo=new StorePojo();
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .patch("/8922");
        response.then().log().all().statusCode(200);
    }
    @Test
    public void test004 () {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/8922");
        response.then().log().all().statusCode(200);
    }
}
