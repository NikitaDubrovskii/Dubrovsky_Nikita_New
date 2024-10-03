package dev.dubrovsky.service.cart;

import dev.dubrovsky.dto.request.cart.NewCartItemRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartItemRequest;
import dev.dubrovsky.dto.response.cart.CartItemResponse;
import dev.dubrovsky.service.ICommonService;
import dev.dubrovsky.model.cart.CartItem;

public interface ICartItemService extends ICommonService<CartItemResponse, NewCartItemRequest, UpdateCartItemRequest> {
}
