package br.uniriotec.propid;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPMessageHandler;
import javax.jws.soap.SOAPMessageHandlers;

@WebService
@SOAPMessageHandlers(
		{@SOAPMessageHandler(className="br.uniriotec.propid.UsernameHandlerRPC")})
public class ServicoHello {

	@WebMethod
	public String hello() {
		if(UsernameHandlerRPC._username.get() != null){
			return "Ola "+ UsernameHandlerRPC._username.get().toString() + "!";
		}else{
			return "Usuario nao identificado.";
		}
	}
}