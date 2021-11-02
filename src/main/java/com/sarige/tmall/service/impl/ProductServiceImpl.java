package com.sarige.tmall.service.impl;

import com.sarige.tmall.mapper.CategoryMapper;
import com.sarige.tmall.mapper.ProductImageMapper;
import com.sarige.tmall.mapper.ProductMapper;
import com.sarige.tmall.pojo.Category;
import com.sarige.tmall.pojo.Product;
import com.sarige.tmall.pojo.ProductImage;
import com.sarige.tmall.service.ProductService;
import com.sarige.tmall.util.example.ProductExample;
import com.sarige.tmall.util.example.ProductImageExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductMapper productMapper;
    @Resource
    ProductImageMapper productimageMapper;
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
        Category category = categoryMapper.selectByPrimaryKey(product.getCategoryId());
        product.setCategory(category);
        return product;
    }

    @Override
    public List<Product> list(int categoryId) {
        ProductExample productExample = new ProductExample();
        productExample.or().andCategoryIdEqualTo(categoryId);
        List<Product> productList = productMapper.selectByExample(productExample);
        setProductImage(productList);
        return productList;
    }

    public void setProductImage(Product product) {
        ProductImageExample productimageExample = new ProductImageExample();
        productimageExample.or().andProductIdEqualTo(product.getId());
        List<ProductImage> productImageList = productimageMapper.selectByExample(productimageExample);
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
