package com.sarige.tmall.service;

import com.sarige.tmall.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {

    String type_single = "type_single";
    String type_detail = "type_detail";

    void add(ProductImage productimage);

    void delete(int id);

    void update(ProductImage productimage);

    ProductImage get(int id);

    List<ProductImage> list(int productID, String type);

}
