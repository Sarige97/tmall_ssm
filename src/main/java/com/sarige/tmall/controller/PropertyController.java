package com.sarige.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sarige.tmall.pojo.Category;
import com.sarige.tmall.pojo.Property;
import com.sarige.tmall.service.CategoryService;
import com.sarige.tmall.service.PropertyService;
import com.sarige.tmall.util.Page;
import com.sarige.tmall.util.UrlBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Transactional
public class PropertyController {

    @Resource
    CategoryService categoryService;

    @Resource
    PropertyService propertyService;

    @RequestMapping("admin_property_add")
    public String add(Property property) {
        System.out.println(property);
        propertyService.add(property);
        return "redirect:" + new UrlBuilder("admin_property_list").addParam("categoryId", property.getCategoryId());
    }

    @RequestMapping("admin_property_delete")
    public String delete(int id) {
        Integer categoryId = propertyService.get(id).getCategoryId();
        propertyService.delete(id);
        return "redirect:" + new UrlBuilder("admin_property_list").addParam("categoryId", categoryId);
    }

    @RequestMapping("admin_property_edit")
    public String edit(Model model, int id) {
        Property property = propertyService.get(id);
        Category category = categoryService.get(property.getCategoryId());
        property.setCategory(category);
        model.addAttribute("property", property);
        return "admin/editProperty";
    }

    @RequestMapping("admin_property_update")
    public String update(Property property) {
        propertyService.update(property);
        return "redirect:" + new UrlBuilder("admin_property_list").addParam("categoryId", property.getCategoryId());
    }

    @RequestMapping("admin_property_list")
    public String list(int categoryId, Model model, Page page) {
        Category category = categoryService.get(categoryId);
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Property> propertyList = propertyService.list(categoryId);
        int total = (int) new PageInfo<>(propertyList).getTotal();
        page.setTotal(total);
        page.setParam("&cid=" + category.getId());
        model.addAttribute("category", category);
        model.addAttribute("propertyList", propertyList);
        model.addAttribute("page", page);
        return "admin/listProperty";
    }

}
