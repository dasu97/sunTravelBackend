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
import com.sunTravel.sunTravelAssignment.dto.HotelDto;
import com.sunTravel.sunTravelAssignment.exception.DbException;
import com.sunTravel.sunTravelAssignment.exception.NotFoundException;
import com.sunTravel.sunTravelAssignment.mapper.HotelMapper;
import com.sunTravel.sunTravelAssignment.model.Hotel;
import com.sunTravel.sunTravelAssignment.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class HotelService
{
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelMapper hotelMapper;

    public HotelDto saveHotel( HotelDto hotelDto )
    {
        //Throw an exception when all data is not provided
        if (Objects.isNull(hotelDto.getHotelName()) || Objects.isNull(hotelDto.getHotelAddress())) {
            throw new IllegalArgumentException("Invalid request");
        }

        //map the hotelDto object to a Hotel entity
        Hotel hotel = hotelMapper.mapIn( hotelDto );
        //mapped Hotel entity is then saved in the database
        Hotel savedhotel = hotelRepository.save( hotel );
        //saved Hotel entity is mapped back to a HotelDto object and return
        return hotelMapper.mapOut( savedhotel );
    }

    public HotelDto getHotelById( Long id ) throws NotFoundException,IllegalArgumentException
    {
        if (id == null || id == 0) {
            throw new IllegalArgumentException("This is not a valid id");
        }
        else{
            Hotel hotel = hotelRepository.findById( id )
                                         .orElseThrow(() -> new NotFoundException("Hotel not found for the given id "+id));
            return hotelMapper.mapOut( hotel );
        }
    }

    // Retrieve All Hotel
    public List<HotelDto> getAllHotels()
    {
        List<Hotel> hotelList = hotelRepository.findAll();
        List<HotelDto> hotelDTOList = new ArrayList<>();
        for( Hotel hotel : hotelList )
        {
            hotelDTOList.add( hotelMapper.mapOut( hotel ) );
        }
        return hotelDTOList;
    }

    public HotelDto updateHotel( Long id, HotelDto hotelDto ) throws NotFoundException
    {
        // Throw an exception if HotelName and HotelCity are not in the DTO.
        if ( Objects.isNull(hotelDto.getHotelName()) || Objects.isNull(hotelDto.getHotelAddress())) {
            throw new IllegalArgumentException("Invalid request");
        }
        if (id == null || id == 0) {
            throw new IllegalArgumentException("This is not a valid id");
        }

        // Retrieve the Hotel by id and update the attributes
        Hotel exsitingHotel = hotelRepository.findById( id ).orElseThrow(() -> new NotFoundException("Hotel not found for the given id " + id));
        exsitingHotel.setHotelName( hotelDto.getHotelName() );
        exsitingHotel.setHotelAddress( hotelDto.getHotelAddress() );

        Hotel savedEntity = hotelRepository.save( exsitingHotel );
        return hotelMapper.mapOut( savedEntity );
    }

    public DeleteResponseDto deleteHotelById( Long id ) throws DbException, IllegalArgumentException, NotFoundException
    {
        // Check for an invalid id
        if (id == null || id == 0) {
            throw new IllegalArgumentException("This is not a valid id");
        }

        // Check for the existence of the id in the DB
        if (!hotelRepository.existsById( id )){
            throw new NotFoundException("No Hotel is found for the given id => " + id);
        }

        try{
            hotelRepository.deleteById( id );
        }
        catch( Exception e ){
            throw new DbException("Can't delete the Hotel with Id: "+ id , e);
        }

        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setModel( "Hotel" );
        deleteResponseDto.setId( id );
        deleteResponseDto.setMessage( "Hotel "+id+" removed successfully!!" );
        return deleteResponseDto;
    }

    public List<Hotel> getAllHotels( String searchKey )
    {
        if(searchKey.equals( "" )){
            return (List<Hotel>) hotelRepository.findAll();
        }else
        {
            return (List<Hotel>) hotelRepository.findByHotelNameContainingIgnoreCaseOrHotelAddressContainingIgnoreCase(
                    searchKey, searchKey
            );
        }
    }
}
