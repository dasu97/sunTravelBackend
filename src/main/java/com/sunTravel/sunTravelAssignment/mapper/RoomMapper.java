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

import com.sunTravel.sunTravelAssignment.dto.RoomHotelDto;
import com.sunTravel.sunTravelAssignment.model.Room;
import org.springframework.stereotype.Component;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 26 Apr 2023
 */

@Component
public class RoomMapper
{
    public Room mapIn( RoomHotelDto roomHotelDto )
    {
        Room room = new Room();
        room.setRoomId( roomHotelDto.getRoomId() );
        room.setRoomType( roomHotelDto.getRoomType() );
        room.setPrice( roomHotelDto.getPrice() );
        room.setMaxNumOfAdult( roomHotelDto.getMaxNumOfAdult() );
        room.setNumOfRooms( roomHotelDto.getNumOfRooms() );
        return room;
    }

    public RoomHotelDto mapOut( Room room )
    {
        RoomHotelDto roomHotelDto = new RoomHotelDto();
        roomHotelDto.setRoomId( room.getRoomId() );
        roomHotelDto.setRoomType( room.getRoomType() );
        roomHotelDto.setPrice( room.getPrice() );
        roomHotelDto.setMaxNumOfAdult( room.getMaxNumOfAdult() );
        roomHotelDto.setNumOfRooms( room.getNumOfRooms() );

        roomHotelDto.setHotelId( room.getHotel().getHotelId() );
        roomHotelDto.setHotelName( room.getHotel().getHotelName() );
        roomHotelDto.setHotelAddress( room.getHotel().getHotelAddress() );


        return roomHotelDto;
    }
}
