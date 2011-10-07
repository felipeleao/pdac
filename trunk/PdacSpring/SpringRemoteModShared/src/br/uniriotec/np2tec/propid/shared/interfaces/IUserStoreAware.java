/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.uniriotec.np2tec.propid.shared.interfaces;

/**
 *
 * @author np2tec-08
 */
public interface IUserStoreAware {
    void setUserStore(IUserStore userStore);
    public IUserStore getUserStore();
}
