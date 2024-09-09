package dev.dubrovsky.service.product;

import dev.dubrovsky.model.product.Product;
import dev.dubrovsky.repository.category.CategoryRepository;
import dev.dubrovsky.repository.product.ProductRepository;
import dev.dubrovsky.util.validation.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product create(Product product) {
        validateProduct(product);
        ValidationUtil.checkEntityPresent(product.getCategory().getId(), categoryRepository);

        return productRepository.save(product);
    }

    @Override
    public Product getById(Integer id) {
        ValidationUtil.checkId(id, productRepository);

        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAll() {
        if (productRepository.findAll().isEmpty()) {
            return null;
        } else {
            return productRepository.findAll();
        }
    }

    @Override
    public Product update(Product product, Integer id) {
        validateProduct(product);
        ValidationUtil.checkEntityPresent(product.getCategory().getId(), categoryRepository);
        ValidationUtil.checkId(id, productRepository);

        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, productRepository);
        productRepository.deleteById(id);

        return "Удалено!";
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
