package com.sarige.tmall.service.impl;

import com.sarige.tmall.mapper.OrderMapper;
import com.sarige.tmall.mapper.UserMapper;
import com.sarige.tmall.pojo.Order;
import com.sarige.tmall.pojo.User;
import com.sarige.tmall.service.OrderService;
import com.sarige.tmall.util.example.OrderExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderMapper orderMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public void add(Order order) {
        orderMapper.insert(order);
    }

    @Override
    public void delete(int orderId) {
        orderMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public Order get(int orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public List<Order> list() {
        OrderExample orderExample = new OrderExample();
        orderExample.setOrderByClause("id desc");
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        setUser(orderList);
        return orderList;
    }

    public void setUser(Order order) {
        Integer userId = order.getUid();
        User user = userMapper.selectByPrimaryKey(userId);
        order.setUser(user);
    }

    public void setUser(List<Order> orderList) {
        for (Order order : orderList) {
            setUser(order);
        }
    }
}
