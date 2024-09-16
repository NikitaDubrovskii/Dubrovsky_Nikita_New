package dev.dubrovsky.service.product;

import dev.dubrovsky.dto.request.product.NewProductRequest;
import dev.dubrovsky.dto.request.product.UpdateProductRequest;
import dev.dubrovsky.dto.response.product.ProductResponse;
import dev.dubrovsky.exception.DbResponseErrorException;
import dev.dubrovsky.exception.EntityNotFoundException;
import dev.dubrovsky.model.product.Product;
import dev.dubrovsky.repository.category.CategoryRepository;
import dev.dubrovsky.repository.product.ProductRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void create(NewProductRequest request) {
        ValidationUtil.checkEntityPresent(request.categoryId(), categoryRepository);

        Product product = new Product();
        product.setName(request.name());
        if (request.description() != null && !request.description().isEmpty()) {
            product.setDescription(request.description());
        }
        product.setPrice(request.price());
        product.setCategory(categoryRepository
                .findById(request.categoryId())
                .orElseThrow(DbResponseErrorException::new));
        product.setCreatedAt(LocalDateTime.now());

        productRepository.save(product);
    }

    @Override
    public ProductResponse getById(Integer id) {
        ValidationUtil.checkId(id, productRepository);

        Product product = productRepository.findById(id).orElseThrow(DbResponseErrorException::new);
        return product.mapToResponse();
    }

    @Override
    public List<ProductResponse> getAll() {
        if (productRepository.findAll().isEmpty()) {
            throw new EntityNotFoundException("По запросу ничего не найдено :(");
        } else {
            List<ProductResponse> responses = new ArrayList<>();
            List<Product> all = productRepository.findAll();

            all.forEach(product -> responses.add(product.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdateProductRequest request, Integer id) {
        ValidationUtil.checkId(id, productRepository);

        Product product = productRepository.findById(id).orElseThrow(DbResponseErrorException::new);

        if (request.name() != null && !request.name().isEmpty()) {
            product.setName(request.name());
        }
        if (request.description() != null && !request.description().isEmpty()) {
            product.setDescription(request.description());
        }
        if (request.price() != null && request.price() > 0) {
            product.setPrice(request.price());
        }
        if (request.categoryId() != null && request.categoryId() > 0) {
            ValidationUtil.checkEntityPresent(request.categoryId(), categoryRepository);
            product.setCategory(categoryRepository.findById(request.categoryId()).orElseThrow(DbResponseErrorException::new));
        }
        product.setId(id);

        productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, productRepository);
        productRepository.deleteById(id);
    }

}
