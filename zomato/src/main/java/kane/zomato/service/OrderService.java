package kane.zomato.service;

import kane.zomato.dto.CartDto;
import kane.zomato.dto.MessageDto;
import kane.zomato.dto.OrderItemDto;

public interface OrderService {
    OrderItemDto createOrderItem(OrderItemDto createOrderItemData);


    MessageDto addToCart(CartDto addToCartRequest);

}
