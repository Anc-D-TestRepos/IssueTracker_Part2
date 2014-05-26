<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" href="webResources/css/scssfulAddingData.css" rel="stylesheet" />
		<title>data adding</title>
	</head>
	
	<body>
		<div class="text"> Entered data adding successful</div>
		<c:set var="email" value = "${email}" scope = "session"/>
		<a href="<c:url value='/DispatcherController'/>"> back </a>
	</body>
</html>