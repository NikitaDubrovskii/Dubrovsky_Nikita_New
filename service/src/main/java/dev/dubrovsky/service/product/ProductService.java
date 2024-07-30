package dev.dubrovsky.service.product;

import dev.dubrovsky.dao.category.CategoryDao;
import dev.dubrovsky.dao.product.ProductDao;
import dev.dubrovsky.model.product.Product;
import dev.dubrovsky.util.validation.ValidationUtil;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    private final ProductDao productDao;
    private final CategoryDao categoryDao;

    public ProductService(ProductDao productDao, CategoryDao categoryDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public void create(Product product) {
        validateProduct(product);
        ValidationUtil.checkEntityPresent(product.getCategoryId(), categoryDao);

        productDao.create(product);
    }

    @Override
    public void getById(Integer id) {
        ValidationUtil.checkId(id, productDao);

        System.out.println(productDao.getById(id));
    }

    @Override
    public void getAll() {
        if (productDao.getAll().isEmpty() && productDao.getAll() == null) {
            System.out.println("Таблица товаров пустая");
        } else {
            productDao.getAll().forEach(System.out::println);
        }
    }

    @Override
    public void update(Product product, Integer id) {
        validateProduct(product);
        ValidationUtil.checkEntityPresent(product.getCategoryId(), categoryDao);
        ValidationUtil.checkId(id, productDao);

        product.setId(id);
        productDao.update(product);
    }

    @Override
    public void delete(Integer id) {
        ValidationUtil.checkId(id, productDao);

        productDao.delete(id);
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
