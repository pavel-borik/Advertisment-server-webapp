<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <%--Bootstrap--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link href="/css/style.css" rel="stylesheet">
    <title>Advertisment server</title>
</head>
<body>
<div class="bodyContainer">
    <jsp:include page="menu.jsp"/>
    <div class="row">
        <div class="col-md-3">
            <div class="box">
                <h3 class="mt-0">Categories:</h3>
                <c:forEach items="${categories}" var="categ">
                    <spring:url value="/adverts/categories/{categoryId}" var = "advertsCategoryUrl">
                        <spring:param name="categoryId" value="${categ.id}"/>
                    </spring:url>
                    <p><a href="${fn:escapeXml(advertsCategoryUrl)}"><c:out value="${categ.name}"/></a></p>
                </c:forEach>
            </div>
        </div>
        <div class="col-md-8">
            <section id="one" class="wrapper style1">
                <div class="inner">
                    <jsp:useBean id="pagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder"/>
                    <%--pagination works relatively to current URL--%>
                    <c:url value="${requestScope['javax.servlet.forward.request_uri']}" var="pagedLink">
                        <c:param name="p" value="~"/>
                    </c:url>
                    <c:if test= "${fn:length(pagedListHolder.pageList) eq 0}">
                        <p>No advertisment found.</p>
                    </c:if>
                    <c:forEach items="${pagedListHolder.pageList}" var="ad">

                        <article class="feature left">
                            <div class="content">
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
                                                <%--If the description is longer than a certain value, abbreviation is applied--%>
                                            <c:set var="desc" value="${ad.description}" />

                                            <c:choose>
                                                <c:when test="${fn:length(desc) > 50}">
                                                    <b>Description:</b> <c:out value="${fn:substring(desc,0,50)}"/>...
                                                </c:when>

                                                <c:otherwise>
                                                    <b>Description:</b> <c:out value="${ad.description}"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                    </div>
                                </div>
                                <span class="img-responsive pull-right"> <img src="/advertImage/imageDisplay?advertId=${ad.id}" alt=""/></span>
                                Location: <c:out value="${ad.location}"/>
                                <br/>
                                Price: <c:out value="${ad.price}"/>
                                <br/>Last edited: <fmt:formatDate pattern="dd. MM. yyyy HH:mm" dateStyle = "medium" timeStyle = "medium" value = "${ad.timestamp}" />
                                <ul class="actions">
                                    <li>
                                        <a href="${fn:escapeXml(advertUrl)}" class="button alt">More</a>
                                    </li>
                                </ul>
                            </div>
                        </article>

                    </c:forEach>
                </div>
            </section>
            <tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"></tg:paging>
        </div>
    </div>
</div>
</body>
</html>
