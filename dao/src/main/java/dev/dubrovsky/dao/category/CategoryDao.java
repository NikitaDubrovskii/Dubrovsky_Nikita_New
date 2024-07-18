package dev.dubrovsky.dao.category;

import dev.dubrovsky.dao.AbstractDao;
import dev.dubrovsky.model.category.Category;

public class CategoryDao extends AbstractDao<Category> implements ICategoryDao {

    public CategoryDao(Class<Category> entityClass) {
        super(entityClass);
    }

}
