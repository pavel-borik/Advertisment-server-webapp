<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="/resources/css/style2.css"/>
    <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css"  media="screen,projection"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <title>Advertisment server - Registration</title>

</head>
<body>
    <div class="container">
        <jsp:include page="menu.jsp"/>

            <h1>Register:</h1>
            <form:form method="POST" modelAttribute="user">
                <fieldset>
                        <div class="form-group">
                            <label><b>Username</b><span class="text-danger">*</span></label>
                            <form:input path="username" class="form-control" type="text" placeholder="Enter username" required ="true"/>
                        </div>
                        <div class="form-group">
                            <label><b>Password</b><span class="text-danger">*</span></label>
                            <form:input path="password" class="form-control" type="password" placeholder="Enter password" id="psw" required ="true"/>
                        </div>
                        <div class="form-group">
                            <label><b>Repeat Password</b><span class="text-danger">*</span></label>
                            <form:input path="passwordRepeat" class="form-control" type="password" placeholder="Repeat password" id="psw-repeat" required ="true"/>
                        </div>
                        <div class="form-group">
                            <label><b>Email</b><span class="text-danger">*</span></label>
                            <form:input path="email" class="form-control" type="email" placeholder="Enter Email" required="true" />
                        </div>
                        <div class="form-group">
                            <label><b>First name</b><span class="text-danger">*</span></label>
                            <form:input path="firstname" class="form-control" type="text" placeholder="Enter first name" required ="true"/>
                        </div>
                        <div class="form-group">
                            <label><b>Surname</b><span class="text-danger">*</span></label>
                            <form:input path="surname" class="form-control" type="text" placeholder="Enter surname" required ="true"/>
                        </div>
                        <div class="form-group">
                            <label><b>Phone number</b></label>
                            <form:input path="phoneNumber" class="form-control" type="text" placeholder="Enter phone number"/>
                        </div>
                        <p><span class="text-danger">* = required field</span></p>
                        <div class="clearfix">
                            <a href="/"> <button type="button"  class="cancelbtn">Cancel</button></a>
                            <button type="submit" class="signupbtn">Sign Up</button>
                        </div>
                </fieldset>
            </form:form>
    </div>
<script>
    var password = document.getElementById("psw"),
        confirm_password = document.getElementById("psw-repeat");

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
