package com.hotel.management.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.hotel.management.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
	
	@Query("SELECT r FROM Room r WHERE r.status = :status")
	List<Room> getRoomsByStatus(String status);
	
	@Query("SELECT r FROM Room r WHERE r.roomType = :roomType")
	List<Room> getRoomsByType(String roomType);
	
	@Query("SELECT r FROM Room r WHERE r.roomNumber = :roomNumber")
	Room getRoomByRoomNumber(String roomNumber);
	
	@Query("SELECT r FROM Room r WHERE r.status = 'Available'")
	List<Room> getAvailableRooms();
}
