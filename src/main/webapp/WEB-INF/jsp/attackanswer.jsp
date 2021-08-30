
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Access denied</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>

<nav class="fh5co-nav" role="navigation">
    <!-- <div class="top-menu"> -->
    <div class="text-center logo-wrap">
        <div id="nav-logo" class="no-deco"><a href="${pageContext.request.contextPath}/home">Epam <span>Cafe</span></a></div>
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

<br>
<br>
<br>
<br>

<div class="row" style="width: 1280px">
    <div class="home-pic-box">
        <img src="${pageContext.request.contextPath}/get_remote_image?url=/cafe-hackerman-picture.jpg" alt="#" width="1024" height="600">
    </div>
    <div class="home-desc-box">
        <h2 class="home-heading"><fmt:message key="hack.hello"/> </h2>
        <fmt:message key="hack.description"/>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>