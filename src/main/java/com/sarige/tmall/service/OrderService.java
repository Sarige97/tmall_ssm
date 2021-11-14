package com.sarige.tmall.service;

import com.sarige.tmall.pojo.Order;
import com.sarige.tmall.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    void add(Order order);

    void delete(int orderId);

    void update(Order order);

    Order get(int orderId);

    List<Order> list();

    List<Order> list(int userId, String excludeStatus);

    float add(Order order, List<OrderItem> orderItemList);
}
