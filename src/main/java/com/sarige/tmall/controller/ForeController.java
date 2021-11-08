package com.sarige.tmall.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.sarige.tmall.pojo.*;
import com.sarige.tmall.service.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
    private ProductImageService productImageService;
    @Resource
    private PropertyValueService propertyValueService;
    @Resource
    private ReviewService reviewService;

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
        logger.debug("name=" + name);
        logger.debug("password=" + password);
        User user = userService.get(name, password);
        logger.debug("user=" + user);
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
        logger.debug(user.getName() + "注销");
        session.removeAttribute("user");
        return "redirect:forehome";
    }

    @RequestMapping("foreproduct")
    public String product(int pid, Model model) {
        Product product = productService.get(pid);

        List<ProductImage> productSingleImages = productImageService.list(product.getId(), ProductImageService.type_single);
        List<ProductImage> productDetailImages = productImageService.list(product.getId(), ProductImageService.type_detail);
        product.setProductSingleImageList(productSingleImages);
        product.setProductDetailImages(productDetailImages);
        product.setFirstProductImage(productSingleImages.get(0));
        List<PropertyValue> propertyValueList = propertyValueService.list(product.getId());
        List<Review> reviewList = reviewService.list(product.getId());
        productService.setSaleAndReviewNumber(product);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("product", product);
        model.addAttribute("propertyValueList", propertyValueList);
        logger.debug(JSONUtil.parseObj(product));
        return "fore/product";
    }
}


