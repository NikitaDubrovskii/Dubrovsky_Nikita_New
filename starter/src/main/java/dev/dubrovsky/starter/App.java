package dev.dubrovsky.starter;

import dev.dubrovsky.dao.analytics.AnalyticsDao;
import dev.dubrovsky.dao.analytics.IAnalyticsDao;
import dev.dubrovsky.dao.bonus.BonusDao;
import dev.dubrovsky.dao.bonus.IBonusDao;
import dev.dubrovsky.dao.bonus.IUserBonusDao;
import dev.dubrovsky.dao.bonus.UserBonusDao;
import dev.dubrovsky.dao.cart.CartDao;
import dev.dubrovsky.dao.cart.CartItemDao;
import dev.dubrovsky.dao.cart.ICartDao;
import dev.dubrovsky.dao.cart.ICartItemDao;
import dev.dubrovsky.dao.category.CategoryDao;
import dev.dubrovsky.dao.category.ICategoryDao;
import dev.dubrovsky.dao.loyalty_program.ILoyaltyProgramDao;
import dev.dubrovsky.dao.loyalty_program.LoyaltyProgramDao;
import dev.dubrovsky.dao.order.IOrderDao;
import dev.dubrovsky.dao.order.IOrderItemDao;
import dev.dubrovsky.dao.order.OrderDao;
import dev.dubrovsky.dao.order.OrderItemDao;
import dev.dubrovsky.dao.payment_method.IPaymentMethodDao;
import dev.dubrovsky.dao.payment_method.PaymentMethodDao;
import dev.dubrovsky.dao.product.IProductDao;
import dev.dubrovsky.dao.product.ProductDao;
import dev.dubrovsky.dao.user.IUserDao;
import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.service.analytics.AnalyticsService;
import dev.dubrovsky.service.analytics.IAnalyticsService;
import dev.dubrovsky.service.bonus.BonusService;
import dev.dubrovsky.service.bonus.IBonusService;
import dev.dubrovsky.service.bonus.IUserBonusService;
import dev.dubrovsky.service.bonus.UserBonusService;
import dev.dubrovsky.service.cart.CartItemService;
import dev.dubrovsky.service.cart.CartService;
import dev.dubrovsky.service.cart.ICartItemService;
import dev.dubrovsky.service.cart.ICartService;
import dev.dubrovsky.service.category.CategoryService;
import dev.dubrovsky.service.category.ICategoryService;
import dev.dubrovsky.service.loyalty_program.ILoyaltyProgramService;
import dev.dubrovsky.service.loyalty_program.LoyaltyProgramService;
import dev.dubrovsky.service.order.IOrderItemService;
import dev.dubrovsky.service.order.IOrderService;
import dev.dubrovsky.service.order.OrderItemService;
import dev.dubrovsky.service.order.OrderService;
import dev.dubrovsky.service.payment_method.IPaymentMethodService;
import dev.dubrovsky.service.payment_method.PaymentMethodService;
import dev.dubrovsky.service.product.IProductService;
import dev.dubrovsky.service.product.ProductService;
import dev.dubrovsky.service.user.IUserService;
import dev.dubrovsky.service.user.UserService;

public class App {

    public static void main(String[] args) {
        ICategoryDao categoryDao = new CategoryDao();
        IProductDao productDao = new ProductDao();
        IUserDao userDao = new UserDao();
        IAnalyticsDao analyticsDao = new AnalyticsDao();
        IBonusDao bonusDao = new BonusDao();
        ICartItemDao cartItemDao = new CartItemDao();
        ICartDao cartDao = new CartDao();
        ILoyaltyProgramDao loyaltyProgramDao = new LoyaltyProgramDao();
        IOrderItemDao orderItemDao = new OrderItemDao();
        IOrderDao orderDao = new OrderDao();
        IUserBonusDao userBonusDao = new UserBonusDao();
        IPaymentMethodDao paymentMethodDao = new PaymentMethodDao();

        ICategoryService categoryService = new CategoryService(categoryDao);
        IProductService productService = new ProductService(productDao, categoryDao);
        IUserService userService = new UserService(userDao);
        IAnalyticsService analyticsService = new AnalyticsService(analyticsDao, userDao);
        IBonusService bonusService = new BonusService(bonusDao, loyaltyProgramDao);
        ICartService cartService = new CartService(cartDao, userDao);
        ICartItemService cartItemService = new CartItemService(cartItemDao, cartDao, productDao);
        ILoyaltyProgramService loyaltyProgramService = new LoyaltyProgramService(loyaltyProgramDao);
        IOrderService orderService = new OrderService(orderDao, paymentMethodDao, userDao);
        IOrderItemService orderItemService = new OrderItemService(orderItemDao, orderDao, productDao);
        IUserBonusService userBonusService = new UserBonusService(userBonusDao, userDao, bonusDao);
        IPaymentMethodService paymentMethodService = new PaymentMethodService(paymentMethodDao);
    }

}
