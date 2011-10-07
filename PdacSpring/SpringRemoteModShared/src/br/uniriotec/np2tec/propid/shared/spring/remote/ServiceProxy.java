/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uniriotec.np2tec.propid.shared.spring.remote;

import br.uniriotec.np2tec.propid.shared.interfaces.IUserStore;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 *
 */
public class ServiceProxy extends HttpInvokerProxyFactoryBean implements BeanFactoryAware {
    private BeanFactory beanFactory;
    private IUserStore userStore = null;
    private static final String PARAMETER = "chave";

    public void setUserStore(IUserStore userStore) {
        this.userStore = userStore;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
            this.beanFactory = beanFactory;
    }

    @Override
    public String getServiceUrl() {
StringBuilder serviceUrl = new StringBuilder(super.getServiceUrl());
if (userStore != null) {
    int pos = serviceUrl.indexOf("?");
    if(pos == -1){
        serviceUrl.append("?");
    }else{
        serviceUrl.append("&");
    }
        serviceUrl.append(PARAMETER);
        serviceUrl.append("=");
        serviceUrl.append(userStore.getChaveUsuario());
} 

return serviceUrl.toString();
    }
}
