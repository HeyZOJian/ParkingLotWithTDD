package com.oocl.demo.service;

import com.oocl.demo.repository.ParkingLotRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.jupiter.api.extension.ExtendWith;
/**
 * Created by Vito Zhuang on 8/23/2018.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
public class ParkingLotTest {

	@Mock
	ParkingLotRepository parkingLotRepository;

	@Test
	public void should_return_true_when_create_a_new_parkingLot_given_name_and_size (){
	   String name = "OOCL-parkingLot";
	   int size = 20;

	   boolean result = new ParkingLotService(parkingLotRepository).createParkingLot(name,size);

		Assert.assertTrue(result);
	}
}
