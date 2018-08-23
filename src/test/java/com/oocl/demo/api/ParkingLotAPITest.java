package com.oocl.demo.api;

import com.alibaba.fastjson.JSONObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

/**
 * Created by Vito Zhuang on 8/23/2018.
 */
public class ParkingLotAPITest {
	private static RequestSpecification requestSpec;

	@BeforeAll
	public static void createRequestSpecification() {

		requestSpec = new RequestSpecBuilder().
				setBaseUri("http://localhost").
				setPort(8080).
				build();
	}

	@Test
	public void should_return_status_is_200_when_create_parkinglot() {

		JSONObject content = new JSONObject();
		content.put("name","OOCL-ParkingLot");
		content.put("size",12);

		given().
				spec(requestSpec).
				body(content).
				when().
				contentType(String.valueOf(MediaType.APPLICATION_JSON)).
				post("/parkingLots").
				then().
				assertThat().
				statusCode(201);
	}



}
