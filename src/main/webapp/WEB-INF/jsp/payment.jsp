<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Order payment</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/popupprocessor.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="box">
    <hr>
    <h2 class="intro-text text-center"><strong><fmt:message key="payment.p"/></strong></h2>
    <hr>
    <div class="centered">
        <iframe src="https://money.yandex.ru/quickpay/button-widget?targets=Order%20payment&default-sum=${moneyToBePaid}&button-text=12&any-card-payment-type=on&button-size=m&button-color=black&successURL=localhost:8080${pageContext.servletContext.contextPath}/payment_success&quickpay=small&account=4100115365944689&" width="184" height="36" frameborder="0" allowtransparency="true" scrolling="no"></iframe>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
