package dev.dubrovsky.service.product;

import dev.dubrovsky.dto.request.product.NewProductRequest;
import dev.dubrovsky.dto.request.product.UpdateProductRequest;
import dev.dubrovsky.service.ICommonService;
import dev.dubrovsky.model.product.Product;

public interface IProductService extends ICommonService<Product, NewProductRequest, UpdateProductRequest> {
}
