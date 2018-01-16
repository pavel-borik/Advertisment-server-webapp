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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet">
    <title>Title</title>
</head>
<body>
    <div class="bodyContainer">
        <div class="container">
            <jsp:include page="menu.jsp"/>
            <h1>User detail</h1>
            <p>
                Username: ${user.username}<br>
                First name: ${user.firstname}<br>
                Last name: ${user.surname}<br>
                E-mail: ${user.email}<br>
                Phone: ${user.phone}<br>
                Registered: <fmt:formatDate pattern="dd. MM. yyyy HH:mm" dateStyle = "medium" timeStyle = "medium" value = "${user.creationTime}" /><br>
            </p>
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Advert Name</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>

            <h3>User's adverts:</h3>
            <c:forEach items="${adverts}" var="advert">
                    <spring:url value="/adverts/{advertId}" var = "advertUrl">
                        <spring:param name="advertId" value="${advert.id}"/>
                    </spring:url>
                <tr>
                    <td><a href="${fn:escapeXml(advertUrl)}"><c:out value="${advert.name}"/></a></td>
                    <%--Logged in user can edit his adverts--%>
                    <c:if test = "${isLoggedUsersProfile}">
                        <spring:url value="/adverts/{advertId}/edit" var = "editAdvert">
                             <spring:param name="advertId" value="${advert.id}"/>
                        </spring:url>
                        <td> <a href="${fn:escapeXml(editAdvert)}">Edit</a></td>
                    </c:if>
                </tr>
                <br>
            </c:forEach>
            </table>
        </div>
            <br>
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
                        <div class="form-group">
                            <form:textarea path="ratingText" class="form-control" cols="50" rows="10" placeholder="Enter rating" required ="true" />
                        </div>
                        <div class="clearfix">
                            <button type="submit" class="">Rate user</button>
                        </div>
                </fieldset>
            </form:form>
            </p>
        </div>
    </div>
</body>
</html>
