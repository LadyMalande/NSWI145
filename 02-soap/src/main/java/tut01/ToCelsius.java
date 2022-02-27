package tut01;

@javax.jws.WebService
public interface ToCelsius {

    @javax.jws.WebMethod
    public double c2f(double celsius);
}

