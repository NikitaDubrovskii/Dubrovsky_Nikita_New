package dev.dubrovsky.controller.product;

import dev.dubrovsky.controller.AbstractController;
import dev.dubrovsky.dto.request.product.NewProductRequest;
import dev.dubrovsky.dto.request.product.UpdateProductRequest;
import dev.dubrovsky.dto.response.product.ProductResponse;
import dev.dubrovsky.service.product.ProductService;

public abstract class AbstractProductController extends AbstractController<ProductService, ProductResponse, NewProductRequest, UpdateProductRequest> {

    public AbstractProductController(ProductService service) {
        super(service);
    }

}
