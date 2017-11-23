<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <%--Bootstrap--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

    <title>Title</title>
</head>
<body>
    <sec:authorize access="isAuthenticated()">
        <p>Hello <sec:authentication property="principal.username" /></p>
    </sec:authorize>

    <a href="registration">Register</a>
    <a href="advert">Insert advertisment</a>

    <sec:authorize access="isAnonymous()">
        <a href="<c:url value="/login" />">Log in</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a href="<c:url value="/logout" />">Log out</a>
    </sec:authorize>
    <h1>TODO: sem vypsat inzeraty s pagination</h1>
</body>
</html>
