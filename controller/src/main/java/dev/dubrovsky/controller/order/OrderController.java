package dev.dubrovsky.controller.order;

import dev.dubrovsky.controller.ResponseStatus;
import dev.dubrovsky.dto.request.order.NewOrderRequest;
import dev.dubrovsky.dto.request.order.UpdateOrderRequest;
import dev.dubrovsky.service.order.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/order")
@Tag(name = "Заказы", description = "Взаимодействие с заказами")
public class OrderController extends AbstractOrderController {

    public OrderController(OrderService orderService) {
        super(orderService);
    }

    @Override
    @Operation(summary = "Создание заказа (admin)", description = "Создание заказа, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> create(NewOrderRequest request,
                                    BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение заказа (admin)", description = "Получение заказа по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка заказов (admin)", description = "Получение списка заказов, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Обновление заказа (admin)", description = "Обновление заказа по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> update(Integer id,
                                    UpdateOrderRequest request,
                                    BindingResult bindingResult) {
        return super.update(id, request, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление заказа (admin)", description = "Удаление заказа по id, доступно с ролью ROLE_ADMIN")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

    @Override
    @Operation(summary = "Получение списка заказов (user)", description = "Получение списка заказов, доступно с ролью ROLE_USER")
    public ResponseEntity<?> getOrdersByUser(Authentication authentication) {
        String username = super.getUsername(authentication);
        return new ResponseEntity<>(service.getOrdersByUser(username), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Получение заказа (user)", description = "Получение заказа по id, доступно с ролью ROLE_USER")
    public ResponseEntity<?> getOneByUser(Authentication authentication, Integer id) {
        String username = super.getUsername(authentication);
        return new ResponseEntity<>(service.getOneByUser(username, id), HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Создание заказа (user)", description = "Создание заказа, доступно с ролью ROLE_USER")
    public ResponseEntity<?> createOrder(Authentication authentication, Integer cartId, NewOrderRequest newOrderRequest) {
        String username = super.getUsername(authentication);
        service.createOrder(username, cartId, newOrderRequest);
        return new ResponseEntity<>(ResponseStatus.CREATED.getDescription(), HttpStatus.CREATED);
    }

}
