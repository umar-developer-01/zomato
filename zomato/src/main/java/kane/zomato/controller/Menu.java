package kane.zomato.controller;

import jakarta.validation.Valid;
import kane.zomato.dto.HotelDto;
import kane.zomato.dto.MenuDto;
import kane.zomato.service.HotelServiceImpl;
import kane.zomato.service.MenuServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class Menu {

    private final MenuServiceImpl menuService;


    @PostMapping("/create")
    @Operation(summary = "Create a new Menu", tags = {"Create New Menu"})
    public ResponseEntity<MenuDto> create(@Valid @RequestBody MenuDto hotelCreationRequest) {
        log.info("Create a new menu: {}", hotelCreationRequest);
        return new ResponseEntity<>(menuService.create(hotelCreationRequest), HttpStatus.CREATED);
    }


    @PutMapping("/update/{menuId}")
    @Operation(summary = "Update an existing Menu", tags = {"Update an existing Menu"})
    public ResponseEntity<MenuDto> update(@PathVariable Long menuId, @Valid @RequestBody MenuDto updateRequest) {
        log.info("Update menu request received: {}", updateRequest);
        return new ResponseEntity<>(menuService.update(menuId,updateRequest), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{menuId}")
    @Operation(summary = "Get an existing Menu", tags = {"Get Existing Hotel"})
    public ResponseEntity<MenuDto> getMenu(@PathVariable Long menuId) {
        log.info("Get an existing Menu: {}", menuId);
        return new ResponseEntity<>(menuService.getMenu(menuId), HttpStatus.ACCEPTED);
    }

    @PostMapping("/delete/{menuId}")
    @Operation(summary = "Delete Existing Menu", tags = {"Delete Existing Menu"})
    public ResponseEntity<MenuDto> deleteMenuById(@PathVariable Long menuId) {
        log.info("Delete Existing Menu: {}", menuId);
        menuService.deleteMenuById(menuId);
        return ResponseEntity.noContent().build();
    }

}
