<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" href="webResources/css/DAOErrPage.css" rel="stylesheet" />
		<title>DAO Error</title>
	</head>
	<body>
		<div class="text"> ERROR</div>
		<div class="cause"> Cause &rarr; <span>${cause}</span></div>
		
		<c:remove var = "cause" />
		
		<a href="<c:url value='/DispatcherController'/>"> try again </a>
	</body>
</html>