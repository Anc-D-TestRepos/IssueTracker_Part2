package org.training.issueTracker.service.DAO.XML;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.training.issueTracker.beans.Issue;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class DefectHandlerXML  extends DefaultHandler {
	
	private final String NAME = "defect";
	private final int ID= 0;
	private final int PRIORITY = 1;
	private final int ASSIGNEE = 2;
	private final int TYPE = 3;
	private final int STATUS = 4;
	private final int SUMMARY = 5;
	private final String  PARSE_ERR = " Invalid data from input file  ";

	private Issue defect = null;
	private List <Issue> defectsList= null;
	private Logger logger=null;

	
	
	
	public DefectHandlerXML() {
		super();
		logger= Logger.getLogger(DefectHandlerXML.class);
		defectsList = new ArrayList<Issue>();
	}

	/**
	 * @return the employee
	 */
	public Issue getDefect() {
		return defect;
	}



	/**
	 * @return the defects
	 */
	public List<Issue> getDefectsList() {
		return defectsList;
	}

	public void startElement  (String uri, String  localName, 
			String  qName, Attributes  attributes )throws SAXException{


		if(NAME.equals(qName)){
	
			defect = new Issue();
			try {
				defect.setId(attributes.getValue(ID));
				defect.setPriority(attributes.getValue(PRIORITY));
				defect.setAssignee(attributes.getValue(ASSIGNEE));
				defect.setType(attributes.getValue(TYPE));
				defect.setStatus(attributes.getValue(STATUS));
				defect.setSummary(attributes.getValue(SUMMARY));
				defect.setStringColor(defect.getPriority());
				
				defectsList.add(defect);
				
			} catch (ParseException e) {

				defect=null;
				throw new SAXException(PARSE_ERR);
			}
		}
	}
}


