
package accountBalance.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 3.5.1
 * Tue Mar 22 19:37:40 CET 2022
 * Generated source version: 3.5.1
 */

@XmlRootElement(name = "getAccountBalance", namespace = "http://accountBalance/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAccountBalance", namespace = "http://accountBalance/")

public class GetAccountBalance {

    @XmlElement(name = "arg0")
    private java.lang.String arg0;

    public java.lang.String getArg0() {
        return this.arg0;
    }

    public void setArg0(java.lang.String newArg0)  {
        this.arg0 = newArg0;
    }

}

