package com.sarige.tmall.service.impl;

import com.sarige.tmall.mapper.ProductimageMapper;
import com.sarige.tmall.pojo.Productimage;
import com.sarige.tmall.service.ProductImageService;
import com.sarige.tmall.util.example.ProductimageExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Resource
    ProductimageMapper productimageMapper;

    @Override
    public void add(Productimage productimage) {
        productimageMapper.insert(productimage);
    }

    @Override
    public void delete(int id) {
        productimageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Productimage productimage) {
        productimageMapper.updateByPrimaryKey(productimage);
    }

    @Override
    public Productimage get(int id) {
        return productimageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Productimage> list(int productID, String type) {
        ProductimageExample productimageExample = new ProductimageExample();
        productimageExample.or().andPidEqualTo(productID).andTypeEqualTo(type);
        productimageExample.setOrderByClause("id desc");
        return productimageMapper.selectByExample(new ProductimageExample());
    }
}
