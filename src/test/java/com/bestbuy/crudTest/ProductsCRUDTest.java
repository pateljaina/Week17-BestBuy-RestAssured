package com.bestbuy.crudTest;

import com.bestbuy.model.ProductPojo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest {
    @Before
    public void setUp() {
        RestAssured.basePath = "/products";
    }

    @Test
    public void test001() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("Abc");
        productPojo.setType("New Product");
        productPojo.setPrice(59.99);
        productPojo.setShipping("");
        productPojo.setUpc(12345);
        productPojo.setDescription("Brand New Product");
        productPojo.setManufacturer("Made By Product");
        productPojo.setModel("New1234");
        productPojo.setUrl("prime@gmail.com");
        productPojo.setImage("");

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .post();
        response.then().log().all().statusCode(201);
    }

    @Test
    public void test002() {

        Response response = given()
                .when()
                .get("/9999680");
        response.then().log().all().statusCode(200);
    }

    @Test
    public void test003() {
        ProductPojo productPojo = new ProductPojo();
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .patch("/9999680");
        response.then().log().all().statusCode(200);
    }

    @Test
    public void test004() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/9999680");
        response.then().log().all().statusCode(200);
    }

}

