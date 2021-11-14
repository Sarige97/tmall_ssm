package com.sarige.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.sarige.tmall.comparator.*;
import com.sarige.tmall.pojo.*;
import com.sarige.tmall.service.*;
import com.sarige.tmall.util.UrlBuilder;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
public class ForeController {

    @Resource
    private CategoryService categoryService;
    @Resource
    private ProductService productService;
    @Resource
    private UserService userService;
    @Resource
    private OrderItemService orderItemService;
    @Resource
    private ProductImageService productImageService;
    @Resource
    private PropertyValueService propertyValueService;
    @Resource
    private ReviewService reviewService;
    @Resource
    private OrderService orderService;

    Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping("forehome")
    public String home(Model model, HttpSession session) {
        List<Category> categoryList = categoryService.list();
        productService.fill(categoryList);
        productService.fillByRow(categoryList);
        User user = (User) session.getAttribute("user");
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("user", user);
        return "fore/home";
    }

    @RequestMapping("foreregister")
    public String register(Model model, User user) {
        String name = user.getName();
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean exist = userService.isExist(name);

        if (exist) {
            String m = "用户名已经被使用,不能使用";
            model.addAttribute("msg", m);
            model.addAttribute("user", null);
            return "fore/register";
        }
        userService.add(user);

        return "redirect:registerSuccessPage";
    }

    @RequestMapping("forelogin")
    public String login(String name, String password, Model model, HttpSession session) {
        User user = userService.get(name, password);
        if (null == user) {
            model.addAttribute("msg", "账号密码错误");
            logger.debug("登录失败");
            return "fore/login";
        }
        logger.debug("登录成功");
        session.setAttribute("user", user);
        return "redirect:forehome";
    }

    @RequestMapping("forelogout")
    public String logout(HttpSession session) {
        User user = (User) session.getAttribute("user");
        session.removeAttribute("user");
        return "redirect:forehome";
    }

    @RequestMapping("foreproduct")
    public String product(int pid, Model model) {
        Product product = productService.get(pid);

        List<ProductImage> productSingleImages = productImageService.list(product.getId(), ProductImageService.type_single);
        List<ProductImage> productDetailImages = productImageService.list(product.getId(), ProductImageService.type_detail);
        int reviewCount = reviewService.getCount(product.getId());
        product.setProductSingleImageList(productSingleImages);
        product.setProductDetailImages(productDetailImages);
        product.setFirstProductImage(productSingleImages.get(0));
        logger.debug("累计评价：" + reviewCount);
        product.setReviewCount(reviewCount);
        List<PropertyValue> propertyValueList = propertyValueService.list(product.getId());
        List<Review> reviewList = reviewService.list(product.getId());
        productService.setSaleAndReviewNumber(product);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("product", product);
        model.addAttribute("propertyValueList", propertyValueList);
        return "fore/product";
    }

    @ResponseBody
    @RequestMapping("forecheckLogin")
    public String checkLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (null != user)
            return "success";
        return "fail";
    }

    @ResponseBody
    @RequestMapping("foreloginAjax")
    public String loginAjax(String name, String password, HttpSession session) {
        name = HtmlUtils.htmlEscape(name);
        User user = userService.get(name, password);
        if (null == user) {
            return "fail";
        }
        session.setAttribute("user", user);
        return "success";
    }

    @RequestMapping("forecategory")
    public String category(int cid, String sort, Model model) {
        Category category = categoryService.get(cid);
        productService.fill(category);
        productService.setSaleAndReviewNumber(category.getProductList());

        if (null != sort) {
            switch (sort) {
                case "review":
                    category.getProductList().sort(new ProductReviewComparator());
                    break;
                case "date":
                    category.getProductList().sort(new ProductDateComparator());
                    break;

                case "saleCount":
                    category.getProductList().sort(new ProductSaleCountComparator());
                    break;

                case "price":
                    category.getProductList().sort(new ProductPriceComparator());
                    break;

                case "all":
                    category.getProductList().sort(new ProductAllComparator());
                    break;
            }
        }

        model.addAttribute("category", category);
        return "fore/category";
    }

    @RequestMapping("foresearch")
    public String search(String keyword, Model model) {
        PageHelper.offsetPage(0, 20);
        List<Product> productList = productService.search(keyword);
        productService.setSaleAndReviewNumber(productList);
        model.addAttribute("productList", productList);
        return "fore/searchResult";
    }

    @RequestMapping("forebuyone")
    public String buyone(int productId, int num, HttpSession session) {
        Product product = productService.get(productId);
        int orderItemId = 0;

        User user = (User) session.getAttribute("user");
        boolean found = false;
        List<OrderItem> orderItemList = orderItemService.listByUserId(user.getId());
        for (OrderItem orderItem : orderItemList) {
            if (orderItem.getProduct().getId().intValue() == product.getId().intValue()) {
                orderItem.setNumber(orderItem.getNumber() + num);
                orderItemService.update(orderItem);
                found = true;
                orderItemId = orderItem.getId();
                break;
            }
        }

        if (!found) {
            OrderItem orderItem = new OrderItem();
            orderItem.setUserId(user.getId());
            orderItem.setNumber(num);
            orderItem.setProductId(productId);
            orderItemService.add(orderItem);
            orderItemId = orderItem.getId();
        }
        return "redirect:" + new UrlBuilder("forebuy").addParam("orderItemId", orderItemId);
    }

    @RequestMapping("forebuy")
    public String buy(Model model, String[] orderItemIds, HttpSession session) {
        List<OrderItem> orderItemList = new ArrayList<>();
        float total = 0;

        for (String strId : orderItemIds) {
            int id = Integer.parseInt(strId);
            OrderItem orderItem = orderItemService.get(id);
            total += orderItem.getProduct().getPromotePrice() * orderItem.getNumber();
            ProductImage firstProductImage = productImageService.list(orderItem.getProductId(), ProductImageService.type_single).get(0);
            orderItem.getProduct().setFirstProductImage(firstProductImage);
            orderItemList.add(orderItem);
        }

        session.setAttribute("orderItemList", orderItemList);
        model.addAttribute("total", total);
        return "fore/buy";
    }

    @ResponseBody
    @RequestMapping("foreaddCart")
    public String addCart(int pid, int num, Model model, HttpSession session) {
        Product p = productService.get(pid);
        User user = (User) session.getAttribute("user");
        boolean found = false;

        List<OrderItem> orderItemList = orderItemService.listByUserId(user.getId());
        for (OrderItem orderItem : orderItemList) {
            if (orderItem.getProduct().getId().intValue() == p.getId().intValue()) {
                orderItem.setNumber(orderItem.getNumber() + num);
                orderItemService.update(orderItem);
                found = true;
                break;
            }
        }

        if (!found) {
            OrderItem orderItem = new OrderItem();
            orderItem.setUserId(user.getId());
            orderItem.setNumber(num);
            orderItem.setProductId(pid);
            orderItemService.add(orderItem);
        }
        return "success";
    }

    @RequestMapping("forecart")
    public String cart(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<OrderItem> orderItemList = orderItemService.listByUserId(user.getId());
        for (OrderItem orderItem : orderItemList) {
            Product product = orderItem.getProduct();
            List<ProductImage> productImageList = productImageService.list(product.getId(), ProductImageService.type_single);
            product.setFirstProductImage(productImageList.get(0));
        }
        model.addAttribute("orderItemList", orderItemList);
        return "fore/cart";
    }

    @ResponseBody
    @RequestMapping("forechangeOrderItem")
    public String changeOrderItem(HttpSession session, int productId, int number) {
        User user = (User) session.getAttribute("user");
        if (null == user) return "fail";
        List<OrderItem> orderItemList = orderItemService.listByUserId(user.getId());
        for (OrderItem orderItem : orderItemList) {
            if (orderItem.getProduct().getId() == productId) {
                orderItem.setNumber(number);
                orderItemService.update(orderItem);
                break;
            }
        }
        return "success";
    }

    @RequestMapping("foredeleteOrderItem")
    @ResponseBody
    public String deleteOrderItem(Model model, HttpSession session, int orderItemId) {
        User user = (User) session.getAttribute("user");
        if (null == user)
            return "fail";
        orderItemService.delete(orderItemId);
        return "success";
    }

    @RequestMapping("forecreateOrder")
    public String createOrder(Model model, Order order, HttpSession session) {
        User user = (User) session.getAttribute("user");
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + RandomUtils.nextInt(10000);
        order.setOrderCode(orderCode);
        order.setCreateDate(new Date());
        order.setUserid(user.getId());
        order.setStatus(OrderService.waitPay);
        List<OrderItem> orderItemList = (List<OrderItem>) session.getAttribute("orderItemList");

        float total = orderService.add(order, orderItemList);
        return "redirect:" + new UrlBuilder("forealipay").addParam("orderId", order.getId()).addParam("total", total);
    }

    @RequestMapping("forepayed")
    public String payed(int orderId, float total, Model model) {
        Order order = orderService.get(orderId);
        order.setStatus(OrderService.waitDelivery);
        order.setPayDate(new Date());
        orderService.update(order);
        model.addAttribute("order", order);
        return "fore/payed";
    }

    @RequestMapping("forebought")
    public String bought(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Order> orderList = orderService.list(user.getId(), OrderService.delete);
        orderItemService.fill(orderList);
        for (Order order : orderList) {
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                Product product = orderItem.getProduct();
                ProductImage productImage = productImageService.list(product.getId(), ProductImageService.type_single).get(0);
                product.setFirstProductImage(productImage);
            }
        }
        model.addAttribute("orderList", orderList);
        return "fore/bought";
    }

    @RequestMapping("foreconfirmPay")
    public String confirmPay(Model model, int orderId) {
        Order order = orderService.get(orderId);
        orderItemService.fill(order);
        model.addAttribute("order", order);
        return "fore/confirmPay";
    }

    @RequestMapping("foreorderConfirmed")
    public String orderConfirmed(int orderId) {
        Order order = orderService.get(orderId);
        order.setStatus(OrderService.waitReview);
        order.setConfirmDate(new Date());
        orderService.update(order);
        return "fore/orderConfirmed";
    }

    @ResponseBody
    @RequestMapping("foredeleteOrder")
    public String deleteOrder(int orderId) {
        Order order = orderService.get(orderId);
        order.setStatus(OrderService.delete);
        orderService.update(order);
        return "success";
    }

    @RequestMapping("forereview")
    public String review(Model model, int orderId) {
        Order order = orderService.get(orderId);
        orderItemService.fill(order);
        Product product = order.getOrderItems().get(0).getProduct();
        ProductImage firstProductImage = productImageService.list(product.getId(), productImageService.type_single).get(0);
        product.setFirstProductImage(firstProductImage);
        List<Review> reviews = reviewService.list(product.getId());
        productService.setSaleAndReviewNumber(product);
        model.addAttribute("product", product);
        model.addAttribute("order", order);
        model.addAttribute("reviews", reviews);
        return "fore/review";
    }

    @RequestMapping("foredoreview")
    public String doreview(HttpSession session, int orderId, int productId, String content) {
        Order order = orderService.get(orderId);
        order.setStatus(OrderService.finish);
        orderService.update(order);
        content = HtmlUtils.htmlEscape(content);
        User user = (User) session.getAttribute("user");
        Review review = new Review();
        review.setContent(content);
        review.setProductId(productId);
        review.setCreateDate(new Date());
        review.setUserId(user.getId());
        reviewService.add(review);
//        return "redirect:forereview?orderId=" + orderId + "&showonly=true";
        return "redirect:" + new UrlBuilder("forereview").addParam("orderId", orderId).addParam("showonly", "true");
    }


}







