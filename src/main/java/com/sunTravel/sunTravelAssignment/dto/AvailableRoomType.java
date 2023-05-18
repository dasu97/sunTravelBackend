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

import lombok.Data;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 03 May 2023
 */
@Data
public class AvailableRoomType
{
    private String hotelName;
    private String roomType;
    private int maxNumOfAdults;
    private int numOfRooms;
    private Double markedUpPrice;
}
