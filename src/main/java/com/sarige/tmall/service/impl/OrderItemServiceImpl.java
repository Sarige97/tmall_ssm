package com.sarige.tmall.service.impl;

import com.sarige.tmall.mapper.OrderItemMapper;
import com.sarige.tmall.mapper.ProductMapper;
import com.sarige.tmall.pojo.Order;
import com.sarige.tmall.pojo.OrderItem;
import com.sarige.tmall.pojo.Product;
import com.sarige.tmall.service.OrderItemService;
import com.sarige.tmall.service.ProductService;
import com.sarige.tmall.util.example.OrderItemExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Resource
    OrderItemMapper orderItemMapper;
    @Resource
    ProductMapper productMapper;

    @Override
    public void add(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }

    @Override
    public void delete(int orderItemId) {
        orderItemMapper.deleteByPrimaryKey(orderItemId);
    }

    @Override
    public void update(OrderItem orderItem) {
        orderItemMapper.updateByPrimaryKeySelective(orderItem);
    }

    @Override
    public OrderItem get(int orderItemId) {
        OrderItem orderItem = orderItemMapper.selectByPrimaryKey(orderItemId);
        setProduct(orderItem);
        return orderItem;
    }

    @Override
    public List<OrderItem> list() {
        OrderItemExample example = new OrderItemExample();
        example.setOrderByClause("id desc");
        return orderItemMapper.selectByExample(example);
    }

    @Override
    public void fill(List<Order> orderList) {
        for (Order order : orderList) {
            fill(order);
        }
    }

    @Override
    public void fill(Order order) {
        OrderItemExample orderitemExample = new OrderItemExample();
        orderitemExample.or().andOrderIdEqualTo(order.getId());
        List<OrderItem> orderItemList = orderItemMapper.selectByExample(orderitemExample);
        setProduct(orderItemList);

        float total = 0;
        int totalNumber = 0;
        for (OrderItem orderItem : orderItemList) {
            total += orderItem.getProduct().getPromotePrice();
            totalNumber += orderItem.getNumber();
        }
        order.setTotal(total);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(orderItemList);
    }

    @Override
    public int getSaleCount(int productId) {
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.or().andProductIdEqualTo(productId);
        List<OrderItem> orderItemList = orderItemMapper.selectByExample(orderItemExample);
        int result = 0;
        for (OrderItem orderItem : orderItemList) {
            result += orderItem.getNumber();
        }
        return result;
    }

    @Override
    public List<OrderItem> listByUserId(int userId) {
        OrderItemExample orderItemExample = new OrderItemExample();
        orderItemExample.or().andUserIdEqualTo(userId).andOrderIdIsNull();
        List<OrderItem> orderItemList = orderItemMapper.selectByExample(orderItemExample);
        setProduct(orderItemList);
        return orderItemList;
    }

    public void setProduct(List<OrderItem> orderItemList) {
        for (OrderItem orderitem : orderItemList) {
            setProduct(orderitem);
        }
    }

    public void setProduct(OrderItem orderitem) {
        Product product = productMapper.selectByPrimaryKey(orderitem.getProductId());
        orderitem.setProduct(product);
    }
}
