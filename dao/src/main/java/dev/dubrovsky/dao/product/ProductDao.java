package dev.dubrovsky.dao.product;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.product.Product;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductDao extends AbstractDao<Product> implements IProductDao {

    public ProductDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, Product.class);
    }

}
