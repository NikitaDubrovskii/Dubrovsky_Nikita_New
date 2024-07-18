package dev.dubrovsky.starter;

import dev.dubrovsky.dao.analytics.AnalyticsDao;
import dev.dubrovsky.dao.bonus.BonusDao;
import dev.dubrovsky.dao.bonus.IUserBonusDao;
import dev.dubrovsky.dao.bonus.UserBonusDao;
import dev.dubrovsky.dao.cart.CartDao;
import dev.dubrovsky.dao.cart.CartItemDao;
import dev.dubrovsky.dao.category.CategoryDao;
import dev.dubrovsky.dao.loyalty_program.IUserLoyaltyProgramDao;
import dev.dubrovsky.dao.loyalty_program.LoyaltyProgramDao;
import dev.dubrovsky.dao.loyalty_program.UserLoyaltyProgramDao;
import dev.dubrovsky.dao.order.OrderDao;
import dev.dubrovsky.dao.order.OrderItemDao;
import dev.dubrovsky.dao.payment_method.PaymentMethodDao;
import dev.dubrovsky.dao.product.ProductDao;
import dev.dubrovsky.dao.user.UserDao;
import dev.dubrovsky.model.analytics.Analytics;
import dev.dubrovsky.model.bonus.Bonus;
import dev.dubrovsky.model.cart.Cart;
import dev.dubrovsky.model.cart.CartItem;
import dev.dubrovsky.model.category.Category;
import dev.dubrovsky.model.loyalty_program.LoyaltyProgram;
import dev.dubrovsky.model.order.Order;
import dev.dubrovsky.model.order.OrderItem;
import dev.dubrovsky.model.payment_method.PaymentMethod;
import dev.dubrovsky.model.product.Product;
import dev.dubrovsky.model.user.User;
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
import dev.dubrovsky.service.loyalty_program.IUserLoyaltyProgramService;
import dev.dubrovsky.service.loyalty_program.LoyaltyProgramService;
import dev.dubrovsky.service.loyalty_program.UserLoyaltyProgramService;
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
        CategoryDao categoryDao = new CategoryDao(Category.class);
        ProductDao productDao = new ProductDao(Product.class);
        UserDao userDao = new UserDao(User.class);
        AnalyticsDao analyticsDao = new AnalyticsDao(Analytics.class);
        BonusDao bonusDao = new BonusDao(Bonus.class);
        CartItemDao cartItemDao = new CartItemDao(CartItem.class);
        CartDao cartDao = new CartDao(Cart.class);
        LoyaltyProgramDao loyaltyProgramDao = new LoyaltyProgramDao(LoyaltyProgram.class);
        OrderItemDao orderItemDao = new OrderItemDao(OrderItem.class);
        OrderDao orderDao = new OrderDao(Order.class);
        IUserBonusDao userBonusDao = new UserBonusDao();
        PaymentMethodDao paymentMethodDao = new PaymentMethodDao(PaymentMethod.class);
        IUserLoyaltyProgramDao userLoyaltyProgramDao = new UserLoyaltyProgramDao();

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
        IUserLoyaltyProgramService userLoyaltyProgramService = new UserLoyaltyProgramService(userLoyaltyProgramDao, userDao, loyaltyProgramDao);
    }

}
