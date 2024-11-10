package dev.dubrovsky.controller.cart;

import dev.dubrovsky.dto.request.cart.NewCartRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartRequest;
import dev.dubrovsky.service.cart.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/cart")
@Tag(name = "Корзины", description = "Взаимодействие с корзиной")
public class CartController extends AbstractCartController {

    public CartController(CartService cartService) {
        super(cartService);
    }

    @Override
    @Operation(summary = "Создание корзины (admin)", description = "Создание корзины, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> create(NewCartRequest request,
                                    BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение корзины (admin)", description = "Получение корзины по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка корзин (admin)", description = "Получение списка корзин, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Обновление корзины (admin)", description = "Обновление корзины по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> update(Integer id,
                                    UpdateCartRequest request,
                                    BindingResult bindingResult) {
        return super.update(id, request, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление корзины (admin)", description = "Удаление корзины по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

    @Override
    @Operation(summary = "Получение списка корзин (user)", description = "Получение списка корзин, доступно с ролью ROLE_USER")
    public ResponseEntity<?> getCartsByUser(Authentication authentication) {
        String username = super.getUsername(authentication);
        return new ResponseEntity<>(service.getCartsByUser(username), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Получение корзины (user)", description = "Получение корзины по id, доступно с ролью ROLE_USER")
    public ResponseEntity<?> getOneByUser(Authentication authentication, Integer id) {
        String username = super.getUsername(authentication);
        return new ResponseEntity<>(service.getOneByUser(username, id), HttpStatus.OK);
    }

}
