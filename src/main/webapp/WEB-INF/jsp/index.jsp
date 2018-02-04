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
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/css/style2.css"/>
    <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css"  media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Advertisment server</title>
</head>
<body>
    <div class="container">
        <jsp:include page="menu.jsp"/>

        <div class="row">
            <div class="col l4">
                    <ul class="collection with-header">
                        <li class="collection-header"><h4>Categories</h4></li>

                        <c:forEach items="${categories}" var="categ">
                            <spring:url value="/adverts/categories/{categoryId}" var = "advertsCategoryUrl">
                                <spring:param name="categoryId" value="${categ.id}"/>
                            </spring:url>
                            <li class="collection-item"><a href="${fn:escapeXml(advertsCategoryUrl)}"><c:out value="${categ.name}"/></a></li>
                        </c:forEach>
                    </ul>

                <form class="searchad-form" action="/search" method="get">
                    <div class="row">
                        <div class="input-field">
                            <input type="text" name="q" required>
                        </div>
                        <button class="waves-effect waves-light btn red" type="submit">Search</button>
                    </div>
                </form>

            </div>
                <div class="col l8 advert-listing">
                    <jsp:useBean id="pagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder"/>
                    <%--pagination works relatively to current URL--%>
                    <c:url value="${requestScope['javax.servlet.forward.request_uri']}" var="pagedLink">
                        <c:param name="p" value="~"/>
                    </c:url>
                    <c:if test= "${fn:length(pagedListHolder.pageList) eq 0}">
                        <p>There are no advertisments for this category.</p>
                    </c:if>
                    <c:forEach items="${pagedListHolder.pageList}" var="ad">
                        <div class="card large">
                            <div class="card-image">
                                <img src="/resources/images/original/${ad.images[0].uuid}.jpg" alt=""/>
                            </div>
                            <div class="card-stacked">
                                <div class="card-content">
                                        <c:out value="${ad.category.name}"/>
                                        <p>
                                            <span class="ad-name">${ad.name}</span>
                                            <br/>
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
                                            <br/>
                                            Location: <c:out value="${ad.location}"/>
                                            <br/>
                                            Price: <c:out value="${ad.price}"/>
                                            <span class="right">Last edited: <fmt:formatDate pattern="dd. MM. yyyy HH:mm" dateStyle = "medium" timeStyle = "medium" value = "${ad.timestamp}" /></span>
                                        </p>
                                </div>
                                <div class="card-action">
                                    <spring:url value="/adverts/{advertId}" var = "advertUrl">
                                        <spring:param name="advertId" value="${ad.id}"/>
                                    </spring:url>
                                    <a href="${fn:escapeXml(advertUrl)}">Detail</a>
                                </div>
                            </div>
                        </div>

                    </c:forEach>
                    <tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"></tg:paging>
                </div>


         </div>
    </div>

    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/js/materialize.min.js"></script>

</body>
</html>
