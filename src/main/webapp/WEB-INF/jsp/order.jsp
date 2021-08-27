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
        <c:if test="${actor.currentOrder.blank}">
            <div class="col-sm-12 col-md-12 centered intro-text text-center">
                <fmt:message key="basket.empty"/>
            </div>
        </c:if>
        <c:if test="${actor!=null && !actor.currentOrder.blank}">
        <div class="col-sm-12 col-md-12" id="form-wrap">
            <table class="table-w-10 table-border-collapsed">
                <c:forEach var="meal" items="${actor.currentOrder.meals}">
                    <div class="popup-window white-wrap p-w-${meal.id}">
                        <p class="close">x</p>
                        <a class="info-sign info-sign-push" href="${pageContext.request.contextPath}/meal_info?key=${meal.id}">i</a>
                        <hr style="width: 100%;">
                        <div class="popup-inner centered">
                            <p class="intro-text">${meal.name} <a class="invis-ref red-wrap" href="${pageContext.request.contextPath}/remove_meal_from_order?key=${meal.id}">-</a></p>
                            <p><img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${meal.pictureUrl}" width="128" height="128" alt="${meal.name} image"/></p>
                            <p><fmt:message key="meal.category"/>: <a href="${pageContext.request.contextPath}/menu?key=${meal.category.id}" class="invis-ref">${meal.category.name}</a></p>
                            <p><fmt:message key="meal.price"/>: ${meal.price} $</p>
                        </div>
                    </div>
                    <tr class="list-element popup-open" about="${meal.id}">
                        <td style="width: 10%; padding-bottom: 5px">
                            <img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${meal.pictureUrl}" alt="*" width="64" height="64">
                        </td>
                        <td style="width: 60%; text-transform: uppercase; color: rgba(245, 245, 245, 0.9)">
                            ${meal.name}
                        </td>
                        <td class="centered" style="width: 15%">
                            ${meal.mass}
                        </td>
                        <td class="centered" style="width: 10%">
                            ${meal.price}$
                        </td>
                        <td class="centered" style="width: 5%">
                            <a class="invis-ref red-wrap" href="${pageContext.request.contextPath}/remove_meal_from_order?key=${meal.id}">-</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <p class="centered intro-text"><fmt:message key="order.totalPrice"/>: ${actor.currentOrder.totalPrice} $</p>
            <form action="${pageContext.request.contextPath}/place_order" method="post">
                <c:if test="${actor.bonuses >= actor.currentOrder.totalPrice/2}">
                    <div class="row form-group">
                        <div class="centered">
                            <label for="fieldCheckbox" ><fmt:message key="order.payWithBonuses"/> (${actor.currentOrder.totalPrice/2}) </label>
                            <input type="checkbox" id="fieldCheckbox" name="params" value="payWithBonuses">
                        </div>
                    </div>
                </c:if>
                <div class="row form-group">
                    <div class="col-sm-3 col-sm-push-3 col-md-6">
                        <label for="fieldOrderTime" ><fmt:message key="order.requiredTime"/></label>
                        <input type="time" id="fieldOrderTime" class="form-control" min="${minTime}" max="${maxTime}" name="orderTime">
                    </div>
                </div>
                <table class="col-sm-push-2 col-sm-6">
                    <tr>
                        <td>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <label for="fieldSubmitOfflinePayment"></label>
                                    <input type="submit" id="fieldSubmitOfflinePayment" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="order.placeOfflinePayment"/>" name="offlinePayment">
                                </div>
                            </div>
                        </td>
                        <td>
                            <fmt:message key="order.or"/>
                        </td>
                        <td>
                            <div class="row form-group">
                                <div class="col-md-12">
                                    <label for="fieldSubmitOnlinePayment"></label>
                                    <input type="submit" id="fieldSubmitOnlinePayment" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="order.placeOnlinePayment"/>" name="onlinePayment">
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        </c:if>
    </div>
    <div class="popup-window-small red-wrap p-w-timeError" style="height: auto">
        <p class="close">x</p>
        <br>
        <hr style="width: 100%;">
        <div class="popup-inner centered">
            <p class="intro-text error-msg"><fmt:message key="order.timeError"/></p>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
