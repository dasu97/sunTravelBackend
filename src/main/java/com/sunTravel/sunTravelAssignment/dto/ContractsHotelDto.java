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
import org.aspectj.bridge.Message;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

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
public class ContractsHotelDto
{
    private long contractId;
 //   @NotBlank( message = "start date can not be empty")
    private LocalDate startDate;
 //   @NotBlank(message = "end date can not be empty")
    private LocalDate endDate;
 //   @NotBlank(message = "markup can not be empty")
    private Double markup;

  //  @NotBlank(message = "hotel id can not be empty")
    private long hotelId;
    private String hotelName;
    private String hotelAddress;


}
