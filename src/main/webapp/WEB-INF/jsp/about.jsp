<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>About</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>

<div class="container">
    <div class="row">
        <h2 class="home-heading" style="margin-bottom: 0; width: 80%; left: 15%; position: relative">Epam <strong>Cafe</strong></h2>
        <p class="col-md-6 col-sm-6" style="left: 10%">
            <fmt:message key="about.cafe"/>
        </p>
    </div>
</div>

<div class="container">
    <div class="row" style="width: 1280px">
        <div class="home-pic-box">
            <img style="left: 10%; position: relative" src="${pageContext.request.contextPath}/get_remote_image?url=/cafe-about-picture.jpg" alt="#" width="840" height="600">
        </div>
        <div class="home-desc-box no-back" style="font-size: 28px; top: 60%; left: 55%; width: 35%">
            <fmt:message key="about.citate"/>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
