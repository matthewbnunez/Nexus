<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Post</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<h1>Add a Book to Your Shelf!</h1>
<form:form action="/home/post/process" method="POST" modelAttribute="newShare">

<div>
	<form:label path="theShare">Tweet: </form:label>
	<form:input path="theShare"/>
	<form:errors path="theShare"/>
</div>


<form:hidden path="user" value="${ userId }" />

<button type="submit" class="bg-blue-500 rounded-lg p-2 text-xl text-white my-3">Submit</button>

</form:form>
</body>
</html>