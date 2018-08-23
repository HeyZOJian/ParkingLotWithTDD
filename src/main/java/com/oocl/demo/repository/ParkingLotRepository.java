package com.oocl.demo.repository;

import com.oocl.demo.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Vito Zhuang on 8/23/2018.
 */
@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot,Long> {
}
