
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Meal</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/popupprocessor.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="container">
    <div class="row">
        <hr>
        <h2 class="intro-text text-center"><fmt:message key="meal.info"/></h2>
        <hr>
    </div>
    <div class="row">
        <table class="meal-table-1">
            <tr>
                <td class="centered" style="width: 60%">
                    <img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${meal.pictureUrl}" width="320" height="320" alt="${meal.name} image"/>
                </td>
                <td>
                    <p class="intro-text">${meal.name} </p>
                    <p><fmt:message key="meal.category"/>: <a href="${pageContext.request.contextPath}/menu?key=${meal.category.id}" class="invis-ref">${meal.category.name}</a></p>
                    <p><fmt:message key="meal.price"/>: ${meal.price} $</p>
                        <fmt:message key="meal.composition"/>
                        <table class="table-border-collapsed">
                            <c:forEach var="ingredient" items="${meal.ingredients}">
                                <c:if test="${ingredient.mass!=0}">
                                        <tr class="list-element">
                                            <td style="width: 15%; padding-bottom: 5px">
                                                <img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${ingredient.pictureUrl}" alt="*" width="64" height="64">
                                            </td>
                                            <td style="width: 70%; color: rgba(245, 245, 245, 0.9)">
                                                    ${ingredient.name}
                                            </td>
                                            <td class="centered" style="width: 15%">
                                                    ${ingredient.mass}
                                            </td>
                                        </tr>
                                </c:if>
                            </c:forEach>
                        </table>
                </td>
            </tr>
        </table>
    </div>
</div>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
