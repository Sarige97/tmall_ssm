package com.sarige.tmall.pojo;

public class PropertyValue {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column propertyvalue.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column propertyvalue.productId
     *
     * @mbg.generated
     */
    private Integer productId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column propertyvalue.propertyId
     *
     * @mbg.generated
     */
    private Integer propertyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column propertyvalue.value
     *
     * @mbg.generated
     */
    private String value;

    private Product product;

    private Property property;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column propertyvalue.id
     *
     * @return the value of propertyvalue.id
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column propertyvalue.id
     *
     * @param id the value for propertyvalue.id
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column propertyvalue.productId
     *
     * @return the value of propertyvalue.productId
     * @mbg.generated
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column propertyvalue.productId
     *
     * @param productId the value for propertyvalue.productId
     * @mbg.generated
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column propertyvalue.propertyId
     *
     * @return the value of propertyvalue.propertyId
     * @mbg.generated
     */
    public Integer getPropertyId() {
        return propertyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column propertyvalue.propertyId
     *
     * @param propertyId the value for propertyvalue.propertyId
     * @mbg.generated
     */
    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column propertyvalue.value
     *
     * @return the value of propertyvalue.value
     * @mbg.generated
     */
    public String getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column propertyvalue.value
     *
     * @param value the value for propertyvalue.value
     * @mbg.generated
     */
    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}