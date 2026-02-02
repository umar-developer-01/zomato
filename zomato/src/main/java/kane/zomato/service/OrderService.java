package kane.zomato.service;

import kane.zomato.dto.OrderItemDto;

public interface OrderService {
    OrderItemDto createOrderItem(OrderItemDto createOrderItemData);

}
