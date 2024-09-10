package dev.dubrovsky.service.cart;

import dev.dubrovsky.dto.request.cart.NewCartRequest;
import dev.dubrovsky.dto.request.cart.UpdateCartRequest;
import dev.dubrovsky.service.ICommonService;
import dev.dubrovsky.model.cart.Cart;

public interface ICartService extends ICommonService<Cart, NewCartRequest, UpdateCartRequest> {

    Float getTotalPrice(Integer id);

}
