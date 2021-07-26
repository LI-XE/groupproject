<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
 <div id="table1">
         <form action="/login" method="post">
				<h2>Login</h2>
				<span>${ error }</span>
				<div>
					<label for="email">Email</label>
					<input type="email" name="email" id="email" />
				</div>
				<div >
			        <label for="password">Password</label>
			        <input type="password" name="password" id="password"/>		        
			    </div>
			    <button>Login</button>
			</form>
			
			<h4><a href="/register">Register</a></h4>
			</div>

</body>
</html>