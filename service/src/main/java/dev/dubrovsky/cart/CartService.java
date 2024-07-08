package dev.dubrovsky.cart;

public class CartService implements ICartService {

    private final ICartDao cartDao;

    public CartService(ICartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Override
    public void create(Cart entity) {
        cartDao.create(entity);
    }

    @Override
    public void getAll() {
        if (cartDao.getAll().isEmpty() && cartDao.getAll() == null) {
            System.out.println("Таблица корзин пустая");
        } else {
            cartDao.getAll().forEach(System.out::println);
        }
    }

    @Override
    public void update(Cart entity) {
        cartDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        if (id < 1) {
            System.out.println("Id должен быть > 0");
        } else {
            cartDao.delete(id);
        }
    }

}
