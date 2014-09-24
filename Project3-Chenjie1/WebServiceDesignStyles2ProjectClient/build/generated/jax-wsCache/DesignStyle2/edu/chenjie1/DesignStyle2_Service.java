
package edu.chenjie1;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "DesignStyle2", targetNamespace = "http://chenjie1.edu/", wsdlLocation = "http://localhost:6502/WebServiceDesignStyles2Project/DesignStyle2?WSDL")
public class DesignStyle2_Service
    extends Service
{

    private final static URL DESIGNSTYLE2_WSDL_LOCATION;
    private final static WebServiceException DESIGNSTYLE2_EXCEPTION;
    private final static QName DESIGNSTYLE2_QNAME = new QName("http://chenjie1.edu/", "DesignStyle2");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:6502/WebServiceDesignStyles2Project/DesignStyle2?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        DESIGNSTYLE2_WSDL_LOCATION = url;
        DESIGNSTYLE2_EXCEPTION = e;
    }

    public DesignStyle2_Service() {
        super(__getWsdlLocation(), DESIGNSTYLE2_QNAME);
    }

    public DesignStyle2_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), DESIGNSTYLE2_QNAME, features);
    }

    public DesignStyle2_Service(URL wsdlLocation) {
        super(wsdlLocation, DESIGNSTYLE2_QNAME);
    }

    public DesignStyle2_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, DESIGNSTYLE2_QNAME, features);
    }

    public DesignStyle2_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DesignStyle2_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns DesignStyle2
     */
    @WebEndpoint(name = "DesignStyle2Port")
    public DesignStyle2 getDesignStyle2Port() {
        return super.getPort(new QName("http://chenjie1.edu/", "DesignStyle2Port"), DesignStyle2.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DesignStyle2
     */
    @WebEndpoint(name = "DesignStyle2Port")
    public DesignStyle2 getDesignStyle2Port(WebServiceFeature... features) {
        return super.getPort(new QName("http://chenjie1.edu/", "DesignStyle2Port"), DesignStyle2.class, features);
    }

    private static URL __getWsdlLocation() {
        if (DESIGNSTYLE2_EXCEPTION!= null) {
            throw DESIGNSTYLE2_EXCEPTION;
        }
        return DESIGNSTYLE2_WSDL_LOCATION;
    }

}