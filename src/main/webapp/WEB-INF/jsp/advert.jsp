<%--
  Created by IntelliJ IDEA.
  User: pb
  Date: 12.11.17
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body>
<form:form method="POST" modelAttribute="advert">
    <fieldset>

        <div class="container">
            <div class="form-group">
            <label><b>Name</b></label>
            <form:input path="name" class="form-control" type="text" placeholder="Enter Name" required="true" />
            </div>

            <div class="form-group">
            <label><b>Location</b></label>
            <form:input path="location" class="form-control" type="text" placeholder="Enter Location" required="true" />
            </div>

            <div class="form-group">
            <label><b>Description</b></label>
            <form:input path="description" class="form-control" type="textarea" placeholder="Description" required ="true"/>
            </div>

            <div class="form-group">
                <label><b>Image</b></label>
                <form:input path="image" class="form control file" type="file" />
            </div>

            <div class="clearfix">
                <button type="button"  class="cancelbtn">Cancel</button>
                <button type="submit" class="createbtn">Create</button>
            </div>
        </div>
    </fieldset>
</form:form>


</body>
</html>
