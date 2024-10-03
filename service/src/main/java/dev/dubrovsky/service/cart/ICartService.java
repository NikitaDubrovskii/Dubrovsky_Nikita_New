package dev.dubrovsky.service.cart;

import dev.dubrovsky.dto.request.cart.NewCartRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartRequest;
import dev.dubrovsky.dto.response.cart.CartResponse;
import dev.dubrovsky.service.ICommonService;

public interface ICartService extends ICommonService<CartResponse, NewCartRequest, UpdateCartRequest> {

    Float getTotalPrice(Integer id);

}
