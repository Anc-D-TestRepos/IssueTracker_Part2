<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" href="webResources/css/errEditingData.css" rel="stylesheet" />
		<title>data adding</title>
	</head>
	<body>
		<div class="text"> Entered data is not added</div>
		<div class="cause"> Cause &rarr; <span>${cause}</span></div>
		<c:if test="${not empty badField}">
			<c:forEach items = "${badField}" var = "field" varStatus = "status" >
					<div class="field"> ${field}; </div>
			</c:forEach>
			
		</c:if>
		<c:remove var = "badField" />
		<a href="<c:url value='/defectEditPage.jsp'/>"> back </a>
	</body>
</html>