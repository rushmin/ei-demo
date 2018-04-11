package org.wso2.ei.sample;

import java.io.IOException;
import java.io.InputStream;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMDocument;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNode;
import org.apache.axiom.soap.SOAPEnvelope;
import org.apache.axiom.soap.SOAPFactory;
import org.apache.axis2.AxisFault;
import org.apache.axis2.builder.Builder;
import org.apache.axis2.context.MessageContext;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelMessageBuilder implements Builder
{

	public OMElement processDocument(InputStream inputStream, String arg1, MessageContext messageContext) throws AxisFault {
			
		SOAPFactory soapFactory = OMAbstractFactory.getSOAP11Factory();
        SOAPEnvelope soapEnvelope = soapFactory.getDefaultEnvelope();
		
		try {
			
	        OMFactory omFactory = OMAbstractFactory.getOMFactory();
			 
			Workbook workbook = WorkbookFactory.create(inputStream);
			
			OMElement root = omFactory.createOMElement("Sheets", null);
			for(Sheet sheet: workbook) {
	            String sheetName = sheet.getSheetName();
	            OMElement sheetElement = omFactory.createOMElement("Sheet", null);
	            OMElement nameElement = omFactory.createOMElement("Name", null);
	            nameElement.addChild(omFactory.createOMText(sheetName));
	            sheetElement.addChild(nameElement);
	            
	            OMElement recordsElement = omFactory.createOMElement("Records", null);
	            
	            for (Row row: sheet) {
	            	OMElement recordElement = omFactory.createOMElement("Record", null);
	                for(Cell cell: row) {
	                	OMElement fieldElement = omFactory.createOMElement("Field", null);
	                	fieldElement.addChild(omFactory.createOMText(cell.getStringCellValue()));
	                	recordElement.addChild(fieldElement);
	                }
	                recordsElement.addChild(recordElement);
	            }
	            sheetElement.addChild(recordsElement);
	            root.addChild(sheetElement);
	        }
			
			
			soapEnvelope.getBody().addChild((OMNode) root);
			
			
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return soapEnvelope;
	}
}
