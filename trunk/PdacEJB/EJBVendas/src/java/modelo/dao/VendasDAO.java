package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.hibernate.Session;

public class VendasDAO {
    private String chaveUsuario;
    
    public double consultarPrevisaoMudanca() throws Exception{
        String sql = "select sum(l_extendedprice * l_discount) as revenue "+
                     "from tpch.lineitem "+
                     "where l_shipdate >= date '1994-01-01' "+
                      "and l_shipdate < date '1994-01-01' + interval '1' year "+
                      "and l_discount between 0.06 - 0.01 and 0.06 + 0.01 "+
                      "and l_quantity < 24";

        Session s = HibernateUtil.getSession(chaveUsuario);
        Connection con = s.connection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        HibernateUtil.closeCurrentSession();

        double resultado = 0;
        while(rs.next()){
            resultado = rs.getDouble("revenue");
        }
        return resultado;
    }

    public String getUsuario(){
        return this.chaveUsuario;
    }

    public void setUsuario(String chaveusuario){
        this.chaveUsuario = chaveusuario;
    }

}
