package dev.dubrovsky.controller.cart;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.cart.NewCartItemRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartItemRequest;
import dev.dubrovsky.dto.response.cart.CartItemResponse;
import dev.dubrovsky.service.cart.CartItemService;

public abstract class AbstractCartItemController extends AbstractController<CartItemService, CartItemResponse, NewCartItemRequest, UpdateCartItemRequest> {

    public AbstractCartItemController(CartItemService service) {
        super(service);
    }

}
