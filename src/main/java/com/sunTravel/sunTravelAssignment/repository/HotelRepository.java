/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.sunTravel.sunTravelAssignment.repository;

import com.sunTravel.sunTravelAssignment.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 24 Apr 2023
 */
@Repository
public interface HotelRepository extends CrudRepository<Hotel, Long>
{
    //retrieve all Hotel entities from the database.
    public List<Hotel> findAll();

    public List<Hotel> findByHotelNameContainingIgnoreCaseOrHotelAddressContainingIgnoreCase(
            String key1, String key2
    );

}
