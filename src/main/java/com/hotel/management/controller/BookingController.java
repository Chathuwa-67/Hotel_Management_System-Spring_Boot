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
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/bookings")
public class BookingController {

	@Autowired
	BookingService bookingService;
	
	@Autowired
	RoomService roomService;
	
	@Autowired
	GuestService guestService;
	
	@GetMapping("")
	public String getAllBookings(HttpSession session, Model model) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}
		List<Booking> bookings = bookingService.getAllBookings();
		model.addAttribute("bookings", bookings);
		return "admin/bookings";
	}
	
	@GetMapping("/edit/{id}")
	public String editBookingForm(@PathVariable int id, HttpSession session, Model model) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}
		Booking booking = bookingService.getBookingById(id);
		List<Room> rooms = roomService.getAllRooms();
		model.addAttribute("booking", booking);
		model.addAttribute("rooms", rooms);
		return "admin/booking-edit";
	}
	
	@PostMapping("/update/{id}")
	public String updateBooking(@PathVariable int id, @ModelAttribute Booking booking, 
								@RequestParam int roomId, HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}
		Room room = roomService.getRoomById(roomId);
		booking.setRoom(room);
		bookingService.updateBooking(id, booking);
		return "redirect:/admin/bookings";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteBooking(@PathVariable int id, HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}
		bookingService.deleteBooking(id);
		return "redirect:/admin/bookings";
	}
	
	@GetMapping("/cancel/{id}")
	public String cancelBooking(@PathVariable int id, HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}
		bookingService.cancelBooking(id);
		return "redirect:/admin/bookings";
	}
	
	// REST API endpoints
	@GetMapping("/api/all")
	@ResponseBody
	public List<Booking> getAllBookingsAPI() {
		return bookingService.getAllBookings();
	}
	
	@GetMapping("/api/{id}")
	@ResponseBody
	public Booking getBookingByIdAPI(@PathVariable int id) {
		return bookingService.getBookingById(id);
	}
	
	@GetMapping("/api/guest/{guestId}")
	@ResponseBody
	public List<Booking> getBookingsByGuestIdAPI(@PathVariable int guestId) {
		return bookingService.getBookingsByGuestId(guestId);
	}
}
