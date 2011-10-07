/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uniriotec.np2tec.propid.dao;

import br.uniriotec.np2tec.propid.shared.interfaces.IUserStore;
import br.uniriotec.np2tec.propid.shared.interfaces.IUserStoreAware;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.hibernate.Session;

/**
 *
 * @author np2tec-02
 */
public class SessionFactory implements IUserStoreAware{
    private IUserStore userStore = null;

    public Session openSession(){
        Session s = HibernateUtil.getSessionFactory().openSession();

        if(userStore != null){
            //preparar o contexto da sessao no banco com a chave do usuario
            Connection con = s.connection();
            try{
                CallableStatement st = con.prepareCall("{call dbms_application_info.set_client_info(?)}");
                st.setString(1, userStore.getChaveUsuario());
                st.executeUpdate();
            }catch(SQLException e){
                return null;
            }
        }

        return s;
    }


    public void setUserStore(IUserStore userStore) {
        this.userStore = userStore;
    }

    public IUserStore getUserStore() {
        return userStore;
    }


}
