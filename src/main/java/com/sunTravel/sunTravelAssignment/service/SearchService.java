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

import com.sunTravel.sunTravelAssignment.dto.AvailableRoomType;
import com.sunTravel.sunTravelAssignment.dto.RoomRequestCriteria;
import com.sunTravel.sunTravelAssignment.dto.SearchRoomRequest;
import com.sunTravel.sunTravelAssignment.dto.SearchRoomResponse;
import com.sunTravel.sunTravelAssignment.model.Contract;
import com.sunTravel.sunTravelAssignment.model.Room;
import com.sunTravel.sunTravelAssignment.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 03 May 2023
 */
@Service
public class SearchService
{
    @Autowired
    private RoomRepository roomRepository;

    public SearchRoomResponse searchAvailableRooms( SearchRoomRequest searchRoomRequest )
    {
        LocalDate checkInDate = searchRoomRequest.getCheckInDate();
        int numOfNight = searchRoomRequest.getNumOfNight();

        LocalDate checkOutDate = checkInDate.plusDays(numOfNight) ;

        int maxNumOfAdultsInRequest = 0, totalRoomsRequested = 0;

        for( RoomRequestCriteria criteria: searchRoomRequest.getRoomRequests()){
            if ( maxNumOfAdultsInRequest < criteria.getNumOfAdults() ){
                maxNumOfAdultsInRequest = criteria.getNumOfAdults();
            }
            totalRoomsRequested += criteria.getNumOfRooms();
        }

        //retrieve the search query response
        List<Object[]> searchQueryResponse = roomRepository.getAvailableRoomsByRequestCriteria( checkInDate, checkOutDate , maxNumOfAdultsInRequest , totalRoomsRequested);

        SearchRoomResponse response = new SearchRoomResponse();
        response.setAvailable( ( searchQueryResponse.size() >= 1 ) );

        List<AvailableRoomType> availableRoomTypes = new ArrayList<>();

        // array: [h.hotel_name,h.hotel_Address ,r.room_type,r.max_Num_Of_Adult, r.NUM_OF_ROOMS, r.price, c.markup, c.start_date, c.end_date]
        for( Object[] array : searchQueryResponse )
        {
            AvailableRoomType availableRoomType = new AvailableRoomType();

            availableRoomType.setHotelName( array[0].toString() );
            availableRoomType.setRoomType( array[2].toString() );
            availableRoomType.setMaxNumOfAdults( Integer.parseInt( array[3].toString() ) );
            availableRoomType.setNumOfRooms( Integer.parseInt( array[4].toString() ) );

            double pricePerPerson = Double.parseDouble( array[5].toString() );
            Double markup = Double.parseDouble( array[6].toString() ) + 100.0 ;
            int noOfNights = searchRoomRequest.getNumOfNight();

            Double markedUpPrice =  (pricePerPerson * markup * noOfNights )/ 100.0;

            availableRoomType.setMarkedUpPrice( markedUpPrice );

            // add the availableRoomType to the list
            availableRoomTypes.add( availableRoomType );
        }

        response.setAvailableRoomTypeList( availableRoomTypes );

        return response;
    }


}
