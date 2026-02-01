package com.hotel.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	
	private static final String ADMIN_USERNAME = "admin";
	private static final String ADMIN_PASSWORD = "admin123";
	
	@GetMapping("/admin/login")
	public String loginPage() {
		return "admin/login";
	}
	
	@PostMapping("/admin/login")
	public String login(@RequestParam String username, 
						@RequestParam String password,
						HttpSession session, Model model) {
		if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
			session.setAttribute("admin", true);
			return "redirect:/admin/dashboard";
		} else {
			model.addAttribute("error", "Invalid username or password");
			return "admin/login";
		}
	}
	
	@GetMapping("/admin/dashboard")
	public String dashboard(HttpSession session, Model model) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/admin/login";
		}
		return "admin/dashboard";
	}
	
	@GetMapping("/admin/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/login";
	}
}
