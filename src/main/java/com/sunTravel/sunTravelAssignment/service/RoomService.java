/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.sunTravel.sunTravelAssignment.service;

import com.sunTravel.sunTravelAssignment.dto.DeleteResponseDto;
import com.sunTravel.sunTravelAssignment.dto.RoomHotelDto;
import com.sunTravel.sunTravelAssignment.exception.DbException;
import com.sunTravel.sunTravelAssignment.exception.NotFoundException;
import com.sunTravel.sunTravelAssignment.mapper.RoomMapper;
import com.sunTravel.sunTravelAssignment.model.Hotel;
import com.sunTravel.sunTravelAssignment.model.Room;
import com.sunTravel.sunTravelAssignment.repository.HotelRepository;
import com.sunTravel.sunTravelAssignment.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 24 Apr 2023
 */

@Service
public class RoomService
{
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomMapper roomMapper;

    public RoomHotelDto saveRoom( RoomHotelDto roomHotelDto ) throws NotFoundException, IllegalArgumentException
    {
//        if(Objects.isNull( roomHotelDto.getRoomType() ) || Objects.isNull( roomHotelDto.getPrice()  )){
//            throw new IllegalArgumentException("invalid request");
//        }

        Room room = roomMapper.mapIn( roomHotelDto );
        Hotel hotel = hotelRepository.findById( roomHotelDto.getHotelId() ).orElseThrow(()-> new NotFoundException( "Hotel not found for the given id "+roomHotelDto.getHotelId() ));
        room.setHotel( hotel );
        Room savedRoom = roomRepository.save( room );
        return roomMapper.mapOut( savedRoom );
    }

    public RoomHotelDto getRoomById( Long id ) throws NotFoundException
    {
        if (id == null || id == 0) {
            throw new IllegalArgumentException("This is not a valid id");
        }
        else{
            Room room = roomRepository.findById( id ).orElseThrow( () -> new NotFoundException( "Room for id " + id + " was not found" ) );
            return roomMapper.mapOut( room );
        }
    }

    public List<RoomHotelDto> getAllRoom()
    {

        List<Room> roomList = roomRepository.findAll();
        List<RoomHotelDto> roomHotelDtoList = new ArrayList<>();
        for( Room room : roomList )
        {
            roomHotelDtoList.add( roomMapper.mapOut( room ) );
        }
        return roomHotelDtoList;
    }

    public List<RoomHotelDto> getRoomByHotelId( Long hotelId )
    {
        List<Room> roomListByHotelId = roomRepository.findByHotelHotelId( hotelId );
        List<RoomHotelDto> roomHotelDtoList = new ArrayList<>();
        for( Room room : roomListByHotelId )
        {
            roomHotelDtoList.add( roomMapper.mapOut( room ) );
        }
        return roomHotelDtoList;
    }

    public RoomHotelDto updateRoom( Long id, RoomHotelDto roomHotelDto ) throws NotFoundException
    {
        Room exsitingRoom = roomRepository.findById( id ).orElseThrow(() ->new NotFoundException("Room for id "+id+" was not found"));

//        if(Objects.isNull( roomHotelDto.getRoomType() ) || Objects.isNull( roomHotelDto.getNumOfRooms() ) || Objects.isNull( roomHotelDto.getMaxNumOfAdult() ) || Objects.isNull( roomHotelDto.getPrice() )){
//            throw new IllegalArgumentException("invalid request");
//        }

        exsitingRoom.setRoomType( roomHotelDto.getRoomType() );
        exsitingRoom.setPrice( roomHotelDto.getPrice() );
        exsitingRoom.setMaxNumOfAdult( roomHotelDto.getMaxNumOfAdult() );
        exsitingRoom.setNumOfRooms( roomHotelDto.getNumOfRooms() );

        if( roomHotelDto.getHotelId() != 0 && exsitingRoom.getHotel().getHotelId() != roomHotelDto.getHotelId() )
        {
            exsitingRoom.setHotel( hotelRepository.findById( roomHotelDto.getHotelId() ).orElseThrow(()-> new NotFoundException( "Hotel not found for the given id " )) );
        }

        Room savedRoom = roomRepository.save( exsitingRoom );
        return roomMapper.mapOut( savedRoom );
    }

    public DeleteResponseDto deleteRoomById( Long id ) throws DbException, NotFoundException, IllegalArgumentException
    {
        // Check for an invalid id
        if (id == null || id == 0) {
            throw new IllegalArgumentException("You must provide valid id");
        }

        // Check for the existence of the id in the DB
        if (!roomRepository.existsById( id )){
            throw new NotFoundException("RoomType not found for the given id => " + id);
        }

        try{
            roomRepository.deleteById( id );
        }
        catch( Exception e ){
            throw new DbException("Can't delete the RoomType with Id: "+ id , e);
        }

        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setModel( "RoomType" );
        deleteResponseDto.setId( id );
        deleteResponseDto.setMessage( "Room "+id+" removed successfully!!" );
        return deleteResponseDto;


    }

}
