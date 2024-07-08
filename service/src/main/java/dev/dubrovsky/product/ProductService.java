package dev.dubrovsky.product;

public class ProductService implements IProductService {

    private final IProductDao productDao;

    public ProductService(IProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void create(Product entity) {
        productDao.create(entity);
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
    public void update(Product entity) {
        productDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        if (id < 1) {
            System.out.println("Id должен быть > 0");
        } else {
            productDao.delete(id);
        }
    }

}
