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
    <script><jsp:include page="/WEB-INF/js/popupprocessor.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="background-shadow"></div>

<div class="container">
        <h2 class="text-center home-heading"><fmt:message key="profile.personal"/> <strong><fmt:message key="profile.account"/></strong></h2>
    <table class="table-a-top table-div-2 table-w-10 table-s-hor-2">
        <tr>
            <td>
                <div class="row">
                    <div class="row form-group">
                        <div class="col-md-12">
                            <h2 class="intro-text text-center">
                                <a class="info-text" href="${pageContext.request.contextPath}/client_orders">
                                    <fmt:message key="admin.orders"/>
                                </a>
                            </h2>
                            <table class="table-w-10 table-border-collapsed">
                                <c:forEach var="order" items="${actor.orders}">
                                    <c:if test="${!order.taken}">
                                        <tr class="list-element">
                                            <td style="width: 20%; color: rgba(245, 245, 245, 0.9)">
                                                <fmt:message key="orders.order"/> ${order.id}
                                            </td>
                                            <td style="width: 60%; text-transform: uppercase; color: rgba(245, 245, 245, 0.9)">
                                                    ${order.orderDate}
                                                <c:if test="${order.prepared}">
                                                    <a>•</a>
                                                </c:if>
                                            </td>
                                            <td style="width: 20%; color: rgba(245, 245, 245, 0.9)">
                                                    ${order.totalPrice}$
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </td>
            <td>
                <div class="row">
                    <div>
                        <table class="table-div-auto table-border-collapsed ">
                            <tr>
                                <td>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <fmt:message key="profile.username"/>: ${actor.user.username}
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/sign_out" method="post">
                                        <div class="row form-group">
                                            <div class="col-sm-9 col-sm-push-2">
                                                <input type="submit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="profile.out"/>">
                                            </div>
                                        </div>
                                    </form>
                                </td>
                            </tr>
                            <tr style="border-bottom: 1px solid gray">
                                <td>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <fmt:message key="profile.loyalty"/>: ${actor.loyaltyPoints}
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="row form-group">
                                        <div class="col-sm-12 col-sm-push-2">
                                            <fmt:message key="profile.bonuses"/>: ${actor.bonuses}
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <fmt:message key="profile.name"/>: ${actor.name}
                                        </div>
                                    </div>
                                </td>
                                <td style="position: relative; left: 20%">
                                    <a class="popup-open edit-pointer" about="edit-personal-info">
                                        <img src="${pageContext.request.contextPath}/get_local_image?key=edit" alt="#" width="20" height="20">
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <fmt:message key="profile.mail"/>: ${actor.user.email}
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="row form-group">
                                        <div class="col-md-12">
                                            <fmt:message key="profile.contactPhone"/>: ${actor.user.phone}
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </td>
        </tr>
    </table>
    <div class="popup-window white-wrap p-w-edit-personal-info" style="height: 65%">
        <p class="close">x</p>
        <br>
        <hr style="width: 100%;">
        <div class="popup-inner centered">
            <form action="${pageContext.request.contextPath}/change_profile" method="post">
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldName"><fmt:message key="profile.name"/></label>
                        <input type="text" id="fieldName" class="form-control" value="${actor.name}" required name="name">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldEmail"><fmt:message key="profile.mail"/></label>
                        <input type="text" id="fieldEmail" class="form-control"  value="${actor.user.email}" pattern="^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}$" required name="email" >
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldPhone"><fmt:message key="sign.phone"/></label>
                        <input type="text"  id="fieldPhone" class="form-control" value="${actor.user.phone}" pattern="80[0-9]{9}" title="Phone number starting with 80" required name="phone">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12">
                        <label for="fieldPassword"><fmt:message key="sign.password"/></label>
                        <input type="password"  id="fieldPassword" class="form-control" value="${actor.user.password}" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" title="8 or more latin uppercase letter, latin lowercase letters, numbers and special symbols" required name="password">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-12"  style="margin: 10px 0">
                        <input type="submit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="profile.save"/>">
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div>

    </div>
    <div class="popup-window-small white-wrap p-w-success">
        <p class="close">x</p>
        <br>
        <hr style="width: 100%;">
        <div class="popup-inner centered">
            <p class="intro-text"><fmt:message key="order.success"/></p>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>