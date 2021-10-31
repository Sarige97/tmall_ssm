package com.sarige.tmall.service;

import com.sarige.tmall.pojo.Product;
import com.sarige.tmall.pojo.Propertyvalue;

import java.util.List;

public interface PropertyValueService {
    void init(Product product);

    void update(Propertyvalue propertyValue);

    Propertyvalue get(int productId, int propertyId);

    List<Propertyvalue> list(int productId);
}
