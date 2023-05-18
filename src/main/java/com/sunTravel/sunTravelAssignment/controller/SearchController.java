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

import com.sunTravel.sunTravelAssignment.dto.SearchRoomRequest;
import com.sunTravel.sunTravelAssignment.dto.SearchRoomResponse;
import com.sunTravel.sunTravelAssignment.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 03 May 2023
 */

@RestController
@RequestMapping("/search")
public class SearchController
{
    @Autowired
    private SearchService searchService;

    @PostMapping
    public ResponseEntity<SearchRoomResponse> getRoomsAvailability( @RequestBody SearchRoomRequest searchRoomRequest ){
        return  new ResponseEntity<>( searchService.searchAvailableRooms(searchRoomRequest), HttpStatus.OK );
    }
}
