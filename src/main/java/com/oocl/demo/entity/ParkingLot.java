package com.oocl.demo.entity;

/**
 * Created by Vito Zhuang on 8/23/2018.
 */
public class ParkingLot {
	private Long id;
	private String name;
	private int size;
	private boolean status;

	public ParkingLot(String name, int size) {
		this.name = name;
		this.size = size;
	}

	public ParkingLot() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}


	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
