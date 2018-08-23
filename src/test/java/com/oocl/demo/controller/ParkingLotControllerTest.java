package com.oocl.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.demo.entity.ParkingLot;
import com.oocl.demo.exception.ParkinglotStillHasCarsException;
import com.oocl.demo.repository.ParkingLotRepository;
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

import javax.swing.text.html.Option;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
	private ParkingLotRepository parkingLotRepository;
	@MockBean
	private ParkingLotService parkingLotsService;
	@Autowired
	private ObjectMapper mapper;

	@Test
	public void should_return_status_code_201_when_create_a_new_parkingLot () throws Exception {
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

	@Test
	public void should_return_status_code_204_when_update_parkingLot () throws Exception {
		JSONObject content = new JSONObject();
		content.put("name","OOCL-ParkingLot-1");
		content.put("size",20);
		given(parkingLotsService.updateParkingLot(anyLong(),any(ParkingLot.class))).willReturn(true);

		ResultActions resultActions = mvc.perform(put("/parkingLots/1")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("UTF-8")
				.content(mapper.writeValueAsString(content)));

		resultActions.andExpect(status().isNoContent()).andDo(print());
	}

	@Test
	public void should_return_status_code_404_when_update_parkingLot_failed_given_nonexistent_parkinglot_id () throws Exception {
		JSONObject content = new JSONObject();
		content.put("name","OOCL-ParkingLot-1");
		content.put("size",20);
		given(parkingLotsService.updateParkingLot(anyLong(),any(ParkingLot.class))).willReturn(false);

		ResultActions resultActions = mvc.perform(put("/parkingLots/1")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.characterEncoding("UTF-8")
				.content(mapper.writeValueAsString(content)));

		resultActions.andExpect(status().isNotFound()).andDo(print());
	}

	@Test
	public void should_return_status_code_204_when_freeze_parkingLot_given_parkinglot_id () throws Exception {
		given(parkingLotRepository.findById(anyLong())).willReturn(java.util.Optional.of(new ParkingLot("oocl-parkinglot",20)));

		ResultActions resultActions = mvc.perform(patch("/parkingLots/1"));

		resultActions.andExpect(status().isNoContent()).andDo(print());
	}

	@Test
	public void should_return_status_code_404_when_freeze_parkingLot_given_nonexistent_parkinglot_id () throws Exception {
		given(parkingLotsService.freezeParkingLot(anyLong())).willThrow(NullPointerException.class);

		ResultActions resultActions = mvc.perform(patch("/parkingLots/1"));

		resultActions.andExpect(status().isNotFound()).andDo(print());
	}

	@Test
	public void should_return_status_code_404_when_freeze_parkingLot_given_parkinglot_that_has_cars () throws Exception {
		ParkingLot parkingLot = new ParkingLot("oocl-parkinglot",20);
		parkingLot.setSurplusSize(12);
		given(parkingLotsService.freezeParkingLot(anyLong())).willThrow(ParkinglotStillHasCarsException.class);

		ResultActions resultActions = mvc.perform(patch("/parkingLots/1"));

		resultActions.andExpect(status().isBadRequest()).andDo(print());
	}
}
