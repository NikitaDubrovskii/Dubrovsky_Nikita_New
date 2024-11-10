package dev.dubrovsky.service.cart;

import dev.dubrovsky.dto.request.cart.NewCartRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartRequest;
import dev.dubrovsky.dto.response.cart.CartResponse;
import dev.dubrovsky.model.cart.Cart;
import dev.dubrovsky.service.ICommonService;

import java.util.List;

public interface ICartService extends ICommonService<CartResponse, NewCartRequest, UpdateCartRequest> {

    Float getTotalPrice(Integer id);

    List<CartResponse> getCartsByUser(String username);

    CartResponse getOneByUser(String username, Integer id);

    Cart getByUserId(Integer id);

}
