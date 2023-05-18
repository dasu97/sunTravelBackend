package com.sunTravel.sunTravelAssignment.controller;

import com.sunTravel.sunTravelAssignment.dto.HotelDto;
import com.sunTravel.sunTravelAssignment.model.Hotel;
import com.sunTravel.sunTravelAssignment.service.HotelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 09 May 2023
 */



@ExtendWith(MockitoExtension.class)
class HotelControllerTest
{

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private HotelController hotelController;

    @Test
    void save() throws IllegalArgumentException
    {
        // Arrange
        HotelDto hotelDto = new HotelDto( 1L, "Hotel A", "City A" );
        when( hotelService.saveHotel( hotelDto ) ).thenReturn( hotelDto );

        // Act
        ResponseEntity<HotelDto> responseEntity = hotelController.save( hotelDto );

        // Assert
        assertThat( responseEntity.getStatusCode() ).isEqualTo( HttpStatus.CREATED );
        assertThat( responseEntity.getBody() ).isEqualTo( hotelDto );
        verify( hotelService, times( 1 ) ).saveHotel( hotelDto );
    }

    @Test
    void getAll()
    {
        // Arrange
        HotelDto hotelDto1 = new HotelDto(  1L, "Hotel A", "City A" );
        HotelDto hotelDto2 = new HotelDto(  2L, "Hotel B", "City B");
        List<HotelDto> hotelDtoList = Arrays.asList( hotelDto1, hotelDto2 );
        when( hotelService.getAllHotels() ).thenReturn( hotelDtoList );

        // Act
        ResponseEntity<List<HotelDto>> responseEntity = hotelController.getAll();

        // Assert
        assertThat( responseEntity.getStatusCode() ).isEqualTo( HttpStatus.OK );
        assertThat( responseEntity.getBody() ).isEqualTo( hotelDtoList );
        verify( hotelService, times( 1 ) ).getAllHotels();
    }

    @Test
    void getAllHotels()
    {
        // Arrange
        Hotel hotel1 = new Hotel( 1L,"Hotel A","City A",null,null );
        Hotel hotel2 = new Hotel( 2L,"Hotel B","City B",null,null  );
        List<Hotel> hotelList = Arrays.asList( hotel1, hotel2 );
        when( hotelService.getAllHotels( "A" ) ).thenReturn( hotelList );

        // Act
        List<Hotel> resultList = hotelController.getAllHotels( "A" );

        // Assert
        assertThat( resultList ).isEqualTo( hotelList );
        verify( hotelService, times( 1 ) ).getAllHotels( "A" );
    }



}