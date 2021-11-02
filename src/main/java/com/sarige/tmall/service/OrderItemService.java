package com.sarige.tmall.service;

import com.sarige.tmall.pojo.Order;
import com.sarige.tmall.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {

    void add(OrderItem orderItem);

    void delete(int orderItemId);

    void update(OrderItem orderItem);

    OrderItem get(int orderItemId);

    List<OrderItem> list();

    void fill(List<Order> orderList);

    void fill(Order order);

}
