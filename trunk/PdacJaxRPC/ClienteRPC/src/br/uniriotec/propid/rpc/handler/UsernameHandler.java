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
	 * Método que recebe a mensagem SOAP através do atributo context do tipo SOAPMessageContext.
	 * Através deste atributo este método inspeciona a mensagem de requisição(Consumidor -> Serviço)
	 * verifica a existência de um elemento no header que obedeça os atributos NAMESPACE e HEADER_ELEMENT_NAME.
	 * Caso exista, armazena o conteúdo deste elemento no atributo _username.
	 */
	public boolean handleMessage(SOAPMessageContext context) {
		try {
			// OutboundDirection significa que é uma mensagem no sentido Consumidor -> Serviço.
			boolean isOutboundDirection = ((Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)).booleanValue();

			// O restante do código do método só executa se for OutboundDirection 
			if (!isOutboundDirection) {
				
				// Obtém o header da mensagem e armazena na variável soapHeader
				SOAPHeader soapHeader = context.getMessage().getSOAPHeader();
				
				//Obtém a lista de elementos dentro do header que tenham o namespace igual o atributo NAMESPACE
				// e o nome do elemento seja igual ao atributo HEADER_ELEMENT_NAME; armazena estes elementos
				// na variável headerElements que é do tipo NodeList(classe que representa uma lista de nós) 
				NodeList headerElements = soapHeader.getElementsByTagNameNS(NAMESPACE, HEADER_ELEMENT_NAME);
				String value = null;

				// Caso o elemento headerElements seja diferente de nulo e tenha nós
				if ((headerElements != null) && (headerElements.getLength() > 0)) {
					
					//Obtém o primeiro nó da lista. 
					// Isso é feito pois a estrutura do elemento(DOM) utilizado para armazenar o usuário tem apenas um filho
					// que é o nó texto que representa o usuário. Ex.: "João"
					Node node = headerElements.item(0);

					if (node != null) {
						//Obtém o conteúdo do nó texto e o armazena no atributo _username.
						value = node.getFirstChild().getNodeValue();
						_username.set(value);
					}
				}
			}else{
				if(_username.get() != null){
					SOAPMessage msg = context.getMessage();
					SOAPEnvelope env = msg.getSOAPPart().getEnvelope();
					SOAPHeader soapHeader = env.getHeader();
					// Verifica se a mensagem SOAP tem cabeçalho, se não, cria o header.
					if (soapHeader == null)
						soapHeader = env.addHeader();
					
					// cria o elemento(DOM) que representa o usuário.
					QName qname = new QName(NAMESPACE, HEADER_ELEMENT_NAME);
	
					
					//adiciona o elemento criado no header da mensagem SOAP e guarda a sua referência
					// na variável headerElement
					SOAPHeaderElement headerElement = soapHeader.addHeaderElement(qname);
					
					// adiciona um nó texto cujo conteúdo é o nome do usuário.
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
