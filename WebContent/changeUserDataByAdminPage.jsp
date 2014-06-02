<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="org.training.issueTracker.service.constants.RoleConst" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>change data</title>
 
			<link type="text/css" href="webResources/css/changeUserDataByAdminPage.css" rel="stylesheet" />
</head>
<body>
		<div class="inputData"> 
			<div class="description">Please enter new user data</div>
					
			<form method="POST" action = "<c:url value='/ChangeUserDataByAdmin'/>">
				<div class="firstName"><span class="firstInput"><input type="text" name="first_name" value="${choisenFirstName}"></span><span class="firstDescr"> First Name</span></div>
				<div class="lastName"><span class="lastInput"><input type="text" name="last_name" value="${choisenLastName}"></span><span class="lastDescr"> Last Name</span></div>
				
				
				<div class="email">
					<select name="email">
					<c:forEach items = "${mailList}" var = "name" varStatus = "status" >		
				
						<c:if test="${choisenEmail == name}"><option value="${name}" selected>${name}</option></c:if>
						<c:if test="${choisenEmail != name}"><option value="${name}">${name}</option></c:if>
						
					</c:forEach>
												
					</select>
					<span class="emailDescr"> Email Adress </span>
					
					
				</div>
			-[${RoleConst.ADMIN.toString()}]-
				<div class="role">	
					<select name="role">
						<c:if test="${choisenRole == 'admin'}"><option value="<%= RoleConst.ADMIN.toString()%>" selected><%= RoleConst.ADMIN.toString()%></option></c:if>
						<c:if test="${choisenRole != 'admin'}"><option value="<%= RoleConst.ADMIN.toString()%>"><%= RoleConst.ADMIN.toString()%></option></c:if>
						<c:if test="${choisenRole == 'user'}"><option value="<%= RoleConst.USER.toString()%>" selected><%= RoleConst.USER.toString()%></option></c:if>
						<c:if test="${choisenRole != 'user'}"><option value="<%= RoleConst.USER.toString()%>"><%= RoleConst.USER.toString()%></option></c:if>
						<c:if test="${choisenRole == 'guest'}"><option value="<%= RoleConst.GUEST.toString()%>" selected><%= RoleConst.GUEST.toString()%></option></c:if>
				   		<c:if test="${choisenRole != 'guest'}"><option value="<%= RoleConst.GUEST.toString()%>"><%= RoleConst.GUEST.toString()%></option></c:if>
					</select>
				
				<span class="roleDescr"> Role</span>
				
				</div>
				
				
				
				<div class="button"><input type="submit" value="   enter   "></div>
			</form>
		</div>
</body>
</html>