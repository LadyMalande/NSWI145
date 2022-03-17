package com.terezamiklosova;

import java.io.IOException;
import java.util.Iterator;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;


/**
 * Servlet implementation class myServlet
 */
@WebServlet("/myServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        //Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Message for doGet method result
		response.getWriter().println("Intermediary for SOAP. Use doPost to get results.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            boolean isdphHeader = false;
            SOAPConnectionFactory soapcf = SOAPConnectionFactory.newInstance();
            SOAPConnection soapc = soapcf.createConnection();
            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage soapm = mf.createMessage(null, request.getInputStream());

            SOAPHeader header = soapm.getSOAPHeader();
            // If the header is there do the statistics, otherwise just send the request and return immediately.
            QName headerName = new QName("http://dph.com/", "taxFromAddedDirt");
            Iterator myHeader = header.getChildElements(headerName);
            if (myHeader.hasNext()) {
                ((SOAPHeaderElement) myHeader.next()).detachNode();
                System.out.println("1");
                isdphHeader = true;
            }
            String endpoint = "http://127.0.0.1:8000/Receipt";
            SOAPMessage soapResponse = soapc.call(soapm, endpoint);
            soapc.close();

            if (!soapResponse.getSOAPBody().hasFault()) {
                
                if (isdphHeader) {
                	double addedDPH = countExtraDPH(soapResponse.getSOAPBody());
                	System.out.println(addedDPH);
                    // Adding header for extra dph
                    soapResponse.getSOAPPart().getEnvelope().addHeader();
                    alterResponse(soapResponse.getSOAPHeader(), soapResponse.getSOAPBody(), true, addedDPH);
                } else {
                	alterResponse(soapResponse.getSOAPHeader(), soapResponse.getSOAPBody(), false, 0.0);
                }
            }
            soapResponse.writeTo(response.getOutputStream());
        } catch (Exception e) {
            System.out.println(e);
        }
	}
	
	private double countExtraDPH(SOAPBody body) {
		double addedDPH = 0.0;
		QName methodName =
                new QName("http://tut01/", "priceDPH", "tns");
        QName parameterName = new QName("return");

        Iterator it = body.getChildElements(methodName);
        if (it.hasNext()) {
            SOAPBodyElement countedDPH = (SOAPBodyElement)(((SOAPBodyElement)it.next()).getChildElements(parameterName).next());
            double startingValue = Double.parseDouble(countedDPH.getValue());
            addedDPH = startingValue * 0.5;
        } else addedDPH = 0.0;
		return addedDPH;
	}
	
	protected void alterResponse(SOAPHeader responseHeader, SOAPBody responseBody, boolean isDPH, double addedDPH) throws Exception {
        QName headerName = new QName("http://dph.com/", "taxFromAddedDirt", "dph");
        QName responseName = new QName("http://tut01/", "priceDPHResponse", "tns");
        QName returnValueName = new QName("return");
        System.out.println("2");
        SOAPHeaderElement header = responseHeader.addHeaderElement(headerName);
       
               addToHeader(addedDPH, header, isDPH);
         
    }

    protected void addToHeader(double addedDPHValue, SOAPHeaderElement responseHeader, boolean isDPH) throws Exception {
        QName name = new QName("http://dph.com/", "addedTax", "dph");
        QName attribute = new QName("dph");
        SOAPElement addedDPH = responseHeader.addChildElement(name);
        addedDPH.addAttribute(attribute, Double.toString(addedDPHValue));
        if(!isDPH) {
        	addedDPH.addTextNode("Na tento produkt se daň z přidané hlíny nevztahuje.");
        } else {
        	addedDPH.addTextNode("Daň z přidané hlíny u tohoto produktu činí " + addedDPHValue + ",-.");
        }
    }

}
