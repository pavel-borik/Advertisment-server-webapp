<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/css/style2.css"/>
    <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css"  media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href="css/style.css" rel="stylesheet">
    <title>Advertisment server</title>
</head>
<body>
<div class="bodyContainer">
    <div class="container">
        <jsp:include page="menu.jsp"/>
        <p>
            Thank you for registering!
        </p>
        <a href="<spring:url value="/" />">Back to Index</a>
        <a href="<spring:url value="/adverts/new" />">Create an advert</a>
    </div>
</div>

</body>
</html>
