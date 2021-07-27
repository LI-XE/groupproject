<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

		<form:form  action="/Create" method="post" modelAttribute="recipe">	
		<form:input type="hidden" value="${user_id}" path="user"/>
				<div>
			    <form:label path="title">Title</form:label>
			    <form:errors path="title"/>
			    <form:input path="title"/>
			    </div>
			    <div>
			    <form:label path="photo">photo URL</form:label>
			    <form:errors path="photo"/>
			    <form:input path="photo" />
			    </div>
			    <div>
			    <form:label path="description">Description</form:label>
			    <form:errors path="description"/>
			    <form:input path="description" />
				</div>
			   
			    <button>create</button>
		</form:form >
		
		<h2> Ingredients </h2>
		<form:form  action="/Create" method="post" modelAttribute="ingredients">	
		<form:input type="hidden" value="${user_id}" path="user"/>
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
			    <button>ADD</button>
		</form:form >
		
		<h2> Steps </h2>
		<form:form  action="/Create" method="post" modelAttribute="steps">	
		<form:input type="hidden" value="${user_id}" path="user"/>
				<div>
			    <form:label path="name">Name</form:label>
			    <form:errors path="name"/>
			    <form:input path="name"/>
			    </div>
			    <div>
			    <form:label path="description">description</form:label>
			    <form:errors path="description"/>
			    <form:input path="description" />
			    </div>			   
			    <button>ADD</button>
		</form:form >

</body>
</html>