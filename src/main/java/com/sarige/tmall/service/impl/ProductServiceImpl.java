package com.sarige.tmall.service.impl;

import com.sarige.tmall.mapper.CategoryMapper;
import com.sarige.tmall.mapper.ProductMapper;
import com.sarige.tmall.mapper.ProductimageMapper;
import com.sarige.tmall.pojo.Category;
import com.sarige.tmall.pojo.Product;
import com.sarige.tmall.pojo.Productimage;
import com.sarige.tmall.service.ProductService;
import com.sarige.tmall.util.example.ProductExample;
import com.sarige.tmall.util.example.ProductimageExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductMapper productMapper;
    @Resource
    ProductimageMapper productimageMapper;
    @Resource
    CategoryMapper categoryMapper;

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
        Product product = productMapper.selectByPrimaryKey(id);
        Category category = categoryMapper.selectByPrimaryKey(product.getCid());
        product.setCategory(category);
        return product;
    }

    @Override
    public List<Product> list(int cid) {
        ProductExample productExample = new ProductExample();
        productExample.or().andCidEqualTo(cid);
        List<Product> productList = productMapper.selectByExample(productExample);
        setProductImage(productList);
        return productList;
    }

    public void setProductImage(Product product) {
        ProductimageExample productimageExample = new ProductimageExample();
        productimageExample.or().andPidEqualTo(product.getId());
        List<Productimage> productImageList = productimageMapper.selectByExample(productimageExample);
        if (!productImageList.isEmpty()) {
            product.setFirstImage(productImageList.get(0));
        }
    }

    public void setProductImage(List<Product> productList) {
        for (Product product : productList) {
            setProductImage(product);
        }
    }
}
