<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>change password</title>
 
			<link type="text/css" href="webResources/css/changeUserPass.css" rel="stylesheet" />
</head>
<body>
<div class="description">Please enter new password</div>
		<div class="inputData"> 
		
			<form method="POST" action = "<c:url value='/ChangeUserPassController'/>">
				<div class="newPass"><span class="newInput"><input type="password" name="newPass"></span><span class="newDescr"> New Password</span></div>
				<div class="confirmPass"><span class="confirmInput"><input type="password" name="confirmPass"></span><span class="confirmDescr"> Password Confirmation</span></div>
				<div class="button"><input type="submit" value="   enter   "></div>
			</form>
		</div>
</html>