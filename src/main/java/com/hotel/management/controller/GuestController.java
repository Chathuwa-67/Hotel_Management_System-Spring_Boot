package com.hotel.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.hotel.management.entity.Guest;
import com.hotel.management.entity.Booking;
import com.hotel.management.service.GuestService;
import com.hotel.management.service.BookingService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/guests")
public class GuestController {

	@Autowired
	GuestService guestService;
	
	@Autowired
	BookingService bookingService;
	
	@GetMapping("")
	public String getAllGuests(HttpSession session, Model model) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}
		List<Guest> guests = guestService.getAllGuests();
		model.addAttribute("guests", guests);
		return "admin/guests";
	}
	
	@GetMapping("/history/{id}")
	public String guestHistory(@PathVariable int id, HttpSession session, Model model) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}
		Guest guest = guestService.getGuestById(id);
		List<Booking> bookings = bookingService.getBookingsByGuestId(id);
		model.addAttribute("guest", guest);
		model.addAttribute("bookings", bookings);
		return "admin/guest-history";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteGuest(@PathVariable int id, HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}
		guestService.deleteGuest(id);
		return "redirect:/admin/guests";
	}
	
	// REST API endpoints
	@GetMapping("/api/all")
	@ResponseBody
	public List<Guest> getAllGuestsAPI() {
		return guestService.getAllGuests();
	}
	
	@GetMapping("/api/{id}")
	@ResponseBody
	public Guest getGuestByIdAPI(@PathVariable int id) {
		return guestService.getGuestById(id);
	}
}
