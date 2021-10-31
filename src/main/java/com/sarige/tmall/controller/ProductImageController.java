package com.sarige.tmall.controller;

import com.sarige.tmall.pojo.Product;
import com.sarige.tmall.pojo.Productimage;
import com.sarige.tmall.service.ProductImageService;
import com.sarige.tmall.service.ProductService;
import com.sarige.tmall.util.FileUtil;
import com.sarige.tmall.util.ImageUtil;
import com.sarige.tmall.util.UrlBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProductImageController {

    @Resource
    ProductService productService;
    @Resource
    ProductImageService productImageService;
    Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping("admin_productImage_add")
    public String add(Productimage productimage, Model model, HttpSession session, MultipartFile image) {
        productImageService.add(productimage);
        String fileName = productimage.getId() + ".jpg";
        String imageFolder;
        String imageFolder_small = null;
        String imageFolder_middle = null;
        if (ProductImageService.type_single.equals(productimage.getType())) {
            imageFolder = FileUtil.getRealPath(session, "img/productSingle");
            imageFolder_small = FileUtil.getRealPath(session, "img/productSingle_small");
            imageFolder_middle = FileUtil.getRealPath(session, "img/productSingle_middle");
        } else {
            imageFolder = FileUtil.getRealPath(session, "img/productDetail");
        }
        File file = new File(imageFolder, fileName);
        try {
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            assert img != null;
            ImageIO.write(img, "jpg", file);
            if (ProductImageService.type_single.equals(productimage.getType())) {
                File file_small = new File(imageFolder_small, fileName);
                File file_middle = new File(imageFolder_middle, fileName);
                ImageUtil.resizeImage(file, 56, 56, file_small);
                ImageUtil.resizeImage(file, 217, 190, file_middle);
            }
        } catch (Exception e) {
            logger.error("上传文件发生错误", e);
        }
        return "redirect:" + new UrlBuilder("admin_productImage_list").addParam("productId", productimage.getPid());
    }

    @RequestMapping("admin_productImage_delete")
    public String delete(int productImageId, HttpSession session) {
        Productimage productimage = productImageService.get(productImageId);
        String fileName = productimage.getId() + ".jpg";
        String imageFolder;
        String imageFolder_small = null;
        String imageFolder_middle = null;
        if (ProductImageService.type_single.equals(productimage.getType())) {
            imageFolder = FileUtil.getRealPath(session, "img/productSingle");
            imageFolder_small = FileUtil.getRealPath(session, "img/productSingle_small");
            imageFolder_middle = FileUtil.getRealPath(session, "img/productSingle_middle");
            File imageFile = new File(imageFolder, fileName);
            File imageFile_small = new File(imageFolder_small, fileName);
            File imageFile_middle = new File(imageFolder_middle, fileName);
            imageFile.delete();
            imageFile_small.delete();
            imageFile_middle.delete();
        } else {
            imageFolder = FileUtil.getRealPath(session, "img/productDetail");
            File imageFile = new File(imageFolder, fileName);
            imageFile.delete();
        }
        productImageService.delete(productImageId);
        return "redirect:" + new UrlBuilder("admin_productImage_list").addParam("productId", productimage.getPid());
    }

    @RequestMapping("admin_productImage_list")
    public String list(int productId, Model model) {
        Product product = productService.get(productId);
        List<Productimage> productImagesSingleList = productImageService.list(productId, ProductImageService.type_single);
        List<Productimage> productImagesDetailList = productImageService.list(productId, ProductImageService.type_detail);
        model.addAttribute("product", product);
        model.addAttribute("productImagesSingleList", productImagesSingleList);
        model.addAttribute("productImagesDetailList", productImagesDetailList);
        return "admin/listProductImage";
    }

}
