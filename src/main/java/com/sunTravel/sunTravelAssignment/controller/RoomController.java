/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.sunTravel.sunTravelAssignment.controller;

import com.sunTravel.sunTravelAssignment.dto.DeleteResponseDto;
import com.sunTravel.sunTravelAssignment.dto.RoomHotelDto;
import com.sunTravel.sunTravelAssignment.exception.DbException;
import com.sunTravel.sunTravelAssignment.exception.NotFoundException;
import com.sunTravel.sunTravelAssignment.model.Room;
import com.sunTravel.sunTravelAssignment.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 24 Apr 2023
 */

@RestController
@RequestMapping("/rooms")
public class RoomController
{
    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomHotelDto>> getAll()
    {
        return new ResponseEntity<>(roomService.getAllRoom(),HttpStatus.OK);
    }

    @GetMapping( { "/{id}" } )
    public ResponseEntity<RoomHotelDto> getById( @PathVariable( "id" ) Long id ) throws NotFoundException
    {
        return new ResponseEntity<>(  roomService.getRoomById( id ), HttpStatus.OK);
    }

    @GetMapping("/hotel")
    public ResponseEntity<List<RoomHotelDto>> getRoomByHotelId(@RequestParam Long hotelId){
        return new ResponseEntity<>(  roomService.getRoomByHotelId(hotelId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<RoomHotelDto> save( @RequestBody RoomHotelDto roomHotelDto ) throws NotFoundException
    {
        return new ResponseEntity<>( roomService.saveRoom( roomHotelDto ), HttpStatus.CREATED);
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<RoomHotelDto> update( @PathVariable( "id" ) Long id, @RequestBody RoomHotelDto roomHotelDto ) throws NotFoundException
    {
        return new ResponseEntity<>(  roomService.updateRoom( id, roomHotelDto ), HttpStatus.OK);
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<DeleteResponseDto> delete( @PathVariable( "id" ) Long id ) throws DbException, NotFoundException
    {
        return new ResponseEntity<>(  roomService.deleteRoomById( id ), HttpStatus.OK);
    }

}
