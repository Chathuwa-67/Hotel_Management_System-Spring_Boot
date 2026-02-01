package com.hotel.management.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.hotel.management.entity.Guest;

public interface GuestRepository extends JpaRepository<Guest, Integer> {
	
	@Query("SELECT g FROM Guest g WHERE g.phone = :phone")
	Guest getGuestByPhone(String phone);
	
	@Query("SELECT g FROM Guest g WHERE g.email = :email")
	Guest getGuestByEmail(String email);
	
	@Query("SELECT g FROM Guest g WHERE LOWER(g.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Guest> searchGuestsByName(String name);
}
