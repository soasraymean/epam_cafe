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
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>
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
                        <a class="info-sign info-sign-push" href="${pageContext.request.contextPath}/admin_meal_info?key=${meal.id}">i</a>
                        <hr style="width: 100%;">
                        <div class="popup-inner centered">
                            <p class="intro-text">${meal.name}</p>
                            <p><img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${meal.pictureUrl}" width="128" height="128" alt="${meal.name} image"/></p>
                            <p><fmt:message key="meal.category"/>: <a href="${pageContext.request.contextPath}/admin_menu?key=${meal.category.id}" class="invis-ref">${meal.category.name}</a></p>
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
                    <form action="${pageContext.request.contextPath}/update_order?ukey=${order.id}&backToCurrent=true" method="post">
                        <div class="row form-group centered">
                            <div class="col-md-12">
                                <label>
                                    <c:if test="${order.paid}">
                                        <input type="checkbox" name="params" value="isPaid" checked/>
                                    </c:if>
                                    <c:if test="${!order.paid}">
                                        <input type="checkbox" name="params" value="isPaid"/>
                                    </c:if>
                                    <a><fmt:message key="orders.paid"/></a>
                                </label>
                            </div>
                        </div>
                        <div class="row form-group centered">
                            <div class="col-md-12">
                                <label>
                                    <c:if test="${order.prepared}">
                                        <input type="checkbox" name="params" value="isPrepared" checked/>
                                    </c:if>
                                    <c:if test="${!order.prepared}">
                                        <input type="checkbox" name="params" value="isPrepared"/>
                                    </c:if>
                                    <a><fmt:message key="orders.prepared"/> </a>
                                </label>
                            </div>
                        </div>
                        <div class="row form-group centered">
                            <div class="col-md-12">
                                <label>
                                    <c:if test="${order.taken}">
                                        <input type="checkbox" name="params" value="isTaken" checked/>
                                    </c:if>
                                    <c:if test="${!order.taken}">
                                        <input type="checkbox" name="params" value="isTaken"/>
                                    </c:if>
                                    <a><fmt:message key="orders.taken"/> </a>
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
            <div class="row form-group">
                <div class="centered">
                    <a href="${pageContext.request.contextPath}/admin_clients?open=${order.customer.id}">
                        <fmt:message key="orders.orderer"/> : ${order.customer.name}
                    </a>
                </div>
            </div>
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
