/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.sunTravel.sunTravelAssignment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 03 May 2023
 */

@Data
public class SearchRoomRequest
{

    private LocalDate checkInDate;
    private int numOfNight;

    private List<RoomRequestCriteria> roomRequests;
}
