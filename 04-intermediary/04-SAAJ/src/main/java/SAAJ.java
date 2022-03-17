import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.io.IOException;

public class SAAJ {

    public static void main(String[] args) throws SOAPException, IOException {
        String methodName;
        String arg0;
        String arg1;
        if(args.length > 0){
            methodName = args[0];
        } else{
            methodName = "priceDPH";
        }

        arg0 = args.length > 1 ? args[1] : "20228025";
        arg1 = args.length > 2 ? args[2] : "10";


        String namespaceURI = "http://tut01/";
        SOAPConnectionFactory soapcf = SOAPConnectionFactory.newInstance();
        SOAPConnection soapc = soapcf.createConnection();

        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage soapm = mf.createMessage();
        //SOAPMessage soapm = mf.createMessage(null, request.getInputStream());

        SOAPPart soapp = soapm.getSOAPPart();
        SOAPEnvelope soape = soapp.getEnvelope();
        SOAPBody soapb = soape.getBody();

        soape.getHeader().detachNode();
        QName name = new QName(namespaceURI, methodName, "tns");
        SOAPElement soapel = soapb.addBodyElement(name);
        //System.out.println(name.toString());

        soapel.addChildElement(
                new QName("arg0")).addTextNode(arg0);
        if(methodName.equals("priceDPH")) {
            soapel.addChildElement(
                    new QName("arg1")).addTextNode(arg1);
        }
        String endpoint = "http://127.0.0.1:8000/Receipt";
        //soapm.writeTo(System.out);
        SOAPMessage response = soapc.call(soapm, endpoint);
        soapc.close();

        SOAPBody responseBody = response.getSOAPBody();
        if (responseBody.hasFault()) {
            System.out.println(responseBody.getFault().getFaultString());
        } else {
            QName responseName;
            if(methodName.equals("makePDF")){
                responseName = new QName(namespaceURI, "makePDFResponse");
            } else{
                responseName = new QName(namespaceURI, "priceDPHResponse");
            }

            QName resultName = new QName("return");

            SOAPBodyElement methodResponse = (SOAPBodyElement)
                    responseBody.getChildElements(responseName).next();
            SOAPBodyElement result = (SOAPBodyElement)
                    methodResponse.getChildElements(resultName).next();

            System.out.println(result.getValue());
        }

    }

}
