<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Authorization</title>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/popupprocessor.js"/></script>
    <script><jsp:include page="/WEB-INF/js/headerprocessor.js"/></script>
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
            <li><a id="/sign_in" href="${pageContext.request.contextPath}/sign_in"><fmt:message key="sign.in"/></a></li>
            <li><a id="/sign_up" href="${pageContext.request.contextPath}/sign_up"><fmt:message key="sign.up"/></a></li>
        </ul>
    </div>
    <!-- </div> -->
</nav>

<div class="container">
    <div class="row">
        <div class="faq-window white-wrap">
            <p id="close-faq" class="close-faq">x</p>
            <br>
            <hr style="width: 100%;">
            <div class="popup-inner centered">
                <ol>
                    <li><a id="manual" class="info-text" href="${pageContext.request.contextPath}/profile"><fmt:message key="faq.auth"/></a></li>
                    <li><a class="info-text" href="${pageContext.request.contextPath}/menu?key=all"><fmt:message key="faq.addMeal"/></a></li>
                    <li><a class="info-text" href="${pageContext.request.contextPath}/client_order"><fmt:message key="faq.checkOrder"/></a></li>
                    <li><a class="info-text"><fmt:message key="faq.choosePaymentSystem"/></a></li>
                    <li><a class="info-text"><fmt:message key="faq.placeOrder"/></a></li>
                </ol>
            </div>
        </div>
    </div>
</div>

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
