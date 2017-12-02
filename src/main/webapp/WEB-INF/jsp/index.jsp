<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <%--Bootstrap--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

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

    <a href="<spring:url value="/registration" htmlEscape="true"/>">Register</a>
    <a href='<spring:url value="/advert/new" htmlEscape="true"/>'>Insert advertisment</a>
    <sec:authorize access="isAuthenticated()">
        <a href="<spring:url value="/logout" />">Log out</a>
    </sec:authorize>
    <sec:authorize access="isAnonymous()">
        <a href="<spring:url value="/login" />">Log in</a>
    </sec:authorize>

    <h1>TODO: sem vypsat inzeraty s pagination</h1>
</body>
</html>
