/*
 * Copyright (c) 2023. CodeGen International (Pvt) Ltd. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of CodeGen
 * International (Pvt) Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with CodeGen International.
 *
 */
package com.sunTravel.sunTravelAssignment.advice;

import com.sunTravel.sunTravelAssignment.constants.AppConstants;
import com.sunTravel.sunTravelAssignment.exception.DbException;
import com.sunTravel.sunTravelAssignment.exception.NotFoundException;
import com.sunTravel.sunTravelAssignment.wrapper.ExceptionWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 16 May 2023
 */

@RestControllerAdvice
public class HotelExceptionHandler
{

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler( NotFoundException.class)
    public Map<String, String> handleBusinessArguments( NotFoundException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put( "errorMessage", ex.getMessage() );
        return errorMap;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionWrapper> validationException( IllegalArgumentException exception) {
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(HttpStatus.BAD_REQUEST.toString(), exception.getMessage());
        return new ResponseEntity<ExceptionWrapper>(exceptionWrapper, HttpStatus.BAD_REQUEST);
    }

  //  ExceptionWrapper is like a custom class that wraps the exception information.

    @ExceptionHandler( DbException.class)
    public ResponseEntity<ExceptionWrapper> dbException(DbException exception) {
        if (!Objects.isNull(exception.getMessage())
                    && AppConstants.INCORRECT_RESULT_SIZE.equalsIgnoreCase(exception.getMessage())) {
            ExceptionWrapper exceptionWrapper = new ExceptionWrapper(HttpStatus.OK.toString(), exception.getMessage());

            return new ResponseEntity<ExceptionWrapper>(exceptionWrapper, HttpStatus.OK);
        }

        ExceptionWrapper exceptionWrapper = new ExceptionWrapper(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                exception.getMessage());

        return new ResponseEntity<ExceptionWrapper>(exceptionWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //    @ResponseStatus( HttpStatus.BAD_REQUEST)
//    @ExceptionHandler( MethodArgumentNotValidException.class)
//    public Map<String, String> handleInvalidArguments( MethodArgumentNotValidException ex) {
//        Map<String, String> errorMap = new HashMap<>();
//
//        for ( FieldError error : ex.getBindingResult().getFieldErrors()) {
//            errorMap.put(error.getField(), error.getDefaultMessage());
//        }
//
//        return errorMap;
//    }

}
