package controle;

import br.np2tec.propid.controleacesso.ControleAcesso;
import java.sql.SQLException;
import javax.ejb.Remote;

@Remote
public interface ManterVendasRemote extends ControleAcesso{
    double consultarPrevisaoMudancaReceita() throws SQLException;
}


