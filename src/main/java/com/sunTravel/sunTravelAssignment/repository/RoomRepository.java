/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.sunTravel.sunTravelAssignment.repository;

import com.sunTravel.sunTravelAssignment.model.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 24 Apr 2023
 */

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> //Hotel,
{
    public List<Room> findAll( );
   public List<Room> findByHotelHotelId( Long hotelId );

    //List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, int maxNumOfAdults, int totalRoomsRequested);

    @Query( value = "SELECT h.hotel_name,h.hotel_Address ,r.room_type,r.max_Num_Of_Adult, r.NUM_OF_ROOMS, r.price, c.markup, c.start_date, c.end_date" +

                            " FROM T964_room r " +
                            "JOIN T964_hotel h ON h.hotel_id = r.hotel_id " +
                            "JOIN T964_contracts c ON c.hotel_id= h.hotel_id " +
                            "WHERE c.start_date <= :checkInDate AND c.end_date >=:checkOutDate AND r.max_Num_Of_Adult >=:maxNumOfAdultsInRequest AND r.NUM_OF_ROOMS >=:totalRoomsRequested ",nativeQuery = true
    )
   public List<Object[]> getAvailableRoomsByRequestCriteria(
           @Param( "checkInDate" ) LocalDate checkInDate,
           @Param( "checkOutDate" ) LocalDate checkOutDate,
           @Param( "maxNumOfAdultsInRequest" ) int maxNumOfAdultsInRequest,
           @Param( "totalRoomsRequested" ) int totalRoomsRequested
           );


}
