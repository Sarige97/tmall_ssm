package com.sarige.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sarige.tmall.pojo.Category;
import com.sarige.tmall.pojo.Product;
import com.sarige.tmall.service.CategoryService;
import com.sarige.tmall.service.ProductService;
import com.sarige.tmall.util.Page;
import com.sarige.tmall.util.UrlBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
public class ProductController {

    @Resource
    CategoryService categoryService;
    @Resource
    ProductService productService;

    @RequestMapping("admin_product_add")
    public String add(Product product) {
        System.out.println(product);
        product.setCreateDate(new Date());
        productService.add(product);
        System.out.println(1 / 0);
        productService.add(product);
        return "redirect:" + new UrlBuilder("admin_product_list").addParam("cid", product.getCategoryId());
    }

    @RequestMapping("admin_product_delete")
    public String delete(int id) {
        Product product = productService.get(id);
        productService.delete(id);
        return "redirect:" + new UrlBuilder("admin_product_list").addParam("cid", product.getCategoryId());
    }

    @RequestMapping("admin_product_edit")
    public String edit(Model model, int id) {
        Product product = productService.get(id);
        product.setCategory(categoryService.get(product.getCategoryId()));
        model.addAttribute("product", product);
        return "admin/editProduct";
    }

    @RequestMapping("admin_product_update")
    public String update(Product product) {
        productService.update(product);
        return "redirect:" + new UrlBuilder("admin_product_list").addParam("cid", product.getCategoryId());
    }

    @RequestMapping("admin_product_list")
    public String list(int cid, Model model, Page page) {
        Category category = categoryService.get(cid);
        List<Product> productList = productService.list(cid);
        PageHelper.offsetPage(page.getStart(), page.getCount());
        int total = (int) new PageInfo<>(productList).getTotal();
        page.setTotal(total);
        model.addAttribute("productList", productList);
        model.addAttribute("category", category);
        model.addAttribute("page", page);
        return "admin/listProduct";
    }
}
