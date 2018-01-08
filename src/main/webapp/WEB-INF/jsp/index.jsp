<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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

        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample08" aria-controls="navbarsExample08" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <sec:authorize access="isAuthenticated()">
                            <a class="nav-link" href="<spring:url value="/logout" />">Log out</a>
                        </sec:authorize>
                        <sec:authorize access="isAnonymous()">
                            <a class="nav-link" href="<spring:url value="/login" />">Log in</a>
                        </sec:authorize>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<spring:url value="/registration" htmlEscape="true"/>">Register</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href='<spring:url value="/adverts/new" htmlEscape="true"/>'>Insert advertisement</a>
                    </li>
                    <%-- <li class="nav-item dropdown">
                         <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown08" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
                         <div class="dropdown-menu" aria-labelledby="dropdown08">
                             <a class="dropdown-item" href="#">Action</a>
                             <a class="dropdown-item" href="#">Another action</a>
                             <a class="dropdown-item" href="#">Something else here</a>
                         </div>
                     </li>--%>
                </ul>
        </nav>

    <h1>Dodělám: vylepším styling + pagination</h1>

    <div class="container">
        <div class="row">
    <div class="col-md-6 col-md-offset-3">
        <div class="inzeratyContainer">

            <c:forEach items="${adverts}" var="ad">
                <div class="inzeratBody">
                    <div class="InzeratNadpis">
                        <h2>
                            <spring:url value="/adverts/{advertId}" var = "advertUrl">
                                <spring:param name="advertId" value="${ad.id}"/>
                            </spring:url>
                            <a href="${fn:escapeXml(advertUrl)}"><c:out value="${ad.name}"/></a>
                        </h2>
                    </div>
                    <div class="category">
                        <c:out value="${ad.category.name}"/>
                    </div>
                    <div class="box">

                        <div class="InzeratText">
                            <p>
                               Description: <c:out value="${ad.description}"/>
                            </p>
                        </div>
                    </div>
                    Location: <c:out value="${ad.location}"/>
                    <br/>
                    Price: <c:out value="${ad.price}"/>
                    <br/>Added: <fmt:formatDate pattern="dd. MM. yyyy HH:mm" dateStyle = "medium" timeStyle = "medium" value = "${ad.timestamp}" />
                    <img src="/advertImage/imageDisplay?advertId=${ad.id}" height="160" width = "160"/>
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
