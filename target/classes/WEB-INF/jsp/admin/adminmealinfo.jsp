<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Menu</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>
<div class="background-shadow"></div>

<div class="container">
    <div class="row">
        <hr>
        <h2 class="intro-text text-center"><fmt:message key="meal.info"/></h2>
        <hr>
    </div>
    <div class="row">
        <table class="meal-table-1">
            <tr>
                <td class="centered" style="width: 60%">
                    <img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${meal.pictureUrl}" width="320" height="320" alt="${meal.name} image"/>
                </td>
                <td>
                    <div class="intro-text text-center">
                        <form action="${pageContext.request.contextPath}/update_meal?ukey=${meal.id}&backToCurrent=true" method="post">
                            <div class="row">
                                <div class="col-md-12">
                                    <label for="name"></label>
                                    <input type="text" id="name" class="form-control" required value="${meal.name}" placeholder="" name="mealName">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label for="pictureUrl"></label>
                                    <input type="text" id="pictureUrl" class="form-control" required value="${meal.pictureUrl}" placeholder="" name="mealPictureUrl">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label for="category"></label>
                                    <select class="form-control" id="category" name="mealCategoryName">
                                        <c:forEach var="category" items="${categories}">
                                            <c:if test="${category==meal.category}">
                                                <option value="${category.name}" selected class="form-control">${category.name}</option>
                                            </c:if>
                                            <c:if test="${category!=meal.category}">
                                                <option value="${category.name}" class="form-control">${category.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-6">
                                    <table>
                                        <tr>
                                            <td style="width: 80%; margin: 0; padding: 0;">
                                                <label for="price"></label>
                                                <input type="text" id="price" class="form-control" required value="${meal.price}" placeholder="" name="mealPrice">
                                            </td>
                                            <td style="width: 20%; margin: 0; padding: 0;">
                                                $
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-md-12 centered">
                                    <label for="fieldSubmit"></label>
                                    <input type="submit" id="fieldSubmit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="admin.update"/>">
                                </div>
                            </div>
                        </form>
                    </div>
                    <table class="table-border-collapsed">
                        <c:forEach var="ingredient" items="${meal.ingredients}">
                            <c:if test="${ingredient.mass!=0}">
                                <tr class="list-element">
                                    <td style="width: 15%; padding-bottom: 5px">
                                        <img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${ingredient.pictureUrl}" alt="*" width="64" height="64">
                                    </td>
                                    <td style="width: 70%; color: rgba(245, 245, 245, 0.9)">
                                            ${ingredient.name}
                                    </td>
                                    <td class="centered" style="width: 15%">
                                            ${ingredient.mass}
                                    </td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/remove_ingredient?ukey=${meal.id}&rkey=${ingredient.id}" method="post">
                                            <div class="col-md-12 centered">
                                                <label for="fieldSubmitRemoving"></label>
                                                <input type="submit" id="fieldSubmitRemoving" class="btn btn-primary btn-outline btn-lg" value="-">
                                            </div>
                                        </form>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        <div class="popup-window white-wrap p-w-add-ingredient" style="height: 45%">
                            <p class="close">x</p>
                            <br>
                            <hr style="width: 100%;">
                            <div class="popup-inner centered">
                                <form action="${pageContext.request.contextPath}/add_ingredient?ukey=${meal.id}" method="post">
                                    <div class="row">
                                        <div class="col-md-12 centered">
                                            <label for="ingredient"><fmt:message key="ingredient.name"/> </label>
                                            <select class="form-control" id="ingredient" name="ingredientName">
                                                <c:forEach var="ingredient" items="${ingredients}">
                                                    <c:if test="${!meal.contains(ingredient)}">
                                                        <option value="${ingredient.name}" class="form-control">${ingredient.name}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <label for="mass"><fmt:message key="ingredient.mass"/></label>
                                            <input type="text" id="mass" class="form-control" required placeholder="" name="ingredientMass">
                                        </div>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-md-12 centered" style="margin: 15px 0px">
                                            <label for="fieldSubmitAdding"></label>
                                            <input type="submit" id="fieldSubmitAdding" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="admin.addnew"/>">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <tr>
                            <td>
                                <div class="popup-open" about="add-ingredient">
                                    <a class="btn btn-primary btn-outline btn-lg">+</a>
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
