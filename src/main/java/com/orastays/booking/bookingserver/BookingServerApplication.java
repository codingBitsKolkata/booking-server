package com.orastays.booking.bookingserver;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
//@EnableDiscoveryClient
public class BookingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingServerApplication.class, args);
	}
	
	
	@Bean
    public ModelMapper modelMapper() {
		
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setAmbiguityIgnored(true);
		return modelMapper;
	}
	
	@Bean
	@LoadBalanced
    public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
