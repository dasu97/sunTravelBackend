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

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 20 Apr 2023
 */

@Entity
@Table(
        name = "T964_ROOM"
)
@AllArgsConstructor
@NoArgsConstructor
@Data
@SequenceGenerator(
        name = "room_sequence",
        sequenceName = "room_sequence",
        allocationSize = 1
)
public class Room
{
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "room_sequence"
    )
    //@Column(name = "room_id")
    private Long roomId;
    /*
    @Column(name = "hotel_id")
    private Long hotelId;
    @Column(name = "contract_id")
    private Long contractId;

     */
    //@Column(name = "room_type")
    private String roomType;
    //private int num_of_rooms;

    private Double price;
    //@Column(name = "max_num_of_adults")
    private int maxNumOfAdult;

    private int numOfRooms;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "hotelId"
    )
    @JsonIgnore
    private Hotel hotel;

    /*
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "contract_id",
           referencedColumnName = "contractId"
    )
    @JsonIgnore
    private Contract contract;
    */

}
