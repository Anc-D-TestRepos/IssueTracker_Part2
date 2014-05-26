package org.training.issueTracker.beans;

public class Project {
	
	private String id;
	private String name;
	private String description;
	private String build;
	private String manager;
	
	
	
	public Project() {
		super();
		
	}



	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}



	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}



	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the build
	 */
	public String getBuild() {
		return build;
	}



	/**
	 * @param build the build to set
	 */
	public void setBuild(String build) {
		this.build = build;
	}



	/**
	 * @return the manager
	 */
	public String getManager() {
		return manager;
	}



	/**
	 * @param manager the manager to set
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}
	
	
	
}
