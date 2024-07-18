package dev.dubrovsky.service.product;

import dev.dubrovsky.dao.category.CategoryDao;
import dev.dubrovsky.dao.product.ProductDao;
import dev.dubrovsky.model.product.Product;

import java.util.NoSuchElementException;
import java.util.Optional;

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
        checkCategoryPresent(product.getCategoryId());

        productDao.create(product);
    }

    @Override
    public void getById(Integer id) {
        checkId(id);

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
        checkCategoryPresent(product.getCategoryId());
        checkId(id);

        product.setId(id);
        productDao.update(product);
    }

    @Override
    public void delete(Integer id) {
        checkId(id);

        productDao.delete(id);
    }

    private void validateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Аналитика не может отсутствовать");
        }
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Название не может отсутствовать");
        }
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("Цена не может отсутствовать");
        }
    }

    private void checkCategoryPresent(Integer categoryId) {
        if (categoryId > 0) {
            Optional
                    .ofNullable(categoryDao.getById(categoryId))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + categoryId));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

    private void checkId(Integer id) {
        if (id > 0) {
            Optional
                    .ofNullable(productDao.getById(id))
                    .orElseThrow(() -> new NoSuchElementException("Ничего не найдено с id: " + id));
        } else {
            throw new IllegalArgumentException("Id должен быть больше 0");
        }
    }

}
