package com.sarige.tmall.pojo;

public class OrderItem {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orderitem.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orderitem.productId
     *
     * @mbg.generated
     */
    private Integer productId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orderitem.orderId
     *
     * @mbg.generated
     */
    private Integer orderId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orderitem.userId
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column orderitem.number
     *
     * @mbg.generated
     */
    private Integer number;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orderitem.id
     *
     * @return the value of orderitem.id
     *
     * @mbg.generated
     */

    private Product product;


    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orderitem.id
     *
     * @param id the value for orderitem.id
     *
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orderitem.productId
     *
     * @return the value of orderitem.productId
     *
     * @mbg.generated
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orderitem.productId
     *
     * @param productId the value for orderitem.productId
     *
     * @mbg.generated
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orderitem.orderId
     *
     * @return the value of orderitem.orderId
     *
     * @mbg.generated
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orderitem.orderId
     *
     * @param orderId the value for orderitem.orderId
     *
     * @mbg.generated
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orderitem.userId
     *
     * @return the value of orderitem.userId
     *
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orderitem.userId
     *
     * @param userId the value for orderitem.userId
     *
     * @mbg.generated
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column orderitem.number
     *
     * @return the value of orderitem.number
     *
     * @mbg.generated
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column orderitem.number
     *
     * @param number the value for orderitem.number
     *
     * @mbg.generated
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", productId=" + productId +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", number=" + number +
                ", product=" + product +
                '}';
    }
}