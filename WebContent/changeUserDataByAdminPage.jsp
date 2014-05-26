<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>change data</title>
 
			<link type="text/css" href="webResources/css/changeUserDataByAdminPage.css" rel="stylesheet" />
</head>
<body>
<div class="description">Please enter new user data</div>
		<div class="inputData"> 
			
			<form method="POST" action = "<c:url value='/ChangeUserDataByAdmin'/>">
				<div class="firstName"><span class="firstInput"><input type="text" name="firstName"></span><span class="firstDescr"> First Name</span></div>
				<div class="lastName"><span class="lastInput"><input type="text" name="lastName"></span><span class="lastDescr"> Last Name</span></div>
				<div class="email"><span class="emailInput"><input type="text" name="email"></span><span class="emailDescr"> Email Adress</span></div>
				<div class="role"><span class="roleInput"><input type="text" name="role"></span><span class="roleDescr"> Role</span></div>
				<div class="button"><input type="submit" value="   enter   "></div>
			</form>
		</div>
</body>
</html>