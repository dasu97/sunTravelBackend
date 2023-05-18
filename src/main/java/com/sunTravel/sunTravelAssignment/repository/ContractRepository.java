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

import com.sunTravel.sunTravelAssignment.model.Contract;
import com.sunTravel.sunTravelAssignment.model.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 19 Apr 2023
 */
@Repository
public interface ContractRepository extends CrudRepository<Contract, Long>
{
    public List<Contract> findAll( );
    public List<Contract> findByHotelHotelId( Long hotelId );

    //Optional<Contract> findByContractId( Long id );
    //void deleteByContractId( Long id );
}
