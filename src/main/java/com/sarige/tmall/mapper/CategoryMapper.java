package com.sarige.tmall.mapper;

import com.sarige.tmall.pojo.Category;
import com.sarige.tmall.util.Page;

import java.util.List;

public interface CategoryMapper {

    List<Category> list();

    void add(Category category);

    void delete(int id);

    Category get(int id);

    void update(Category category);
}
