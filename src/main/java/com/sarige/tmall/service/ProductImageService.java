package com.sarige.tmall.service;

import com.sarige.tmall.pojo.Productimage;

import java.util.ArrayList;
import java.util.List;

public interface ProductImageService {

    String type_single = "type_single";
    String type_detail = "type_detail";

    void add(Productimage productimage);

    void delete(int id);

    void update(Productimage productimage);

    Productimage get(int id);

    List<Productimage> list(int productID, String type);

}
