
package tut01;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the tut01 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MakePDF_QNAME = new QName("http://tut01/", "makePDF");
    private final static QName _MakePDFResponse_QNAME = new QName("http://tut01/", "makePDFResponse");
    private final static QName _PriceDPH_QNAME = new QName("http://tut01/", "priceDPH");
    private final static QName _PriceDPHResponse_QNAME = new QName("http://tut01/", "priceDPHResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: tut01
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MakePDF }
     * 
     */
    public MakePDF createMakePDF() {
        return new MakePDF();
    }

    /**
     * Create an instance of {@link MakePDFResponse }
     * 
     */
    public MakePDFResponse createMakePDFResponse() {
        return new MakePDFResponse();
    }

    /**
     * Create an instance of {@link PriceDPH }
     * 
     */
    public PriceDPH createPriceDPH() {
        return new PriceDPH();
    }

    /**
     * Create an instance of {@link PriceDPHResponse }
     * 
     */
    public PriceDPHResponse createPriceDPHResponse() {
        return new PriceDPHResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakePDF }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MakePDF }{@code >}
     */
    @XmlElementDecl(namespace = "http://tut01/", name = "makePDF")
    public JAXBElement<MakePDF> createMakePDF(MakePDF value) {
        return new JAXBElement<MakePDF>(_MakePDF_QNAME, MakePDF.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakePDFResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link MakePDFResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://tut01/", name = "makePDFResponse")
    public JAXBElement<MakePDFResponse> createMakePDFResponse(MakePDFResponse value) {
        return new JAXBElement<MakePDFResponse>(_MakePDFResponse_QNAME, MakePDFResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PriceDPH }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PriceDPH }{@code >}
     */
    @XmlElementDecl(namespace = "http://tut01/", name = "priceDPH")
    public JAXBElement<PriceDPH> createPriceDPH(PriceDPH value) {
        return new JAXBElement<PriceDPH>(_PriceDPH_QNAME, PriceDPH.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PriceDPHResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PriceDPHResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://tut01/", name = "priceDPHResponse")
    public JAXBElement<PriceDPHResponse> createPriceDPHResponse(PriceDPHResponse value) {
        return new JAXBElement<PriceDPHResponse>(_PriceDPHResponse_QNAME, PriceDPHResponse.class, null, value);
    }

}
