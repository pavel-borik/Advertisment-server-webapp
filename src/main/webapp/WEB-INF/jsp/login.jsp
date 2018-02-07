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

        <form method="POST" action="<c:url value='/login'/>">
            <fieldset>
                    <div class="input-field">
                        <input name="username" type="text" placeholder="Enter username" required ="true"/>
                    </div>
                    <div class="input-field">
                        <input name="password" type="password" placeholder="Enter Password" required ="true"/>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}"value="${_csrf.token}"/>

                    <div class="clearfix">
                        <button type="submit" class="btn red">Log in</button>
                    </div>
            </fieldset>
        </form>
    </div>

</body>
</html>
