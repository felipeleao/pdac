package modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    private static ThreadLocal<Session> sessions = new ThreadLocal<Session>();

    static {
        sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    }

    public static Session getSession(){
        return getSession("");
    }

    public static Session getSession(String chaveUsuario) {

        if (sessions.get() == null) {
           sessions.set(sessionFactory.openSession());        

            //preparar o contexto da sessao no banco com a chave do usuario
            Connection con = sessions.get().connection();
            try{
                CallableStatement st = con.prepareCall(
                        "{call dbms_application_info.set_client_info(?)}");
                st.setString(1, chaveUsuario);
                st.executeUpdate();
            }catch(SQLException e){
                return null;
            }
        }

        return sessions.get();
    }

    public static void closeCurrentSession() {
        sessions.get().close();
        sessions.set(null);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}