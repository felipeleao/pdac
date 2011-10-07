/*
 * To change this template;
private String choose Tools | Templates
 * and open the template in the editor.
 */
package br.uniriotec.np2tec.propid.shared.classes;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Felipe
 */
public class Pedido implements Serializable{
    private String o_orderkey;
    private String o_custkey;
    private String o_orderstatus;
    private String o_totalprice;
    private String o_orderdate;
    private String o_orderpriority;
    private String o_clerk;
    private String o_shippriority;
    private String o_comment;
    private List<ItemPedido> itensPedido;
    
    public Pedido(String o_orderkey, String o_custkey, String o_orderstatus, 
            String o_totalprice, String o_orderdate, String o_orderpriority, 
            String o_clerk, String o_shippriority, String o_comment){
        this.o_orderkey = o_orderkey; 
        this.o_custkey = o_custkey; 
        this.o_orderstatus = o_orderstatus; 
        this.o_totalprice = o_totalprice;
        this.o_orderdate = o_orderdate; 
        this.o_orderpriority = o_orderpriority; 
        this.o_clerk = o_clerk; 
        this.o_shippriority = o_shippriority; 
        this.o_comment = o_comment;
    }
    
    public String getO_orderkey() {
        return o_orderkey;
    }

    public void setO_orderkey(String o_orderkey) {
        this.o_orderkey = o_orderkey;
    }

    public String getO_custkey() {
        return o_custkey;
    }

    public void setO_custkey(String o_custkey) {
        this.o_custkey = o_custkey;
    }

    public String getO_orderstatus() {
        return o_orderstatus;
    }

    public void setO_orderstatus(String o_orderstatus) {
        this.o_orderstatus = o_orderstatus;
    }

    public String getO_totalprice() {
        return o_totalprice;
    }

    public void setO_totalprice(String o_totalprice) {
        this.o_totalprice = o_totalprice;
    }

    public String getO_orderdate() {
        return o_orderdate;
    }

    public void setO_orderdate(String o_orderdate) {
        this.o_orderdate = o_orderdate;
    }

    public String getO_orderpriority() {
        return o_orderpriority;
    }

    public void setO_orderpriority(String o_orderpriority) {
        this.o_orderpriority = o_orderpriority;
    }

    public String getO_clerk() {
        return o_clerk;
    }

    public void setO_clerk(String o_clerk) {
        this.o_clerk = o_clerk;
    }

    public String getO_shippriority() {
        return o_shippriority;
    }

    public void setO_shippriority(String o_shippriority) {
        this.o_shippriority = o_shippriority;
    }

    public String getO_comment() {
        return o_comment;
    }

    public void setO_comment(String o_comment) {
        this.o_comment = o_comment;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }
    
    
}
