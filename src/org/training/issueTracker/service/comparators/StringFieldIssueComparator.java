package org.training.issueTracker.service.comparators;

import java.util.Comparator;

import org.training.issueTracker.beans.Issue;

public class StringFieldIssueComparator implements Comparator<Issue> {

	
	private final String key;
	private final String EMPTY = "";
	
	
	private enum IssueColumnName {
		PRIORITY,ASSIGNEE,TYPE,STATUS,SUMMARY;
		
	 }
	
	public StringFieldIssueComparator() {
		key= EMPTY;
	}
	
	public StringFieldIssueComparator(String key) {
		this.key = key.toUpperCase();
	}
	
		
	@Override
	public int compare(Issue o1, Issue o2) {
		
			
			switch (IssueColumnName.valueOf(key.toUpperCase())) {
		case PRIORITY:
			return (o1.getPriority()).compareToIgnoreCase(o2.getPriority());
		case ASSIGNEE:
			return (o1.getAssignee()).compareToIgnoreCase(o2.getAssignee());
		case TYPE:
			return (o1.getType()).compareToIgnoreCase(o2.getType());	
		case STATUS:
			return (o1.getStatus()).compareToIgnoreCase(o2.getStatus());
		case SUMMARY:
			return (o1.getSummary()).compareToIgnoreCase(o2.getSummary());
			
		default:
			return 0;
			
		}
	}

}
