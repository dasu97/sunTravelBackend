/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.sunTravel.sunTravelAssignment.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 20 Apr 2023
 */

@Entity
@Table(
        name = "T964_HOTEL"
)
@SequenceGenerator(
        name = "hotel_sequence",
        sequenceName = "hotel_sequence",
        allocationSize = 1
)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Hotel
{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "hotel_sequence"
    )

    private long hotelId;
    private String hotelName;
    private String hotelAddress;

    @OneToMany(
            mappedBy = "hotel",
            cascade = CascadeType.ALL
    )
    private List<Contract> contract;

    @OneToMany(
            mappedBy = "hotel",
            cascade = CascadeType.ALL
    )
    private List<Room> room;
}
