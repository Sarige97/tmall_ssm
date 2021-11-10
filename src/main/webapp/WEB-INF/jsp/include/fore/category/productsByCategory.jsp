<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<c:if test="${empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="100"/>
</c:if>

<c:if test="${!empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="${param.categorycount}"/>
</c:if>

<div class="categoryProducts">
    <c:forEach items="${category.productList}" var="product" varStatus="stc">
        <c:if test="${stc.count<=categorycount}">
            <div class="productUnit" price="${product.promotePrice}">
                <div class="productUnitFrame">
                    <a href="foreproduct?pid=${product.id}">
                        <img class="productImage" src="img/productSingle_middle/${product.firstProductImage.id}.jpg">
                    </a>
                    <span class="productPrice">¥<fmt:formatNumber type="number" value="${product.promotePrice}" minFractionDigits="2"/></span>
                    <a class="productLink" href="foreproduct?pid=${product.id}">
                            ${fn:substring(product.name, 0, 50)}
                    </a>

                    <a  class="tmallLink" href="foreproduct?pid=${product.id}">天猫专卖</a>

                    <div class="show1 productInfo">
                        <span class="monthDeal ">月成交 <span class="productDealNumber">${product.saleCount}笔</span></span>
                        <span class="productReview">评价<span class="productReviewNumber">${product.reviewCount}</span></span>
                        <span class="wangwang">
                    <a class="wangwanglink" href="#nowhere">
                        <img src="img/site/wangwang.png">
                    </a>

                    </span>
                    </div>
                </div>
            </div>
        </c:if>
    </c:forEach>
    <div style="clear:both"></div>
</div>