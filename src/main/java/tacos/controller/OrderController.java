package tacos.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.support.SessionStatus;
import tacos.entity.TacoOrder;
import tacos.entity.User;
import tacos.service.OrderService;
import tacos.service.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping ("/orders")
@SessionAttributes ("tacoOrder")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @ModelAttribute ("tacoOrder")
    public TacoOrder createTacoOrder() {
        return new TacoOrder();
    }

    @ModelAttribute ("user")
    public User getUser(Principal principal) {
        String username = principal.getName();
        return userService.findUserByUsername(username);
    }

    @GetMapping ("/current")
    public String showOrderForm(@AuthenticationPrincipal User user, @ModelAttribute TacoOrder order) {
        setSavedUserDataToOrder(user, order);

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder tacoOrder, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        tacoOrder.setUser(user);
        tacoOrder = orderService.create(tacoOrder);
        log.info("Order successfully saved and submitted: {}", tacoOrder);
        sessionStatus.setComplete();

        return "redirect:/";
    }

    // TODO: refactor
    private void setSavedUserDataToOrder(User user, TacoOrder order) {
        if (order.getDeliveryName() == null) {
            order.setDeliveryName(user.getFullName());
        }
        if (order.getDeliveryStreet() == null) {
            order.setDeliveryStreet(user.getStreet());
        }
        if (order.getDeliveryCity() == null) {
            order.setDeliveryCity(user.getCity());
        }
        if (order.getDeliveryState() == null) {
            order.setDeliveryState(user.getState());
        }
        if (order.getDeliveryZip() == null) {
            order.setDeliveryZip(user.getZip());
        }
    }

}
