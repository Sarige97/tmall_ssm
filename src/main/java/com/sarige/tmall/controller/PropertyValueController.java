package com.sarige.tmall.controller;

import com.sarige.tmall.pojo.Category;
import com.sarige.tmall.pojo.Product;
import com.sarige.tmall.pojo.Propertyvalue;
import com.sarige.tmall.service.CategoryService;
import com.sarige.tmall.service.ProductService;
import com.sarige.tmall.service.PropertyValueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PropertyValueController {

    @Resource
    PropertyValueService propertyValueService;
    @Resource
    ProductService productService;
    @Resource
    CategoryService categoryService;

    @RequestMapping("admin_propertyValue_edit")
    public String edit(Model model, int productId) {
        Product product = productService.get(productId);
        Category category = categoryService.get(product.getCid());
        product.setCategory(category);
        propertyValueService.init(product);
        List<Propertyvalue> propertyValueList = propertyValueService.list(productId);
        model.addAttribute("product", product);
        model.addAttribute("propertyValueList", propertyValueList);
        return "admin/editPropertyValue";
    }

    @ResponseBody
    @RequestMapping("admin_propertyValue_update")
    public String update(Propertyvalue propertyValue) {
        propertyValueService.update(propertyValue);
        return "success";
    }

}