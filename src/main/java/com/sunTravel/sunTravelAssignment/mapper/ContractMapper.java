/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.sunTravel.sunTravelAssignment.mapper;

import com.sunTravel.sunTravelAssignment.dto.ContractsHotelDto;
import com.sunTravel.sunTravelAssignment.model.Contract;
import org.springframework.stereotype.Component;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 26 Apr 2023
 */

@Component
public class ContractMapper
{

    public Contract mapIn( ContractsHotelDto contractsHotelDto)
    {
        Contract contract = new Contract();
        contract.setContractId( contractsHotelDto.getContractId() );
        contract.setStartDate( contractsHotelDto.getStartDate() );
        contract.setEndDate( contractsHotelDto.getEndDate() );
        contract.setMarkup( contractsHotelDto.getMarkup() );

        return contract;
    }

    public  ContractsHotelDto mapOut( Contract contract )
    {
        ContractsHotelDto contractsHotelDto = new ContractsHotelDto();

        contractsHotelDto.setContractId( contract.getContractId() );
        contractsHotelDto.setStartDate( contract.getStartDate() );
        contractsHotelDto.setEndDate( contract.getEndDate() );
        contractsHotelDto.setMarkup( contract.getMarkup() );
        if(contract.getHotel() != null) {
            contractsHotelDto.setHotelId( contract.getHotel().getHotelId() );
            contractsHotelDto.setHotelName( contract.getHotel().getHotelName() );
            contractsHotelDto.setHotelAddress( contract.getHotel().getHotelAddress() );
        }

        return contractsHotelDto;
    }

}
