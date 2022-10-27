package com.windcloud.service;

import org.springframework.http.ResponseEntity;

import com.windcloud.dto.RoomDTO;

public interface RoomService {
	
	public ResponseEntity<?> createRoom(RoomDTO roomDTO);

	public ResponseEntity<?> getRoom(String roomNo);

}
