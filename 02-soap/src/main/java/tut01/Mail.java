package tut01;

@javax.jws.WebService
public interface Mail {

    @javax.jws.WebMethod
    public boolean sendMail(String mailAddress, int orderNumber);
}
