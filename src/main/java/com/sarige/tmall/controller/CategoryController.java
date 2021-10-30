package com.sarige.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sarige.tmall.pojo.Category;
import com.sarige.tmall.service.CategoryService;
import com.sarige.tmall.util.ImageUtil;
import com.sarige.tmall.util.Page;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class CategoryController {

    @Resource
    CategoryService categoryService;
    Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping("admin_category_list")
    public String list(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Category> categoryList = categoryService.list();
        int total = (int) new PageInfo<>(categoryList).getTotal();
        page.setTotal(total);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("page", page);
        return "admin/listCategory";
    }

    @RequestMapping("admin_category_add")
    public String add(Category category, HttpSession session, MultipartFile image) throws IOException {
        categoryService.add(category);
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, category.getId() + ".jpg");
        if (!imageFolder.exists()) {
            boolean mkdirs = imageFolder.mkdirs();
            logger.info("新建分类图片文件夹" + (mkdirs ? "成功" : "失败"));
        }
        image.transferTo(file);
        BufferedImage bufferedImage = ImageUtil.change2jpg(file);
        assert bufferedImage != null;
        ImageIO.write(bufferedImage, "jpg", file);
        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_delete")
    public String delete(int id, HttpSession session) {
        String filePath = session.getServletContext().getRealPath("img/category/") + id + ".jpg";
        File img = new File(filePath);
        boolean delete = img.delete();
        logger.info("删除图片(id:" + id + ")" + (delete ? "成功" : "失败"));
        categoryService.delete(id);
        return "redirect:/admin_category_list";
    }

    @RequestMapping("admin_category_edit")
    public String edit(int id, Model model) {
        Category category = categoryService.get(id);
        model.addAttribute("category", category);
        return "admin/editCategory";
    }

    @RequestMapping("admin_category_update")
    public String update(Category category, HttpSession session, MultipartFile image) throws IOException {
        categoryService.update(category);
        if (!image.isEmpty()) {
            String imgPath = session.getServletContext().getRealPath("img/category/") + category.getId() + ".jpg";
            File imgFile = new File(imgPath);
            image.transferTo(imgFile);
            BufferedImage bufferedImage = ImageUtil.change2jpg(imgFile);
            assert bufferedImage != null;
            ImageIO.write(bufferedImage, "jpg", imgFile);
        }
        return "admin/editCategory";
    }

}
