package kane.zomato.controller;

import jakarta.validation.Valid;
import kane.zomato.dto.HotelDto;
import kane.zomato.dto.MenuDto;
import kane.zomato.dto.OrderItemDto;
import kane.zomato.entity.OrderItem;
import kane.zomato.service.HotelServiceImpl;
import kane.zomato.service.MenuServiceImpl;
import kane.zomato.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@Slf4j
@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class Order {

    private final OrderServiceImpl orderService;


    @PostMapping("/createOrderItem")
    @Operation(summary = "Create a new order item", tags = {"Create New Order Item"})
    public ResponseEntity<OrderItemDto> createOrderItem(@Valid @RequestBody OrderItemDto createOrderItemData) {
        log.info("Create a new order item: {}", createOrderItemData);
        return new ResponseEntity<>(orderService.createOrderItem(createOrderItemData), HttpStatus.CREATED);
    }


//    @PostMapping("/addToCart")
//    @Operation(summary = "Add to Cart", tags = {"Add to Cart"})
//    public ResponseEntity<OrderItemDto> addToCart(@Valid @RequestBody OrderItemDto addToCartRequest) {
//        log.info("Add to Cart: {}", addToCartRequest);
//        return new ResponseEntity<>(orderService.addToCart(addToCartRequest), HttpStatus.CREATED);
//    }


//    @PutMapping("/update/{menuId}")
//    @Operation(summary = "Update an existing Menu", tags = {"Update an existing Menu"})
//    public ResponseEntity<MenuDto> update(@PathVariable Long menuId, @Valid @RequestBody MenuDto updateRequest) {
//        log.info("Update menu request received: {}", updateRequest);
//        return new ResponseEntity<>(menuService.update(menuId,updateRequest), HttpStatus.ACCEPTED);
//    }
//
//    @GetMapping("/{menuId}")
//    @Operation(summary = "Get an existing Menu", tags = {"Get Existing Hotel"})
//    public ResponseEntity<MenuDto> getMenu(@PathVariable Long menuId) {
//        log.info("Get an existing Menu: {}", menuId);
//        return new ResponseEntity<>(menuService.getMenu(menuId), HttpStatus.ACCEPTED);
//    }
//
//    @PostMapping("/delete/{menuId}")
//    @Operation(summary = "Delete Existing Menu", tags = {"Delete Existing Menu"})
//    public ResponseEntity<MenuDto> deleteMenuById(@PathVariable Long menuId) {
//        log.info("Delete Existing Menu: {}", menuId);
//        menuService.deleteMenuById(menuId);
//        return ResponseEntity.noContent().build();
//    }

}
