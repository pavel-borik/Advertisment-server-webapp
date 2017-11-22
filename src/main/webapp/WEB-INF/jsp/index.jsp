<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <%--Bootstrap--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">

    <title>Title</title>
</head>
<body>
    <a href="registration">Register</a>
    <a href="advert">Insert advertisment</a>
    <a href="<c:url value="/login" />">Log in</a>
    <a href="<c:url value="/logout" />">Log out</a>
    <h1>TODO: sem vypsat inzeraty s pagination</h1>
</body>
</html>
