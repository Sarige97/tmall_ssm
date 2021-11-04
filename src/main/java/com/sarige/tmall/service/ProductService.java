package com.sarige.tmall.service;

import com.sarige.tmall.pojo.Category;
import com.sarige.tmall.pojo.Product;

import java.util.List;

public interface ProductService {
    void add(Product product);

    void delete(int id);

    void update(Product product);

    Product get(int id);

    List<Product> list(int cid);

    public void fill(List<Category> categoryList);

    public void fill(Category category);

    public void fillByRow(List<Category> categoryList);
}
