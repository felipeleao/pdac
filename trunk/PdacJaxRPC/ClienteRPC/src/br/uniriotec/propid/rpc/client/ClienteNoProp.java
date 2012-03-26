package br.uniriotec.propid.rpc.client;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import br.uniriotec.propid.rpc.noprop.ServicoHelloNoProp;
import br.uniriotec.propid.rpc.noprop.ServicoHelloNoPropService;

public class ClienteNoProp {
	private Map<String, String> lista;
	@BeforeClass
	public void beforeClass() {
		lista = new HashMap<String, String>();
			lista.put("Y2R7", "Ola Y2R7!");
			lista.put("Y5QL", "Ola Y5QL!");
			lista.put("Y2T0", "Ola Y2T0!");
	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("--- Inicio do Teste: "+DateUtils.now());
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("--- Fim do Teste: "+DateUtils.now());
	}
	
	 @Test(threadPoolSize = 5, invocationCount = 100)
	  public void invokeNoProp() {
		  ServicoHelloNoPropService service = new ServicoHelloNoPropService();
		  ServicoHelloNoProp port = service.getServicoHelloNoPropSoapPort();
		  
		  String result = port.hello();
		  String msgEsperada = "Ola usuario!";
		  
		  System.out.println("Retornado: "+result+" - Esperado: "+msgEsperada);
		  Assert.assertEquals(result, msgEsperada, "Valor retornado pelo servico difere do esperado");
	  }
}