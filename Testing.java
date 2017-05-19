package com.citi.cs;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import com.citi.citiscreening.Request;
import com.citi.cs.BatchInformationType;
import com.citi.cs.Transaction;

public class Testing {

	public static void main(String[] args) {
	
			
			java.io.StringWriter sw = new StringWriter();	
			String message=null;
			Transaction transaction = new Transaction();
			BatchInformationType batchInformationType = new BatchInformationType();
			batchInformationType.setSender("PURUHOSTTAM THIMDMDMODOHD DGHDHHD GDJDG JD JDJG DJD&DHHDHJDK");
			batchInformationType.setCurrency("USA");
			transaction.setWorkflowHeader(batchInformationType);

			  try {

				File file = new File("C:\\var\\gpos1.xml");
				JAXBContext jaxbContext = JAXBContext.newInstance(Transaction.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
				
				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(transaction, file);
				jaxbMarshaller.marshal(transaction, System.out);
				jaxbMarshaller.marshal(transaction, sw);
				

				  SchemaFactory sf = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
				  Schema schema = sf.newSchema(new File("C:\\temp\\GPOS\\gpos_variant.xsd"));

				  JAXBSource source = new JAXBSource(jaxbContext, transaction);
				  Validator validator = schema.newValidator();
				  validator.setErrorHandler(new CustomValidationErrorHandler());
				  try {
					validator.validate(source);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  
				//System.out.println(sw.toString());
				message= sw.toString();
			      } catch (JAXBException e) {
				e.printStackTrace();
			      } catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


		  
			  
			  
				Request request = new Request();
				request.setMessage(message);
				 try {
				File file1 = new File("C:\\var\\message1.xml");
				JAXBContext jaxbContext1 = JAXBContext.newInstance(Request.class);
				Marshaller jaxbMarshaller1 = jaxbContext1.createMarshaller();
				
				// output pretty printed
				jaxbMarshaller1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				jaxbMarshaller1.marshal(request, file1);
				jaxbMarshaller1.marshal(request, System.out);
				  } catch (JAXBException e) {
						e.printStackTrace();
					      }
				
			}
			// TODO Auto-generated method stub
}
