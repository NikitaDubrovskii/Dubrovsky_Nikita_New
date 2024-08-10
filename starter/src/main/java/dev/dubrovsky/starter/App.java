package dev.dubrovsky.starter;

import dev.dubrovsky.configuration.OnlineStoreContextConfiguration;
import dev.dubrovsky.model.analytics.Analytics;
import dev.dubrovsky.model.bonus.Bonus;
import dev.dubrovsky.model.category.Category;
import dev.dubrovsky.model.loyalty.program.LoyaltyProgram;
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

import java.util.List;

public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(OnlineStoreContextConfiguration.class);

        /*IAnalyticsService analyticsService = applicationContext.getBean(IAnalyticsService.class);
        analyticsService.getById(1);*/
        /*IBonusService bonusService = applicationContext.getBean(IBonusService.class);
        bonusService.delete(2);*/
        /*ILoyaltyProgramService loyaltyProgramService = applicationContext.getBean(ILoyaltyProgramService.class);
        loyaltyProgramService.delete(1);*/
        /*ICartService cartService = applicationContext.getBean(ICartService.class);
        cartService.delete(1);*/
        /*ICartItemService cartItemService = applicationContext.getBean(ICartItemService.class);
        cartItemService.delete(1);*/
        /*ICategoryService categoryService = applicationContext.getBean(ICategoryService.class);
        categoryService.delete(2);*/
        /*IOrderService orderService = applicationContext.getBean(IOrderService.class);
        orderService.delete(2);*/
        /*IOrderItemService orderItemService = applicationContext.getBean(IOrderItemService.class);
        orderItemService.delete(1);*/
        /*IPaymentMethodService paymentMethodService = applicationContext.getBean(IPaymentMethodService.class);
        paymentMethodService.delete(1);*/
        /*IUserService userService = applicationContext.getBean(IUserService.class);
        userService.delete(1);*/

        /*IUserBonusService userBonusService = applicationContext.getBean(IUserBonusService.class);
        userBonusService.getAll();

        IUserLoyaltyProgramService userLoyaltyProgramService = applicationContext.getBean(IUserLoyaltyProgramService.class);
        userLoyaltyProgramService.getAll();*/
    }

}
