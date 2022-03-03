package tut01;

import com.itextpdf.text.Document;

@javax.jws.WebService
public interface Receipt {

    @javax.jws.WebMethod
    public String makePDF(int orderNumber);

    @javax.jws.WebMethod
    public double priceDPH(int orderNumber, int dph);
}
