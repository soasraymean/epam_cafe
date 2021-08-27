<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html xml:lang="${locale}">
<head>
    <title>Ingredients</title>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/admin/aheader.jsp"/>
<div class="background-shadow"></div>

<div class="container">
    <div class="row">
        <table class="menu-table-1">
            <tr>
                <td class="menu-table-item centered">
                    <div class="fform-group-a">
                        <img src="${pageContext.servletContext.contextPath}/get_local_image?key=adding" class="centered text-center" style="border: 2px solid gray" alt="add image" width="320" height="320"/>
                        <div class="intro-text text-center">
                            <form action="${pageContext.request.contextPath}/save_ingredient" method="post">
                                <table>
                                    <tr>
                                        <td style="margin: 0; padding: 0; width: 80%;">
                                            <div class="col-md-12" style="padding: 0;">
                                                <label for="newName"></label>
                                                <input type="text" id="newName" class="form-control-a" required placeholder="" name="ingredientName">
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                                <div class="row form-group-a">
                                    <div class="col-md-12">
                                        <label for="newPictureUrl"></label>
                                        <input type="text" id="newPictureUrl" class="form-control-a" required placeholder="" name="ingredientPictureUrl">
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
                <c:forEach var="ingredient" items="${ingredients}">
                <c:set var="count" value="${count+1}"/>
                <td class="menu-table-item centered">
                    <div class="form-group-a">
                        <div class="popup-window-small white-wrap p-w-del-${ingredient.id}" style="height: 40%">
                            <p class="close">x</p>
                            <br>
                            <hr style="width: 100%;">
                            <div class="popup-inner centered">
                                <p class="intro-text">
                                    <fmt:message key="ingredient.delete"/>
                                </p>
                                <form action="${pageContext.request.contextPath}/delete_ingredient?dkey=${ingredient.id}" method="post">
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
                            <img src="${pageContext.request.contextPath}/get_local_image?key=deleting" class="del-div d-d-${ingredient.id} popup-open centered text-center" about="del-${ingredient.id}" alt=" #" width="320" height="320" style="background-color: rgba(169, 169, 169, 0.6);">
                            <img src="${pageContext.servletContext.contextPath}/get_remote_image?url=${ingredient.pictureUrl}" class="centered text-center" about="${ingredient.id}" alt="#" width="320" height="320"/>
                        </a>
                        <div class="intro-text text-center">
                            <form action="${pageContext.request.contextPath}/update_ingredient?ukey=${ingredient.id}" method="post">
                                <table>
                                    <tr>
                                        <td style="margin: 0; padding: 0; width: 80%;">
                                            <div class="col-md-12" style="padding: 0;">
                                                <label for="name"></label>
                                                <input type="text" id="name" class="form-control-a" required value="${ingredient.name}" placeholder="" name="ingredientName">
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                                <div class="row form-group-a">
                                    <div class="col-md-12">
                                        <label for="pictureUrl"></label>
                                        <input type="text" id="pictureUrl" class="form-control-a" required value="${ingredient.pictureUrl}" placeholder="" name="ingredientPictureUrl">
                                    </div>
                                </div>
                                <div class="row form-group-a">
                                    <div class="col-md-12 centered">
                                        <label for="fieldSubmit"></label>
                                        <input type="submit" id="fieldSubmit" class="btn btn-primary btn-outline btn-lg" value="<fmt:message key="admin.update"/>">
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
