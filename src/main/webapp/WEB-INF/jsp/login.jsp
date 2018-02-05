<%--
  Created by IntelliJ IDEA.
  User: pb
  Date: 12.11.17
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/resources/css/style2.css"/>
    <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css"  media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href="/css/style.css" rel="stylesheet">
    <title>Advertisment server - Login</title>
</head>
<body>
    <div class="container">
        <jsp:include page="menu.jsp"/>
        <h1>Log in:</h1>

        <form:form method="POST" modelAttribute="user">
            <fieldset>
                    <div class="form-group">
                        <label><b>Username</b></label>
                        <form:input path="username" class="form-control" type="text" placeholder="Enter username" required ="true"/>
                    </div>
                    <div class="form-group">
                        <label><b>Password</b></label>
                        <form:input path="password" class="form-control" type="password" placeholder="Enter Password" id="psw" required ="true"/>
                    </div>

                    <div class="clearfix">
                        <button type="submit" class="btn red">Log in</button>
                    </div>
            </fieldset>
        </form:form>
    </div>

</body>
</html>
