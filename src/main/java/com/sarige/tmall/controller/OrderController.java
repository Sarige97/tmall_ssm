package com.sarige.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sarige.tmall.pojo.Order;
import com.sarige.tmall.pojo.OrderItem;
import com.sarige.tmall.pojo.Product;
import com.sarige.tmall.pojo.ProductImage;
import com.sarige.tmall.service.OrderItemService;
import com.sarige.tmall.service.OrderService;
import com.sarige.tmall.service.ProductImageService;
import com.sarige.tmall.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
public class OrderController {
    @Resource
    OrderService orderService;
    @Resource
    OrderItemService orderItemService;
    @Resource
    ProductImageService productImageService;

    @RequestMapping("admin_order_list")
    public String list(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(), page.getCount());

        List<Order> orderList = orderService.list();

        int total = (int) new PageInfo<>(orderList).getTotal();
        page.setTotal(total);

        orderItemService.fill(orderList);
        for (Order order : orderList) {
            List<OrderItem> orderItemList = order.getOrderItems();
            for (OrderItem orderItem : orderItemList) {
                Product product = orderItem.getProduct();
                ProductImage productImage = productImageService.list(product.getId(), productImageService.type_single).get(0);
                product.setFirstProductImage(productImage);
            }
        }

        model.addAttribute("orderList", orderList);
        model.addAttribute("page", page);

        return "admin/listOrder";
    }

    @RequestMapping("admin_order_delivery")
    public String delivery(Order order) throws IOException {
        order.setDeliveryDate(new Date());
        order.setStatus(OrderService.waitConfirm);
        orderService.update(order);
        return "redirect:admin_order_list";
    }
}
