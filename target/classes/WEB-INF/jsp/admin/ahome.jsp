<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Main</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="row">
    <div class="home-pic-box">
        <img src="${pageContext.request.contextPath}/get_remote_image?url=/cafe-home-picture.jpg" alt="#" width="1024" height="600">
    </div>
    <div class="home-desc-box">
        <h2 class="home-heading"><fmt:message key="main.cafe"/> </h2>
        <fmt:message key="main.description"/>
    </div>
</div>

<div class="row">
    <h2 class="home-heading col-sm-push-6 col-md-push-6 col-md-12 col-sm-12"><a class="invis-ref" href="${pageContext.request.contextPath}/admin_orders?key=active"><fmt:message key="admin.activeOrders"/>: <strong>${activesNumber}</strong></a></h2>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>