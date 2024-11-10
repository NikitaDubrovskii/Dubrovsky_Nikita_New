package dev.dubrovsky.service.product;

import dev.dubrovsky.dto.request.product.NewProductRequest;
import dev.dubrovsky.dto.request.product.UpdateProductRequest;
import dev.dubrovsky.dto.response.product.ProductResponse;
import dev.dubrovsky.service.ICommonService;

public interface IProductService extends ICommonService<ProductResponse, NewProductRequest, UpdateProductRequest> {
}
