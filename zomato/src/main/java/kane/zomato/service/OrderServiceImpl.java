package kane.zomato.service;

import kane.zomato.dto.OrderItemDto;
import kane.zomato.entity.OrderItem;
import kane.zomato.respository.HotelRepository;
import kane.zomato.respository.MenuRepository;
import kane.zomato.respository.OrderItemRepository;
import kane.zomato.respository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
}
