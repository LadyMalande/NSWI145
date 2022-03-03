package tut01;

@javax.jws.WebService
public interface Mail {

    @javax.jws.WebMethod
    public boolean sendOrderNumber(String mailAddress, int orderNumber);

    @javax.jws.WebMethod
    public boolean sendPDFReceipt(String mailAddress, int orderNumber);
}
