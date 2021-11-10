package com.sarige.tmall.comparator;

import com.sarige.tmall.pojo.Product;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class ProductAllComparator implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        return product2.getReviewCount() * product2.getSaleCount() - product1.getReviewCount() * product1.getSaleCount();
    }
}
