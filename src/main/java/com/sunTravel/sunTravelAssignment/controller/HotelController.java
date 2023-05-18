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
import com.sunTravel.sunTravelAssignment.dto.HotelDto;
import com.sunTravel.sunTravelAssignment.exception.DbException;
import com.sunTravel.sunTravelAssignment.exception.NotFoundException;
import com.sunTravel.sunTravelAssignment.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.sunTravel.sunTravelAssignment.service.HotelService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 24 Apr 2023
 */

//this class serves as a restful controller and maps endpoints to the url
@RestController
@RequestMapping("/hotels")
public class HotelController
{
    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<HotelDto> save( @RequestBody HotelDto hotelDto )
    {
            return new ResponseEntity<HotelDto>( hotelService.saveHotel( hotelDto ), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HotelDto>> getAll()
    {
        return new ResponseEntity<List<HotelDto>>(hotelService.getAllHotels(), HttpStatus.OK);
    }

    @GetMapping({"/getAllHotels"})
    public List<Hotel> getAllHotels( @RequestParam(defaultValue = "") String searchKey ){
        return hotelService.getAllHotels(searchKey);
    }

    @GetMapping( { "/{id}" } )
    public ResponseEntity<HotelDto> getById( @PathVariable( "id" ) Long id ) throws NotFoundException
    {
        return new ResponseEntity<HotelDto>(hotelService.getHotelById( id ), HttpStatus.OK);
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<HotelDto> update( @PathVariable( "id" ) Long id, @RequestBody @Valid HotelDto hotelDto ) throws NotFoundException, IllegalArgumentException
    {
        return new ResponseEntity<HotelDto>( hotelService.updateHotel( id, hotelDto ), HttpStatus.OK);
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<DeleteResponseDto> delete( @PathVariable( "id" ) Long id ) throws DbException, IllegalArgumentException, NotFoundException
    {
        return new ResponseEntity<DeleteResponseDto>( hotelService.deleteHotelById( id ),HttpStatus.OK );
    }

}
