package dev.dubrovsky.controller.cart;

import dev.dubrovsky.dto.request.cart.NewCartRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartRequest;
import dev.dubrovsky.service.cart.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cart")
@Tag(name = "Корзины", description = "Взаимодействие с корзиной")
public class CartController extends AbstractCartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        super(cartService);
        this.cartService = cartService;
    }

    @Override
    @Operation(summary = "Создание корзины", description = "Создание корзины")
    public ResponseEntity<?> create(NewCartRequest request,
                                    BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение корзины", description = "Получение корзины по id")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка корзин", description = "Получение списка корзин")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Обновление корзины", description = "Обновление корзины по id")
    public ResponseEntity<?> update(UpdateCartRequest request,
                                    Integer id,
                                    BindingResult bindingResult) {
        return super.update(request, id, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление корзины", description = "Удаление корзины по id")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

}
