package com.sarige.tmall.service;

import com.sarige.tmall.pojo.Order;
import com.sarige.tmall.pojo.Orderitem;

import java.util.List;

public interface OrderItemService {

    void add(Orderitem orderItem);

    void delete(int orderItemId);

    void update(Orderitem orderItem);

    Orderitem get(int orderItemId);

    List<Orderitem> list();

    void fill(List<Order> orderList);

    void fill(Order order);

}
