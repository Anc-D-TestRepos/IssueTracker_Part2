package org.training.issueTracker.beans;
import java.sql.Date;

public class Comment {

	
private final String PRINT_ID = "id : ";
private final String PRINT_ADDED_BY = "Added by  : ";
private final String PRINT_ADD_DATE = "Add date      : ";
private final String PRINT_COMMENT = "Comment       : ";
private final String PRINT_NEW_LINE = "\n";
private int id;	
private String comment;
private String addedBy;
private Date addDate;

public Comment() {
	super();
	// TODO Auto-generated constructor stub
}

public Comment(int id, String comment, String addedBy, Date addDate) {
	super();
	this.id = id;
	this.comment = comment;
	this.addedBy = addedBy;
	this.addDate = addDate;
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

/**
 * @return the comment
 */
public String getComment() {
	return comment;
}

/**
 * @param comment the comment to set
 */
public void setComment(String comment) {
	this.comment = comment;
}

/**
 * @return the addedBy
 */
public String getAddedBy() {
	return addedBy;
}

/**
 * @param addedBy the addedBy to set
 */
public void setAddedBy(String addedBy) {
	this.addedBy = addedBy;
}

/**
 * @return the addDate
 */
public Date getAddDate() {
	return addDate;
}

/**
 * @param addDate the addDate to set
 */
public void setAddDate(Date addDate) {
	this.addDate = addDate;
}

@Override
public String toString() {
	StringBuilder result = new StringBuilder();
		

	
	
	result= result.append(PRINT_ID);
	result= result.append(id);
	result= result.append(PRINT_NEW_LINE);
	result= result.append(PRINT_ADDED_BY);
	result= result.append(addedBy);
	result= result.append(PRINT_NEW_LINE);
	result= result.append(PRINT_ADD_DATE);
	result= result.append(addDate);
	result= result.append(PRINT_NEW_LINE);
	result= result.append(PRINT_COMMENT);
	result= result.append(comment);
	result= result.append(PRINT_NEW_LINE);

	return result.toString();
}


}
