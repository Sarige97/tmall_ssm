package com.sarige.tmall.service.impl;

import com.sarige.tmall.mapper.ReviewMapper;
import com.sarige.tmall.mapper.UserMapper;
import com.sarige.tmall.pojo.Review;
import com.sarige.tmall.pojo.User;
import com.sarige.tmall.service.ReviewService;
import com.sarige.tmall.service.UserService;
import com.sarige.tmall.util.example.ReviewExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Resource
    ReviewMapper reviewMapper;
    @Resource
    UserService userService;

    @Override
    public void add(Review review) {
        reviewMapper.insert(review);
    }

    @Override
    public void delete(int id) {
        reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Review review) {
        reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public Review get(int id) {
        return reviewMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Review> list(int productId) {
        ReviewExample reviewExample = new ReviewExample();
        reviewExample.or().andProductIdEqualTo(productId);
        List<Review> reviewList = reviewMapper.selectByExample(reviewExample);
        setUser(reviewList);
        return reviewList;
    }

    private void setUser(List<Review> reviewList) {
        for (Review review : reviewList) {
            setUser(review);
        }
    }

    private void setUser(Review review) {
        Integer userId = review.getUserId();
        User user = userService.get(userId);
        review.setUser(user);
    }

    @Override
    public int getCount(int productId) {
        return 0;
    }
}
