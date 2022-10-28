package com.windcloud.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.windcloud.config.Response;
import com.windcloud.constants.CommanConstants;
import com.windcloud.dto.RoomDTO;
import com.windcloud.entity.Game;
import com.windcloud.entity.Room;
import com.windcloud.repository.RoomRepository;
import com.windcloud.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService
{

	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ResponseEntity<?> createRoom(RoomDTO roomDTO)
	{
		Response<Room> response=new Response<Room>();
		if(roomDTO.getRoomName()==null||"".equals(roomDTO.getRoomName()))
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.ROOM_NAME_EMPTY);
			return new ResponseEntity<Response<?>>(response, HttpStatus.BAD_REQUEST);
		}
		Room room=modelMapper.map(roomDTO, Room.class);		
		Room rm=saveRoom(room);
		if(rm!=null)
		{
			response.setData(rm);
			response.setStatus(CommanConstants.SUCCESS);
			response.setMessage(CommanConstants.MSG_SUCCESS_SAVE);
			return new ResponseEntity<Response<?>>(response, HttpStatus.OK);
		}
		else
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.MSG_FAIED);
			return new ResponseEntity<Response<?>>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> getRoom(String roomNo)
	{
		Response<Room> response=new Response<Room>();
		if(roomNo==null||"".equals(roomNo))
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.ROOM_NAME_EMPTY);
			return new ResponseEntity<Response<?>>(response, HttpStatus.BAD_REQUEST);
		}
		Optional<Room> roomOtp=roomRepository.findById(roomNo);		
		if(roomOtp.isPresent())
		{
			response.setData(roomOtp.get());
			response.setStatus(CommanConstants.SUCCESS);
			response.setMessage(CommanConstants.SUCCESS_DATA_FOUND);
			return new ResponseEntity<Response<?>>(response, HttpStatus.OK);
		}
		else
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.INVALID_ROOM_NO);
			return new ResponseEntity<Response<?>>(response, HttpStatus.OK);
		}
	}
	
	public Room saveRoom(Room rm)
	{
		String roomNo=CommanConstants.ROOM_NO_GENERATOR+roomRepository.findCount()+1;
		rm.setRoomNo(roomNo);
		return roomRepository.save(rm);
	}
	
	@Override
	public ResponseEntity<?> getAllRoom() 
	{
		
		Response<List<Room>> response = new Response<List<Room>>();
		List<Room>gameList=roomRepository.findAll();
		if(gameList!=null&&gameList.size()>0)
		{
			response.setData(gameList);
			response.setStatus(CommanConstants.SUCCESS);
			response.setMessage(CommanConstants.SUCCESS_DATA_FOUND);
			return new ResponseEntity<Response<?>>(response, HttpStatus.OK);
		}
		else
		{
			response.setStatus(CommanConstants.FAILED);
			response.setMessage(CommanConstants.DATA_NOT_FOUND);
			return new ResponseEntity<Response<?>>(response, HttpStatus.OK);
		}
	}
	
}
