package dev.dubrovsky.service.product;

import dev.dubrovsky.dto.request.product.NewProductRequest;
import dev.dubrovsky.dto.request.product.UpdateProductRequest;
import dev.dubrovsky.dto.response.product.ProductResponse;
import dev.dubrovsky.service.ICommonService;
import dev.dubrovsky.model.product.Product;

public interface IProductService extends ICommonService<ProductResponse, NewProductRequest, UpdateProductRequest> {
}
