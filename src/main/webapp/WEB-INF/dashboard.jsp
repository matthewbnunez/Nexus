<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="flex flex-col m-5">
	<div class="flex justify-around">
		<h1>Welcome, ${ userName }</h1>
		<a class="text-white bg-blue-700 hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800" href="/logout">logout</a>
		
	</div>
	
	<%-- ================= Add a post  ================================= --%>
	<div class="flex justify-center">
        <div class="flex my-5 p-6 max-w-lg bg-white rounded-lg border border-gray-200 shadow-md hover:bg-gray-100 dark:bg-gray-800 dark:border-gray-700 dark:hover:bg-gray-700">
	<form:form action="/share/post/process" method="POST"
		modelAttribute="newShare">
		<div class="my-3 bg-blue-100 w-96">
			<form:label path="theShare">Message: </form:label>
			<form:input path="theShare" class="ml-auto bg-blue-100 border-b-2 border-black w-96 h-12"/>
			<form:errors path="theShare" />
		</div>
		<form:hidden path="user" value="${ userId }" />
		<button type="submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Share Message</button>
	</form:form>
	</div>
    </div>

	
<%-- ================= Show all posts, dates, likes, comment count and names  ================================= --%>
    <div class="flex justify-center">
        <div class="flex flex-col justify-center">
            <c:forEach var="shares" items="${ thePosts }">
                <div class="block p-6 m-3 bg-white rounded-lg border border-gray-200">
                    <div class="block bg-white rounded-lg">
                        <h2>
                            <a href="/shares/${ shares.user.id }/detail"><c:out value="${ shares.user.userName }" /></a>
                            Posted:
                            <div class="block p-5 bg-white rounded-lg border">
                                <c:out value="${ shares.theShare }" />
                            </div>
                        </h2>
                        <div class="flex justify-between">
                            <p class="text-gray-500">
                                Likes:
                                <c:out value="${ shares.vote }" />
                            </p>
                            
                        </div>
                        <div class="flex justify-between">
                            
                            <p class="text-gray-500">
                                <fmt:formatDate type="both" dateStyle="short" timeStyle="short"
                                    value="${shares.createdAt}" />
                            </p>
                        </div>
                    </div>
                    
		

<%-- ================= Submit a Comment  ================================= --%>
 
		<form:form action="/share/comment/process" method="POST"
			modelAttribute="newcom">
			<div class="my-3 bg-blue-100 w-96">
				<form:label path="comment">Comment: </form:label>
				<form:input path="comment" class="ml-auto bg-blue-100 border-b-2 border-black w-96" />
				<form:hidden path="user" value="${ userId }" />
				<form:hidden path="share" value="${ shares.id }" />
			</div>
				<button type="submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Share Comment</button>
		</form:form>
		
		
<%-- ================= Loop to display comments matched with share Id ================================= --%>
 <div class="block max-w-sm">
				<c:forEach var="coms" items="${ theComments }">
					<c:if test="${coms.share.id.equals(shares.id) }">
			<div class="block p-3 my-3 bg-gray-200 rounded-lg border border-gray-200">
						<p>
							<c:out value="${ coms.user.userName}" />:
							<c:out value="${ coms.comment }" />
						</p>
					</div>
					</c:if>	
				</c:forEach>
				</div>


<%-- ================= Add Like  ================================= --%>

		<c:choose>
			<c:when test="${shares.receivers.contains(loggedInUser)}">
				<form method="POST" action="/shares/${shares.id }/unlike">
					<input type="hidden" name="_method" value="put" />
					<button type="submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Unlike</button>
				</form>

			</c:when>
<%-- ================= Remove Like ================================= --%>
			<c:otherwise>
				<form method="POST" action="/shares/${shares.id }/receive">
					<input type="hidden" name="_method" value="put" />
					<button type="submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300 font-medium rounded-full text-sm px-5 py-2.5 text-center mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Like</button>
				</form>

			</c:otherwise>
		</c:choose>
		</div>
	</c:forEach>
	</div>
</div>


</body>
</html>