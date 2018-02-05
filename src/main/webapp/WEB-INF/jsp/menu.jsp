<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<nav>
    <div class="nav-wrapper">
        <ul class="left">
            <li>
                <a class="nav-link" href='<spring:url value="/" htmlEscape="true"/>'>Home</a>
            </li>
            <sec:authorize access="isAuthenticated()">
                <li class="">
                    <a class="" href="<spring:url value="/logout" />">Log out</a>
                </li>
                <li class="nav-item">
                    <spring:url value="/users/{userId}" var = "userUrl">
                        <spring:param name="userId" value="${loggedUserId}"/>
                    </spring:url>
                    <a class="nav-link" href="${fn:escapeXml(userUrl)}"/>My profile</a>
                </li>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <li class="">
                    <a class="nav-link" href="<spring:url value="/login" />">Log in</a>
                </li>
                <li class="">
                    <a class="nav-link" href="<spring:url value="/registration" htmlEscape="true"/>">Register</a>
                </li>
            </sec:authorize>
            <li class="nav-item">
                <a class="nav-link" href='<spring:url value="/adverts/new" htmlEscape="true"/>'>Insert advertisement</a>
            </li>
        </ul>

        <div id="welcome-msg-user" class="right">
            <sec:authorize access="isAuthenticated()">
                    <spring:url value="/users/{userId}" var = "userUrl">
                        <spring:param name="userId" value="${loggedUserId}"/>
                    </spring:url>
                    <span>Hello <a href="${fn:escapeXml(userUrl)}"><sec:authentication property="principal.username" /></a></span>
            </sec:authorize>
        </div>

    </div>

</nav>