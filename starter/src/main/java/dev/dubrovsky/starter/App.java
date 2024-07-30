package dev.dubrovsky.starter;

import dev.dubrovsky.configuration.OnlineStoreContextConfiguration;
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
import dev.dubrovsky.service.loyalty.program.ILoyaltyProgramService;
import dev.dubrovsky.service.loyalty.program.IUserLoyaltyProgramService;
import dev.dubrovsky.service.loyalty.program.LoyaltyProgramService;
import dev.dubrovsky.service.loyalty.program.UserLoyaltyProgramService;
import dev.dubrovsky.service.order.IOrderItemService;
import dev.dubrovsky.service.order.IOrderService;
import dev.dubrovsky.service.order.OrderItemService;
import dev.dubrovsky.service.order.OrderService;
import dev.dubrovsky.service.payment.method.IPaymentMethodService;
import dev.dubrovsky.service.payment.method.PaymentMethodService;
import dev.dubrovsky.service.product.IProductService;
import dev.dubrovsky.service.product.ProductService;
import dev.dubrovsky.service.user.IUserService;
import dev.dubrovsky.service.user.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(OnlineStoreContextConfiguration.class);

        IAnalyticsService analyticsService = applicationContext.getBean(AnalyticsService.class);
        analyticsService.getAll();

        ICategoryService categoryService = applicationContext.getBean(CategoryService.class);
        categoryService.getAll();

        IProductService productService = applicationContext.getBean(ProductService.class);
        productService.getAll();

        IUserService userService = applicationContext.getBean(UserService.class);
        userService.getAll();

        IUserBonusService userBonusService = applicationContext.getBean(UserBonusService.class);
        //userBonusService.getAll();

        IBonusService bonusService = applicationContext.getBean(BonusService.class);
        bonusService.getAll();

        ICartService cartService = applicationContext.getBean(CartService.class);
        cartService.getAll();

        ICartItemService cartItemService = applicationContext.getBean(CartItemService.class);
        cartItemService.getAll();

        ILoyaltyProgramService loyaltyProgramService = applicationContext.getBean(LoyaltyProgramService.class);
        loyaltyProgramService.getAll();

        IOrderService orderService = applicationContext.getBean(OrderService.class);
        orderService.getAll();

        IOrderItemService orderItemService = applicationContext.getBean(OrderItemService.class);
        orderItemService.getAll();

        IPaymentMethodService paymentMethodService = applicationContext.getBean(PaymentMethodService.class);
        paymentMethodService.getAll();

        IUserLoyaltyProgramService userLoyaltyProgramService = applicationContext.getBean(UserLoyaltyProgramService.class);
        //userLoyaltyProgramService.getAll();

    }

}
