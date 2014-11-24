package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
            try {
                Configuration cfg = new Configuration().configure("hibernate.cfg.xml");         
                StandardServiceRegistryBuilder sb = new StandardServiceRegistryBuilder();
                sb.applySettings(cfg.getProperties());
                StandardServiceRegistry standardServiceRegistry = sb.build();                   
                sessionFactory = cfg.buildSessionFactory(standardServiceRegistry);              
            } catch (Throwable th) {
                    System.err.println("Enitial SessionFactory creation failed" + th);
                    throw new ExceptionInInitializerError(th);
            }
    }
    public static SessionFactory getSessionFactory() {
            return sessionFactory;
    }

   /* @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch"})
    private static SessionFactory buidSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                    applySettings(configuration.getProperties()).build();

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }
*/
  
    @SuppressWarnings({"CallToThreadDumpStack", "BroadCatchBlock", "TooBroadCatch", "CallToPrintStackTrace"})
    public static void closeSessionFactory(Session sessao) {
        try {
            sessao.flush();
            sessao.clear();
            
            if (!sessionFactory.isClosed() && sessionFactory != null) {
                sessionFactory.close();
            }
            
            if(sessao.isOpen()) {
                sessao.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


