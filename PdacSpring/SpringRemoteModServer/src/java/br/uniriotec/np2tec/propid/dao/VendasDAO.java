package br.uniriotec.np2tec.propid.dao;

import br.uniriotec.np2tec.propid.shared.classes.ItemPedido;
import br.uniriotec.np2tec.propid.shared.classes.Pedido;
import br.uniriotec.np2tec.propid.shared.interfaces.IUserStore;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.hibernate.Session;
import br.uniriotec.np2tec.propid.shared.spring.context.ApplicationContextProvider;
import java.sql.SQLException;
import java.util.Random;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

public class VendasDAO {

    private SessionFactory sessionFactory = null;

    public double consultarPrevisaoMudanca() throws Exception{
        
        ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
        sessionFactory = (SessionFactory) ctx.getBean("beanSessionFactory");

        String sql = "select sum(l_extendedprice * l_discount) as revenue "+
                     "from tpch.lineitem "+
                     "where l_shipdate >= date '1994-01-01' "+
                      "and l_shipdate < date '1994-01-01' + interval '1' year "+
                      "and l_discount between 0.06 - 0.01 and 0.06 + 0.01 "+
                      "and l_quantity < 24";

        Session s = sessionFactory.openSession();
        
        /**************************************************************************
         ** Trecho de código usado no teste. Desencessário para a arquitetura.   **
         **************************************************************************/
            //Gerar ID randomico para a Thread
            int threadID = generateID();
            //Printar o ID da thread e a chave do usuário armazenado
            printStatusConsole(threadID, s, "PreProc");
            //Parada para testar concorrencia na sessão
            Thread.sleep(15000);
            //Printar o ID da thread e a chave do usuário armazenado
            printStatusConsole(threadID, s, "PosProc");
        /**************************************************************************
         **                         Fim do código de teste                       **
         **************************************************************************/
        
        Connection con = s.connection();
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();  
        s.close();
        

        double resultado = 0;
        while(rs.next()){
            resultado = rs.getDouble("revenue");
        }
        
        return resultado;
    }
    
    @Transactional
    public boolean inserirPedido(Pedido p) throws Exception{
        ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
        sessionFactory = (SessionFactory) ctx.getBean("beanSessionFactory");
        Session s = sessionFactory.openSession();
        
        Transaction tx = null;
        
        try{
            //Inserir Pedido
            tx = s.beginTransaction();
            s.save(p);
            tx.commit();
            
            tx = s.beginTransaction();
            //Inserir Itens do pedido
            for(ItemPedido ip : p.getItensPedido()){
                s.save(ip);
            }
            tx.commit();
        }catch(RuntimeException ex){
            throw ex;
        }
        
        //Fechar Sessão
        s.close();

        
        return true;
    }
    
    
    
    /*********************************************************************
     **                                                                 **
     **      Métodos auxiliares utilizados no teste de concorrência     **
     **                                                                 **
     *********************************************************************/
    
    private int generateID(){
        Random randomGenerator = new Random();
        int threadID = randomGenerator.nextInt(1000000000);
        return threadID;
    }
    
    private IUserStore retrieveUserStore(){
        ApplicationContext ctx = ApplicationContextProvider.getApplicationContext();
        IUserStore userStore = (IUserStore) ctx.getBean("beanUserStore");
        return userStore;
    }

    private String retrieveClientInfo(Session s){
        String sql = "select sys_context('userenv', 'client_info') as CLIENT_INFO from dual";
        String resultado = "";
        try{
            Connection con = s.connection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                resultado = rs.getString("CLIENT_INFO");
            }
        }catch(SQLException ex){
            System.out.println("ERRO: " + ex.getMessage());
        }
        return resultado;
    }
    
    private boolean printStatusConsole(int threadID, Session s, String tipo){
        System.out.println(tipo+" -> Thread ID: " + threadID + 
            " / UserKey: " + retrieveUserStore().getChaveUsuario() +
            " / Session_Client_Info: " + retrieveClientInfo(s));
        return true;
    }

}
