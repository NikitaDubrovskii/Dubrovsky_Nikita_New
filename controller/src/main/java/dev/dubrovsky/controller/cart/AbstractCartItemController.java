package dev.dubrovsky.controller.cart;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.cart.NewCartItemRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartItemRequest;
import dev.dubrovsky.dto.response.cart.CartItemResponse;
import dev.dubrovsky.service.cart.CartItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractCartItemController extends AbstractController<CartItemService, CartItemResponse, NewCartItemRequest, UpdateCartItemRequest> {

    public AbstractCartItemController(CartItemService service) {
        super(service);
    }

    @PostMapping("/my")
    public abstract ResponseEntity<?> addItemToCart(Authentication authentication,
                                                    @RequestBody @Valid NewCartItemRequest request,
                                                    BindingResult bindingResult);

    @GetMapping("/my/{cartId}")
    public abstract ResponseEntity<?> getCartItemsByUser(Authentication authentication, @PathVariable Integer cartId);

    @GetMapping("/my/{cartId}/{itemId}")
    public abstract ResponseEntity<?> getOneByUser(Authentication authentication, @PathVariable Integer cartId, @PathVariable Integer itemId);

    @PutMapping("/my/{itemId}")
    public abstract ResponseEntity<?> updateCartItem(Authentication authentication,
                                                     @PathVariable Integer itemId,
                                                     @RequestBody @Valid UpdateCartItemRequest request,
                                                     BindingResult bindingResult);

    @DeleteMapping("/my/{itemId}")
    public abstract ResponseEntity<?> deleteItemFromCart(Authentication authentication, @PathVariable Integer itemId);

}
