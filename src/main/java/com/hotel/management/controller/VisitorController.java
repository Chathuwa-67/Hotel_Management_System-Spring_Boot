package com.hotel.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.hotel.management.entity.Booking;
import com.hotel.management.entity.Room;
import com.hotel.management.entity.Guest;
import com.hotel.management.service.BookingService;
import com.hotel.management.service.RoomService;
import com.hotel.management.service.GuestService;

@Controller
public class VisitorController {

	@Autowired
	RoomService roomService;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	GuestService guestService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<Room> availableRooms = roomService.getAvailableRooms();
		model.addAttribute("rooms", availableRooms);
		return "visitor/home";
	}
	
	@GetMapping("/book/{roomId}")
	public String bookingForm(@PathVariable int roomId, Model model) {
		Room room = roomService.getRoomById(roomId);
		model.addAttribute("room", room);
		model.addAttribute("booking", new Booking());
		model.addAttribute("guest", new Guest());
		return "visitor/booking-form";
	}
	
	@PostMapping("/book")
	public String createBooking(@ModelAttribute Booking booking,
								@RequestParam int roomId,
								@RequestParam String guestName,
								@RequestParam String guestPhone,
								@RequestParam String guestEmail,
								@RequestParam(required = false) String guestAddress,
								Model model) {
		// Create or find guest
		Guest guest = guestService.getGuestByPhone(guestPhone);
		if (guest == null) {
			guest = new Guest();
			guest.setName(guestName);
			guest.setPhone(guestPhone);
			guest.setEmail(guestEmail);
			guest.setAddress(guestAddress);
		}
		
		// Create booking
		Room room = roomService.getRoomById(roomId);
		booking.setRoom(room);
		booking.setGuest(guest);
		booking.setStatus("Confirmed");
		bookingService.addBooking(booking);
		
		model.addAttribute("booking", booking);
		return "visitor/booking-success";
	}
	
	@GetMapping("/cancel-booking")
	public String cancelBookingPage() {
		return "visitor/cancel-booking";
	}
	
	@PostMapping("/cancel-booking")
	public String cancelBooking(@RequestParam int bookingId, Model model) {
		bookingService.cancelBooking(bookingId);
		model.addAttribute("message", "Booking cancelled successfully!");
		return "visitor/cancel-success";
	}
	
	@GetMapping("/my-bookings")
	public String myBookings(@RequestParam String phone, Model model) {
		Guest guest = guestService.getGuestByPhone(phone);
		if (guest != null) {
			List<Booking> bookings = bookingService.getBookingsByGuestId(guest.getId());
			model.addAttribute("bookings", bookings);
			model.addAttribute("guest", guest);
		}
		return "visitor/my-bookings";
	}
}
