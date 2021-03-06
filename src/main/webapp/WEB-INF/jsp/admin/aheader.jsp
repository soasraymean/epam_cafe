
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-16" pageEncoding="UTF-16" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Header</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/popupprocessor.js"/></script>
    <script><jsp:include page="/WEB-INF/js/headerprocessor.js"/></script>
    <script><jsp:include page="/WEB-INF/js/menuprocessor.js"/></script>
    <script><jsp:include page="/WEB-INF/js/adminmenuprocessor.js"/></script>
</head>
<body>

<br>
<br>
<br>
<br>

<nav class="fh5co-nav" role="navigation">
    <!-- <div class="top-menu"> -->
    <div class="text-center logo-wrap">
        <div id="nav-logo" class="no-deco"><a href="${pageContext.request.contextPath}/home">Epam <span>Cafe</span></a></div>
    </div>
    <div class="text-center no-deco" id="nav-header">
        <ul>
            <li><a id="/home" href="${pageContext.request.contextPath}/home"><fmt:message key="header.home"/></a></li>
            <li><a id="/admin_menu" href="${pageContext.request.contextPath}/admin_menu?key=all"><fmt:message key="header.menu"/></a></li>
            <li><a id="/admin_categories" href="${pageContext.request.contextPath}/admin_categories"><fmt:message key="admin.categories"/></a></li>
            <li><a id="/admin_ingredients" href="${pageContext.request.contextPath}/admin_ingredients"><fmt:message key="admin.ingredients"/></a></li>
            <li><a id="/admin_halls" href="${pageContext.request.contextPath}/admin_halls"><fmt:message key="admin.halls"/></a></li>
            <li><a id="/admin_clients" href="${pageContext.request.contextPath}/admin_clients"><fmt:message key="admin.clients"/></a></li>
            <li><a id="/admin_orders" href="${pageContext.request.contextPath}/admin_orders?key=all"><fmt:message key="admin.orders"/></a></li>
            <li><a id="/admin_reviews" href="${pageContext.request.contextPath}/admin_reviews?page=1"><fmt:message key="admin.reviews"/></a></li>
            <li><a id="/profile" href="${pageContext.request.contextPath}/profile"><fmt:message key="header.profile"/></a></li>
        </ul>
    </div>
    <!-- </div> -->
</nav>

<div class="container">
    <div class="row">
        <details class="invis-ref " style="padding: 10px 15px; margin: 5px 15px;  top: 0; position: fixed; z-index: 1091; font-size: 16px">
            <summary><fmt:message key="lang"/></summary>
            <a class="invis-ref" href="?locale=en">
                EN
            </a>
            <a class="invis-ref" href="?locale=by">
                BY
            </a>
            <a class="invis-ref" href="?locale=ru">
                RU
            </a>
        </details>
    </div>
</div>


</body>
</html>
