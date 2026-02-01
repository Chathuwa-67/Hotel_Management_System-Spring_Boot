package com.hotel.management.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotel.management.entity.Guest;
import com.hotel.management.repository.GuestRepository;

@Service
public class GuestService {

	@Autowired
	GuestRepository guestRepository;

	public List<Guest> getAllGuests() {
		return guestRepository.findAll();
	}

	public Guest getGuestById(int id) {
		return guestRepository.findById(id).orElse(null);
	}

	public void addGuest(Guest guest) {
		guestRepository.save(guest);
	}

	public Guest updateGuest(int id, Guest updatedGuest) {
		Guest guest = guestRepository.findById(id).orElse(null);
		if (guest != null) {
			guest.setName(updatedGuest.getName());
			guest.setEmail(updatedGuest.getEmail());
			guest.setPhone(updatedGuest.getPhone());
			guest.setAddress(updatedGuest.getAddress());
			guestRepository.save(guest);
		}
		return guest;
	}

	public void deleteGuest(int id) {
		guestRepository.deleteById(id);
	}
	
	public Guest getGuestByPhone(String phone) {
		return guestRepository.getGuestByPhone(phone);
	}
	
	public Guest getGuestByEmail(String email) {
		return guestRepository.getGuestByEmail(email);
	}
	
	public List<Guest> searchGuestsByName(String name) {
		return guestRepository.searchGuestsByName(name);
	}
}
