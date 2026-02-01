package com.hotel.management.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotel.management.entity.Room;
import com.hotel.management.repository.RoomRepository;

@Service
public class RoomService {

	@Autowired
	RoomRepository roomRepository;

	public List<Room> getAllRooms() {
		return roomRepository.findAll();
	}

	public Room getRoomById(int id) {
		return roomRepository.findById(id).orElse(null);
	}

	public void addRoom(Room room) {
		if (room.getStatus() == null || room.getStatus().isEmpty()) {
			room.setStatus("Available");
		}
		roomRepository.save(room);
	}

	public Room updateRoom(int id, Room updatedRoom) {
		Room room = roomRepository.findById(id).orElse(null);
		if (room != null) {
			room.setRoomNumber(updatedRoom.getRoomNumber());
			room.setRoomType(updatedRoom.getRoomType());
			room.setPricePerNight(updatedRoom.getPricePerNight());
			room.setFloor(updatedRoom.getFloor());
			room.setCapacity(updatedRoom.getCapacity());
			room.setStatus(updatedRoom.getStatus());
			room.setDescription(updatedRoom.getDescription());
			roomRepository.save(room);
		}
		return room;
	}

	public void deleteRoom(int id) {
		roomRepository.deleteById(id);
	}
	
	public List<Room> getRoomsByStatus(String status) {
		return roomRepository.getRoomsByStatus(status);
	}
	
	public List<Room> getRoomsByType(String roomType) {
		return roomRepository.getRoomsByType(roomType);
	}
	
	public List<Room> getAvailableRooms() {
		return roomRepository.getAvailableRooms();
	}
	
	public Room getRoomByRoomNumber(String roomNumber) {
		return roomRepository.getRoomByRoomNumber(roomNumber);
	}
}
