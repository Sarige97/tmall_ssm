package com.sarige.tmall.pojo;

import java.util.List;

public class Category {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column category.name
     *
     * @mbg.generated
     */
    private String name;

    private List<Product> productList;

    private List<List<Product>> productListByRow;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.id
     *
     * @return the value of category.id
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.id
     *
     * @param id the value for category.id
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column category.name
     *
     * @return the value of category.name
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column category.name
     *
     * @param name the value for category.name
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<List<Product>> getProductListByRow() {
        return productListByRow;
    }

    public void setProductListByRow(List<List<Product>> productListByRow) {
        this.productListByRow = productListByRow;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}