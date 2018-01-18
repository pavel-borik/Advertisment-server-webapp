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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet">
    <title>Title</title>

</head>
<body>
<div class="bodyContainer">
    <div class="container">
        <jsp:include page="menu.jsp"/>

            <p><h1>Add/Edit advertisment</h1></p>

            <form:form method="POST" modelAttribute="advert" enctype="multipart/form-data">
            <fieldset>
                <div class="form-group">
                    <label><b>Name</b><span class="text-danger">*</span></label>
                    <form:input path="name" class="form-control" type="text" placeholder="Enter Name" required="true" />
                </div>

                <div class="form-group">
                    <label><b>Category</b><span class="text-danger">*</span></label><br>
                    <form:select name="category" path ="category">
                        <c:forEach items="${categories}" var="categ">
                            <form:option value="${categ.name}" />
                        </c:forEach>
                    </form:select>
                </div>

                <div class="form-group">
                    <label><b>Location</b><span class="text-danger">*</span></label>
                    <form:input path="location" class="form-control" type="text" placeholder="Enter Location" required="true" />
                </div>

                <div class="form-group">
                    <label><b>Description</b><span class="text-danger">*</span></label>
                    <form:textarea path="description" class="form-control" rows="10" placeholder="Description" required ="true" />
                </div>

                <div class="form-group">
                    <label><b>Price</b><span class="text-danger">*</span></label>
                    <form:input path="price" class="form-control" type="textarea" placeholder="Price" required ="true"/>
                </div>

                <div class="form-group">
                    <label><b>Image</b></label>
                    <form:input path="mpf" class="form control file" type="file" />
                </div>
                <p><span class="text-danger">* = required field</span></p>

                <div class="clearfix">
                    <a class="btn btn-default" role ="button" href='<spring:url value="/" htmlEscape="true"/>'>Cancel</a>
                    <button type="submit" class="btn btn-primary" role="button">Submit</button>
                </div>
            </fieldset>
        </form:form>
    </div>
</div>
</body>
</html>
