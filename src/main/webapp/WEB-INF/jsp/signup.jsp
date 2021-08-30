
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>SignUp</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/authorizationheader.jsp"/>

<div class="container">
    <div class="row">
        <br>
    </div>
    <div class="row">
        <div class="col-sm-3 col-sm-push-3"  id="form-wrap">
            <form action="${pageContext.request.contextPath}/sign_up" method="post">
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldUsername"><fmt:message key="sign.login"/></label>
                        <p>
                            <input type="text" id="fieldUsername" class="form-control" pattern="^[a-zA-Z][a-zA-Z0-9-_.]{4,20}$" title="From 4 to 20 latin any-case letters, numbers, -, _, ." required name="username">
                        </p>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldPassword"><fmt:message key="sign.password"/></label>
                        <p>
                            <input type="password"  id="fieldPassword" class="form-control" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" title="8 or more latin uppercase letter, latin lowercase letters, numbers and special symbols" required name="password">
                        </p>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldEmail"><fmt:message key="sign.mail"/></label>
                        <p>
                            <input type="text" id="fieldEmail" class="form-control" pattern="^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}$" required name="email">
                        </p>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldPhone"><fmt:message key="sign.phone"/></label>
                        <p>
                            <input type="text"  id="fieldPhone" class="form-control" pattern="80[0-9]{9}" title="Phone number starting with 80" required name="phone">
                        </p>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldName"><fmt:message key="sign.name"/></label>
                        <p>
                            <input type="text" id="fieldName" class="form-control" name="name">
                        </p>
                    </div>
                </div>
                <c:if test="${error!=null}">
                    <p class="error-msg text-center">${error}</p>
                </c:if>
                <div class="row form-group">
                    <div class="col-sm-push-3 col-sm-3">
                        <input type="submit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="sign.up"/>">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
