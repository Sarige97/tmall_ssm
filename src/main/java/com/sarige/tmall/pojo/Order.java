package com.sarige.tmall.pojo;

import com.sarige.tmall.service.OrderService;

import java.util.Date;
import java.util.List;

public class Order {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_.orderCode
     *
     * @mbg.generated
     */
    private String orderCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_.address
     *
     * @mbg.generated
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_.post
     *
     * @mbg.generated
     */
    private String post;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_.receiver
     *
     * @mbg.generated
     */
    private String receiver;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_.mobile
     *
     * @mbg.generated
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_.userMessage
     *
     * @mbg.generated
     */
    private String userMessage;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_.createDate
     *
     * @mbg.generated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_.payDate
     *
     * @mbg.generated
     */
    private Date payDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_.deliveryDate
     *
     * @mbg.generated
     */
    private Date deliveryDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_.confirmDate
     *
     * @mbg.generated
     */
    private Date confirmDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_.userId
     *
     * @mbg.generated
     */
    private Integer userid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column order_.status
     *
     * @mbg.generated
     */
    private String status;

    private List<OrderItem> orderItems;

    private User user;

    private float total;//总金额

    private int totalNumber;//订单总数

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_.id
     *
     * @return the value of order_.id
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_.id
     *
     * @param id the value for order_.id
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_.orderCode
     *
     * @return the value of order_.orderCode
     * @mbg.generated
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_.orderCode
     *
     * @param orderCode the value for order_.orderCode
     * @mbg.generated
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_.address
     *
     * @return the value of order_.address
     * @mbg.generated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_.address
     *
     * @param address the value for order_.address
     * @mbg.generated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_.post
     *
     * @return the value of order_.post
     * @mbg.generated
     */
    public String getPost() {
        return post;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_.post
     *
     * @param post the value for order_.post
     * @mbg.generated
     */
    public void setPost(String post) {
        this.post = post == null ? null : post.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_.receiver
     *
     * @return the value of order_.receiver
     * @mbg.generated
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_.receiver
     *
     * @param receiver the value for order_.receiver
     * @mbg.generated
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver == null ? null : receiver.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_.mobile
     *
     * @return the value of order_.mobile
     * @mbg.generated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_.mobile
     *
     * @param mobile the value for order_.mobile
     * @mbg.generated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_.userMessage
     *
     * @return the value of order_.userMessage
     * @mbg.generated
     */
    public String getUserMessage() {
        return userMessage;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_.userMessage
     *
     * @param userMessage the value for order_.userMessage
     * @mbg.generated
     */
    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage == null ? null : userMessage.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_.createDate
     *
     * @return the value of order_.createDate
     * @mbg.generated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_.createDate
     *
     * @param createDate the value for order_.createDate
     * @mbg.generated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_.payDate
     *
     * @return the value of order_.payDate
     * @mbg.generated
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_.payDate
     *
     * @param payDate the value for order_.payDate
     * @mbg.generated
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_.deliveryDate
     *
     * @return the value of order_.deliveryDate
     * @mbg.generated
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_.deliveryDate
     *
     * @param deliveryDate the value for order_.deliveryDate
     * @mbg.generated
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_.confirmDate
     *
     * @return the value of order_.confirmDate
     * @mbg.generated
     */
    public Date getConfirmDate() {
        return confirmDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_.confirmDate
     *
     * @param confirmDate the value for order_.confirmDate
     * @mbg.generated
     */
    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_.userId
     *
     * @return the value of order_.userId
     * @mbg.generated
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_.userId
     *
     * @param userid the value for order_.userId
     * @mbg.generated
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column order_.status
     *
     * @return the value of order_.status
     * @mbg.generated
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column order_.status
     *
     * @param status the value for order_.status
     * @mbg.generated
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getStatusDesc() {
        String desc ="未知";
        switch(status){
            case OrderService.waitPay:
                desc="待付款";
                break;
            case OrderService.waitDelivery:
                desc="待发货";
                break;
            case OrderService.waitConfirm:
                desc="待收货";
                break;
            case OrderService.waitReview:
                desc="等评价";
                break;
            case OrderService.finish:
                desc="完成";
                break;
            case OrderService.delete:
                desc="刪除";
                break;
            default:
                desc="未知";
        }
        return desc;
    }
}