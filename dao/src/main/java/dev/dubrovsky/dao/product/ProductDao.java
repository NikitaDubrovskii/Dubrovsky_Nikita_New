package dev.dubrovsky.dao.product;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.product.Product;

public class ProductDao extends AbstractDao<Product> implements IProductDao {

    public ProductDao(Class<Product> entityClass) {
        super(entityClass);
    }

}
