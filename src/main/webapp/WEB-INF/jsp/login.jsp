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

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <title>Title</title>

</head>
<body>
<form:form method="POST" modelAttribute="user">
    <fieldset>
        <div class="container">

            <div class="form-group">
                <label><b>Username</b></label>
                <form:input path="username" class="form-control" type="text" placeholder="Enter username" required ="true"/>
            </div>
            <div class="form-group">
                <label><b>Password</b></label>
                <form:input path="password" class="form-control" type="password" placeholder="Enter Password" id="psw" required ="true"/>
            </div>

            <div class="clearfix">
                <button type="submit" class="signupbtn">Log in</button>
            </div>
        </div>
    </fieldset>
</form:form>

<script>
    var password = document.getElementById("psw")
        , confirm_password = document.getElementById("psw-repeat");

    function validatePassword() {
        if (password.value != confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Don't Match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
</script>
</body>
</html>
