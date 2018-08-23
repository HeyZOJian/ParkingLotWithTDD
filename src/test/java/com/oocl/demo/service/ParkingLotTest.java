package com.oocl.demo.service;

import com.oocl.demo.entity.ParkingLot;
import com.oocl.demo.repository.ParkingLotRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Vito Zhuang on 8/23/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
public class ParkingLotTest {

	@Mock
	private ParkingLotRepository parkingLotRepository;

	private ParkingLotService parkingLotService;

	@Before
	public void init() {
		this.parkingLotService = new ParkingLotService(parkingLotRepository);
	}

	@Test
	public void should_return_true_when_create_a_new_parkingLot_given_name_and_size() {
		String name = "OOCL-parkingLot";
		int size = 20;

		boolean result = parkingLotService.createParkingLot(name, size);

		Assert.assertTrue(result);
	}

	@Test
	public void should_return_true_when_update_parkingLot_info_given_parkingLot_id_and_new_info() {
		Long id = 1L;
		ParkingLot parkingLot = new ParkingLot("old name", 12);
		ParkingLot newParkingLot = new ParkingLot("new name", 20);


		given(parkingLotRepository.findById(id)).willReturn((java.util.Optional.of(parkingLot)));
		boolean result = parkingLotService.updateParkingLot(id, newParkingLot);

		Assert.assertTrue(result);
	}

	@Test
	public void should_return_false_when_update_parkingLot_info_given_parkingLot_is_not_exist() {
		Long id = 1L;
		ParkingLot newParkingLot = new ParkingLot("new name", 20);

		given(parkingLotRepository.findById(id)).willReturn(null);
		boolean result = parkingLotService.updateParkingLot(id, newParkingLot);

		Assert.assertFalse(result);
	}

	@Test
	public void should_return_true_when_freeze_parkingLot_given_parkingLot_id (){
		Long id = 1L;
		ParkingLot parkingLot = new ParkingLot("old name", 12);

		given(parkingLotRepository.findById(id)).willReturn((java.util.Optional.of(parkingLot)));
		boolean result = parkingLotService.freezeParkingLot(id);

		Assert.assertTrue(result);
	}

	@Test
	public void should_return_false_when_freeze_parkingLot_given_parkingLot_id_that_have_car (){
		Long id = 1L;
		ParkingLot parkingLot = new ParkingLot("old name", 12);
		parkingLot.setSurplusSize(3);

		given(parkingLotRepository.findById(id)).willReturn((java.util.Optional.of(parkingLot)));
		boolean result = parkingLotService.freezeParkingLot(id);

		Assert.assertFalse(result);
	}

}
