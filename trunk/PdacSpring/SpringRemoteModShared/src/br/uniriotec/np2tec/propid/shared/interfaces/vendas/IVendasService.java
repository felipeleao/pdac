/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uniriotec.np2tec.propid.shared.interfaces.vendas;

import br.uniriotec.np2tec.propid.shared.classes.Pedido;
import java.io.Serializable;
import java.sql.SQLException;

/**
 *
 * @author Felipe
 */
public interface IVendasService extends Serializable{
    double consultarPrevisaoMudancaReceita() throws SQLException;
    boolean inserirPedido(Pedido p) throws SQLException;
}
