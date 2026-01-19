package kane.zomato.controller;

import jakarta.validation.Valid;
import kane.zomato.dto.HotelDto;
import kane.zomato.service.HotelServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

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


    @PutMapping("/updateHotel/{hotelId}")
    @Operation(summary = "Update an existing Hotel", tags = {"Update an existing Hotel"})
    public ResponseEntity<HotelDto> updateHotel(@PathVariable Long hotelId, @Valid @RequestBody HotelDto updateHotelRequest) {
        log.info("Update hotel request received: {}", updateHotelRequest);
        return new ResponseEntity<>(hotelService.updateHotel(hotelId,updateHotelRequest), HttpStatus.CREATED);
    }

    @GetMapping("/getHotelById/{hotelId}")
    @Operation(summary = "Get an existing Hotel", tags = {"Fetch Existing Hotel"})
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long hotelId) {
        log.info("Get an existing Hotel: {}", hotelId);
        return new ResponseEntity<>(hotelService.getHotelById(hotelId), HttpStatus.CREATED);
    }

    @PostMapping("/deleteHotelById/{hotelId}")
    @Operation(summary = "Delete Existing Hotel", tags = {"Delete Existing Hotel"})
    public ResponseEntity<HotelDto> deleteHotelById(@PathVariable Long hotelId) {
        log.info("Delete Existing Hotel: {}", hotelId);
        hotelService.deleteHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }

}
