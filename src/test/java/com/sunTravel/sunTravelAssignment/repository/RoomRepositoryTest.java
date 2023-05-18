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

import com.sunTravel.sunTravelAssignment.model.Room;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * <b>Description Title</b>
 * Description Text.
 *
 * @author dasunis
 * @since 09 May 2023
 */

@DataJpaTest
public class RoomRepositoryTest
{
    //given - when - then
    @Autowired
    private RoomRepository roomRepository;
    Room room;


    @BeforeEach
    void setUp()
    {
//       room = new Room(1L, "SeaView", 2000.0, 2, 5, 2L );
//       roomRepository.save( room );

    }

    @AfterEach
    void tearDown()
    {
//        room = null;
//        roomRepository.deleteAll();

    }
}
