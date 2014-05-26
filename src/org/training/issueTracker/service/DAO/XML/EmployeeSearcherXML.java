package org.training.issueTracker.service.DAO.XML;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.training.issueTracker.beans.Employee;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;


public class EmployeeSearcherXML {
	
	private final String  FILE_ERR = "file employeeData.xml not found";
	private final String  IO_ERR = "I\\O error in AuthorizedAdmin - ";
	private InputStream stream;
	private final String PATH = "resources/employeeData.xml";
	private Logger logger= Logger.getLogger(DefectSearcherXML.class);

	
	
	public EmployeeSearcherXML() {
		super();
		
	}

	
	public Employee findEmployee(String email) throws SAXException {
			EmployeeHandlerXML employeehandler = new EmployeeHandlerXML(email);
		
			stream = getClass().getResourceAsStream(PATH);

			try {
				if ( stream != null){
					
					XMLReader reader = XMLReaderFactory.createXMLReader();
					reader.setContentHandler(employeehandler);
					reader.parse(new InputSource(stream));
				}else{
					logger.error(FILE_ERR);
				}
			} catch ( IOException e) {
				throw new SAXException(IO_ERR);
			} 
		return employeehandler.getEmployee();
	}
}