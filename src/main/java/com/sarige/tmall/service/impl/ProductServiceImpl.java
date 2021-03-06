package com.sarige.tmall.service.impl;

import com.sarige.tmall.mapper.CategoryMapper;
import com.sarige.tmall.mapper.ProductImageMapper;
import com.sarige.tmall.mapper.ProductMapper;
import com.sarige.tmall.pojo.Category;
import com.sarige.tmall.pojo.Product;
import com.sarige.tmall.pojo.ProductImage;
import com.sarige.tmall.service.OrderItemService;
import com.sarige.tmall.service.ProductService;
import com.sarige.tmall.service.ReviewService;
import com.sarige.tmall.util.example.ProductExample;
import com.sarige.tmall.util.example.ProductImageExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductMapper productMapper;
    @Resource
    ProductImageMapper productimageMapper;
    @Resource
    CategoryMapper categoryMapper;
    @Resource
    OrderItemService orderItemService;
    @Resource
    ReviewService reviewService;

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

    @Override
    public void fill(List<Category> categoryList) {
        for (Category category : categoryList) {
            fill(category);
        }
    }

    @Override
    public void fill(Category category) {
        List<Product> productList = list(category.getId());
        category.setProductList(productList);
    }

    @Override
    public void fillByRow(List<Category> categoryList) {
        int productNumberEachRow = 8;
        for (Category category : categoryList) {
            List<Product> products = category.getProductList();
            List<List<Product>> productsByRow = new ArrayList<>();
            for (int i = 0; i < products.size(); i += productNumberEachRow) {
                int size = i + productNumberEachRow;
                size = Math.min(size, products.size());
                List<Product> productsOfEachRow = products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            category.setProductListByRow(productsByRow);
        }
    }

    @Override
    public void setSaleAndReviewNumber(Product product) {
        int saleCount = orderItemService.getSaleCount(product.getId());
        product.setSaleCount(saleCount);
        int reviewCount = reviewService.getCount(product.getId());
        product.setReviewCount(reviewCount);
    }

    @Override
    public void setSaleAndReviewNumber(List<Product> productList) {
        for (Product product : productList) {
            setSaleAndReviewNumber(product);
        }
    }

    @Override
    public List<Product> search(String keyword) {
        ProductExample example = new ProductExample();
        example.createCriteria().andNameLike("%" + keyword + "%");
        example.setOrderByClause("id desc");
        List<Product> result = productMapper.selectByExample(example);
        setProductImage(result);
        setCategory(result);
        return result;
    }

    public void setProductImage(Product product) {
        ProductImageExample productimageExample = new ProductImageExample();
        productimageExample.or().andProductIdEqualTo(product.getId());
        List<ProductImage> productImageList = productimageMapper.selectByExample(productimageExample);
        if (!productImageList.isEmpty()) {
            product.setFirstProductImage(productImageList.get(0));
        }
    }

    public void setProductImage(List<Product> productList) {
        for (Product product : productList) {
            setProductImage(product);
        }
    }

    public void setCategory(List<Product> productList) {
        for (Product product : productList) {
            setCategory(product);
        }
    }

    public void setCategory(Product product) {
        Integer categoryId = product.getCategoryId();
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        product.setCategory(category);
    }


}
