
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Menu</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="background-shadow"></div>

<div class="container">

    <div class="row">
        <table class="c-navbar">
            <tr>
                <c:forEach var="category" items="${categories}">
                    <c:if test="${category.id!=1}">
                        <td class="c-navbar-item c-navbar-item-${category.id}">
                            <a href="${pageContext.request.contextPath}/menu?key=${category.id}" class="invis-ref" style="display: block">
                                ${category.name}
                            </a>
                        </td>
                    </c:if>
                </c:forEach>
            </tr>
        </table>
    </div>

    <c:if test="${actor==null}">
        <p class="centered"><fmt:message key="menu.guestmsg"/></p>
    </c:if>

    <div class="row">
        <table class="menu-table-1">
            <tr>
                <c:forEach var="meal" items="${meals}">
                <c:set var="count" value="${count+1}"/>
                    <td class="menu-table-item centered">
                        <div class="form-group">
                            <img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${meal.pictureUrl}" class="centered text-center" alt="${meal.name} image" width="320" height="320"/>
                            <div class="intro-text text-center">
                                    <a class="menu-info-sign" href="${pageContext.request.contextPath}/meal_info?key=${meal.id}">${meal.name}</a>
                                        <br>
                                    ${meal.price}$
                            </div>
                            <c:if test="${actor!=null}">
                                <form action="${pageContext.request.contextPath}/add_meal_to_order?key=${meal.id}" method="post">
                                    <div class="row form-group">
                                        <div class="col-md-12 centered">
                                            <label for="fieldSubmit"></label>
                                            <input type="submit" id="fieldSubmit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="faq.addMeal"/>">
                                        </div>
                                    </div>
                                </form>
                            </c:if>
                        </div>
                    </td>
                <c:if test="${count%3==0}">
                    </tr>
                    <tr>
                </c:if>
                </c:forEach>
            </tr>
        </table>
    </div>

    <div class="popup-window-small white-wrap p-w-success">
        <p class="close">x</p>
        <br>
        <hr style="width: 100%;">
        <div class="popup-inner centered">
            <p class="intro-text">
                <fmt:message key="menu.success"/>
            </p>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
