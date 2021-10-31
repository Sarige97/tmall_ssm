package com.sarige.tmall.service.impl;

import com.sarige.tmall.mapper.OrderitemMapper;
import com.sarige.tmall.mapper.ProductMapper;
import com.sarige.tmall.pojo.Order;
import com.sarige.tmall.pojo.Orderitem;
import com.sarige.tmall.pojo.Product;
import com.sarige.tmall.service.OrderItemService;
import com.sarige.tmall.service.ProductService;
import com.sarige.tmall.util.example.OrderitemExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Resource
    OrderitemMapper orderItemMapper;
    @Resource
    ProductMapper productMapper;

    @Override
    public void add(Orderitem orderItem) {
        orderItemMapper.insert(orderItem);
    }

    @Override
    public void delete(int orderItemId) {
        orderItemMapper.deleteByPrimaryKey(orderItemId);
    }

    @Override
    public void update(Orderitem orderItem) {
        orderItemMapper.updateByPrimaryKeySelective(orderItem);
    }

    @Override
    public Orderitem get(int orderItemId) {
        Orderitem orderitem = orderItemMapper.selectByPrimaryKey(orderItemId);
        setProduct(orderitem);
        return orderitem;
    }

    @Override
    public List<Orderitem> list() {
        OrderitemExample example = new OrderitemExample();
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
        OrderitemExample orderitemExample = new OrderitemExample();
        orderitemExample.or().andOidEqualTo(order.getId());
        List<Orderitem> orderItemList = orderItemMapper.selectByExample(orderitemExample);
        setProduct(orderItemList);

        float total = 0;
        int totalNumber = 0;
        for (Orderitem orderItem : orderItemList) {
            total += orderItem.getProduct().getPromoteprice();
            totalNumber += orderItem.getNumber();
        }
        order.setTotal(total);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(orderItemList);
    }

    public void setProduct(List<Orderitem> orderItemList) {
        for (Orderitem orderitem : orderItemList) {
            setProduct(orderitem);
        }
    }

    public void setProduct(Orderitem orderitem) {
        Product product = productMapper.selectByPrimaryKey(orderitem.getPid());
        orderitem.setProduct(product);
    }
}
