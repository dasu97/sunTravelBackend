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

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 24 Apr 2023
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto
{
    private Long hotelId;
   // @NotBlank(message = " hotel name shouldn't be empty")
    private String hotelName;
   // @NotBlank(message = " hotel address shouldn't be empty")
    private String hotelAddress;
}
