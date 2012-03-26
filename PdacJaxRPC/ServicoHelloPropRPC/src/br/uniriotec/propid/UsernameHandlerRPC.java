package br.uniriotec.propid;

import javax.xml.namespace.QName;
import javax.xml.rpc.handler.GenericHandler;
import javax.xml.rpc.handler.MessageContext;
import javax.xml.rpc.handler.soap.SOAPMessageContext;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UsernameHandlerRPC extends GenericHandler{
	private final static String NAMESPACE = "http://br.unirio.webservice";
	private final static String HEADER_ELEMENT_NAME = "username";
	public static final ThreadLocal<String> _username = new ThreadLocal<String>();
	
	@Override
	public boolean handleRequest(MessageContext messageContext) {
		SOAPMessageContext context = (SOAPMessageContext) messageContext;
		try {
			SOAPHeader soapHeader = context.getMessage().getSOAPHeader();
			
			NodeList headerElements = soapHeader.getElementsByTagNameNS(NAMESPACE, HEADER_ELEMENT_NAME);
			String value = null;

			if ((headerElements != null) && (headerElements.getLength() > 0)) {
				
				Node node = headerElements.item(0);

				if (node != null) {
					value = node.getFirstChild().getNodeValue();
					_username.set(value);
				}
			}
		}catch(SOAPException e){
			System.out.println(e);
		} 

		return true;
	}
	
	@Override
	public QName[] getHeaders() {
		return null;
	}
}
