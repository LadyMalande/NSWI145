package tut01;
import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args)
    {
        //Endpoint.publish("http://127.0.0.1:8000/ToCelsius", new ToCelsiusImpl());
        Endpoint.publish("http://127.0.0.1:8000/Mail", new MailImpl());
        Endpoint.publish("http://127.0.0.1:8000/Receipt", new ReceiptImpl());
    }
}

