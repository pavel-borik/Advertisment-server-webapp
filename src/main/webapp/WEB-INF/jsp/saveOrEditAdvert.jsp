<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css"  media="screen,projection"/>
    <link type="text/css" rel="stylesheet" href="/css/style2.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>Advertisment server - Add/Edit advert</title>

</head>
<body>
    <div class="container">
        <jsp:include page="menu.jsp"/>

            <p><h1>Add/Edit advertisment</h1></p>

            <form:form method="POST" modelAttribute="advertDto" enctype="multipart/form-data">
            <fieldset>
                <div class="input-field">
                    <label><b>Name</b></label>
                    <form:input path="name" class="validate" type="text" placeholder="Enter Name" required="true" />
                </div>

                <div class="input-field">
                    <label><b>Category</b></label><br>
                    <form:select name="category" path ="category">
                        <c:forEach items="${categories}" var="categ">
                            <form:option value="${categ.name}" />
                        </c:forEach>
                    </form:select>
                </div>

                <div class="input-field">
                    <label><b>Location</b></label>
                    <form:input path="location" class="" type="text" placeholder="Enter location" required="true" />
                </div>

                <div class="input-field">
                    <label><b>Description</b></label>
                    <form:textarea path="description" class="materialize-textarea" placeholder="Enter description" required ="true" />
                </div>

                <div class="input-field">
                    <label><b>Price</b></label>
                    <form:input path="price" class="materialize-textarea" placeholder="Price" required ="true"/>
                </div>

                <div class="file-field input-field">
                    <label><b>Image</b></label>
                </div>
                <div class="file-field input-field">
                    <div class="btn red lighten-3">
                        <span>Choose images</span>
                        <form:input path="mpf" type="file" multiple="true" />
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text">
                    </div>
                </div>
                <div class="clearfix"></div>

                <a class="btn red" role ="button" href='<spring:url value="/" htmlEscape="true"/>'>Cancel</a>
                <button type="submit" class="btn green" role="button">Submit</button>

            </fieldset>
        </form:form>


    </div>


<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/js/materialize.min.js"></script>
<script>
    $(document).ready(function() {
        $('select').material_select();
    });
</script>
</body>
</html>
