
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Registered clients</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>

<div class="container">
    <div class="row">
        <hr>
        <h2 class="intro-text text-center"><fmt:message key="client.client"/><strong>${client.id}</strong></h2>
        <hr>
    </div>
    <div class="row">
        <div class="col-md-6 col-sm-6 col-md-push-6 col-sm-push-6" id="form-wrap">
            <div class="row form-group">
                <div class="col-md-12">
                    ${client.name}
                </div>
            </div>
            <details>
                <summary><fmt:message key="client.accountInfo"/></summary>
                <div class="row form-group">
                    <div class="col-md-12">
                        <fmt:message key="client.user"/>: ${client.user.id}
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <fmt:message key="profile.username"/>: ${client.user.username}
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <fmt:message key="profile.mail"/>: ${client.user.email}
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <fmt:message key="profile.contactPhone"/>: ${client.user.phone}
                    </div>
                </div>
            </details>
            <a class="invis-ref" href="${pageContext.request.contextPath}/admin_orders?key=${client.id}"><fmt:message key="orders.history"/></a>
            <form action="${pageContext.request.contextPath}/update_client?ukey=${client.id}&backToCurrent=true" method="post">
                <div class="row form-group centered">
                    <div class="col-md-12">
                        <label for="fieldLoyalty"><fmt:message key="profile.loyalty"/></label>
                        <input type="text" id="fieldLoyalty" class="form-control" value="${client.loyaltyPoints}" placeholder="" name="clientLoyalty">
                    </div>
                </div>
                <div class="row form-group centered">
                    <div class="col-md-12">
                        <label for="fieldBonuses"><fmt:message key="profile.bonuses"/></label>
                        <input type="text" id="fieldBonuses" class="form-control" value="${client.bonuses}" placeholder="" name="clientBonuses">
                    </div>
                </div>
                <div class="row form-group centered">
                    <div class="col-md-12">
                        <label><fmt:message key="client.banned"/>
                            <c:if test="${client.banned}">
                                <input type="checkbox" name="params" value="isBanned" checked/>
                            </c:if>
                            <c:if test="${!client.banned}">
                                <input type="checkbox" name="params" value="isBanned"/>
                            </c:if>
                        </label>
                    </div>
                </div>
                <div class="row form-group centered">
                    <div class="col-md-12">
                        <p>
                            <input type="submit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="profile.save"/>">
                        </p>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
