/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uniriotec.np2tec.propid.shared.classes;

import java.io.Serializable;

/**
 *
 * @author Felipe
 */
public class ItemPedidoPK implements Serializable{
    private int L_ORDERKEY;
    private int L_LINENUMBER;

    public int getL_ORDERKEY() {
        return L_ORDERKEY;
    }

    public void setL_ORDERKEY(int L_ORDERKEY) {
        this.L_ORDERKEY = L_ORDERKEY;
    }

    public int getL_LINENUMBER() {
        return L_LINENUMBER;
    }

    public void setL_LINENUMBER(int L_LINENUMBER) {
        this.L_LINENUMBER = L_LINENUMBER;
    }
    
    
}
