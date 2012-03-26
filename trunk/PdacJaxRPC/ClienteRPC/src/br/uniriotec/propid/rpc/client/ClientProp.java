package br.uniriotec.propid.rpc.client;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import br.uniriotec.propid.rpc.handler.UsernameHandler;
import br.uniriotec.propid.rpc.prop.ServicoHello;
import br.uniriotec.propid.rpc.prop.ServicoHelloService;

public class ClientProp {
	private Map<String, String> lista;
	private int flag = 0;
	
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
  public void invokeInject() {
	  String[] usuarios = lista.keySet().toArray(new String[0]);
	  String usuario = usuarios[flag];
	  String msgEsperada = lista.get(usuario);
	  
	  UsernameHandler._username.set(usuario);
	  
		if(flag == lista.size()-1){
			flag = 0;
		}else{
			flag++;
		}
		
	  
	  ServicoHelloService service = new ServicoHelloService();
	  ServicoHello port = service.getServicoHelloSoapPort();
	  
	  String result = port.hello();
	  
	  System.out.println("Retornado: "+result+" - Esperado: "+msgEsperada);
	  Assert.assertEquals(result, msgEsperada, "Valor retornado pelo servico difere do esperado");
	  
  }
  

}
