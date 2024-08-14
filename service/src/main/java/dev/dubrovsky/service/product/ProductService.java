package dev.dubrovsky.service.product;

import dev.dubrovsky.dao.category.CategoryDao;
import dev.dubrovsky.dao.product.ProductDao;
import dev.dubrovsky.model.product.Product;
import dev.dubrovsky.util.validation.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    private final ProductDao productDao;
    private final CategoryDao categoryDao;

    public ProductService(ProductDao productDao, CategoryDao categoryDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public Product create(Product product) {
        validateProduct(product);
        ValidationUtil.checkEntityPresent(product.getCategory().getId(), categoryDao);

        return productDao.create(product);
    }

    @Override
    public Product getById(Integer id) {
        ValidationUtil.checkId(id, productDao);

        return productDao.getById(id);
    }

    @Override
    public List<Product> getAll() {
        if (productDao.getAll().isEmpty() && productDao.getAll() == null) {
            return null;
        } else {
            return productDao.getAll();
        }
    }

    @Override
    public Product update(Product product, Integer id) {
        validateProduct(product);
        ValidationUtil.checkEntityPresent(product.getCategory().getId(), categoryDao);
        ValidationUtil.checkId(id, productDao);

        product.setId(id);
        return productDao.update(product);
    }

    @Override
    public String delete(Integer id) {
        ValidationUtil.checkId(id, productDao);

        return productDao.delete(id);
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
