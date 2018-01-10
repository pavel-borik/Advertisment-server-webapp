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
    <title>Title</title>
</head>
<body>
    <sec:authorize access="isAuthenticated()">
        <p>Hello
            <spring:url value="/users/{userId}" var = "userUrl">
                <spring:param name="userId" value="${userId}"/>
            </spring:url>
            <a href="${fn:escapeXml(userUrl)}"><sec:authentication property="principal.username" /></a>
        </p>
    </sec:authorize>

    <h3>User detail</h3>
    <p>
        Username: ${user.username}<br>
        First name: ${user.firstname}<br>
        Last name: ${user.surname}<br>
        E-mail: ${user.email}<br>
        Phone: ${user.phone}<br>
        Registered: <fmt:formatDate pattern="dd. MM. yyyy HH:mm" dateStyle = "medium" timeStyle = "medium" value = "${user.creationTime}" /><br>
        <br>
    </p>

    User's adverts:
    <c:forEach items="${adverts}" var="advert">
        <p>
            <spring:url value="/adverts/{advertId}" var = "advertUrl">
                <spring:param name="advertId" value="${advert.id}"/>
            </spring:url>
            <a href="${fn:escapeXml(advertUrl)}"><c:out value="${advert.name}"/></a>

            <%--Logged in user can edit his adverts--%>
            <c:if test = "${isLoggedUsersProfile}">
                <spring:url value="/adverts/{advertId}/edit" var = "editAdvert">
                     <spring:param name="advertId" value="${advert.id}"/>
                </spring:url>
                <a href="${fn:escapeXml(editAdvert)}">Edit</a>
            </c:if>
        </p>
    </c:forEach>

    User's ratings:
    <c:forEach items="${ratings}" var="rating">
        <p>
        <spring:url value="/users/{userId}" var = "userUrl">
            <spring:param name="userId" value="${rating.author.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(userUrl)}"><c:out value="${rating.author.username}"/></a>
        <fmt:formatDate pattern="dd. MM. yyyy HH:mm" dateStyle = "medium" timeStyle = "medium" value = "${rating.postDate}" /><br>
        <br>
        <c:out value="${rating.ratingText}"/>
        </p>
    </c:forEach>

    <p>
    <form:form method="POST" modelAttribute="addedRating">
        <fieldset>
            <div class="container">
                <div class="form-group">
                    <form:textarea path="ratingText" class="form-control" cols="50" rows="10" placeholder="Enter rating" required ="true" />
                </div>
                <div class="clearfix">
                    <button type="submit" class="">Rate user</button>
                </div>
            </div>
        </fieldset>
    </form:form>
    </p>
</body>
</html>
