package com.sarige.tmall.service;

import com.sarige.tmall.pojo.Product;
import com.sarige.tmall.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {
    void init(Product product);

    void update(PropertyValue propertyValue);

    PropertyValue get(int productId, int propertyId);

    List<PropertyValue> list(int productId);
}
