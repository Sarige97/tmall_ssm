<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<div class="categoryMenu">
    <c:forEach items="${categoryList}" var="category" varStatus="status">
        <div cid="${category.id}" class="eachCategory">
            <span class="glyphicon glyphicon-link"></span>
            <a href="${pageContext.request.contextPath}/forecategory?cid=${category.id}">
                    ${category.name}
            </a>
        </div>
    </c:forEach>
</div>