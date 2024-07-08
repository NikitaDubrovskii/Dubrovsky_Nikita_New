package dev.dubrovsky.category;

public class CategoryService implements ICategoryService {

    private final ICategoryDao categoryDao;

    public CategoryService(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public void create(Category entity) {
        categoryDao.create(entity);
    }

    @Override
    public void getAll() {
        if (categoryDao.getAll().isEmpty() && categoryDao.getAll() == null) {
            System.out.println("Таблица категорий пустая");
        } else {
            categoryDao.getAll().forEach(System.out::println);
        }
    }

    @Override
    public void update(Category entity) {
        categoryDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        if (id < 1) {
            System.out.println("Id должен быть > 0");
        } else {
            categoryDao.delete(id);
        }
    }

}
