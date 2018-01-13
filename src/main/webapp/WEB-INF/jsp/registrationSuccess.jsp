<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--Bootstrap--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet">
    <title>Title</title>
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
