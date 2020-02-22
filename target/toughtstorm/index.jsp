<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>--%>
<%--<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>ToughtStorm::Login</title>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<script>
    const required = 'This field is required.';
    $( document ).ready(function () {

        $("#login-card").show();
        $("#register-card").hide();

        $("#show-login").click(function () {
            $("#register-card").hide();
            $("#login-card").show();
        });
        $("#show-register").click(function () {
            $("#login-card").hide();
            $("#register-card").show();
        });


    });


</script>
<body>
<div class="font-weight-bold mx-auto my-5" style="width:60rem;">
    <h1>ToughtStorm</h1>
</div>

<!--Login card-->
<div class="card bg-light shadow-sm
  rounded mx-auto my-auto" style="width:25rem;" id="login-card">
    <div class="card-body">
        <div class="pb-4">
            <div class="border-bottom border-black">
                <h5>Login</h5>
            </div>
        </div>

        <form class="px-3" action="login" method="post">
            <div class="form-group">
                <!-- <label id="username-label" for = "username-input">Username: </label> -->
                <input type="username" class="form-control" id="login-username-input" name="login-username-input" placeholder="Username">
            </div>
            <div class="form-group">
                <!-- <label id="password-label" for = "password-input">Password: </label> -->
                <input type="password" class="form-control" id="login-password-input" name="login-password-input" placeholder="Password">
            </div>
            <button type="button" class="btn btn-outline-primary" style="background:none; border:none;"
                    id="show-register">Are you new here?
            </button>
            <button type="submit" name="submit" value="submit" class="btn btn-outline-dark border border-dark float-right"> Enter</button>

        </form>
    </div>
</div>
<!--Register card-->
<div class="card bg-light shadow-sm
  rounded mx-auto my-auto" style="width:25rem;" id="register-card">
    <div class="card-body">
        <div class="pb-4">
            <div class="border-bottom border-black">
                <h5>Register</h5>
            </div>
        </div>

        <form class="px-3" action="register" method="post">
            <div class="form-group">
                <label id="username-label" for="register-username-input">Username: </label>
                <input type="username" class="form-control" id="register-username-input" name="register-username-input">
            </div>
            <div class="form-group">
                <label id="password-label" for="register-password-input">Password: </label>
                <input type="password" class="form-control" id="register-password-input" name="register-password-input">
            </div>
            <div class="form-group">
                <label id="dob-label" for="register-dob-input">Date of birth: </label>
                <input type="date" class="form-control" id="register-dob-input" name="register-dob-input">
            </div>
            <div class="form-group">
                <label id="gender-label" for="register-gender-select">Gender: </label>
                <select class="form-control" id="register-gender-select" name="register-gender-select">
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other" selected="selected">Other</option>
                </select>
            </div>
            <div class="form-group">
                <label id="shortBio-label" for="register-shortBio-textarea">Short bio: </label>
                <textarea class="form-control" id="register-shortBio-textarea" name="register-shortBio-textarea" rows="4"></textarea>
            </div>
            <button type="button" class="btn btn-outline-primary" style="background:none; border:none;" id="show-login">
                Already have an account?
            </button>
            <button type="submit" name="submit" value="submit" class="btn btn-outline-dark border border-dark float-right" id="register-submit"> Join
                Us
            </button>
        </form>
    </div>
</div>
</body>
</html>
