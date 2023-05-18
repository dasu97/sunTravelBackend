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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 24 Apr 2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomHotelDto
{
    private long roomId;
    private String roomType;
    private double price;
    private int maxNumOfAdult;
    private int numOfRooms;

    private long hotelId;
    private String hotelName;
    private String hotelAddress;
}
