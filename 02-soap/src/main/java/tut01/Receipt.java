package tut01;

import com.itextpdf.text.Document;

@javax.jws.WebService
public interface Receipt {

    @javax.jws.WebMethod
    public Document makePDF(int orderNumber);
}
