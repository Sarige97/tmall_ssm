package com.sarige.tmall.service.impl;

import com.sarige.tmall.mapper.ProductImageMapper;
import com.sarige.tmall.pojo.ProductImage;
import com.sarige.tmall.service.ProductImageService;
import com.sarige.tmall.util.example.ProductImageExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Resource
    ProductImageMapper productimageMapper;

    @Override
    public void add(ProductImage productimage) {
        productimageMapper.insert(productimage);
    }

    @Override
    public void delete(int id) {
        productimageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ProductImage productimage) {
        productimageMapper.updateByPrimaryKey(productimage);
    }

    @Override
    public ProductImage get(int id) {
        return productimageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductImage> list(int productID, String type) {
        ProductImageExample productimageExample = new ProductImageExample();
        productimageExample.or().andProductIdEqualTo(productID).andTypeEqualTo(type);
        productimageExample.setOrderByClause("id desc");
        return productimageMapper.selectByExample(productimageExample);
    }
}
