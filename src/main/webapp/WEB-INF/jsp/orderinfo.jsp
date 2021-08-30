
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Basket</title>
    <style><jsp:include page="/WEB-INF/css/popup.css"/></style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.2.min.js"></script>
    <script><jsp:include page="/WEB-INF/js/popupprocessor.js"/></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="background-shadow"></div>

<div class="container">
    <div class="row">
        <hr>
        <h2 class="intro-text text-center"><fmt:message key="orders.order"/> ${order.id} ${order.orderDate}</h2>
        <hr>
    </div>
    <div class="row">
        <div class="col-sm-12 col-md-12" id="form-wrap">
            <table class="table-w-10 table-border-collapsed">
                <c:forEach var="meal" items="${order.meals}">
                    <div class="popup-window white-wrap p-w-${meal.id}">
                        <p class="close">x</p>
                        <a class="info-sign info-sign-push" href="${pageContext.request.contextPath}/meal_info?key=${meal.id}">i</a>
                        <hr style="width: 100%;">
                        <div class="popup-inner centered">
                            <p class="intro-text">${meal.name}</p>
                            <p><img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${meal.pictureUrl}" width="128" height="128" alt="${meal.name} image"/></p>
                            <p><fmt:message key="meal.category"/>: <a href="${pageContext.request.contextPath}/menu?key=${meal.category.id}" class="invis-ref">${meal.category.name}</a></p>
                            <p><fmt:message key="meal.price"/>: ${meal.price}$</p>
                        </div>
                    </div>
                    <tr class="popup-open list-element" about="${meal.id}">
                        <td style="width: 10%; padding-bottom: 5px">
                            <img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${meal.pictureUrl}" alt="*" width="64" height="64">
                        </td>
                        <td style="width: 60%; text-transform: uppercase; color: rgba(245, 245, 245, 0.9)">
                            ${meal.name}
                        </td>
                        <td class="centered" style="width: 15%">
                            ${meal.mass}
                        </td>
                        <td class="centered" style="width: 15%">
                            ${meal.price}$
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="row form-group">
                <div class="centered">
                    <fmt:message key="order.totalPrice"/>: ${order.totalPrice} $
                </div>
            </div>
            <div class="row form-group">
                <div class="centered">
                    <p><fmt:message key="profile.status"/>:
                        <c:choose>
                            <c:when test="${order.taken}"> <fmt:message key="profile.taken"/> </c:when>
                            <c:when test="${order.prepared}"> <fmt:message key="profile.prep"/></c:when>
                            <c:when test="${order.paid}"> <fmt:message key="profile.paid"/></c:when>
                            <c:otherwise> <fmt:message key="profile.notPaid"/></c:otherwise>
                        </c:choose>
                    </p>
                </div>
            </div>
            <c:if test="${order.clientMark==0}">
                <form action="${pageContext.request.contextPath}/rate_order?key=${order.id}&backToOrderInfo=true" method="post">
                    <div class="rating_block centered">
                        <div class="rating-area">
                            <input type="radio" id="star-5" name="rating" value="5">
                            <label for="star-5" title="«5»"></label>
                            <input type="radio" id="star-4" name="rating" value="4">
                            <label for="star-4" title="«4»"></label>
                            <input type="radio" id="star-3" name="rating" value="3">
                            <label for="star-3" title="«3»"></label>
                            <input type="radio" id="star-2" name="rating" value="2">
                            <label for="star-2" title="«2»"></label>
                            <input type="radio" id="star-1" name="rating" value="1">
                            <label for="star-1" title="«1»"></label>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-sm-8 col-sm-push-1">
                            <label for="message"></label>
                            <textarea name="message" id="message" cols="25" rows="4" maxlength="256" placeholder="<fmt:message key="orders.comm"/>" class="form-control"></textarea>
                        </div>
                    </div>
                    <div class="row form-group">
                        <div class="col-sm-12" style="left: 40%;">
                            <input type="submit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="orders.comment"/>">
                        </div>
                    </div>
                </form>
            </c:if>
            <c:if test="${order.clientMark!=0}">
                <div class="row form-group">
                    <div class="centered">
                        <fmt:message key="order.mark"/> <a>${order.clientMark}</a>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="centered">
                        <fmt:message key="order.comment"/> <br>
                            ${order.clientComment}
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
