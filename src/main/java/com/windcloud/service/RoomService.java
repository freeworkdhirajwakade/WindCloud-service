package com.windcloud.service;

import org.springframework.http.ResponseEntity;

import com.windcloud.dto.RoomDTO;
import com.windcloud.entity.Room;

public interface RoomService {
	
	public ResponseEntity<?> createRoom(RoomDTO roomDTO);

	public ResponseEntity<?> getRoom(String roomNo);
	public ResponseEntity<?> getAllRoom();

	public Room findRoomByNo(String roomNo);

}
