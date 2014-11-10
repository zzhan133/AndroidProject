package com.example.myfirstapp;

import android.os.SystemClock;

public class ParkingItem {
	
	public static final String ITEM_SEP = System.getProperty("line.separator");
	
	private long parkingStartTime = 0;
	
	private final int carResourceID;
	
	private final int priceResourceID;
	
	private Boolean bStatus = false;
	
	
	ParkingItem(int carID, int priceID){
		carResourceID = carID;
		priceResourceID = priceID;
	}

	public long getParkingStartTime() {
		return parkingStartTime;
	}

	public void setParkingStartTime(long parkingStartTime) {
		this.parkingStartTime = SystemClock.uptimeMillis();
	}

	public int getCarResourceID() {
		return carResourceID;
	}

	public int getPriceResourceID() {
		return priceResourceID;
	}

	public Boolean getbStatus() {
		return bStatus;
	}

	public void setbStatus(Boolean bStatus) {
		this.bStatus = bStatus;
	}

	
	public String toString() {
		return bStatus+ ITEM_SEP + parkingStartTime;
	}
}
