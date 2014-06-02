package org.training.issueTracker.service.DAO.JDBC;

import java.util.ArrayList;
import java.util.List;

import org.training.issueTracker.service.DAO.exceptions.DAOException;


public class CheckerEditingIssueFields {
	
	private static final String ERR_REQ = "wrong request data from previous page";
	
	
	private enum IssueFieldName {
		SUMMARY,DESCRIPTION,STATUS,TYPE,PRIORITY,PROJECT,BUILD,ASSIGNEE;
		
	 }
		
	public CheckerEditingIssueFields() {
		super();
		
	}

	public static List<String> isFillingCorrectly(List <String> fields) throws DAOException {	
	
		
		List <String> checkingFields = new ArrayList<String>();
		List <String> badFields = new ArrayList<String>();
		String field;
		
		for (IssueFieldName element : IssueFieldName.values()) {
					
			field =fields.get(getFieldIndex(element));
				
			checkingFields.add(field);
		}
		
		
		for (int i =0; i<checkingFields.size()-1; i++) {
			field = checkingFields.get(i);
		
	
			if ((field == null) ||(field.trim().isEmpty())){
				
				badFields.add(getFieldName(i));
			
			}
				
		}
	
		field = fields.get(fields.size()-1);
			
			if (field == null){
				badFields.add(field);
			}
		
		
		return badFields;
	}

	public static boolean isCorrectlySatusAndAssignee(List<String> fields) throws DAOException {
		
		
		boolean IsCorrectStatusField = false;
		boolean IsCorrectAssignedField =false;
		boolean result = false;
		
		IsCorrectStatusField =(!fields.get(getFieldIndex(IssueFieldName.ASSIGNEE)).isEmpty());
		IsCorrectAssignedField =(fields.get(getFieldIndex(IssueFieldName.ASSIGNEE)).isEmpty())&( (fields.get(getFieldIndex(IssueFieldName.STATUS)).toLowerCase()).equals("new"));
	
		result = (IsCorrectStatusField||IsCorrectAssignedField);
		
		return result;
	}
	private static int getFieldIndex (IssueFieldName field) throws DAOException{
					
			switch ( field) {
				case SUMMARY:
					return 5;
				case DESCRIPTION:
					return 6;
				case STATUS:
					return 7;
				case TYPE:
					return 9;
				case PRIORITY:
					return 10;
				case PROJECT:
					return 11;
				case BUILD:
					return 12;
				case ASSIGNEE:
					return 13;
					
				default:
					throw new DAOException(ERR_REQ);
				
		}
	}
	
	private static String getFieldName (int index){
	
		
		switch (index) {
			case 0:
				return  IssueFieldName.SUMMARY.toString().toLowerCase();
			case 1:
				return  IssueFieldName.DESCRIPTION.toString().toLowerCase();
			case 2:
				return  IssueFieldName.STATUS.toString().toLowerCase();
			case 3:
				return  IssueFieldName.TYPE.toString().toLowerCase();
			case 4:
				return  IssueFieldName.PRIORITY.toString().toLowerCase();
			case 5:
				return  IssueFieldName.PROJECT.toString().toLowerCase();
			case 6:
				return  IssueFieldName.BUILD.toString().toLowerCase();
			case 7:
				return  IssueFieldName.ASSIGNEE.toString().toLowerCase();
				
			default:
				return  "";
		
		}
	}
		
		
}
