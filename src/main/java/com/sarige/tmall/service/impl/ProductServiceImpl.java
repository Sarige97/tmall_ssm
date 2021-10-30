package com.sarige.tmall.service.impl;

import com.sarige.tmall.mapper.ProductMapper;
import com.sarige.tmall.pojo.Product;
import com.sarige.tmall.service.ProductService;
import com.sarige.tmall.util.example.ProductExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductMapper productMapper;

    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKey(product);
    }

    @Override
    public Product get(int id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> list(int cid) {
        ProductExample productExample = new ProductExample();
        productExample.or().andCidEqualTo(cid);
        return productMapper.selectByExample(productExample);
    }
}
