package com.hotel.management.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotel.management.entity.Booking;
import com.hotel.management.entity.Room;
import com.hotel.management.repository.BookingRepository;
import com.hotel.management.repository.RoomRepository;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	RoomRepository roomRepository;

	public List<Booking> getAllBookings() {
		return bookingRepository.getAllBookingsOrderByDate();
	}

	public Booking getBookingById(int id) {
		return bookingRepository.findById(id).orElse(null);
	}

	public void addBooking(Booking booking) {
		booking.calculateTotalPrice();
		bookingRepository.save(booking);
		
		Room room = booking.getRoom();
		if (room != null) {
			room.setStatus("Booked");
			roomRepository.save(room);
		}
	}

	public Booking updateBooking(int id, Booking updatedBooking) {
		Booking booking = bookingRepository.findById(id).orElse(null);
		if (booking != null) {
			if (booking.getRoom().getId() != updatedBooking.getRoom().getId()) {
				Room oldRoom = booking.getRoom();
				oldRoom.setStatus("Available");
				roomRepository.save(oldRoom);
				
				Room newRoom = updatedBooking.getRoom();
				newRoom.setStatus("Booked");
				roomRepository.save(newRoom);
			}
			
			booking.setRoom(updatedBooking.getRoom());
			booking.setGuest(updatedBooking.getGuest());
			booking.setCheckInDate(updatedBooking.getCheckInDate());
			booking.setCheckOutDate(updatedBooking.getCheckOutDate());
			booking.setNumberOfGuests(updatedBooking.getNumberOfGuests());
			booking.setStatus(updatedBooking.getStatus());
			booking.calculateTotalPrice();
			bookingRepository.save(booking);
		}
		return booking;
	}

	public void deleteBooking(int id) {
		Booking booking = bookingRepository.findById(id).orElse(null);
		if (booking != null) {
			Room room = booking.getRoom();
			if (room != null) {
				room.setStatus("Available");
				roomRepository.save(room);
			}
			bookingRepository.deleteById(id);
		}
	}
	
	public void cancelBooking(int id) {
		Booking booking = bookingRepository.findById(id).orElse(null);
		if (booking != null) {
			booking.setStatus("Cancelled");
			bookingRepository.save(booking);
			
			Room room = booking.getRoom();
			if (room != null) {
				room.setStatus("Available");
				roomRepository.save(room);
			}
		}
	}
	
	public List<Booking> getBookingsByStatus(String status) {
		return bookingRepository.getBookingsByStatus(status);
	}
	
	public List<Booking> getBookingsByGuestId(int guestId) {
		return bookingRepository.getBookingsByGuestId(guestId);
	}
	
	public List<Booking> getBookingsByRoomId(int roomId) {
		return bookingRepository.getBookingsByRoomId(roomId);
	}
}
