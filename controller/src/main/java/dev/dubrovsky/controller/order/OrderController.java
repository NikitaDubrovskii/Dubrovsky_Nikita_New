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
    @Operation(summary = "Создание заказа", description = "Создание заказа")
    public ResponseEntity<?> create(NewOrderRequest request,
                                    BindingResult bindingResult) {
        return super.create(request, bindingResult);
    }

    @Override
    @Operation(summary = "Получение заказа", description = "Получение заказа по id")
    public ResponseEntity<?> getById(Integer id) {
        return super.getById(id);
    }

    @Override
    @Operation(summary = "Получение списка заказов", description = "Получение списка заказов")
    public ResponseEntity<?> getAll() {
        return super.getAll();
    }

    @Override
    @Operation(summary = "Обновление заказа", description = "Обновление заказа по id")
    public ResponseEntity<?> update(UpdateOrderRequest request,
                                    Integer id,
                                    BindingResult bindingResult) {
        return super.update(request, id, bindingResult);
    }

    @Override
    @Operation(summary = "Удаление заказа", description = "Удаление заказа по id")
    public ResponseEntity<?> delete(Integer id) {
        return super.delete(id);
    }

    @Override
    public ResponseEntity<?> getOrdersByUser(Authentication authentication) {
        String username = super.getUsername(authentication);
        return new ResponseEntity<>(service.getOrdersByUser(username), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getOneByUser(Authentication authentication, Integer id) {
        String username = super.getUsername(authentication);
        return new ResponseEntity<>(service.getOneByUser(username, id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> createOrder(Authentication authentication, Integer cartId, NewOrderRequest newOrderRequest) {
        String username = super.getUsername(authentication);
        service.createOrder(username, cartId, newOrderRequest);
        return new ResponseEntity<>(ResponseStatus.CREATED.getDescription(), HttpStatus.CREATED);
    }

}
