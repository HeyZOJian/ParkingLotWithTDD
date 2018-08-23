package com.oocl.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.oocl.demo.entity.ParkingLot;
import com.oocl.demo.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

/**
 * Created by Vito Zhuang on 8/23/2018.
 */
@RequestMapping("/parkingLots")
@RestController
public class ParkingLotController {
	@Autowired
	private ParkingLotService parkingLotService;

	@Transactional
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createParkingLots(@RequestBody JSONObject request) {
		if (parkingLotService.createParkingLot(request.get("name").toString(), (int) request.get("size"))) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
