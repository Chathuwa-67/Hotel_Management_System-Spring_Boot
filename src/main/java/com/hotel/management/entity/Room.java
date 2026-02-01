package com.hotel.management.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "room_number", unique = true, nullable = false)
	private String roomNumber;
	
	@Column(name = "room_type", nullable = false)
	private String roomType; // Single, Double, Suite
	
	@Column(name = "price_per_night", nullable = false)
	private double pricePerNight;
	
	@Column(name = "floor")
	private int floor;
	
	@Column(name = "capacity")
	private int capacity;
	
	@Column(name = "status")
	private String status; // Available, Booked
	
	@Column(name = "description")
	private String description;
	
	public Room() {
		super();
	}

	public Room(int id, String roomNumber, String roomType, double pricePerNight, 
				int floor, int capacity, String status, String description) {
		super();
		this.id = id;
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.pricePerNight = pricePerNight;
		this.floor = floor;
		this.capacity = capacity;
		this.status = status;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
