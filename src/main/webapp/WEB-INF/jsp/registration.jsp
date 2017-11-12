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
</head>
<body>
<form:form method="POST" modelAttribute="user">
    <fieldset>
        <div class="container">
            <label><b>Email</b></label>
            <form:input path="email" type="text" placeholder="Enter Email" required="true" />

            <label><b>Password</b></label>
            <form:input path="password" type="password" placeholder="Enter Password" id="psw" required ="true"/>

            <label><b>Repeat Password</b></label>
            <input type="password" placeholder="Repeat Password" id="psw-repeat" required ="true">

            <div class="clearfix">
                <button type="button"  class="cancelbtn">Cancel</button>
                <button type="submit" class="signupbtn">Sign Up</button>
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
