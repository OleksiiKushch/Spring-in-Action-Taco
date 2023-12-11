package tacos.service;

import tacos.entity.TacoOrder;

public interface OrderService {

    TacoOrder create(TacoOrder tacoOrder);
    void removeAllOrders();
}
