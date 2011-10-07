/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uniriotec.np2tec.propid.shared.spring.remote;

import br.uniriotec.np2tec.propid.shared.interfaces.IUserStore;
import br.uniriotec.np2tec.propid.shared.spring.context.ApplicationContextProvider;
import java.io.IOException;
import java.io.Serializable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.web.HttpRequestHandler;

/**
 *  Classe que através do padrão de projeto COMPOSITE substitui a classe 
 * HttpInvokerServiceExporter, original do Spring Remote. O método haldeRequest()
 * é reimplementado objetvando-se recuperar no request o parâmetro cujo nome é 
 * definido na variável estatica PARAMETRO.
 *
 * @author Felipe Leão
 */
public class ServiceExporter implements HttpRequestHandler, InitializingBean, BeanClassLoaderAware, BeanFactoryAware, Serializable {

    private HttpInvokerServiceExporter invoker = new HttpInvokerServiceExporter();
    private BeanFactory beanFactory;
    private ApplicationContext context;
    private static final String PARAMETRO = "chave";

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter(PARAMETRO) != null){
            String valorParametro = request.getParameter(PARAMETRO);

            context = ApplicationContextProvider.getApplicationContext();
            IUserStore userStore = (IUserStore) context.getBean("beanUserStore");
            //Injeção da chave na UserStore
            userStore.setChaveUsuario(valorParametro);
        }

        invoker.handleRequest(request, response);
    }

    public void afterPropertiesSet() throws Exception {
        invoker.afterPropertiesSet();
    }

    public void setBeanClassLoader(ClassLoader cl) {
        invoker.setBeanClassLoader(cl);
    }

    public void setBeanFactory(BeanFactory bf) throws BeansException {
        beanFactory = bf;
    }

    public void setService(Object service) {
        invoker.setService(service);
    }

    public void setServiceInterface(Class serviceInterface) {
        invoker.setServiceInterface(serviceInterface);
    }

    
}