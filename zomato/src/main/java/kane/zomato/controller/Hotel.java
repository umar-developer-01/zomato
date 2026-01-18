package kane.zomato.controller;

import jakarta.validation.Valid;
import kane.zomato.dto.HotelDto;
import kane.zomato.service.HotelServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@RequestMapping("/hotel")
@RequiredArgsConstructor
public class Hotel {

    private final HotelServiceImpl hotelService;


    @PostMapping("/createNewHotel")
    @Operation(summary = "Create a new Hotel", tags = {"Create New Hotel"})
    public ResponseEntity<HotelDto> createNewHotel(@Valid @RequestBody HotelDto hotelCreationRequest) {
        log.info("Signup request received: {}", hotelCreationRequest);
        return new ResponseEntity<>(hotelService.createNewHotel(hotelCreationRequest), HttpStatus.CREATED);
    }

}
