package org.example.utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.function.Function;

import static io.restassured.RestAssured.with;

public class HttpClientAllureWrapper {


    public static Response restAssuredClient(Function<RequestSpecification, Response> request) {
        return request.apply(restAssuredSpecificationWithAllure());
    }

    private static RequestSpecification restAssuredSpecificationWithAllure() {
        return with().filter(new AllureRestAssured()).log().all();
    }
}
