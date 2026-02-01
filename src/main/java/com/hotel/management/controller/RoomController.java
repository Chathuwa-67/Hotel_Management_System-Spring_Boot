package com.hotel.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.hotel.management.entity.Room;
import com.hotel.management.service.RoomService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/rooms")
public class RoomController {

	@Autowired
	RoomService roomService;
	
	@GetMapping("")
	public String getAllRooms(HttpSession session, Model model) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}
		List<Room> rooms = roomService.getAllRooms();
		model.addAttribute("rooms", rooms);
		return "admin/rooms";
	}
	
	@GetMapping("/add")
	public String addRoomForm(HttpSession session, Model model) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}
		model.addAttribute("room", new Room());
		model.addAttribute("isEdit", false);
		return "admin/room-form";
	}
	
	@PostMapping("/add")
	public String addRoom(@ModelAttribute Room room, HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}
		roomService.addRoom(room);
		return "redirect:/admin/rooms";
	}
	
	@GetMapping("/edit/{id}")
	public String editRoomForm(@PathVariable int id, HttpSession session, Model model) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}
		Room room = roomService.getRoomById(id);
		model.addAttribute("room", room);
		model.addAttribute("isEdit", true);
		return "admin/room-form";
	}
	
	@PostMapping("/update/{id}")
	public String updateRoom(@PathVariable int id, @ModelAttribute Room room, HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}
		roomService.updateRoom(id, room);
		return "redirect:/admin/rooms";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteRoom(@PathVariable int id, HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}
		roomService.deleteRoom(id);
		return "redirect:/admin/rooms";
	}
	
	// REST API endpoints
	@GetMapping("/api/all")
	@ResponseBody
	public List<Room> getAllRoomsAPI() {
		return roomService.getAllRooms();
	}
	
	@GetMapping("/api/available")
	@ResponseBody
	public List<Room> getAvailableRoomsAPI() {
		return roomService.getAvailableRooms();
	}
	
	@GetMapping("/api/{id}")
	@ResponseBody
	public Room getRoomByIdAPI(@PathVariable int id) {
		return roomService.getRoomById(id);
	}
}
