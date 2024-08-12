package tacos.service;

import tacos.entity.TacoOrder;

import java.util.List;

public interface OrderService {

    TacoOrder create(TacoOrder tacoOrder);
    void removeAllOrders();
    List<TacoOrder> getAllOrders();
}
