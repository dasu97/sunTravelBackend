/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.sunTravel.sunTravelAssignment.controller;

import com.sunTravel.sunTravelAssignment.dto.ContractsHotelDto;
//import com.sunTravel.sunTravelAssignment.model.Contracts;
//import com.sunTravel.sunTravelAssignment.repository.ContractRepository;
import com.sunTravel.sunTravelAssignment.dto.DeleteResponseDto;
import com.sunTravel.sunTravelAssignment.dto.RoomHotelDto;
import com.sunTravel.sunTravelAssignment.exception.DbException;
import com.sunTravel.sunTravelAssignment.exception.NotFoundException;
import com.sunTravel.sunTravelAssignment.service.ContractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 19 Apr 2023
 */

@RestController
@RequestMapping("/contracts")
public class ContractController
{
    @Autowired
    private ContractService contractService;

    @GetMapping
    public ResponseEntity<List<ContractsHotelDto>> getAllContracts()
    {
        return new ResponseEntity<List<ContractsHotelDto>>(contractService.findAllContracts(), HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContractsHotelDto> getContractsById( @PathVariable("id") Long id ) throws NotFoundException
    {
        return new ResponseEntity<ContractsHotelDto>( contractService.findContractsById(id), HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity<ContractsHotelDto> save(@RequestBody @Valid ContractsHotelDto contractsHotelDto) throws IllegalArgumentException, NotFoundException
    {
        return new ResponseEntity<>( contractService.addContracts(contractsHotelDto), HttpStatus.CREATED );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContractsHotelDto> updateContracts(@PathVariable("id") Long id, @RequestBody ContractsHotelDto contractsHotelDto) throws NotFoundException
    {
        return new ResponseEntity<>(contractService.updateContract(id, contractsHotelDto), HttpStatus.OK );
    }

    @GetMapping("/hotel")
    public ResponseEntity<List<ContractsHotelDto>> getContractByHotelId(@RequestParam Long hotelId)
    {
        return new ResponseEntity<>(  contractService.getContractByHotelId(hotelId), HttpStatus.OK);
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<DeleteResponseDto> delete( @PathVariable( "id" ) Long id ) throws DbException, NotFoundException
    {
        return new ResponseEntity<>(  contractService.deleteContractById( id ), HttpStatus.OK);
    }

}
