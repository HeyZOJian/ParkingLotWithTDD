package com.oocl.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.demo.service.ParkingLotService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
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
	ParkingLotController parkingLotsController;
	@Autowired
	private MockMvc mvc;
	@MockBean
	private ParkingLotService parkingLotsService;
	@Autowired
	private ObjectMapper mapper;

	@Test
	public void should_return_201_when_create_a_new_parkingLot () throws Exception {
		String name = "OOCL-ParkingLot";
		int size = 12;
		JSONObject content = new JSONObject();
		content.put("name",name);
		content.put("size",size);
		given(parkingLotsService.createParkingLot(name,size)).willReturn(true);

		ResultActions resultActions = mvc.perform(post("/parkingLots")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(mapper.writeValueAsString(content.toJSONString())));

		resultActions.andExpect(status().isCreated()).andDo(print());
	}
}
