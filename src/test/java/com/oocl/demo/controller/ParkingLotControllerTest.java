package com.oocl.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.demo.service.ParkingLotService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Vito Zhuang on 8/23/2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ParkingLotController.class)
public class ParkingLotControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private ParkingLotService parkingLotsService;
	@Autowired
	private ObjectMapper mapper;

	@Test
	public void should_return_201_when_create_a_new_parkingLot () throws Exception {
		JSONObject content = new JSONObject();
		content.put("name","OOCL-ParkingLot");
		content.put("size",12);
		given(parkingLotsService.createParkingLot(anyString(),anyInt())).willReturn(true);

		ResultActions resultActions = mvc.perform(post("/parkingLots")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("UTF-8")
				.content(mapper.writeValueAsString(content)));

		resultActions.andExpect(status().isCreated()).andDo(print());
	}
}
