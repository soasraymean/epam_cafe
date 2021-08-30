
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
<div class="background-shadow"></div>

<div class="container">
    <div class="row" id="form-wrap">
        <table class="table-w-10 table-border-collapsed">
            <c:forEach var="client" items="${clients}">
                <div class="popup-window white-wrap p-w-${client.id}">
                    <p class="close">x</p>
                    <a class="info-sign info-sign-push" href="${pageContext.request.contextPath}/admin_client_info?key=${client.id}">i</a>
                    <hr style="width: 100%;">
                    <div class="popup-inner centered">
                        <fmt:message key="client.client"/> ${client.id} <br>
                        ${client.name} <br>
                    </div>
                    <form action="${pageContext.request.contextPath}/update_client?ukey=${client.id}" method="post">
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
                <tr class="list-element popup-open" about="${client.id}">
                    <td style="width: 20%; color: rgba(245, 245, 245, 0.9)">
                        <fmt:message key="client.client"/> ${client.id}
                    </td>
                    <td style="width: 60%; color: rgba(245, 245, 245, 0.9)">
                            ${client.user.email}
                    </td>
                    <td style="width: 20%; color: rgba(245, 245, 245, 0.9)">
                            ${client.name}
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
