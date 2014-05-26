package org.training.issueTracker.beans;

import java.sql.Date;
import java.text.ParseException;
import java.util.Comparator;

public class Issue implements Comparable<Issue> {
	private final String PRINT_ID = "ID           : ";
	private final String PRINT_CREATE_DATE= "Create date  : ";
	private final String PRINT_CREATE_BY = "Create By    : ";
	private final String PRINT_MODIFIED_DATE = "Modified date  : ";
	private final String PRINT_MODIFIED_BY = "Modified By  : ";
	private final String PRINT_SUMMARY = "Summary      : ";
	private final String PRINT_DESCRIPTION = "Description  : ";
	private final String PRINT_STATUS = "Status       : ";
	private final String PRINT_RESOLUTION = "Resolution   : ";
	private final String PRINT_TYPE = "Type         : ";
	private final String PRINT_PRIORITY = "Priority     : ";
	private final String PRINT_PROJECT_NAME = "Project name : ";
	private final String PRINT_BUILD_FOUND = "Build found  : ";
	private final String PRINT_ASSIGNEE = "Assignee     : ";
	private final String PRINT_NEW_LINE = "\n";
	private final String RED = "red";
	private final String ORANGE = "orange";
	private final String BLUE = "blue";
	private final String GREEN = "green";
	private final String BLACK = "black";
	private int id ;
	private Date createDate;
	private String createBy;
	private String modifiedDate;
	private String modifiedBy;
	private String summary;
	private String description;
	private String status;
	private String resolution;
	private String type;
	private String priority;
	private String projectName;
	private String buildFound;
	private String assignee;
	private String stringColor;
	
	
	private enum DefectStringColor {
		CRITICAL,MAJOR,MINOR,IMPORTANT;
		
	 }
	/**
	 * 
	 */
	public Issue() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param id
	 * @param priority
	 * @param assignee
	 * @param type
	 * @param status
	 * @param summary
	 *  @param color
	 */
	public Issue(int id, String priority, String assignee, String type,
			String status, String summary) {
		super();
		this.id = id;
		this.priority = priority;
		this.assignee = assignee;
		this.type = type;
		this.status = status;
		this.summary = summary;
		stringColor = getColor(priority);
	}
	
	
	
	
	
	public Issue(int id, Date createDate, String createBy,String modifiedDate, String modifiedBy,
			String summary, String description, String status,
			String resolution, String type, String priority,
			String projectName, String buildFound, String assignee	) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.createBy = createBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		this.summary = summary;
		this.description = description;
		this.status = status;
		this.resolution = resolution;
		this.type = type;
		this.priority = priority;
		this.projectName = projectName;
		this.buildFound = buildFound;
		this.assignee = assignee;
		stringColor = getColor(priority);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	public void setId(String id) throws ParseException {
		this.id = Integer.parseInt(id);
	}
	
	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * @return the assignee
	 */
	public String getAssignee() {
		return assignee;
	}

	/**
	 * @param assignee the assignee to set
	 */
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
	
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the createBy
	 */
	public String getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy the createBy to set
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	
	
	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the resolution
	 */
	public String getResolution() {
		return resolution;
	}

	/**
	 * @param resolution the resolution to set
	 */
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	/**
	 * @return the project
	 */
	public String getProject() {
		return projectName;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(String projectName) {
		this.projectName = projectName;
	}

	/**
	 * @return the buildFount
	 */
	public String getBuildFound() {
		return buildFound;
	}

	/**
	 * @param buildFount the buildFount to set
	 */
	public void setBuildFound(String buildFound) {
		this.buildFound = buildFound;
	}

	/**
	 * @param stringColor the stringColor to set
	 */
	public void setStringColor(String priority) {
		this.stringColor = getColor(priority);
	}

	public String getStringColor() {
		return stringColor;
	}

	private String getColor (String priority){
		
		
		switch (DefectStringColor.valueOf(priority.toUpperCase())) {
		case CRITICAL:
			return RED;
		case MAJOR:
			return ORANGE;
		case IMPORTANT:
			return BLUE;	
		case MINOR:
			return GREEN;
		
		default:
			return BLACK;
			
		}
	}
	/** 
	 * 
	 */
	@Override
	public String toString() {
		
		StringBuilder result = new StringBuilder();
		result= result.append(PRINT_ID);
		result= result.append(id);
		result= result.append(PRINT_NEW_LINE);
		result= result.append(PRINT_CREATE_DATE);
		result= result.append(createDate);
		result= result.append(PRINT_NEW_LINE);
		result= result.append(PRINT_CREATE_BY);
		result= result.append(createBy);
		result= result.append(PRINT_NEW_LINE);
		result= result.append(PRINT_MODIFIED_DATE);
		result= result.append(modifiedDate);
		result= result.append(PRINT_NEW_LINE);
		result= result.append(PRINT_MODIFIED_BY);
		result= result.append(modifiedBy);
		result= result.append(PRINT_NEW_LINE);
		result= result.append(PRINT_SUMMARY);
		result= result.append(summary);
		result= result.append(PRINT_NEW_LINE);
		result= result.append(PRINT_DESCRIPTION);
		result= result.append(description);
		result= result.append(PRINT_NEW_LINE);
		result= result.append(PRINT_STATUS);
		result= result.append(status);
		result= result.append(PRINT_NEW_LINE);
		result= result.append(PRINT_RESOLUTION);
		result= result.append(resolution);
		result= result.append(PRINT_NEW_LINE);
		result= result.append(PRINT_TYPE);
		result= result.append(type);
		result= result.append(PRINT_NEW_LINE);
		result= result.append(PRINT_PRIORITY);
		result= result.append(priority);
		result= result.append(PRINT_NEW_LINE);
		result= result.append(PRINT_PROJECT_NAME);
		result= result.append(projectName);
		result= result.append(PRINT_NEW_LINE);
		result= result.append(PRINT_BUILD_FOUND);
		result= result.append(buildFound);
		result= result.append(PRINT_NEW_LINE);
		result= result.append(PRINT_ASSIGNEE);
		result= result.append(assignee);
		result= result.append(PRINT_NEW_LINE);
	
		 
		return result.toString();
	}

	@Override
	public int compareTo(Issue o) {
		if (this.getId()<o.getId()){
		return -1;
		}
		if (this.getId()>o.getId()){
			return 1;
			}
		return 0;
	}


	
	
	

}


