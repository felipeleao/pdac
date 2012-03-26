package br.uniriotec.propid.rpc.noprop;

import javax.jws.*;

@WebService
public class ServicoHelloNoProp {

	@WebMethod
	public String hello() {
		return "Ola usuario!";
	}
}