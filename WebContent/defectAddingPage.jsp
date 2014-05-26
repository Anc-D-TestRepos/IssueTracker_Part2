<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" href="webResources/css/defectAddingPage.css" rel="stylesheet" />
</head>
	<body>

	<div class="title">Enter new defect</div>
		<div class="inputData"> 
			
			<form name="addIssue"  method="POST" action = "<c:url value='/DefectAddingController'/>">
				<div class="summary">
					<span class="summaryInput">
						<input type="text" name="summary"  maxlength="50" value = "${activeSummary}" >
					</span>
					<span class="summaryDescr"> Summary
					</span>
				</div>
				
				<c:remove var = "activeSummary" />
				
				<div class="description">
					<span class="descriptionDInput">
						<textarea name="description" cols="29" rows="5"  >${activeDescription}</textarea>
						</span>
					<span class="descriptionDescr"> Description 
					</span>
				</div>
				
				<c:remove var = "activeDescription" />
				
				<div class="status">
					<select name="status"  >
						<c:if test="${activeStatus == 'New'}"><option  value="New" selected >New</option></c:if>
						<c:if test="${activeStatus != 'New'}"><option  value="New">New</option></c:if>
						<c:if test="${activeStatus == 'Assigned'}"><option  value="Assigned" selected >Assigned</option></c:if>
						<c:if test="${activeStatus != 'Assigned'}"><option  value="Assigned">Assigned</option></c:if>
					</select>
					<span class="descriptionStatus"> Status </span>
				</div>
					<c:remove var = "activeStatus" />
				
				<input id="id_2" type="hidden" name="text" value="Assigned">
				<div class="type">
					<select name="type">
						<c:if test="${activeType == 'Cosmetic'}"><option value="Cosmetic" selected>Cosmetic</option></c:if>
						<c:if test="${activeType != 'Cosmetic'}"><option value="Cosmetic">Cosmetic</option></c:if>
						<c:if test="${activeType == 'Bug'}"><option value="Bug" selected>Bug</option></c:if>
						<c:if test="${activeType != 'Bug'}"><option value="Bug">Bug</option></c:if>
						<c:if test="${activeType == 'Feature'}"><option value="Feature" selected>Feature</option></c:if>
						<c:if test="${activeType != 'Feature'}"><option value="Feature">Feature</option></c:if>
						<c:if test="${activeType == 'Performance'}"><option value="Performance" selected>Performance</option></c:if>
						<c:if test="${activeType != 'Performance'}"><option value="Performance">Performance</option></c:if>
					</select>
					<span class="descriptionType"> Type </span>
				</div>
				
				<c:remove var = "activeType" />
				
				<div class="priority">
					<select name="priority">
						<c:if test="${activePriority == 'Critical'}"><option value="Critical" selected>Critical</option></c:if>
						<c:if test="${activePriority != 'Critical'}"><option value="Critical">Critical</option></c:if>
						<c:if test="${activePriority == 'Major'}"><option value="Major" selected>Major</option></c:if>
						<c:if test="${activePriority != 'Major'}"><option value="Major">Major</option></c:if>
						<c:if test="${activePriority == 'Important'}"><option value="Important" selected>Important</option></c:if>
						<c:if test="${activePriority != 'Important'}"><option value="Important">Important</option></c:if>
						<c:if test="${activePriority == 'Minor'}"><option value="Minor" selected>Minor</option></c:if>
						<c:if test="${activePriority != 'Minor'}"><option value="Minor">Minor</option></c:if>
					</select>
					<span class="descriptionPriority"> Priority </span>
				</div>
				
				
				<c:remove var = "activePriority" />
				<div class="project">
					<select name="project">
						<c:forEach items = "${projectName}" var = "name" varStatus = "status" >		
							<c:if test="${name == activeProject}"><option value="${name}" selected>${name}</option></c:if>
							<c:if test="${name != activeProject}"><option value="${name}">${name}</option></c:if>
						</c:forEach>
					</select>
					<span class="descriptionProject"> Project </span>
				</div>
				
				<c:remove var = "activeProject" />
						
				<div class="buildFound">
					<select name="build">
						<c:forEach items = "${buildList}" var = "version" varStatus = "status" >		
							<c:if test="${version == activeBuild}"><option value="${version}" selected>${version}</option></c:if>
							<c:if test="${version != activeBuild}"><option value="${version}">${version}</option></c:if>
						</c:forEach>
					</select>
					<span class="descriptionBuild"> Build found </span>
				</div> 
				
				<c:remove var = "activeBuild" />
				
				<div class="assignee">
					<select name="assignee">
						<option value=""></option>
						<c:forEach items = "${mailList}" var = "assignee" varStatus = "status" >		
							<c:if test="${assignee == activeAssignee}"><option value="${assignee}" selected>${assignee}</option></c:if>
							<c:if test="${assignee != activeAssignee}"><option value="${assignee}">${assignee}</option></c:if>
						</c:forEach>
					</select>
					<span class="descriptionAssignee"> Assignee</span>
				</div> 
				
				<c:remove var = "activeAssignee" />
				
				<div class="button">
					<input type="submit" value="   ADD   ">
				</div>
			</form>
			
		</div>
		<div class="back">
			<a  href="<c:url value='/DispatcherController'/>">back</a>
		</div>
	</body>
</html>