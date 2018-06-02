<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/resources/css/style2.css"/>
    <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css"  media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>Advertisment server - User detail</title>
</head>
<body>
        <div class="container">
            <jsp:include page="menu.jsp"/>
            <h1>User detail</h1>
            <table class="table table-sm">
                <tbody>
                <tr>
                    <th scope="col">Username:</th>
                    <td>${user.username}</td>
                </tr>
                <tr>
                    <th scope="col">First name:</th>
                    <td>${user.firstname}</td>
                </tr>
                <tr>
                    <th scope="col">Surname:</th>
                    <td>${user.surname}</td>
                </tr>
                <tr>
                    <th scope="col">Email:</th>
                    <td>${user.email}</td>
                </tr>
                <tr>
                    <th scope="col">Phone:</th>
                    <td>${user.phone}</td>
                </tr>
                <tr>
                    <th scope="col">Registration:</th>
                    <td><fmt:formatDate pattern="dd. MM. yyyy HH:mm" dateStyle = "medium" timeStyle = "medium" value = "${user.creationTime}"/></td>
                </tr>
                </tbody>
            </table>
            <h3>User's adverts:</h3>

            <div class="table-responsive">

            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Advert Name</th>
                        <c:if test = "${isLoggedUsersProfile}">
                        <th scope="col">Action</th>
                        </c:if>
                    </tr>
                </thead>

                <c:forEach items="${adverts}" var="advert">
                        <spring:url value="/adverts/{advertId}" var = "advertUrl">
                            <spring:param name="advertId" value="${advert.id}"/>
                        </spring:url>
                    <tr>
                        <td><a href="${fn:escapeXml(advertUrl)}"><c:out value="${advert.name}"/></a></td>
                        <%--Logged in user can edit or delete his adverts--%>
                        <c:if test = "${isLoggedUsersProfile}">
                            <spring:url value="/adverts/{advertId}/edit" var = "editAdvert">
                                 <spring:param name="advertId" value="${advert.id}"/>
                            </spring:url>
                            <td> <a class="btn btn-info" href="${fn:escapeXml(editAdvert)}">Edit</a></td>

                            <c:url var="deleteUrl" value="/adverts/${advert.id}/delete"/>
                            <td><form:form action="${deleteUrl}" method="POST">
                                <input id="advertId" name="advertId" type="hidden" value="<c:out value="${advert.id}"/>"/>
                                <input type="submit" class="btn btn-danger" value="Delete" onClick="return confirm('Are you sure?')"/>
                            </form:form></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
            </div>
            <h3>User's ratings:</h3>
            <c:forEach items="${ratings}" var="rating">
                <p>
                <spring:url value="/users/{userId}" var = "userUrl">
                    <spring:param name="userId" value="${rating.author.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(userUrl)}"><c:out value="${rating.author.username}"/></a>
                <fmt:formatDate pattern="dd. MM. yyyy HH:mm" dateStyle = "medium" timeStyle = "medium" value = "${rating.postDate}" /><br>
                <c:out value="${rating.ratingText}"/>
                </p>
            </c:forEach>

            <p>
            <form:form method="POST" modelAttribute="addedRating">
                <fieldset>
                        <div class="input-field">
                            <form:textarea path="ratingText" class="materialize-textarea" cols="50" rows="10" placeholder="Enter rating" required ="true" />
                        </div>
                        <div class="clearfix">
                            <sec:authorize access="isAuthenticated()">
                                <button type="submit" class="btn red" >Rate user</button>
                            </sec:authorize>
                            <sec:authorize access="isAnonymous()">
                                <button type="submit" class="btn red" disabled>Rate user</button><br>
                                <span class="red-text">You have to be logged in.</span>
                            </sec:authorize>
                        </div>
                </fieldset>
            </form:form>
            </p>
        </div>
</body>
</html>
