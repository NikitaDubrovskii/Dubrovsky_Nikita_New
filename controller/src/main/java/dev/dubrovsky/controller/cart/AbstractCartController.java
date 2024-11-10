package dev.dubrovsky.controller.cart;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.cart.NewCartRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartRequest;
import dev.dubrovsky.dto.response.cart.CartResponse;
import dev.dubrovsky.service.cart.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public abstract class AbstractCartController extends AbstractController<CartService, CartResponse, NewCartRequest, UpdateCartRequest> {

    public AbstractCartController(CartService service) {
        super(service);
    }

    @GetMapping("/my")
    public abstract ResponseEntity<?> getCartsByUser(Authentication authentication);

    @GetMapping("/my/{id}")
    public abstract ResponseEntity<?> getOneByUser(Authentication authentication, @PathVariable Integer id);

}
