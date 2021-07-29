<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
 
 <div id="wrapper">
 				<h1  class="header">Magic Touch</h1>
 				
 				<div id="sub_wrapper">
				
				
				<div class="divider"></div>
 
 <div id="table1">
 <h2>Register</h2>
<form:form  action="/register" method="post" modelAttribute="user">
		
			 	<div >
			        <form:label path="user_name">Name</form:label>
			        <form:errors path="user_name"/>
			        <form:input  path="user_name" />
			    </div>
			    <div>
			        <form:label path="email">Email</form:label>
			        <form:errors path="email"/>
			        <form:input type="email" path="email" />
			    </div>
			     <div>
			        <form:label path="password">Password</form:label>
			        <form:errors path="password"/>
			        <form:password path="password" />
			    </div>
			    <div>
			        <form:label path="passwordConfirmation">Confirm Password</form:label>
			        <form:errors path="passwordConfirmation"/>
			        <form:password path="passwordConfirmation" />
			    </div>
			    <button>Register</button>
			</form:form>
			
			<h4><a href="/login">login</a></h4>
			</div>
			</div>
			</div>
			
			
</body>
</html>