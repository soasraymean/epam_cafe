
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Orders history</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>
<div class="background-shadow"></div>

<div class="container">
    <div class="row" id="form-wrap">
        <table class="table-w-10 table-border-collapsed">
            <c:forEach var="order" items="${orders}">
                <div class="popup-window white-wrap p-w-${order.id}" style="height: 40%;">
                    <p class="close">x</p>
                    <a class="info-sign info-sign-push" href="${pageContext.request.contextPath}/admin_order_info?key=${order.id}">i</a>
                    <hr style="width: 100%;">
                    <div class="popup-inner centered">
                        <fmt:message key="orders.order"/> ${order.id} <br>
                        ${order.orderDate}
                    </div>
                    <form action="${pageContext.request.contextPath}/update_order?ukey=${order.id}" method="post">
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
                <tr class="list-element popup-open" about="${order.id}">
                    <td style="width: 20%; color: rgba(245, 245, 245, 0.9)">
                        <fmt:message key="orders.order"/> ${order.id}
                    </td>
                    <td style="width: 60%; text-transform: uppercase; color: rgba(245, 245, 245, 0.9)">
                            ${order.orderDate}
                        <c:if test="${!order.taken}">
                            <a>*</a>
                        </c:if>
                    </td>
                    <td style="width: 20%; color: rgba(245, 245, 245, 0.9)">
                            ${order.totalPrice}$
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
