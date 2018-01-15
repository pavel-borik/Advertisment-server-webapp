<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link href="css/style.css" rel="stylesheet">
    <title>Title</title>
</head>
<body>
    <div class="bodyContainer">
    <jsp:include page="menu.jsp"/>
    <h1>Dodělám: vylepším styling + pagination</h1>

        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="inzeratyContainer">
                        <jsp:useBean id="pagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder"/>
                            <c:url value="/" var="pagedLink">
                                <c:param name="p" value="~"/>
                            </c:url>
                        <c:forEach items="${pagedListHolder.pageList}" var="ad">
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
                                <br/>Last edited: <fmt:formatDate pattern="dd. MM. yyyy HH:mm" dateStyle = "medium" timeStyle = "medium" value = "${ad.timestamp}" />
                                <img src="/advertImage/imageDisplay?advertId=${ad.id}"/>
                            </div>
                        </c:forEach>

                    </div>
                     <tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}"></tg:paging>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
