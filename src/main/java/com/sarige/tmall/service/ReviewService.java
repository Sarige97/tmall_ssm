package com.sarige.tmall.service;

import com.sarige.tmall.pojo.Review;

import java.util.List;

public interface ReviewService {

    void add(Review review);

    void delete(int id);

    void update(Review review);

    Review get(int id);

    List<Review> list(int productId);

    int getCount(int productId);

}
