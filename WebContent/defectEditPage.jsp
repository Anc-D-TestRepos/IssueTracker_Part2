<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" href="webResources/css/defectEditPage.css" rel="stylesheet" />
</head>
	<body>
		<div class="title">Change needed field
		</div>
		<div class="inputData"> 
			
			<form method="POST" action = "<c:url value='/DefectEditingController'/>">
				<div class="id">
					<span class="idInput">
						<input type="text" name="id" readonly="readonly" value="${issue.id}"  >
					</span>
					<span class="idDescr"> Id
					</span>
				</div>
				
				<c:remove var = "id" />
				
				<div class="createDate">
					<span class="createDateInput">
						<input type="text" name="create_date" readonly="readonly"  value="${issue.createDate}" >
					</span>
					<span class="createDateDescr"> Create Date
					</span>
				</div>		
				
				<c:remove var = "createDate" />
				
				<div class="createBy">
					<span class="createByInput">
						<input type="text" name="create_by" readonly="readonly" value="${issue.createBy}"  >
					</span>
					<span class="createByDescr"> Create By
					</span>
				</div>	
				
				<c:remove var = "createBy" />
				
				<div class="modifyDate">
					<span class="modifyDateInput">
						<input type="text" name="modified_date" readonly="readonly"  value="${issue.modifiedDate}" >
					</span>
					<span class="modifyDateDescr"> Modified Date
					</span>
				</div>		
				
				<c:remove var = "modifyDate" />
				
				<div class="modifyBy">
					<span class="modifyByInput">
						<input type="text" name="modified_by" readonly="readonly" value="${issue.modifiedBy}"  >
					</span>
					<span class="modifyByDescr"> Modify By
					</span>
				</div>			
				
				<c:remove var = "modifyBy" />
				
				<div class="summary">
					<span class="summaryInput">
						<input type="text" name="summary"  maxlength="50"  value = "${issue.summary}">
					</span>
					<span class="summaryDescr"> Summary
					</span>
				</div>
				
				<c:remove var = "activeSummary" />
				
				<div class="description">
					<span class="descriptionDInput">
						<textarea name="description" cols="29" rows="5">${issue.description}</textarea>
						</span>
					<span class="descriptionDescr"> Description 
					</span>
				</div>
				
				<c:remove var = "activeDescription" />
				
				<div class="status">
					<select name="status">
					
						<c:if test="${issue.status == 'Assigned'}"><option  value="Assigned" selected >Assigned</option></c:if>
						<c:if test="${issue.status != 'Assigned'}"><option  value="Assigned">Assigned</option></c:if>
						
						<c:if test="${issue.status == 'In Progress'}"><option  value="In Progress" selected >In Progress</option></c:if>
						<c:if test="${issue.status != 'In Progress'}"><option  value="In Progress">In Progress</option></c:if>
							
					</select>
					<span class="descriptionStatus"> Status </span>
				</div>
			 
			 <c:remove var = "activeStatus" />
			 
			<div class="resolution">
					<select name="resolution" disabled="disabled" >
						
						<c:if test="${issue.resolution == 'Fixed'}"><option value="Fixed" selected>Fixed</option></c:if>
						<c:if test="${issue.resolution != 'Fixed'}"><option value="Fixed">Fixed</option></c:if>
						<c:if test="${issue.resolution == 'Invalid'}"><option value="Invalid" selected>Invalid</option></c:if>
						<c:if test="${issue.resolution != 'Invalid'}"><option value="Invalid">Invalid</option></c:if>
						<c:if test="${issue.resolution == 'Wontfix'}"><option value="Wontfix" selected>Wontfix</option></c:if>
						<c:if test="${issue.resolution != 'Wontfix'}"><option value="Wontfix">Wontfix</option></c:if>
						<c:if test="${issue.resolution == 'Worksforme'}"><option value="Worksforme" selected>Worksforme</option></c:if>
						<c:if test="${issue.resolution != 'Worksforme'}"><option value="Worksforme">Worksforme</option></c:if>
					
					</select>
					<span class="resolutionDescr"> Resolution </span>
				</div>
				
				<c:remove var = "activeResolution" />
				
				
					<div class="type">
					<select name="type">
					
						<c:if test="${issue.type == 'Cosmetic'}"><option value="Cosmetic" selected>Cosmetic</option></c:if>
						<c:if test="${issue.type != 'Cosmetic'}"><option value="Cosmetic">Cosmetic</option></c:if>
						<c:if test="${issue.type == 'Bug'}"><option value="Bug" selected>Bug</option></c:if>
						<c:if test="${issue.type != 'Bug'}"><option value="Bug">Bug</option></c:if>
						<c:if test="${issue.type == 'Feature'}"><option value="Feature" selected>Feature</option></c:if>
						<c:if test="${issue.type != 'Feature'}"><option value="Feature">Feature</option></c:if>
						<c:if test="${issue.type == 'Performance'}"><option value="Performance" selected>Performance</option></c:if>
						<c:if test="${issue.type != 'Performance'}"><option value="Performance">Performance</option></c:if>
					
					</select>
					<span class="typeDescr"> Type </span>
				</div>
				
				<c:remove var = "activeType" />
				
				<div class="priority">
					<select name="priority">
				
						
						<c:if test="${issue.priority == 'Critical'}"><option value="Critical" selected>Critical</option></c:if>
						<c:if test="${issue.priority != 'Critical'}"><option value="Critical">Critical</option></c:if>
						<c:if test="${issue.priority == 'Major'}"><option value="Major" selected>Major</option></c:if>
						<c:if test="${issue.priority != 'Major'}"><option value="Major">Major</option></c:if>
						<c:if test="${issue.priority == 'Important'}"><option value="Important" selected>Important</option></c:if>
						<c:if test="${issue.priority != 'Important'}"><option value="Important">Important</option></c:if>
						<c:if test="${issue.priority == 'Minor'}"><option value="Minor" selected>Minor</option></c:if>
						<c:if test="${issue.priority != 'Minor'}"><option value="Minor">Minor</option></c:if>
					
						
					</select>
					<span class="priorityDescr"> Priority </span>
				</div>
				
				<c:remove var = "activePriority" />
				
					<div class="project">
					<select name="project">
					<c:forEach items = "${projectName}" var = "name" varStatus = "status" >		
				
						<c:if test="${name == issue.project}"><option value="${name}" selected>${name}</option></c:if>
						<c:if test="${name != issue.project}"><option value="${name}">${name}</option></c:if>
						
						</c:forEach>
					</select>
					<span class="projectDescr"> Project </span>
				</div>
				
				<c:remove var = "activeProject" />
				
				<div class="buildFound">
					<select name="build">
					<c:forEach items = "${buildList}" var = "version" varStatus = "status" >		
						
						<c:if test="${version ==  issue.buildFound}"><option value="${version}" selected>${version}</option></c:if>
						<c:if test="${version != issue.buildFound}"><option value="${version}">${version}</option></c:if>
					
						
						</c:forEach>
					</select>
					<span class="buildDescr"> Build found </span>
				</div> 
				
				<c:remove var = "activeBuild" />
				
				<div class="assignee">
					<select name="assignee">
					<c:forEach items = "${mailList}" var = "assignee" varStatus = "status" >		
						
						<c:if test="${assignee == issue.assignee}"><option value="${assignee}" selected>${assignee}</option></c:if>
						<c:if test="${assignee != issue.assignee}"><option value="${assignee}">${assignee}</option></c:if>
											
					</c:forEach>
					</select>
					<span class="assigneeDescr"> Assignee</span>
				</div> 
				
				<c:remove var = "activeAssignee" />
				
				<div class="button"><input type="submit" value="   Update   "></div>
			</form>
		</div>
		<div class="back"><a  href="<c:url value='/DispatcherController'/>">back</a></div>
	</body>
</html>