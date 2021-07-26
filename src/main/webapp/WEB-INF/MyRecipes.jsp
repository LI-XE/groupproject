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

<table>
<tr>
<th>Title</th>
<th>Continue</th>
</tr>
<tbody>
<c:forEach items="${AllRecipes}" var="recipe">
<tr>
<td class="elements">${recipe.title}</td>
<td class="elements"><a href="/Create${title.id}">add</a></td>
</tr>
</c:forEach>
</tbody>
</table>

</html>