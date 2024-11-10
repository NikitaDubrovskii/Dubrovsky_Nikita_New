package dev.dubrovsky.controller.cart;

import dev.dubrovsky.controller.ResponseStatus;
import dev.dubrovsky.dto.request.cart.NewCartItemRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartItemRequest;
import dev.dubrovsky.service.cart.CartItemService;
import dev.dubrovsky.util.response.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cart-item")
@Tag(name = "Товары в корзине", description = "Взаимодействие с товарами в корзине")
public class CartItemController extends AbstractCartItemController {

    public CartItemController(CartItemService cartItemService) {
        super(cartItemService);
    }

    @Override
    @Operation(summary = "Создание товара в корзине (admin)", description = "Создание товара в корзине, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> create(NewCartItemRequest request,
                                    BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение товара в корзине (admin)", description = "Получение товара в корзине по id товара, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка товаров в корзинах (admin)", description = "Получение списка товаров в корзинах, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Обновление товаров в корзине (admin)", description = "Обновление товаров в корзине по id товара, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> update(Integer id,
                                    UpdateCartItemRequest request,
                                    BindingResult bindingResult) {
        return super.update(id, request, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление товара из корзины (admin)", description = "Удаление товара из корзины по id товара, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

    @Override
    @Operation(summary = "Добавление продукта в корзину (user)", description = "Добавление продукта в корзину, доступно с ролью ROLE_USER")
    public ResponseEntity<?> addItemToCart(Authentication authentication,
                                           NewCartItemRequest request,
                                           BindingResult bindingResult) {
        String username = super.getUsername(authentication);
        if (bindingResult.hasErrors()) {
            return ResponseUtil.generateErrorResponse(bindingResult);
        } else {
            service.addItemToCart(username, request);
            return new ResponseEntity<>(ResponseStatus.CREATED.getDescription(), HttpStatus.CREATED);
        }
    }

    @Override
    @Operation(summary = "Получение списка товаров в корзине (user)", description = "Получение списка товаров в корзине по cart_id, доступно с ролью ROLE_USER")
    public ResponseEntity<?> getCartItemsByUser(Authentication authentication, Integer cartId) {
        String username = super.getUsername(authentication);
        return new ResponseEntity<>(service.getCartItemsByUser(username, cartId), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Получение товара в корзине (user)", description = "Получение товара в корзине по cart_id и item_id, доступно с ролью ROLE_USER")
    public ResponseEntity<?> getOneByUser(Authentication authentication, Integer cartId, Integer itemId) {
        String username = super.getUsername(authentication);
        return new ResponseEntity<>(service.getOneByUser(username, cartId, itemId), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Обновление товара в корзине (user)", description = "Обновление товара в корзине по item_id, доступно с ролью ROLE_USER")
    public ResponseEntity<?> updateCartItem(Authentication authentication,
                                            Integer itemId,
                                            UpdateCartItemRequest request,
                                            BindingResult bindingResult) {
        String username = super.getUsername(authentication);
        if (bindingResult.hasErrors()) {
            return ResponseUtil.generateErrorResponse(bindingResult);
        } else {
            service.updateCartItem(username, request, itemId);
            return new ResponseEntity<>(ResponseStatus.UPDATED.getDescription(), HttpStatus.OK);
        }
    }

    @Override
    @Operation(summary = "Удаление товара из корзины (user)", description = "Удаление товара из корзины по item_id, доступно с ролью ROLE_USER")
    public ResponseEntity<?> deleteItemFromCart(Authentication authentication, Integer itemId) {
        String username = super.getUsername(authentication);
        service.deleteItemFromCart(username, itemId);
        return new ResponseEntity<>(ResponseStatus.DELETED.getDescription(), HttpStatus.OK);
    }

}
