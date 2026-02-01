package com.hotel.management.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.hotel.management.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
	
	@Query("SELECT b FROM Booking b WHERE b.status = :status")
	List<Booking> getBookingsByStatus(String status);
	
	@Query("SELECT b FROM Booking b WHERE b.guest.id = :guestId ORDER BY b.bookingDate DESC")
	List<Booking> getBookingsByGuestId(int guestId);
	
	@Query("SELECT b FROM Booking b WHERE b.room.id = :roomId")
	List<Booking> getBookingsByRoomId(int roomId);
	
	@Query("SELECT b FROM Booking b ORDER BY b.bookingDate DESC")
	List<Booking> getAllBookingsOrderByDate();
}
