package dev.dubrovsky.service.product;

import dev.dubrovsky.dto.request.product.NewProductRequest;
import dev.dubrovsky.dto.request.product.UpdateProductRequest;
import dev.dubrovsky.dto.response.product.ProductResponse;
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
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setCategory(categoryRepository
                .findById(request.categoryId())
                .orElse(null));
        product.setCreatedAt(LocalDateTime.now());

        validateProduct(product);
        ValidationUtil.checkEntityPresent(product.getCategory().getId(), categoryRepository);

        productRepository.save(product);
    }

    @Override
    public ProductResponse getById(Integer id) {
        ValidationUtil.checkId(id, productRepository);

        Product product = productRepository.findById(id).orElse(null);
        return product.mapToResponse();
    }

    @Override
    public List<ProductResponse> getAll() {
        if (productRepository.findAll().isEmpty()) {
            return null;
        } else {
            List<ProductResponse> responses = new ArrayList<>();
            List<Product> all = productRepository.findAll();

            all.forEach(product -> responses.add(product.mapToResponse()));

            return responses;
        }
    }

    @Override
    public void update(UpdateProductRequest request, Integer id) {
        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setCategory(categoryRepository
                .findById(request.categoryId())
                .orElse(null));

        validateProduct(product);
        ValidationUtil.checkEntityPresent(product.getCategory().getId(), categoryRepository);
        ValidationUtil.checkId(id, productRepository);

        product.setId(id);
        productRepository.save(product);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, productRepository);
        productRepository.deleteById(id);
    }

    private void validateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Товар не может отсутствовать");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Название не может отсутствовать");
        }
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("Цена не может отсутствовать");
        }
    }

}
