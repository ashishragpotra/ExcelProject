<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
     "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring MVC - Hibernate File Upload to Database Demo</title>
</head>
<body>

<div align="left">
<a href="<%= request.getContextPath() %>">Home Page</a>
</div>



	<div align="center">
		<h1>Hi Buddy this is your file data !!!</h1>


		<c:if test="${! empty geologicalSectionsList}">
			<div align="center">
				<h1>List of Uploaded Rows in Sheet:-</h1>
			</div>

			<table id="geologicalSections" class="">
				<thead>
					<tr>
						<th>id</th>
						<th>section name</th>
						<th>class1 name</th>
						<th>class1 code</th>
						<th>class2 name</th>
						<th>class2 code</th>
						<th>Delete Row</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${geologicalSectionsList}" var="geologicalSections" varStatus="varGeologicalSections">
						<tr class="bold">
							<td>${geologicalSections.id}</td>
							<td>${geologicalSections.sectionName}</td>
							<td>${geologicalSections.class1Name}</td>
							<td>${geologicalSections.class1Code}</td>
							<td>${geologicalSections.class2Name}</td>
							<td>${geologicalSections.class2Code}</td>
							<td><a href="<c:url value="${fileId}/geologicalSectionId/${geologicalSections.id}"/>">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>



	</div>
</body>
</html>
