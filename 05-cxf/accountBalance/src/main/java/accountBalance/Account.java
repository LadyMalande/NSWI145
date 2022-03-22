package accountBalance;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name = "Account", targetNamespace = "http://accountBalance/")
public interface Account {
	@WebMethod(operationName = "getAccountBalance", action = "urn:GetAccountBalance")
	public double getAccountBalance(String accountName);
}
