package tacos.service.impl;

import org.springframework.stereotype.Service;
import tacos.entity.TacoOrder;
import tacos.repository.OrderRepository;
import tacos.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public TacoOrder create(TacoOrder tacoOrder) {
        return orderRepository.save(tacoOrder);
    }

    @Override
    public void removeAllOrders() {
        orderRepository.deleteAll();
    }
}
