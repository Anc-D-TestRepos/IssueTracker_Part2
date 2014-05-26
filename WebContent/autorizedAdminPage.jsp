<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>${firstName }_${lastName} </title>
		<link href="webResources/css/authorizedAdminPage.css" rel="stylesheet"   type="text/css" />
		<script src="webResources//js//issue.js" type="text/javascript"></script>
	</head>
	<body>
	
		<div class="content">
			<div class="user">Admin:
				<span class="name">${firstName} ${lastName}</span>
				<span class="logout"><a href="<c:url value='/LogoutController'/>">logout</a></span>
			</div>
						
			<div class="chngUserData">
				<span class="chngData"><a href="<c:url value='/changeUserDataByAdminPage.jsp'/>">change user data</a></span>
				<span class="chngPass"><a href="<c:url value='/changeUserPassByAdminPage.jsp'/>">change password</a></span>
			</div>
						
			<hr color="red">
			<div class="button">
				<form class="createIssue" name="createIssue" method="POST"  action="<c:url value= '/PrepareDataForAddIssueController'/>" > 
					<input type="submit" value = "submit issue">
				</form>
				  
				<form  class="searchIssue" name="searchIssue" method="POST"  action="<c:url value= '/.jsp'/>" > 
					<input type="submit" value = "search">
				</form>
							
			</div>
			<div class="links">
				<a href="">Projects </a>
				<a href="">Statuses </a>
				<a href="">Resolutions </a>
				<a href="">Priorities </a>
				<a href="">Types </a>
			 </div>
						
						
						
			<c:if test="${not empty defectList}">	
				<div class="defectList">
					<table>
						<tr>
							<th><a  href="<c:url value='/IdIssueComparator'/>">Id</a></th>
							<th><a  href="JavaScript:sendColumnName('Priority')" >Priority</a></th>
							<th><a  href="JavaScript:sendColumnName('Assignee')" >Assignee</a></th>
							<th><a  href="JavaScript:sendColumnName('Type')" >Type</a></th>
							<th><a  href="JavaScript:sendColumnName('Status')" >Status</a></th>
							<th><a  href="JavaScript:sendColumnName('Summary')" >Summary</a></th>
						</tr>
									
						<c:forEach items = "${defectList}" var = "defect" varStatus = "status" >
							<tr> 
								<td class="id"><a href="JavaScript:sendId(${defect.id})">${defect.id}</a></td>
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
			<c:if test="${ empty defectList}">	
				<p >No found defects assigned for you</p>
			</c:if>
						
		</div>	
		<div class="footer">
			<div class="addLinks">
				<hr color="blue">
				<a href="">Add projects</a>
				<a href="">Add resolution</a>
				<a href="">Add priority</a>
				<a href="">Add type</a>
			</div>
			<div class="userAction">
				<a href="">Add user</a>
				<a href="">Find user</a>
			</div>

   	       <form  name="columnName" method="POST"  action="<c:url value= '/StringFieldIssueSorter'/>" > 
				<input type=hidden name="sortColumn" value="">
		   </form>
	   	  <form  name="idNumber" method="POST"  action="<c:url value= '/PrepareDataForEditIssueController'/>" > 
				<input type=hidden name="id" value="">
		  </form>
		</div>	   
	</body>
</html>