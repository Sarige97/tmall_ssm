package com.sarige.tmall.controller;

import cn.hutool.json.JSONUtil;
import com.sarige.tmall.pojo.Category;
import com.sarige.tmall.service.CategoryService;
import com.sarige.tmall.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Transactional
public class ForeController {

    @Resource
    CategoryService categoryService;
    @Resource
    ProductService productService;

    @RequestMapping("forehome")
    public String home(Model model) {
        List<Category> categoryList = categoryService.list();
        productService.fill(categoryList);
        productService.fillByRow(categoryList);
        model.addAttribute("categoryList", categoryList);
        System.out.println(categoryList.size());
        return "fore/home";
    }
}
