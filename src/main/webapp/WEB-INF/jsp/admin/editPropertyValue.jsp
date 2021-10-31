<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>

<title>编辑产品属性值</title>

<script>
    $(function () {
        $("input.pvValue").keyup(function () {
            let value = $(this).val();
            let propertyValueId = this.id;
            let parentSpan = $(this).parent("span");
            parentSpan.css("border", "1px solid yellow");
            $.post("admin_propertyValue_update",
                {value, id: propertyValueId},
                function (result) {
                    if ("success" === result)
                        parentSpan.css("border", "1px solid green");
                    else
                        parentSpan.css("border", "1px solid red");
                }
            );
        });
    });
</script>

<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_product_list?cid=${product.category.id}">${product.category.name}</a></li>
        <li class="active">${product.name}</li>
        <li class="active">编辑产品属性</li>
    </ol>

    <div class="editPVDiv">
        <c:forEach items="${propertyValueList}" var="propertyValue">
            <div class="eachPV">
                <span class="pvName"> <label for="${propertyValue.id}">${propertyValue.property.name}</label></span>
                <span class="pvValue"><input class="pvValue" id="${propertyValue.id}" type="text"
                                             value="${propertyValue.value}"></span>
            </div>
        </c:forEach>
        <div style="clear:both"></div>
    </div>

</div>

