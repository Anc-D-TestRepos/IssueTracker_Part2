
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>

	<html lang="en">
	<head>
			<meta charset="UTF-8">
			 <title>Issue Tracker</title>
			<link type="text/css" href="webResources/css/startPage.css" rel="stylesheet" />
			<script src="webResources//js//issue.js" type="text/javascript"></script>
	</head>
	<body>
		<div class="role">${role}</div>
		<div class="description">Please enter your email address and password</div>
		<div class="inputData"> 
		
			<form method="POST" action = "<c:url value='/Authentification'/>">
				<div class="email"> <input type="text" name="email">  <span class="email"> email</span></div>
				<div class="pass">  <input type="password" name="pass"><span class="pass"> pass</span></div>
				<div class="button"><input type="submit" value="   enter   "></div>
			</form>
		</div>
		<hr color="red">
			
		<c:if test="${not empty defectList}">	
			<div class="defectList">
				<table>
					<tr>
						<th><a  href="JavaScript:sendColumnName('Id')"       >Id</a></th>
						<th><a  href="JavaScript:sendColumnName('Priority')" >Priority</a></th>
						<th><a  href="JavaScript:sendColumnName('Assignee')" >Assignee</a></th>
						<th><a  href="JavaScript:sendColumnName('Type')"     >Type</a></th>
						<th><a  href="JavaScript:sendColumnName('Status')"   >Status</a></th>
						<th><a  href="JavaScript:sendColumnName('Summary')"  >Summary</a></th>
					</tr>
								
					<c:forEach items = "${defectList}" var = "defect" varStatus = "status" >
						<tr>
							<td class="id">${defect.id}</td>
							<td><font  color ="${defect.stringColor}">${defect.priority}</font></td>
							<td>${defect.assignee}</td>
							<td>${defect.type} </td>
							<td>${defect.status}</td>
							<td>${defect.summary}</td>
						</tr>
					</c:forEach>	
				</table>
			</div>
		</c:if>
		<c:if test="${empty defectList}">	
			<p align="center">Application doesn't have any defects </p>
		</c:if>
		 <form  name="columnName" method="POST"  action="<c:url value= 'IssueFieldSorter'/>" > 
					<INPUT type=hidden name="sortColumn" value="">
		</form>
	</body>
</html>