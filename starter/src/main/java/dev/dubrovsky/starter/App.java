package dev.dubrovsky.starter;

import dev.dubrovsky.service.analytics.IAnalyticsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "dev.dubrovsky")
@EnableJpaRepositories(basePackages = "dev.dubrovsky.repository")
@EntityScan(basePackages = "dev.dubrovsky.model")
@EnableAspectJAutoProxy
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        /*IAnalyticsService analyticsService = context.getBean(IAnalyticsService.class);
        analyticsService.getById(1);*/
        /*IBonusService bonusService = context.getBean(IBonusService.class);
        bonusService.delete(2);*/
        /*ILoyaltyProgramService loyaltyProgramService = context.getBean(ILoyaltyProgramService.class);
        loyaltyProgramService.delete(1);*/
        /*ICartService cartService = context.getBean(ICartService.class);
        cartService.delete(1);*/
        /*ICartItemService cartItemService = context.getBean(ICartItemService.class);
        cartItemService.delete(1);*/
        /*ICategoryService categoryService = context.getBean(ICategoryService.class);
        categoryService.delete(2);*/
        /*IOrderService orderService = context.getBean(IOrderService.class);
        orderService.delete(2);*/
        /*IOrderItemService orderItemService = context.getBean(IOrderItemService.class);
        orderItemService.delete(1);*/
        /*IPaymentMethodService paymentMethodService = context.getBean(IPaymentMethodService.class);
        paymentMethodService.delete(1);*/
        /*IUserService userService = context.getBean(IUserService.class);
        userService.delete(1);*/

        //IUserService userService = context.getBean(IUserService.class);
        IAnalyticsService analyticsService = context.getBean(IAnalyticsService.class);
        analyticsService.getById(1);
        //analyticsService.create(new Analytics("Unsubscribe", userService.getById(2)));
        //analyticsService.getAll();
        //analyticsService.delete(99);

        /*ILoyaltyProgramService loyaltyProgramService = context.getBean(ILoyaltyProgramService.class);
        loyaltyProgramService.getById(1);
        IPaymentMethodService paymentMethodService = context.getBean(IPaymentMethodService.class);
        paymentMethodService.getById(2);*/

        /*IUserBonusService userBonusService = context.getBean(IUserBonusService.class);
        userBonusService.getAll();

        IUserLoyaltyProgramService userLoyaltyProgramService = context.getBean(IUserLoyaltyProgramService.class);
        userLoyaltyProgramService.getAll();*/
    }

}
