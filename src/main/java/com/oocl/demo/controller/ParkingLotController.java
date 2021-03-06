package com.oocl.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.oocl.demo.entity.ParkingLot;
import com.oocl.demo.exception.ParkinglotStillHasCarsException;
import com.oocl.demo.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity createParkingLot(@RequestBody JSONObject request) {
		if (parkingLotService.createParkingLot(request.get("name").toString(), (int) request.get("size"))) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@Transactional
	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateParkingLot(@PathVariable long id,
	                                       @RequestBody ParkingLot parkingLot) {
		if (parkingLotService.updateParkingLot(id, parkingLot)) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@Transactional
	@PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity freezeParkingLot(@PathVariable long id) {
		try {
			parkingLotService.freezeParkingLot(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (NullPointerException e1) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e2) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}
