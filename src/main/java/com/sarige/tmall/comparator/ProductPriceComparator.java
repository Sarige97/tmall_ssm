package com.sarige.tmall.comparator;

import com.sarige.tmall.pojo.Product;

import java.util.Comparator;

public class ProductPriceComparator  implements Comparator<Product> {
    @Override
    public int compare(Product product1, Product product2) {
        return (int) (product1.getPromotePrice()-product2.getPromotePrice());
    }
}
