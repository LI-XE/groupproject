<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Recipe Detail Page</title>
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
			<div class="recipe">
				<h1>Recipe Name: ${ recipe.title }</h1>
				<div class="recipe_img mt-3">Image</div>
				<ul class="info d-flex justfity-content-around ">
					<li class="menu mx-5"><p>Comments:${ recipe.comments.size() }</p></li>
					<%-- <li class="menu mx-5"><p>Followed: ${ recipe.followingRecipe.size() }</p></li> --%>
					<li class="menu mx-5"><p>Likes: ${ recipe.likers.size() }</p></li>
				</ul>
				<h5>Author: ${ recipe.author.username }</h5>
				<h3 class="my-3">Description:</h3> <p> ${ recipe.description }</p>
				<div class="row my-3">
				<h3 class="d-inline col-3">Ingredinets: </h3> 
				<div class="d-inline col-6">
					<c:forEach items="${ recipe.ingredients }" var="ingredient">
						<p>${ ingredient.name } - ${ ingredient.amount }</p>	
					</c:forEach>
				</div>
				</div>
				<div class="my-3">
					<h3>Steps: </h3>
					<div class="d-inline col-6">
						<c:forEach items="${ recipe.steps }" var="step">
							<p>${ step.name }</p>
							<div>img</div>
							<p>${ step.description }</p>
						</c:forEach>
					</div>
				</div>
				<div>
				<c:if test="${user.id == recipe.author.id}">
				<a href="/myrecipes" class="btn btn-warning">Edit Recipe</a>
				</c:if>
				</div>
				<hr>
				<div>
					<form:form method="POST" action="/recipes/${recipe.id}/comment" modelAttribute="comment">
					    <p>
				            <form:label path="comment">Comment:</form:label>
				            <form:errors path="comment"/>
				            <form:input path="comment"/>
				     
				        </p>
				        <input type="submit" class="btn btn-primary" value="Post"/>
					</form:form>
					<div>
						<c:forEach items="${ recipe.comments }" var="comment">
							<div>
								<p>${ comment.comment }</p>
								<p>${ currentTime }
								<p>posted by: ${ comment.CommentPoster.username }</p>
							</div>
							
						</c:forEach>
					</div>
				</div>			
			</div>
		</div>
	</div>
</body>
</html>