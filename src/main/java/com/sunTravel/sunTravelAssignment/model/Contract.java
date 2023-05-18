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
import java.time.LocalDate;
import java.util.List;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 19 Apr 2023
 */

@Entity
@Table(name="T964_CONTRACTS")
@SequenceGenerator(
        name = "contract_sequence",
        sequenceName = "contract_sequence",
        allocationSize = 1
)

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contract
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long contractId;
    private LocalDate startDate;
    private  LocalDate endDate;
    private Double markup;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "hotelId"
    )
    @JsonIgnore
    private Hotel hotel;

    /*
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "contract_id",
            referencedColumnName = "contractId"
    )
    private List<Room> room;
    */

}
