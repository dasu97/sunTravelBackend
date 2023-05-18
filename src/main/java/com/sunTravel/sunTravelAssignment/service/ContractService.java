/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.sunTravel.sunTravelAssignment.service;

import com.sunTravel.sunTravelAssignment.dto.ContractsHotelDto;
import com.sunTravel.sunTravelAssignment.dto.DeleteResponseDto;
import com.sunTravel.sunTravelAssignment.exception.DbException;
import com.sunTravel.sunTravelAssignment.exception.NotFoundException;
import com.sunTravel.sunTravelAssignment.mapper.ContractMapper;
import com.sunTravel.sunTravelAssignment.model.Contract;
import com.sunTravel.sunTravelAssignment.model.Hotel;
import com.sunTravel.sunTravelAssignment.repository.ContractRepository;
import com.sunTravel.sunTravelAssignment.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 19 Apr 2023
 */

@Service
public class ContractService
{
    @Autowired
    private final ContractRepository contractRepository;

    @Autowired
    public ContractService( ContractRepository contractRepository )
    {
        this.contractRepository = contractRepository;
    }

    @Autowired
    private ContractMapper contractMapper;

    @Autowired
    private HotelRepository hotelRepository;

    public ContractsHotelDto addContracts(ContractsHotelDto contractsHotelDto) throws IllegalArgumentException, NotFoundException
    {
        if(Objects.isNull( contractsHotelDto.getStartDate() ) || Objects.isNull( contractsHotelDto.getEndDate() ) || Objects.isNull( contractsHotelDto.getMarkup() )){
            throw new IllegalArgumentException("invalid request");
        }

        Contract contract = contractMapper.mapIn( contractsHotelDto );
        Hotel hotel = hotelRepository.findById( contractsHotelDto.getHotelId() ).orElseThrow(()-> new NotFoundException( "Hotel not found for the given id "+contractsHotelDto.getHotelId() ));
        contract.setHotel( hotel );
        Contract savedContract = contractRepository.save( contract );
        return contractMapper.mapOut( savedContract );

    }

    public List<ContractsHotelDto> findAllContracts()
    {
        List<Contract> contractList = contractRepository.findAll();
        List<ContractsHotelDto> contractsHotelDtoList = new ArrayList<>();
        for( Contract contract : contractList )
        {
            contractsHotelDtoList.add( contractMapper.mapOut( contract ) );
        }
        return contractsHotelDtoList;
    }

    public ContractsHotelDto findContractsById(Long id) throws NotFoundException,IllegalArgumentException
    {
        if (id == null || id == 0) {
            throw new IllegalArgumentException("This is not a valid id");
        }
        else
        {
            Contract contract = contractRepository.findById( id ).orElseThrow( () -> new NotFoundException( "Contract for id " + id + " was not found" ) );
            return contractMapper.mapOut( contract );
        }
    }

    public ContractsHotelDto updateContract(Long id, ContractsHotelDto contractsHotelDto) throws NotFoundException
    {
        Contract existingcontract = contractRepository.findById(id).orElseThrow(() ->new NotFoundException("Contract for id "+id+" was not found"));

        if(Objects.isNull( contractsHotelDto.getStartDate() ) || Objects.isNull( contractsHotelDto.getEndDate() ) || Objects.isNull( contractsHotelDto.getMarkup()  )){
            throw new IllegalArgumentException("invalid request");
        }
        existingcontract.setStartDate( contractsHotelDto.getStartDate() );
        existingcontract.setEndDate( contractsHotelDto.getEndDate() );
        existingcontract.setMarkup( contractsHotelDto.getMarkup() );
// contractsHotelDto.getHotelId() != 0 &&
        if( existingcontract.getHotel().getHotelId() != contractsHotelDto.getHotelId() )
        {
            existingcontract.setHotel( hotelRepository.findById( contractsHotelDto.getHotelId() ).orElseThrow(()-> new NotFoundException( "Hotel not found for the given id " )) );
        }
        Contract savedContract = contractRepository.save( existingcontract );

        return contractMapper.mapOut( savedContract );
    }

    public DeleteResponseDto deleteContractById( Long id ) throws DbException, IllegalArgumentException, NotFoundException
    {
        // Check for an invalid id
        if (id == null || id == 0) {
            throw new IllegalArgumentException("You must provide valid id");
        }

        // Check for the existence of the id in the DB
        if (!contractRepository.existsById( id )){
            throw new NotFoundException("contract not found for the given id => " + id);
        }

        try{
            contractRepository.deleteById( id );
        }
        catch( Exception e ){
            throw new DbException("Can't delete the contract with Id: "+ id , e);
        }

        DeleteResponseDto deleteResponseDto = new DeleteResponseDto();
        deleteResponseDto.setModel( "contract" );
        deleteResponseDto.setId( id );
        deleteResponseDto.setMessage( "contract "+id+" removed successfully!!" );
        return deleteResponseDto;

    }

    public List<ContractsHotelDto> getContractByHotelId( Long hotelId )
    {
        List<Contract> contractsListByHotelId = contractRepository.findByHotelHotelId( hotelId );
        List<ContractsHotelDto> contractsHotelDtoList = new ArrayList<>();
        for( Contract contract : contractsListByHotelId )
        {
            contractsHotelDtoList.add( contractMapper.mapOut( contract ) );
        }
        return contractsHotelDtoList;
    }
}
