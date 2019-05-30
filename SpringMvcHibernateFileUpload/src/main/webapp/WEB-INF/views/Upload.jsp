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
    <div align="center">
        <h1>Spring MVC - Hibernate File Upload to Database Demo</h1>
        <form method="post" action="doUpload" enctype="multipart/form-data">
            <table border="0">
                <tr>
                    <td>Pick file #1:</td>
                    <td><input type="file" name="fileUpload" size="50" /></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Upload" /></td>
                </tr>
            </table>
        </form>
    </div>
    
    <c:if test="${! empty filesList}">
    	<div align="center">
		<h1>List of Uploaded Files:-</h1>
	</div>
	
	<table id="filesTable" class="">
					<thead>
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${filesList}" var="uploadFile" varStatus="varUploadFile">
							<tr class="bold">
								<td>${uploadFile.id}</td>
								<td>${uploadFile.fileName}</td>
								<td><a href="<c:url value="file/${uploadFile.id}"/>">Edit File</a></td>
								<td><a href="<c:url value="deleteFile/${uploadFile.id}"/>">Delete File</a></td>
							</tr>
						</c:forEach>
					</tbody>
					</table>
	</c:if>
</body>
</html>