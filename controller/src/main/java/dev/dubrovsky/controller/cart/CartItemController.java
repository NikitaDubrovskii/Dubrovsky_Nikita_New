package dev.dubrovsky.controller.cart;

import dev.dubrovsky.dto.request.cart.NewCartItemRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartItemRequest;
import dev.dubrovsky.service.cart.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cart-item")
@Tag(name = "Товары в корзине", description = "Взаимодействие с товарами в корзине")
public class CartItemController extends AbstractCartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        super(cartItemService);
        this.cartItemService = cartItemService;
    }

    @Override
    @Operation(summary = "Создание товара в корзине", description = "Создание товара в корзине")
    public ResponseEntity<?> create(NewCartItemRequest request,
                                    BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение товара в корзине", description = "Получение товара в корзине по id товара")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка товаров в корзинах", description = "Получение списка товаров в корзинах")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Обновление товаров в корзине", description = "Обновление товаров в корзине по id товара")
    public ResponseEntity<?> update(UpdateCartItemRequest request,
                                    Integer id,
                                    BindingResult bindingResult) {
        return super.update(request, id, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление товара из корзины", description = "Удаление товара из корзины по id товара")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

}
