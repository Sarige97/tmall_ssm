package com.sarige.tmall.controller;

import cn.hutool.json.JSONUtil;
import com.sarige.tmall.pojo.Category;
import com.sarige.tmall.pojo.User;
import com.sarige.tmall.service.CategoryService;
import com.sarige.tmall.service.ProductService;
import com.sarige.tmall.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
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

    private final Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping("forehome")
    public String home(Model model) {
        List<Category> categoryList = categoryService.list();
        productService.fill(categoryList);
        productService.fillByRow(categoryList);
        model.addAttribute("categoryList", categoryList);
        logger.info(JSONUtil.parseArray(categoryList));
        return "fore/home";
//        return "include/fore/home/categoryMenu";
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
}
