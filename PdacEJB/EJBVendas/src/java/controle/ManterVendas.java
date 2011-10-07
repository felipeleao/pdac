
package controle;

import java.sql.SQLException;
import javax.ejb.Stateful;
import modelo.dao.VendasDAO;

@Stateful
public class ManterVendas implements ManterVendasRemote {
    VendasDAO dao = new VendasDAO();

    public double consultarPrevisaoMudancaReceita() throws SQLException {
        try{
            return dao.consultarPrevisaoMudanca();
        }catch(Exception e){
            throw new SQLException("Erro ao realizar consulta,");
        }
    }

    public void setUsuario(String chaveUsuario) {
        dao.setUsuario(chaveUsuario);
    }

    public String getUsuario() {
        return dao.getUsuario();
    }
}


