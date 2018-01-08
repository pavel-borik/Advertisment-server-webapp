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

    <h3>Advert detail</h3>
    <p>
        <h4>${advert.name}</h4>
        <p>Description:${advert.description}</p>
        <p>Location: ${advert.location}</p>
        <p>Price: ${advert.price}</p>
        <p>Posted by:
        <spring:url value="/users/{userId}" var = "userUrl">
            <spring:param name="userId" value="${advert.user.id}"/>
        </spring:url>
        <a href="${fn:escapeXml(userUrl)}"><c:out value="${advert.user.username}"/></a><br>
        <fmt:formatDate pattern="dd. MM. yyyy HH:mm" dateStyle = "medium" timeStyle = "medium" value = "${advert.timestamp}" /><br>
        </p>
    </p>

    <h3>Comments:</h3>
    <c:forEach items="${comments}" var="comment">
        <p>
            <spring:url value="/users/{userId}" var = "userUrl">
                <spring:param name="userId" value="${comment.author.id}"/>
            </spring:url>
            <a href="${fn:escapeXml(userUrl)}"><c:out value="${comment.author.username}"/></a>
            <fmt:formatDate pattern="dd. MM. yyyy HH:mm" dateStyle = "medium" timeStyle = "medium" value = "${comment.postDate}" /><br>
            <c:out value="${comment.commentText}"/><br>
        </p>
    </c:forEach>


    <p>
    <form:form method="POST" modelAttribute="addedComment">
    <fieldset>
        <div class="container">
            <div class="form-group">
                <form:textarea path="commentText" class="form-control" cols="50" rows="10" placeholder="Enter comment" required ="true" />
            </div>
            <div class="clearfix">
                <button type="submit" class="">Add comment</button>
            </div>
        </div>
    </fieldset>
    </form:form>
    </p>
</body>
</html>
