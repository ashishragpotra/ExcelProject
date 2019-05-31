<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="url"><%= request.getContextPath() %></c:set>

<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${url}/WEB-INF/js/jquery.form.js"/>"></script>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
     "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring MVC - Hibernate File Upload to Database Demo</title>
</head>
<body>


<div align="left">
<a href="${url}">Home Page</a>
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
							<td><input type="button" id  onclick="deleteData(${fileId},${geologicalSections.id});"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	
	<script type="text/javascript">
	function deleteData(fileId,geologicalSectionsId){
		console.log("url is  >>>>>>>>" + "file/"+fileId+"/geologicalSectionId/"+geologicalSectionsId);
		$.ajax({
		    url: "file/"+fileId+"/geologicalSectionId/"+geologicalSectionsId,
		    context: document.body,
		    success: function(){
		      $(this).addClass("done");
		    }
		});
	}
</script>
</body>
</html>
