<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
                integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet">
    <title>Advertisment server - Advertisment detail</title>
</head>
<body>
    <div class="bodyContainer">
        <div class="container">
            <jsp:include page="menu.jsp"/>

            <h1>Advertisment detail</h1>
            <p>
                <h2>${advert.name}</h2>
                <p>Description: ${advert.description}</p>
                <p>Location: ${advert.location}</p>
                <p>Price: ${advert.price}</p>
            <p>Pictures:<br>
                <span class="img-responsive"> <img src="/advertImage/imageDisplay?advertId=${advert.id}" alt=""/></span>
            </p>
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
                <div class="form-group">
                    <form:textarea path="commentText" class="form-control" rows="5" placeholder="Enter comment" required ="true" />
                </div>
                <div class="clearfix">
                    <button type="submit" class="">Add comment</button>
                </div>
            </fieldset>
        </form:form>
        </p>
        </div>
    </div>
</body>
</html>
