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

        <link href="css/style.css" rel="stylesheet">
    <title>Title</title>
</head>
<div>
    <div class="bodyContainer">
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

    <h1>Dodělám: vylepším styling + pagination</h1>

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
            <br/>
            <c:out value="${ad.timestamp}" />
        </div>
    </c:forEach>

        </div>
    </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
