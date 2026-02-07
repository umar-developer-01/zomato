package kane.zomato.service;

import kane.zomato.dto.CartDto;
import kane.zomato.dto.MessageDto;
import kane.zomato.dto.OrderItemDto;
import kane.zomato.entity.Hotel;
import kane.zomato.entity.Menu;
import kane.zomato.entity.OrderItem;
import kane.zomato.respository.HotelRepository;
import kane.zomato.respository.MenuRepository;
import kane.zomato.respository.OrderItemRepository;
import kane.zomato.respository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final MenuRepository menuRepository;
    private final ModelMapper modelMapper;
    private final HotelRepository hotelRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;


    @Override
    public OrderItemDto createOrderItem(OrderItemDto createOrderItemData) {
        OrderItem orderItem = modelMapper.map(createOrderItemData, OrderItem.class);

        orderItem = orderItemRepository.save(orderItem);

        return modelMapper.map(orderItem, OrderItemDto.class);

    }


    @Override
    public MessageDto addToCart(CartDto addToCartRequest) {

        Menu menuItem = menuRepository.findById(addToCartRequest.getId())
                .orElseThrow(() ->
                        new RuntimeException("Menu Item not found with id: " + addToCartRequest.getId())
                );


        Optional<Hotel> hotelOptional = Optional.ofNullable(menuItem.getHotel());

        if (hotelOptional.isEmpty()) {
            return MessageDto.builder()
                    .message("Hotel does not exist for this Menu Item")
                    .build();
        }





        //return new MessageDto("Successfully added the Menu Item in the Cart");
        return MessageDto.builder()
                .message("Successfully added the Menu Item in the Cart")
                .build();
    }
}
