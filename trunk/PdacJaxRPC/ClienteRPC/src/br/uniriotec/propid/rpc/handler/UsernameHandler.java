package br.uniriotec.propid.rpc.handler;

import java.util.Collections;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UsernameHandler implements SOAPHandler<SOAPMessageContext> {
	private final static String NAMESPACE = "http://br.unirio.webservice";
	private final static String HEADER_ELEMENT_NAME = "username";
	public static final ThreadLocal<String> _username = new ThreadLocal<String>();

	/**
	 * M�todo que recebe a mensagem SOAP atrav�s do atributo context do tipo SOAPMessageContext.
	 * Atrav�s deste atributo este m�todo inspeciona a mensagem de requisi��o(Consumidor -> Servi�o)
	 * verifica a exist�ncia de um elemento no header que obede�a os atributos NAMESPACE e HEADER_ELEMENT_NAME.
	 * Caso exista, armazena o conte�do deste elemento no atributo _username.
	 */
	public boolean handleMessage(SOAPMessageContext context) {
		try {
			// OutboundDirection significa que � uma mensagem no sentido Consumidor -> Servi�o.
			boolean isOutboundDirection = ((Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)).booleanValue();

			// O restante do c�digo do m�todo s� executa se for OutboundDirection 
			if (!isOutboundDirection) {
				
				// Obt�m o header da mensagem e armazena na vari�vel soapHeader
				SOAPHeader soapHeader = context.getMessage().getSOAPHeader();
				
				//Obt�m a lista de elementos dentro do header que tenham o namespace igual o atributo NAMESPACE
				// e o nome do elemento seja igual ao atributo HEADER_ELEMENT_NAME; armazena estes elementos
				// na vari�vel headerElements que � do tipo NodeList(classe que representa uma lista de n�s) 
				NodeList headerElements = soapHeader.getElementsByTagNameNS(NAMESPACE, HEADER_ELEMENT_NAME);
				String value = null;

				// Caso o elemento headerElements seja diferente de nulo e tenha n�s
				if ((headerElements != null) && (headerElements.getLength() > 0)) {
					
					//Obt�m o primeiro n� da lista. 
					// Isso � feito pois a estrutura do elemento(DOM) utilizado para armazenar o usu�rio tem apenas um filho
					// que � o n� texto que representa o usu�rio. Ex.: "Jo�o"
					Node node = headerElements.item(0);

					if (node != null) {
						//Obt�m o conte�do do n� texto e o armazena no atributo _username.
						value = node.getFirstChild().getNodeValue();
						_username.set(value);
					}
				}
			}else{
				if(_username.get() != null){
					SOAPMessage msg = context.getMessage();
					SOAPEnvelope env = msg.getSOAPPart().getEnvelope();
					SOAPHeader soapHeader = env.getHeader();
					// Verifica se a mensagem SOAP tem cabe�alho, se n�o, cria o header.
					if (soapHeader == null)
						soapHeader = env.addHeader();
					
					// cria o elemento(DOM) que representa o usu�rio.
					QName qname = new QName(NAMESPACE, HEADER_ELEMENT_NAME);
	
					
					//adiciona o elemento criado no header da mensagem SOAP e guarda a sua refer�ncia
					// na vari�vel headerElement
					SOAPHeaderElement headerElement = soapHeader.addHeaderElement(qname);
					
					// adiciona um n� texto cujo conte�do � o nome do usu�rio.
					headerElement.addTextNode(_username.get().toString());
					msg.saveChanges();
				}
			}
		}catch(SOAPException e){
			System.out.println(e);
		} 

		return true;
	}

	public boolean handleFault(SOAPMessageContext context) {
		return true;
	}

	public Set<QName> getHeaders() {
		return Collections.emptySet();
	}

	public void close(MessageContext context) {
	}
}
