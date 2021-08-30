
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Not found</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>

<nav class="fh5co-nav scrolled" role="navigation">
    <!-- <div class="top-menu"> -->
    <div class="container">
        <div class="row">
            <div><details class="invis-ref " style="padding: 10px 15px; top: 0; position: fixed; z-index: 1091">
                <summary> <img src="${pageContext.request.contextPath}/get_local_image?key=lang" alt="lang image" height="16" width="16"/> <fmt:message key="lang"/></summary>
                <a class="invis-ref" href="?locale=en">
                    EN
                </a>
                <a class="invis-ref" href="?locale=by">
                    BY
                </a>
                <a class="invis-ref" href="?locale=ru">
                    RU
                </a>
            </details></div>
            <br>
            <br>
            <div class="col-xs-12 text-center logo-wrap">
                <div id="fh5co-logo"><a href="${pageContext.request.contextPath}/home">Epam <span>Cafe</span></a></div>
            </div>
        </div>
    </div>
    <!-- </div> -->
</nav>

<br>
<br>
<br>
<br>

<div class="container">
    <div class="row">
        <hr>
            <h2 class="intro-text intro-text text-center">404 - <strong><fmt:message key="err.notfound"/></strong></h2>
        <hr>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>

</body>
</html>