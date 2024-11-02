package dev.dubrovsky.service.cart;

import dev.dubrovsky.dto.request.cart.NewCartItemRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartItemRequest;
import dev.dubrovsky.dto.response.cart.CartItemResponse;
import dev.dubrovsky.service.ICommonService;

import java.util.List;

public interface ICartItemService extends ICommonService<CartItemResponse, NewCartItemRequest, UpdateCartItemRequest> {

    void addItemToCart(String username, NewCartItemRequest request);

    List<CartItemResponse> getCartItemsByUser(String username, Integer cartId);

    CartItemResponse getOneByUser(String username, Integer cartId, Integer itemId);

    void updateCartItem(String username, UpdateCartItemRequest request, Integer itemId);

    void deleteItemFromCart(String username, Integer itemId);

}
