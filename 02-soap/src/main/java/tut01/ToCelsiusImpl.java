package tut01;
import javax.jws.WebService;

@WebService(endpointInterface = "tut01.ToCelsius")
public class ToCelsiusImpl implements ToCelsius {
    public double c2f(double celsius) {
        return celsius * 9.0 / 5.0 + 32;
    }
}

