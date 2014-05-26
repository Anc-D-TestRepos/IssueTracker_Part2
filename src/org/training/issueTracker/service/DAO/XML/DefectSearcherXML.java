package org.training.issueTracker.service.DAO.XML;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.training.issueTracker.beans.Issue;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class DefectSearcherXML {
	
	private final String  FILE_ERR = "file defectData.xml not found";
	private final String  IO_ERR = "I\\O error in DefectSearcher ";
	
	private Logger logger= Logger.getLogger(DefectSearcherXML.class);
	private InputStream stream = null;
	private final String PATH = "resources/defectData.xml";
	
	
	
	public DefectSearcherXML() {
		super();

	
	}
	
	public List<Issue> findDefects() throws SAXException {
		DefectHandlerXML defectHandler = new DefectHandlerXML();
		stream = getClass().getResourceAsStream(PATH);

		try {
			if ( stream != null){
				
				XMLReader reader = XMLReaderFactory.createXMLReader();
				reader.setContentHandler(defectHandler);
					
				reader.parse(new InputSource(stream));
			}else{
				logger.error(FILE_ERR);
			}
				
				
		} catch ( IOException e) {
			
			throw new SAXException(IO_ERR);
			
		} 
		return defectHandler.getDefectsList();
	}
	
	
	
	public List<Issue> findDefects(int capacity) throws SAXException {
		
		DefectHandlerXML defectHandler = new DefectHandlerXML();
		List<Issue> result = null;
		stream = getClass().getResourceAsStream(PATH);

		try {
			if ( stream != null){
							
					XMLReader reader = XMLReaderFactory.createXMLReader();
					reader.setContentHandler(defectHandler);
					
					reader.parse(new InputSource(stream));
				}else{
					logger.error(FILE_ERR);
				}
				
			} catch ( IOException e) {
				
				throw new SAXException(IO_ERR);
			} 
			result = defectHandler.getDefectsList();
			
			if (result.size()>capacity){
				return result.subList(result.size()-capacity,result.size() );
			}else {
				return result;
			}
		
	}
	
	
	public List<Issue> findDefectsByUser(String email) throws SAXException {
		
		List<Issue> userDefects = new ArrayList<Issue>();
		List<Issue> allDefects = findDefects();
		
		for (Issue defect : allDefects) {
			if (email.equals(defect.getAssignee())){
				userDefects.add(defect);
			}
		}
		
		return userDefects;
	}
	
	public List<Issue> findDefectsByUser(String email, int capacity) throws SAXException {
		
		List<Issue> userDefects = new ArrayList<Issue>();
		List<Issue> allDefects = findDefects();
		
		for (Issue defect : allDefects) {
			if (email.equals(defect.getAssignee())){
				userDefects.add(defect);
			}
		}
		
		if (userDefects.size()>capacity){
			return userDefects.subList( userDefects.size()-capacity ,userDefects.size());
		}else {
			return userDefects;
		}
		
	}
}