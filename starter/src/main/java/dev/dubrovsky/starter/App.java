package dev.dubrovsky.starter;

import dev.dubrovsky.dao.analytics.AnalyticsDao;
import dev.dubrovsky.dao.analytics.IAnalyticsDao;
import dev.dubrovsky.dao.user.IUserDao;
import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.model.analytics.Analytics;
import dev.dubrovsky.service.analytics.AnalyticsService;
import dev.dubrovsky.service.analytics.IAnalyticsService;
import dev.dubrovsky.service.user.IUserService;
import dev.dubrovsky.service.user.UserService;

public class App {

    public static void main(String[] args) {
        /*ICategoryDao categoryDao = new CategoryDao();
        ICategoryService categoryService = new CategoryService(categoryDao);
        categoryService.getAll();*/

        /*IProductDao productDao = new ProductDao();
        IProductService productService = new ProductService(productDao);
        productService.getAll();*/

        /*IUserDao userDao = new UserDao();
        IUserService userService = new UserService(userDao);
        userService.getAll();*/

        IAnalyticsDao analyticsDao = new AnalyticsDao();
        IAnalyticsService analyticsService = new AnalyticsService(analyticsDao);
        //analyticsService.delete(8);
        //analyticsService.create(new Analytics(null, "Comment", null, 2));
        //analyticsService.update(new Analytics(2, "Subscribe", null, 3));
        //analyticsService.getAll();

        /*IBonusDao bonusDao = new BonusDao();
        IBonusService bonusService = new BonusService(bonusDao);
        bonusService.getAll();*/

        /*ICartItemDao cartItemDao = new CartItemDao();
        ICartItemService cartItemService = new CartItemService(cartItemDao);
        cartItemService.getAll();*/

        /*ICartDao cartDao = new CartDao();
        ICartService cartService = new CartService(cartDao);
        cartService.getAll();*/

        /*ILoyaltyProgramDao loyaltyProgramDao = new LoyaltyProgramDao();
        ILoyaltyProgramService loyaltyProgramService = new LoyaltyProgramService(loyaltyProgramDao);
        loyaltyProgramService.getAll();*/

        /*IOrderItemDao orderItemDao = new OrderItemDao();
        IOrderItemService orderItemService = new OrderItemService(orderItemDao);
        orderItemService.getAll();*/

        /*IOrderDao orderDao = new OrderDao();
        IOrderService orderService = new OrderService(orderDao);
        orderService.getAll();*/

        /*IUserBonusDao userBonusDao = new UserBonusDao();
        IUserBonusService userBonusService = new UserBonusService(userBonusDao);
        userBonusService.getAll();*/

        /*IPaymentMethodDao paymentMethodDao = new PaymentMethodDao();
        IPaymentMethodService paymentMethodService = new PaymentMethodService(paymentMethodDao);
        paymentMethodService.getAll();*/
    }

}
