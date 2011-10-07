/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uniriotec.np2tec.propid.spring.userstore;

import br.uniriotec.np2tec.propid.shared.interfaces.IUserStore;

/**
 *
 * @author Felipe
 */
public class UserStore implements IUserStore{
    private String chaveUsuario;
    

    public String getChaveUsuario() {
        return chaveUsuario;
    }

    public void setChaveUsuario(String chaveUsuario) {
        this.chaveUsuario = chaveUsuario;
    }

    
}
