<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <%--Bootstrap--%>

        <link href="css/bootstrap.min.css" rel="stylesheet">

    <title>Title</title>
</head>
<body>
<div class="bodyContainer">
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

    <div class="container">
        <div class="row">
    <div class="col-md-6 col-md-offset-3">
        <div class="inzeratyContainer">

    <c:forEach items="${adverts}" var="ad">
        <div class="inzeratBody">
        <div class="InzeratNadpis">
            <h2>
                    <c:out value="${ad.name}" />
               </h2>
        </div>

        <c:out value="${ad.category.name}" />
        <div class="box">

         <div class="InzeratText">
            <p>
           <c:out value="${ad.description}" />
            </p>
        </div>
        </div>
        <c:out value="${ad.location}" />
        </div>
    </c:forEach>

        </div>
    </div>
        </div>
    </div>
</div>
</body>
</html>
