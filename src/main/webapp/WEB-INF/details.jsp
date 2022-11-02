<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detail Page</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
    <a href="/home">Home</a>
    <c:forEach var="shar" items="${ userShare }">
        <c:if test="${id.equals(shar.user.id)}">
            <h2>
                <c:out value="${ shar.user.userName}" />
            </h2>
            <p>
                <fmt:formatDate type="both" dateStyle="short" timeStyle="short"
                    value="${shar.createdAt}" />
            </p>
            <h3>
                <c:out value="${ shar.theShare}" />
            </h3>
            <c:choose>
                <c:when test="${userId.equals(shar.user.id) }">
                    <form action="/shares/${shar.id }/delete" method="POST">
                        <input type="hidden" name="_method" value="delete" />
                        <button type="submit"
                            class="bg-red-500 rounded-lg p-2 text-xl text-white my-3">Delete
                            Post</button>
                    </form>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
            <c:forEach var="com" items="${ userCom }">
                <c:if test="${com.share.id.equals(shar.id)}">
                    <p class="indent-reply">
                        @
                        <c:out value="${ com.user.userName}" />
                        said:
                        <c:out value="${ com.comment}" />
                    </p>
                </c:if>
            </c:forEach>
        </c:if>
    </c:forEach>
</body>
</html>