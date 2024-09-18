package dev.dubrovsky.controller.cart;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.cart.NewCartRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartRequest;
import dev.dubrovsky.dto.response.cart.CartResponse;
import dev.dubrovsky.service.cart.CartService;

public abstract class AbstractCartController extends AbstractController<CartService, CartResponse, NewCartRequest, UpdateCartRequest> {

    public AbstractCartController(CartService service) {
        super(service);
    }

}
