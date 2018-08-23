package com.oocl.demo.service;

import com.oocl.demo.entity.ParkingLot;
import com.oocl.demo.exception.ParkinglotStillHasCarsException;
import com.oocl.demo.repository.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Vito Zhuang on 8/23/2018.
 */
@Service
public class ParkingLotService {

	private final ParkingLotRepository parkingLotRepository;

	@Autowired
	public ParkingLotService(ParkingLotRepository parkingLotRepository) {
		this.parkingLotRepository = parkingLotRepository;
	}

	public boolean createParkingLot(String name, int size) {
		ParkingLot parkingLot = new ParkingLot(name, size);
		parkingLotRepository.save(parkingLot);
		return true;
	}

	public boolean updateParkingLot(Long id, ParkingLot newParkingLot) {
		try {
			ParkingLot parkingLot = parkingLotRepository.findById(id).orElse(null);
			parkingLot.setName(newParkingLot.getName() != null ? newParkingLot.getName() : parkingLot.getName());
			parkingLot.setSize(newParkingLot.getSize() != 0 ? newParkingLot.getSize() : parkingLot.getSize());
			parkingLotRepository.save(parkingLot);
			return true;
		} catch (NullPointerException e) {
			return false;
		}
	}

	public boolean freezeParkingLot(Long id) throws Exception {
		ParkingLot parkingLot = parkingLotRepository.findById(id).orElse(null);
		if (parkingLot.getSurplusSize() == parkingLot.getSize()) {
			parkingLot.setStatus(!parkingLot.getStatus());
			return true;
		} else {
			throw new ParkinglotStillHasCarsException();
		}
	}
}
