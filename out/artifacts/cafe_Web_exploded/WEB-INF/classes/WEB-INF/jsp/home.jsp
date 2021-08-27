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
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/popupprocessor.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="background-shadow"></div>

<div class="row" style="width: 1280px">
    <div class="home-pic-box">
        <img src="${pageContext.request.contextPath}/get_remote_image?url=/cafe-home-picture.jpg" alt="#" width="1024" height="600">
    </div>
    <div class="home-desc-box">
        <h2 class="home-heading"><fmt:message key="main.cafe"/> </h2>
        <fmt:message key="main.description"/>
    </div>
</div>

<div class="container">
<div class="row">
    <h2 class="home-heading centered"><fmt:message key="main.contact"/></h2>
    <div class="col-sm-6 col-md-6 col-sm-push-6 col-md-push-6"  id="form-wrap">
        <form action="${pageContext.request.contextPath}/leave_comment" method="post">
            <div class="row form-group">
                <div class="col-md-12" style="padding-bottom: 15px">
                    <label for="name"><fmt:message key="main.name"/></label>
                    <input type="text" id="name" class="form-control" required value="${actor.name}" placeholder="" name="authorName">
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-12"  style="padding-bottom: 15px">
                    <label for="phone"><fmt:message key="main.phone"/></label>
                    <input type="text" id="phone" class="form-control" required value="${actor.user.phone}" placeholder="" name="authorPhone">
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-12"  style="padding-bottom: 15px">
                    <label for="message"><fmt:message key="main.msg"/></label>
                    <textarea name="message" id="message" cols="25" rows="6" class="form-control"></textarea>
                </div>
            </div>
            <div class="row form-group" style="padding-bottom: 15px">
                <div class="col-md-12">
                    <input type="submit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="main.leavemsg"/>">
                </div>
            </div>
        </form>
    </div>
    <div class="popup-window-small white-wrap p-w-success">
        <p class="close">x</p>
        <br>
        <hr style="width: 100%;">
        <div class="popup-inner centered">
            <p class="intro-text">
                <fmt:message key="main.success"/>
            </p>
        </div>
    </div>
</div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>