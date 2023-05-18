package com.sunTravel.sunTravelAssignment.repository;

import com.sunTravel.sunTravelAssignment.model.Hotel;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 08 May 2023
 */

@DataJpaTest
class HotelRepositoryTest
{

  @Autowired
  HotelRepository hotelRepository;
  Hotel hotel;


  //test case success
  @Test
  void testSearchByName_Found()
  {
    hotel = new Hotel(1L, "hotelT", "cityT", null, null );
    hotelRepository.save( hotel );

    List<Hotel> hotelList = hotelRepository.findByHotelNameContainingIgnoreCaseOrHotelAddressContainingIgnoreCase( "hotelT", "cityT" );
    assertThat(hotelList.get( 0 ).getHotelId()).isEqualTo( hotel.getHotelId() );
    assertThat( hotelList.get( 0 ).getHotelAddress() ).isEqualTo( hotel.getHotelAddress() );
  }

  //test case failure
  @Test
  void testSearchByName_NotFound()
  {
    List<Hotel> hotelList = hotelRepository.findByHotelNameContainingIgnoreCaseOrHotelAddressContainingIgnoreCase( "hotel2", "city2" );
    assertThat(hotelList.isEmpty()).isTrue();
  }

  @Autowired
  private TestEntityManager testEntityManager;

  @Test
  public void SaveHotelTest()
  {
    Hotel hotel = Hotel.builder()
                       .hotelName( "hotel 1" )
                       .hotelAddress( "city 1" )
                       .build();
    hotelRepository.save( hotel );

    Assertions.assertThat(hotel.getHotelId()).isGreaterThan(0);

  }

  @Test
  public void getAllHotelsTest() {
    Hotel hotel1 = new Hotel();
    hotel1.setHotelName( "Hotel 1" );
    hotel1.setHotelAddress( "City 1" );
    testEntityManager.persistAndFlush(hotel1);

    Hotel hotel2 = new Hotel();
    hotel2.setHotelName( "Hotel 2" );
    hotel2.setHotelAddress( "City 2" );
    testEntityManager.persistAndFlush(hotel2);

    List<Hotel> hotels = hotelRepository.findAll();
    Assertions.assertThat(hotels).isNotNull().isNotEmpty();
    Assertions.assertThat(hotels.size()).isEqualTo(2);
    // Add additional assertions as needed to verify hotel properties
  }



  @Test
  public void updateHotelTest(){
    // Create a new Hotel object
    Hotel hotel = new Hotel();
    hotel.setHotelName( "Hotel 1" );
    hotel.setHotelAddress( "City 1" );

    // Save the Hotel to the in-memory H2-database
    Hotel savedHotel = testEntityManager.persistAndFlush( hotel );

    // Update the Hotel and save it to the database
    savedHotel.setHotelName( "Updated Hotel" );
    savedHotel.setHotelAddress( "Updated City" );

    testEntityManager.persistAndFlush( savedHotel );

    // Retrieve the updated contract from the database and assert its properties
    Hotel retrievedHotel = hotelRepository.findById( savedHotel.getHotelId() ).orElse( null );
    assertThat(retrievedHotel).isNotNull();
    assertThat(retrievedHotel.getHotelName()).isEqualTo(savedHotel.getHotelName());
    assertThat(retrievedHotel.getHotelAddress()).isEqualTo(savedHotel.getHotelAddress());
  }

  @Test
  public void testDeleteHotel(){

    // Create a new Hotel object
    Hotel hotel = new Hotel();
    hotel.setHotelName( "Hotel 1" );
    hotel.setHotelAddress( "City 1" );

    // Save the Hotel to the in-memory H2-database
    Hotel savedHotel = testEntityManager.persistAndFlush( hotel );

    // Delete the contract from the database
    hotelRepository.delete(savedHotel);

    // Try to retrieve the deleted contract from the database and assert that it is null
    Hotel retrievedHotel = hotelRepository.findById(savedHotel.getHotelId()).orElse(null);
    assertThat(retrievedHotel).isNull();
  }


}