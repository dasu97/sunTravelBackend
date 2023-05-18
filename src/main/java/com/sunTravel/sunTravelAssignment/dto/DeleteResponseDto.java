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
 * @since 26 Apr 2023
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeleteResponseDto
{
    private Long id;
    private String model;
    private String message;
}
