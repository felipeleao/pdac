package br.uniriotec.np2tec.propid.shared.spring.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Classe para prover à aplicação o contexto do spring já inicializado, desta
 * forma garante-se que toda a aplicação utilize um único só contexto e evita
 * a múltipla inicialização do framework.
 *
 * @author Felipe Leão
 */
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext contexto = null;

    public static ApplicationContext getApplicationContext() {
        return contexto;
    }

    public void setApplicationContext(ApplicationContext contexto)
            throws BeansException {
        // Assign the ApplicationContext into a static method
        ApplicationContextProvider.contexto = contexto;
    }
}

