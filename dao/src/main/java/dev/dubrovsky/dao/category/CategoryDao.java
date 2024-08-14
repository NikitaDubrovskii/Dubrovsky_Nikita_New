package dev.dubrovsky.dao.category;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.category.Category;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Component;

@Component
public class CategoryDao extends AbstractDao<Category> implements ICategoryDao {

    public CategoryDao(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, Category.class);
    }

}
