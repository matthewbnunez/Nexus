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
<title>Nexus Login/Registration</title>
<script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
    <div class="container">
        <h1 class="text-center text-8xl my-5">Nexus</h1>
		<div class="flex justify-evenly">
            <div class="flex flex-col">
                <h2 class="text-3xl my-3 mx-auto">Register</h2>
                <form:form action="/register" method="POST" modelAttribute="newUser" class="flex flex-col">
                    <div class="my-3 text-xl flex flex-col bg-blue-100">
                        <form:label path="userName" class="text-sm">User Name</form:label>
                        <form:input path="userName" class="ml-auto bg-blue-100 border-b-2 border-black w-96" />
                    </div>
                    <form:errors path="userName" class="text-sm text-red-500" />
                    <div class="my-3 text-xl flex flex-col bg-blue-100">
                        <form:label path="email" class="text-sm">Email</form:label>
                        <form:input path="email" class="ml-auto bg-blue-100 border-b-2 border-black w-96" />
                    </div>
                    <form:errors path="email" class="text-sm text-red-500" />
                    <div class="my-3 text-xl flex flex-col bg-blue-100">
                        <form:label path="password" class="text-sm">Password</form:label>
                        <form:input path="password" type="password" class="ml-auto bg-blue-100 border-b-2 border-black w-96" />
                    </div>
                    <form:errors path="password" class="text-sm text-red-500" />
                    <div class="my-3 text-xl flex flex-col bg-blue-100">
                        <form:label path="confirm" class="text-sm">Confirm password</form:label>
                        <form:input path="confirm" type="password" class="ml-auto bg-blue-100 border-b-2 border-black w-96" />
                    </div>
                    <form:errors path="confirm" class="text-sm text-red-500" />
                    <button class="justify-item-center bg-blue-500 rounded-lg p-2 text-xl text-white my-3" type="submit">Register</button>
                </form:form>
            </div>
            <div class="flex flex-col">
                <h2 class="text-3xl my-3 mx-auto">Login</h2>
                <form:form action="/login" method="POST" modelAttribute="newLogin" class="flex flex-col">
                    <div class="my-3 text-xl flex flex-col bg-blue-100">
                        <form:label path="email" class="text-sm">Email</form:label>
                        <form:input path="email" class="ml-auto bg-blue-100 border-b-2 border-black w-96" />
                    </div>
                    <form:errors path="email" class="text-sm text-red-500" />
                    <div class="my-3 text-xl flex flex-col bg-blue-100">
                        <form:label path="password" class="text-sm">Password</form:label>
                        <form:input path="password" type="password" class="ml-auto bg-blue-100 border-b-2 border-black w-96" />
                    </div>
                    <form:errors path="password" class="text-sm text-red-500" />
                    <button class="justify-item-center bg-blue-500 rounded-lg p-2 text-xl text-white my-3" type="submit">Login</button>
                </form:form>
            </div>
		</div>
	</div>
</body>
</html>