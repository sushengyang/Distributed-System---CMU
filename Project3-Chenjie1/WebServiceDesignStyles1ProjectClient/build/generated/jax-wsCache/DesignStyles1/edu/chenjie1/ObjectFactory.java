
package edu.chenjie1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.chenjie1 package. 
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

    private final static QName _DeleteSpyResponse_QNAME = new QName("http://chenjie1.edu/", "deleteSpyResponse");
    private final static QName _Hello_QNAME = new QName("http://chenjie1.edu/", "hello");
    private final static QName _AddSpyResponse_QNAME = new QName("http://chenjie1.edu/", "addSpyResponse");
    private final static QName _AddSpy_QNAME = new QName("http://chenjie1.edu/", "addSpy");
    private final static QName _HelloResponse_QNAME = new QName("http://chenjie1.edu/", "helloResponse");
    private final static QName _GetList_QNAME = new QName("http://chenjie1.edu/", "getList");
    private final static QName _GetListResponse_QNAME = new QName("http://chenjie1.edu/", "getListResponse");
    private final static QName _DeleteSpy_QNAME = new QName("http://chenjie1.edu/", "deleteSpy");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.chenjie1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link DeleteSpyResponse }
     * 
     */
    public DeleteSpyResponse createDeleteSpyResponse() {
        return new DeleteSpyResponse();
    }

    /**
     * Create an instance of {@link AddSpyResponse }
     * 
     */
    public AddSpyResponse createAddSpyResponse() {
        return new AddSpyResponse();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link AddSpy }
     * 
     */
    public AddSpy createAddSpy() {
        return new AddSpy();
    }

    /**
     * Create an instance of {@link GetListResponse }
     * 
     */
    public GetListResponse createGetListResponse() {
        return new GetListResponse();
    }

    /**
     * Create an instance of {@link GetList }
     * 
     */
    public GetList createGetList() {
        return new GetList();
    }

    /**
     * Create an instance of {@link DeleteSpy }
     * 
     */
    public DeleteSpy createDeleteSpy() {
        return new DeleteSpy();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteSpyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chenjie1.edu/", name = "deleteSpyResponse")
    public JAXBElement<DeleteSpyResponse> createDeleteSpyResponse(DeleteSpyResponse value) {
        return new JAXBElement<DeleteSpyResponse>(_DeleteSpyResponse_QNAME, DeleteSpyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chenjie1.edu/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddSpyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chenjie1.edu/", name = "addSpyResponse")
    public JAXBElement<AddSpyResponse> createAddSpyResponse(AddSpyResponse value) {
        return new JAXBElement<AddSpyResponse>(_AddSpyResponse_QNAME, AddSpyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddSpy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chenjie1.edu/", name = "addSpy")
    public JAXBElement<AddSpy> createAddSpy(AddSpy value) {
        return new JAXBElement<AddSpy>(_AddSpy_QNAME, AddSpy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chenjie1.edu/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chenjie1.edu/", name = "getList")
    public JAXBElement<GetList> createGetList(GetList value) {
        return new JAXBElement<GetList>(_GetList_QNAME, GetList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chenjie1.edu/", name = "getListResponse")
    public JAXBElement<GetListResponse> createGetListResponse(GetListResponse value) {
        return new JAXBElement<GetListResponse>(_GetListResponse_QNAME, GetListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteSpy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://chenjie1.edu/", name = "deleteSpy")
    public JAXBElement<DeleteSpy> createDeleteSpy(DeleteSpy value) {
        return new JAXBElement<DeleteSpy>(_DeleteSpy_QNAME, DeleteSpy.class, null, value);
    }

}
