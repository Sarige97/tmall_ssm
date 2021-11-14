package com.sarige.tmall.service.impl;

import com.sarige.tmall.mapper.OrderMapper;
import com.sarige.tmall.mapper.UserMapper;
import com.sarige.tmall.pojo.Order;
import com.sarige.tmall.pojo.OrderItem;
import com.sarige.tmall.pojo.User;
import com.sarige.tmall.service.OrderItemService;
import com.sarige.tmall.service.OrderService;
import com.sarige.tmall.service.UserService;
import com.sarige.tmall.util.example.OrderExample;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderMapper orderMapper;
    @Resource
    OrderItemService orderItemService;
    @Resource
    UserService userService;

    private final static Logger logger = Logger.getLogger(OrderServiceImpl.class);

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
        orderMapper.updateByPrimaryKeySelective(order);
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
        if (orderList.size()!=0) setUser(orderList);
        return orderList;
    }

    @Override
    public List<Order> list(int userId, String excludeStatus) {
        OrderExample orderExample = new OrderExample();
        orderExample.or().andUseridEqualTo(userId).andStatusNotEqualTo(excludeStatus);
        orderExample.setOrderByClause("id desc");
        return orderMapper.selectByExample(orderExample);
    }

    @Override
    public float add(Order order, List<OrderItem> orderItemList) {
        float total = 0;
        add(order);
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderId(order.getId());
            orderItemService.update(orderItem);
            total += orderItem.getProduct().getPromotePrice() * orderItem.getNumber();
        }
        return total;
    }

    public void setUser(Order order) {
        logger.debug(userService);
        logger.debug(order);
        Integer userId = order.getUserid();
        User user = userService.get(userId);
        order.setUser(user);
    }

    public void setUser(List<Order> orderList) {
        for (Order order : orderList) {
            setUser(order);
        }
    }
}
