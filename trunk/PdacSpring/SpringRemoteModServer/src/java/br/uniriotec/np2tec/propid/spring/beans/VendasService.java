package br.uniriotec.np2tec.propid.spring.beans;

import br.uniriotec.np2tec.propid.dao.VendasDAO;
import br.uniriotec.np2tec.propid.shared.classes.Pedido;
import br.uniriotec.np2tec.propid.shared.interfaces.vendas.IVendasService;
import java.sql.SQLException;

/**
 * O Bean que será acessado remotamente. Ele deve implementar duas interfaces
 * IVendasService, que expõe seus métodos de negócio e IUserStoreAware, para que
 * seja possível injetar um bean IUserStore durante sua inicialização.
 *
 * O bean IUserStore é request-scoped e tem armazenada a chave de usuário que foi
 * passada como parâmetro no request e interceptado pelo ServiceExporter.
 *
 * @author Felipe
 */
public class VendasService implements IVendasService{
    private VendasDAO daoVendas = new VendasDAO();

    public double consultarPrevisaoMudancaReceita() throws SQLException {
        try{
            return daoVendas.consultarPrevisaoMudanca();
        }catch(Exception e){
            throw new SQLException(e.getMessage());
        }
    }

    public boolean inserirPedido(Pedido p) throws SQLException {
        try{
            return daoVendas.inserirPedido(p);
        }catch(Exception e){
            throw new SQLException(e.getMessage());
        }
    }

}
