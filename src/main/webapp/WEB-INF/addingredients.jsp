<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add ingredients</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="header">
			<a href="/home"><h1>Magic Touch</h1></a>
			<div class="pt-2">
				<a href="/">Logout</a>
			</div>
		</div>
		<div class="content">
			<div class="nav row p-3">
				<a href="/recipes/new"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus-lg mt-2 mx-5 " viewBox="0 0 16 16">
				  <path d="M8 0a1 1 0 0 1 1 1v6h6a1 1 0 1 1 0 2H9v6a1 1 0 1 1-2 0V9H1a1 1 0 0 1 0-2h6V1a1 1 0 0 1 1-1z" class="col-2"/>
				</svg></a>
				<div class="input-group mb-3 col-9">
				  <input type="text" class="form-control" placeholder="Search Recipe" aria-label="Search Recipe" aria-describedby="button-addon2">
				  <button class="btn btn-outline-secondary" type="button" id="button-addon2">Button</button>
				</div>
			</div>
			<div>
				<h1 class="m-5 border-bottom pb-3">${recipe.title}</h1>
				<h2> Ingredients </h2>
				<div>
					<form:form  action="/recipes/${recipe.id}/addingredients" method="post" modelAttribute="ingredients">	
							<div>
								<div>
								    <form:label path="name">Name</form:label>
								    <form:errors path="name"/>
								    <form:input path="name"/>
							    </div>
							    <div>
								    <form:label path="amount">amount</form:label>
								    <form:errors path="amount"/>
								    <form:input path="amount" />
							    </div>				   
							    <button class="btn btn-info">ADD Ingredients</button>
							</div>		
						</form:form >
				</div>
				<div class="my-5">
					<a href="/recipes/${recipe.id}/addingredients" type="button" class="btn btn-info">Add More Ingredients</a>
					<a href="/recipes/${recipe.id}/addsteps" type="button" class="mx-3 btn btn-warning">Add Steps</a>
					<a href="/recipes/edit/${recipe.id}" type="button" class="btn btn-primary">Edit Recipe</a>
					<a href="/recipes/${recipe.id}" type="button" class="mx-3 btn btn-success">My Recipe</a>
					
				</div>
					
			</div>
		</div>
	</div>	
</body>
</html>