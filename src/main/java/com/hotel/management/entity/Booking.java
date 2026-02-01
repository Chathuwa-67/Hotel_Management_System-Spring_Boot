package com.hotel.management.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "bookings")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "guest_id", nullable = false)
	private Guest guest;
	
	@Column(name = "check_in_date", nullable = false)
	private LocalDate checkInDate;
	
	@Column(name = "check_out_date", nullable = false)
	private LocalDate checkOutDate;
	
	@Column(name = "number_of_guests")
	private int numberOfGuests;
	
	@Column(name = "total_price")
	private double totalPrice;
	
	@Column(name = "status")
	private String status; // Confirmed, Pending, Cancelled
	
	@Column(name = "booking_date")
	private LocalDate bookingDate;
	
	public Booking() {
		super();
		this.bookingDate = LocalDate.now();
		this.status = "Pending";
	}

	public Booking(int id, Room room, Guest guest, LocalDate checkInDate, 
				   LocalDate checkOutDate, int numberOfGuests, String status) {
		super();
		this.id = id;
		this.room = room;
		this.guest = guest;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.numberOfGuests = numberOfGuests;
		this.status = status;
		this.bookingDate = LocalDate.now();
		calculateTotalPrice();
	}
	
	public void calculateTotalPrice() {
		if (checkInDate != null && checkOutDate != null && room != null) {
			long days = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
			if (days < 1) days = 1;
			this.totalPrice = days * room.getPricePerNight();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public LocalDate getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
}
