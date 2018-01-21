<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<sec:authorize access="isAuthenticated()">
    <p>Hello
        <spring:url value="/users/{userId}" var = "userUrl">
            <spring:param name="userId" value="${loggedUserId}"/>
        </spring:url>
        <a href="${fn:escapeXml(userUrl)}"><sec:authentication property="principal.username" /></a>
    </p>
</sec:authorize>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample08" aria-controls="navbarsExample08" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <ul class="navbar-nav mr-auto">
        <li class="nav-item">
        <a class="nav-link" href='<spring:url value="/" htmlEscape="true"/>'>Home</a>
        </li>
        <sec:authorize access="isAuthenticated()">
            <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/logout" />">Log out</a>
            </li>
            <li class="nav-item">
                <spring:url value="/users/{userId}" var = "userUrl">
                    <spring:param name="userId" value="${loggedUserId}"/>
                </spring:url>
                <a class="nav-link" href="${fn:escapeXml(userUrl)}"/>My profile</a>
            </li>
        </sec:authorize>
        <sec:authorize access="isAnonymous()">
            <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/login" />">Log in</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<spring:url value="/registration" htmlEscape="true"/>">Register</a>
            </li>
        </sec:authorize>
        <li class="nav-item">
            <a class="nav-link" href='<spring:url value="/adverts/new" htmlEscape="true"/>'>Insert advertisement</a>
        </li>
    </ul>
    <form class="form-inline my-2 my-lg-0" action="/search" method="get">
        <input class="form-control mr-sm-2" type="text" name="q" >
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search advert</button>
    </form>
</nav>