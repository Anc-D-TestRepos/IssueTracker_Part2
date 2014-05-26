package org.training.issueTracker.service.DAO.JDBC;
import java.util.ArrayList;
import java.util.List;


public class CheckerAddingIssueFields {
	private static int ASSIGNEE_INDEX = 7;
	private static int STATUS_INDEX  = 2;
	private final static String ASSIGNEE = "assignee";
	private final static String NEW = "new";
	
	private enum IssueFieldName {
		SUMMARY,DESCRIPTION,STATUS,TYPE,PRIORITY,PROJECT,BUILD,ASSIGNEE;
		
	 }
	
	
	public CheckerAddingIssueFields() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static List<String> isFillingCorrectly(List <String> fields) {	
	
		List <String> badFields = new ArrayList<String>();
		String field;
		
		for (int i =0; i<fields.size()-1; i++) {
			field = fields.get(i);
		

			if ((field == null) ||(field.isEmpty())){
				
				badFields.add(getFieldName(i));
			
			}
		}
	

		field = fields.get(fields.size()-1);
		
		if (field == null){
			badFields.add(field);
		}
		
		return badFields;
	}

	public static boolean isCorrectlySatusAndAssignee(List<String> fields) {
		boolean IsCorrectStatusField = false;
		boolean IsCorrectAssignedField =false;
		boolean result = false;
		
		IsCorrectStatusField =(!fields.get(ASSIGNEE_INDEX).isEmpty())&( (fields.get(STATUS_INDEX).toLowerCase()).equals(ASSIGNEE));
		IsCorrectAssignedField =(fields.get(ASSIGNEE_INDEX).isEmpty())&( (fields.get(STATUS_INDEX).toLowerCase()).equals(NEW));
		
		result = IsCorrectStatusField||IsCorrectAssignedField;
		
		return result;
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
