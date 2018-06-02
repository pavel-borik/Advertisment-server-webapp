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
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/resources/css/style2.css"/>
    <link type="text/css" rel="stylesheet" href="/resources/lightbox/css/lightbox.css"/>
    <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css"  media="screen,projection"/>

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>Advertisment server - Advertisment detail</title>
</head>
<body>
        <div class="container">
            <jsp:include page="menu.jsp"/>
            <h1>Advertisment detail</h1>
            <p>
                <h2>${advert.name}</h2>
                <p><b>Description:</b> ${advert.description}</p>
                <p><b>Location:</b> ${advert.location}</p>
                <p><b>Price:</b> ${advert.price}</p>
                <b>Pictures:</b><br>
                <c:forEach items="${advert.images}" var="image">
                    <a href="/resources/images/original/${image.uuid}.jpg" data-lightbox="advertisment-images"><img src="/resources/images/downscaled/${image.uuid}.jpg" width="200" height="150" alt=""/>
                    </a>
                </c:forEach>
            </p>
                <p><b>Posted by</b>:
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
                <div class="input-field">
                    <form:textarea path="commentText" class="materialize-textarea" placeholder="Enter a comment" required ="true" />
                </div>
                <div class="clearfix">
                    <sec:authorize access="isAuthenticated()">
                        <button type="submit" class="btn red" >Add comment</button>
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                        <button type="submit" class="btn red" disabled>Add comment</button><br>
                        <span class="red-text">You have to be logged in.</span>
                    </sec:authorize>
                </div>
            </fieldset>
        </form:form>
        </p>
        </div>

    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/js/materialize.min.js"></script>
    <script src="/resources/lightbox/js/lightbox.js"></script>

</body>
</html>
