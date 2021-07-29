<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Recipe</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
</head>
	<div class="container">
		<div class="header">
			<a href="/home"><h1>Magic Touch</h1></a>
		</div>
		<div class="content">
		<div class="row center">
			<div class="col">
				<h1>Register!</h1>
			    <form:form method="POST" action="/registration" modelAttribute="user">
			    <p>
		            <form:label path="username">Name:</form:label>
		            <form:errors path="username"/>
		            <form:input path="username"/>
		        </p>
		        <p>
		            <form:label path="email">Email:</form:label>
		            <form:errors path="email"/>
		            <form:input type="email" path="email"/>
		        </p>
		        <p>
		            <form:label path="password">Password:</form:label>
		            <form:errors path="password"/>
		            <form:password path="password"/>
		        </p>
		        <p>
		            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
		            <form:password path="passwordConfirmation"/>
		        </p>
		        <input type="submit" value="Register"/>
			    </form:form>
			</div>
			<div class="col">
				<h1>Login</h1>
			    <p><c:out value="${loginError}"/></p>
			    <form method="POST" action="/login">
			        <p>
			            <label for="loginEmail">Email</label>
			            <input type="email" id="loginEmail" name="loginEmail"/>
			        </p>
			        <p>
			            <label for="loginPassword">Password</label>
			            <input type="password" id="loginPassword" name="loginPassword"/>
			        </p>
			        <input type="submit" value="Login"/>
			    </form>
			</div>
		</div>
		</div>
	</div>
</body>
</html>