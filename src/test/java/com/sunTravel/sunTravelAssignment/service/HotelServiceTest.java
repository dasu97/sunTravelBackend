package com.sunTravel.sunTravelAssignment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.sunTravel.sunTravelAssignment.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sunTravel.sunTravelAssignment.dto.HotelDto;
import com.sunTravel.sunTravelAssignment.mapper.HotelMapper;
import com.sunTravel.sunTravelAssignment.model.Hotel;
import com.sunTravel.sunTravelAssignment.repository.HotelRepository;


/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 09 May 2023
 */

@ExtendWith(MockitoExtension.class)
class HotelServiceTest
{

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private HotelMapper hotelMapper;

    @InjectMocks
    private HotelService hotelService;

    @Captor
    ArgumentCaptor<Hotel> hotelCaptor;

    @Test
    void testSaveHotel() throws IllegalArgumentException
    {
        // Arrange
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelId(1L);
        hotelDto.setHotelName("Test Hotel");
        hotelDto.setHotelAddress("Test Address");

        Hotel hotel = new Hotel();
        hotel.setHotelId(1L);
        hotel.setHotelName("Test Hotel");
        hotel.setHotelAddress("Test Address");

        when(hotelMapper.mapIn(any(HotelDto.class))).thenReturn(hotel);
        when(hotelMapper.mapOut(any(Hotel.class))).thenReturn(hotelDto);
        when(hotelRepository.save(any(Hotel.class))).thenReturn(hotel);

        // Act
        HotelDto savedHotelDto = hotelService.saveHotel(hotelDto);

        // Assert
        assertThat(savedHotelDto).isNotNull();
        assertThat(savedHotelDto.getHotelId()).isEqualTo(1L);
        assertThat(savedHotelDto.getHotelName()).isEqualTo("Test Hotel");
        assertThat(savedHotelDto.getHotelAddress()).isEqualTo("Test Address");

        // Verify
        ArgumentCaptor<Hotel> hotelCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(hotelRepository).save(hotelCaptor.capture());
        assertThat(hotelCaptor.getValue().getHotelId()).isGreaterThan( 0 );
        assertThat(hotelCaptor.getValue().getHotelName()).isEqualTo("Test Hotel");
        assertThat(hotelCaptor.getValue().getHotelAddress()).isEqualTo("Test Address");
    }


    @Test
    void testSaveHotelWithInvalidRequest()
    {
        // Arrange
        HotelDto hotelDto = new HotelDto();

        // Act & Assert
        assertThatExceptionOfType( IllegalArgumentException.class )
                .isThrownBy( () -> hotelService.saveHotel( hotelDto ) )
                .withMessage( "Invalid request" );
    }

    @Test
    void testGetHotelById() throws NotFoundException
    {
        // Arrange
        Hotel hotel = new Hotel();
        hotel.setHotelId( 1L );
        hotel.setHotelName( "Test Hotel" );
        hotel.setHotelAddress( "Test Address" );

        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelId( 1L );
        hotelDto.setHotelName( "Test Hotel" );
        hotelDto.setHotelAddress( "Test Address" );

        when( hotelMapper.mapOut( any( Hotel.class ) ) ).thenReturn( hotelDto );
        when( hotelRepository.findById( 1L ) ).thenReturn( Optional.of( hotel ) );

        // Act
        HotelDto actualHotelDto = hotelService.getHotelById( 1L );

        // Assert
        assertThat( actualHotelDto ).isNotNull();
        assertThat( actualHotelDto.getHotelId() ).isEqualTo( 1L );
        assertThat( actualHotelDto.getHotelName() ).isEqualTo( "Test Hotel" );
        assertThat( actualHotelDto.getHotelAddress() ).isEqualTo( "Test Address" );
    }

}