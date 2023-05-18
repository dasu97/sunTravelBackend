/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.sunTravel.sunTravelAssignment.mapper;

import com.sunTravel.sunTravelAssignment.dto.HotelDto;
import com.sunTravel.sunTravelAssignment.model.Hotel;
import org.springframework.stereotype.Component;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 26 Apr 2023
 */

@Component
public class HotelMapper
{
    public Hotel mapIn( HotelDto hotelDto){
        Hotel hotel = new Hotel();
       // hotel.setHotelId( hotelDto.getHotelId() );
        hotel.setHotelName( hotelDto.getHotelName() );
        hotel.setHotelAddress( hotelDto.getHotelAddress() );
        return hotel;
    }

    public HotelDto mapOut( Hotel hotel){
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelId( hotel.getHotelId() );
        hotelDto.setHotelName( hotel.getHotelName() );
        hotelDto.setHotelAddress( hotel.getHotelAddress() );
        return hotelDto;
    }

}
