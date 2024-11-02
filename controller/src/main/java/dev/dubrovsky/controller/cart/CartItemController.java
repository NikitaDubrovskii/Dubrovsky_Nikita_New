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

    @Override
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
    public ResponseEntity<?> getCartItemsByUser(Authentication authentication, Integer cartId) {
        String username = super.getUsername(authentication);
        return new ResponseEntity<>(service.getCartItemsByUser(username, cartId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getOneByUser(Authentication authentication, Integer cartId, Integer itemId) {
        String username = super.getUsername(authentication);
        return new ResponseEntity<>(service.getOneByUser(username, cartId, itemId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateCartItem(Authentication authentication,
                                            UpdateCartItemRequest request,
                                            Integer itemId,
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
    public ResponseEntity<?> deleteItemFromCart(Authentication authentication, Integer itemId) {
        String username = super.getUsername(authentication);
        service.deleteItemFromCart(username, itemId);
        return new ResponseEntity<>(ResponseStatus.DELETED.getDescription(), HttpStatus.OK);
    }

}
