
package clientepropid;

import br.np2tec.propid.controleacesso.ControleAcesso;
import java.util.Properties;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Esta classe implementa o Service Locator Pattern descito em
 * Dependency Injectino (Manning - pg. 35)
 *
 * @author Felipe
 */
public class ServiceLocator {

    private static ServiceLocator instancia = null;
    private String chaveUsuario;

    private ServiceLocator(){}

    public static synchronized ServiceLocator getInstance() {
        if (instancia == null) {
            instancia = new ServiceLocator();
        }
        return instancia;
    }

    public void setUsuario(String chave){
        chaveUsuario = chave;
    }

    public String getUsuario(){
        return chaveUsuario;
    }

    private InitialContext recuperarContexto() throws NamingException{
        // Access JNDI Initial Context.
        Properties p = new Properties();
        p.put("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
        p.put("java.naming.provider.url","jnp://localhost:1099");
        p.put("java.naming.factory.url.pkgs","org.jboss.naming:org.jnp.interfaces");

        InitialContext ctx = new InitialContext(p);

        return ctx;
    }

    public Object get(String jndiName) throws NamingException {
        InitialContext ctx = recuperarContexto();
        Object obj = ctx.lookup(jndiName);

        if(obj instanceof ControleAcesso){
            ControleAcesso controleAcesso = (ControleAcesso) obj;
            controleAcesso.setUsuario(chaveUsuario);
        }

        return obj;
    }
}


