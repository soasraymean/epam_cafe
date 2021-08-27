<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Profile</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="container">
    <div class="row">

        <h2 class="text-center home-heading"><fmt:message key="profile.personal"/> <strong><fmt:message key="profile.account"/></strong></h2>
    </div>
    <div class="row">
        <div class="col-md-6 col-sm-6 col-md-push-6 col-sm-push-6" id="form-wrap">
            <form action="${pageContext.request.contextPath}/change_admin_profile" method="post">
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldUsername"><fmt:message key="profile.username"/></label>
                        <input type="text" id="fieldUsername" class="form-control" name="username" required value="${actor.user.username}" placeholder="username">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldPassword"><fmt:message key="profile.password"/></label>
                        <input type="password" id="fieldPassword" class="form-control" name="password" required value="${actor.user.password}" placeholder="password">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12" style="margin: 5px 0px">
                        <label for="fieldSubmit"></label>
                        <input type="submit" id="fieldSubmit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="profile.save"/>">
                    </div>
                </div>
            </form>
            <form action="${pageContext.request.contextPath}/sign_out" method="post">
                <div class="row form-group">
                    <div class="col-sm-12">
                        <label for="fieldSubmitDeleting"></label>
                        <input type="submit" id="fieldSubmitDeleting" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="profile.out"/>">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>