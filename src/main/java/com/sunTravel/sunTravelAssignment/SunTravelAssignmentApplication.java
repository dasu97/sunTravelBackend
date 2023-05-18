package com.sunTravel.sunTravelAssignment;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//this class serves as a entry point for the springboot application
@SpringBootApplication
public class SunTravelAssignmentApplication
{
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	public static void main(String[] args)
	{
		SpringApplication.run(SunTravelAssignmentApplication.class, args);
	}
}
