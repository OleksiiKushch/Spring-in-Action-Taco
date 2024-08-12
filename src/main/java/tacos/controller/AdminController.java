package tacos.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tacos.entity.TacoOrder;
import tacos.service.OrderService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final OrderService orderService;

    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllOrders")
    public String getAllOrders() {
        List<TacoOrder> result = orderService.getAllOrders();
        return Objects.nonNull(result) ? result.toString() : "[]";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/deleteAllOrders")
    public String deleteAllOrders() {
        orderService.removeAllOrders();
        return "redirect:/admin";
    }
}
