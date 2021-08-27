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
    <div class="row centered">
        <table class="c-navbar-admin">
            <tr>
                <c:forEach var="category" items="${categories}">
                    <td class="c-navbar-item c-navbar-item-${category.id}">
                        <a href="${pageContext.request.contextPath}/admin_menu?key=${category.id}" class="invis-ref" style="display: block">
                                ${category.name}
                        </a>
                    </td>
                </c:forEach>
                    <td class="c-navbar-item c-navbar-item-all">
                        <a href="${pageContext.request.contextPath}/admin_menu?key=all" class="invis-ref" style="display: block">
                            <fmt:message key="menu.all"/>
                        </a>
                    </td>
            </tr>
        </table>
    </div>
    <div class="row">
        <table class="menu-table-1">
            <tr>
                <td class="menu-table-item centered">
                    <div class="form-group-a">
                        <img src="${pageContext.servletContext.contextPath}/get_local_image?key=adding" class="centered text-center" style="border: 2px solid gray" alt="add image" width="320" height="320"/>
                        <div class="intro-text text-center">
                            <form action="${pageContext.request.contextPath}/save_meal" method="post">
                                <div class="row">
                                    <div class="col-md-12">
                                        <label for="newName"></label>
                                        <input type="text" id="newName" class="form-control-a" required placeholder="" name="mealName">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label for="newPictureUrl"></label>
                                        <input type="text" id="newPictureUrl" class="form-control-a" required placeholder="" name="mealPictureUrl">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label for="newCategory"></label>
                                        <select class="form-control-a" id="newCategory" name="mealCategoryName">
                                            <c:forEach var="category" items="${categories}">
                                                <option value="${category.name}" class="form-control-a">${category.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <table>
                                            <tr>
                                                <td style="width: 80%; margin: 0; padding: 0;">
                                                    <label for="newPrice"></label>
                                                    <input type="text" id="newPrice" class="form-control-a" required placeholder="" name="mealPrice">
                                                </td>
                                                <td style="width: 20%; margin: 0; padding: 0;">
                                                    $
                                                </td>
                                            </tr>
                                        </table>
                                    </div>
                                </div>
                                <div class="row form-group-a">
                                    <div class="col-md-12 centered">
                                        <label for="fieldSubmitAdding"></label>
                                        <input type="submit" id="fieldSubmitAdding" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="admin.addnew"/>">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </td>
                <c:set var="count" value="1"/>
                <c:forEach var="meal" items="${meals}">
                <c:set var="count" value="${count+1}"/>
                <td class="menu-table-item centered">
                    <div class="form-group-a">
                        <div class="popup-window-small white-wrap p-w-del-${meal.id}" style="height: 40%">
                            <p class="close">x</p>
                            <br>
                            <hr style="width: 100%;">
                            <div class="popup-inner centered">
                                <p class="intro-text">
                                    <fmt:message key="meal.delete"/>
                                </p>
                                <form action="${pageContext.request.contextPath}/delete_meal?dkey=${meal.id}" method="post">
                                    <div class="row form-group-a">
                                        <div class="col-md-12 centered">
                                            <label for="fieldSubmitDeleting"></label>
                                            <input type="submit" id="fieldSubmitDeleting" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="admin.delete"/>">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <a class="delete-link">
                            <img src="${pageContext.request.contextPath}/get_local_image?key=deleting" class="del-div d-d-${meal.id} popup-open centered text-center" about="del-${meal.id}" alt=" #" width="320" height="320">
                            <img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${meal.pictureUrl}" class="centered text-center" about="${meal.id}" alt="#" width="320" height="320"/>
                        </a>
                        <div class="intro-text text-center">
                            <form action="${pageContext.request.contextPath}/update_meal?ukey=${meal.id}&key=${meal.category.id}" method="post">
                                <div class="row">
                                    <div class="col-md-12">
                                        <label for="name"></label>
                                        <input type="text" id="name" class="form-control-a" required value="${meal.name}" placeholder="" name="mealName">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label for="pictureUrl"></label>
                                        <input type="text" id="pictureUrl" class="form-control-a" required value="${meal.pictureUrl}" placeholder="" name="mealPictureUrl">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <label for="category"></label>
                                        <select class="form-control-a" id="category" name="mealCategoryName">
                                            <c:forEach var="category" items="${categories}">
                                                <c:if test="${category.name==meal.category.name}">
                                                    <option value="${category.name}" selected class="form-control-a">${category.name}</option>
                                                </c:if>
                                                <c:if test="${category.name!=meal.category.name}">
                                                    <option value="${category.name}" class="form-control-a">${category.name}</option>
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
                                                        <input type="text" id="price" class="form-control-a" required value="${meal.price}" placeholder="" name="mealPrice">
                                                    </td>
                                                    <td style="width: 20%; margin: 0; padding: 0;">
                                                        $
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                </div>
                                <div class="row form-group-a">
                                    <div class="col-md-12 centered">
                                        <label for="fieldSubmit"></label>
                                        <input type="submit" id="fieldSubmit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="admin.update"/>"> <a class="info-sign" href="${pageContext.request.contextPath}/admin_meal_info?key=${meal.id}">i</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </td>
                <c:if test="${count%3==0}">
            </tr>
            <tr>
                </c:if>
                </c:forEach>
            </tr>
        </table>
    </div>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp"/>
</body>
</html>
