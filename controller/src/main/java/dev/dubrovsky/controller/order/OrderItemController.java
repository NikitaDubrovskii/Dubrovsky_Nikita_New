package dev.dubrovsky.controller.order;

import dev.dubrovsky.dto.request.order.NewOrderItemRequest;
import dev.dubrovsky.dto.request.order.UpdateOrderItemRequest;
import dev.dubrovsky.service.order.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order-item")
@Tag(name = "Товары в заказе", description = "Взаимодействие с товарами в заказе")
public class OrderItemController extends AbstractOrderItemController {

    public OrderItemController(OrderItemService orderItemService) {
        super(orderItemService);
    }

    @Override
    @Operation(summary = "Создание товара в заказе (admin)", description = "Создание товара в заказе, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> create(NewOrderItemRequest request, BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение товара в заказе (admin)", description = "Получение товара в заказе по id товара, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка товаров в заказах (admin)", description = "Получение списка товаров в заказах, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Обновление товара в заказе (admin)", description = "Обновление товара в заказе по id товара, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> update(Integer id, UpdateOrderItemRequest request, BindingResult bindingResult) {
        return super.update(id, request, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление товара из заказа (admin)", description = "Удаление товара из заказа по id товара, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

    @Override
    @Operation(summary = "Получение списка товаров в заказах (user)", description = "Получение списка товаров в заказах по order_id, доступно с ролью ROLE_USER")
    public ResponseEntity<?> getOrderItemsByUser(Authentication authentication, Integer orderId) {
        String username = super.getUsername(authentication);
        return new ResponseEntity<>(service.getOrderItemsByUser(username, orderId), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Получение товара в заказе (user)", description = "Получение списка товаров в заказах по order_id и item_id, доступно с ролью ROLE_USER")
    public ResponseEntity<?> getOneByUser(Authentication authentication, Integer orderId, Integer itemId) {
        String username = super.getUsername(authentication);
        return new ResponseEntity<>(service.getOneByUser(username, orderId, itemId), HttpStatus.OK);
    }

}
