/*
 * To change this template; choose Tools | Templates
 * and open the template in the editor.
 */
package br.uniriotec.np2tec.propid.shared.classes;

import java.io.Serializable;

/**
 *
 * @author Felipe
 */
public class ItemPedido implements Serializable{
    private ItemPedidoPK pk;
    private int L_PARTKEY; 
    private int L_SUPPKEY; 
    private int L_QUANTITY; 
    private double L_EXTENDEDPRICE; 
    private double L_DISCOUNT; 
    private double L_TAX; 
    private String L_RETURNFLAG; 
    private String L_LINESTATUS; 
    private String L_SHIPDATE; 
    private String L_COMMITDATE; 
    private String L_RECEIPTDATE; 
    private String L_SHIPINSTRUCT; 
    private String L_SHIPMODE; 
    private String L_COMMENT;
    
    public ItemPedido(int L_ORDERKEY, int L_PARTKEY, int L_SUPPKEY, int L_LINENUMBER, int L_QUANTITY, 
            double L_EXTENDEDPRICE, double L_DISCOUNT, double L_TAX, String L_RETURNFLAG, String L_LINESTATUS, 
            String L_SHIPDATE, String L_COMMITDATE, String L_RECEIPTDATE, String L_SHIPINSTRUCT, 
            String L_SHIPMODE, String L_COMMENT){
        pk = new ItemPedidoPK();
        pk.setL_ORDERKEY(L_ORDERKEY);
        this.L_PARTKEY = L_PARTKEY;
        this.L_SUPPKEY = L_SUPPKEY;
        pk.setL_LINENUMBER(L_LINENUMBER);
        this.L_QUANTITY = L_QUANTITY;
        this.L_EXTENDEDPRICE = L_EXTENDEDPRICE;
        this.L_DISCOUNT = L_DISCOUNT;
        this.L_TAX = L_TAX;
        this.L_RETURNFLAG = L_RETURNFLAG;
        this.L_LINESTATUS = L_LINESTATUS;
        this.L_SHIPDATE = L_SHIPDATE;
        this.L_COMMITDATE = L_COMMITDATE;
        this.L_RECEIPTDATE = L_RECEIPTDATE;
        this.L_SHIPINSTRUCT = L_SHIPINSTRUCT;
        this.L_SHIPMODE = L_SHIPMODE;
        this.L_COMMENT = L_COMMENT;
    }
    
    public int getL_PARTKEY() {
        return L_PARTKEY;
    }

    public void setL_PARTKEY(int L_PARTKEY) {
        this.L_PARTKEY = L_PARTKEY;
    }

    public int getL_SUPPKEY() {
        return L_SUPPKEY;
    }

    public void setL_SUPPKEY(int L_SUPPKEY) {
        this.L_SUPPKEY = L_SUPPKEY;
    }

    public int getL_LINENUMBER() {
        return getPk().getL_LINENUMBER();
    }

    public void setL_LINENUMBER(int L_LINENUMBER) {
        getPk().setL_LINENUMBER(L_LINENUMBER);
    }

    public int getL_QUANTITY() {
        return L_QUANTITY;
    }

    public void setL_QUANTITY(int L_QUANTITY) {
        this.L_QUANTITY = L_QUANTITY;
    }

    public double getL_EXTENDEDPRICE() {
        return L_EXTENDEDPRICE;
    }

    public void setL_EXTENDEDPRICE(double L_EXTENDEDPRICE) {
        this.L_EXTENDEDPRICE = L_EXTENDEDPRICE;
    }

    public double getL_DISCOUNT() {
        return L_DISCOUNT;
    }

    public void setL_DISCOUNT(double L_DISCOUNT) {
        this.L_DISCOUNT = L_DISCOUNT;
    }

    public double getL_TAX() {
        return L_TAX;
    }

    public void setL_TAX(double L_TAX) {
        this.L_TAX = L_TAX;
    }

    public String getL_RETURNFLAG() {
        return L_RETURNFLAG;
    }

    public void setL_RETURNFLAG(String L_RETURNFLAG) {
        this.L_RETURNFLAG = L_RETURNFLAG;
    }

    public String getL_LINESTATUS() {
        return L_LINESTATUS;
    }

    public void setL_LINESTATUS(String L_LINESTATUS) {
        this.L_LINESTATUS = L_LINESTATUS;
    }

    public String getL_SHIPDATE() {
        return L_SHIPDATE;
    }

    public void setL_SHIPDATE(String L_SHIPDATE) {
        this.L_SHIPDATE = L_SHIPDATE;
    }

    public String getL_COMMITDATE() {
        return L_COMMITDATE;
    }

    public void setL_COMMITDATE(String L_COMMITDATE) {
        this.L_COMMITDATE = L_COMMITDATE;
    }

    public String getL_RECEIPTDATE() {
        return L_RECEIPTDATE;
    }

    public void setL_RECEIPTDATE(String L_RECEIPTDATE) {
        this.L_RECEIPTDATE = L_RECEIPTDATE;
    }

    public String getL_SHIPINSTRUCT() {
        return L_SHIPINSTRUCT;
    }

    public void setL_SHIPINSTRUCT(String L_SHIPINSTRUCT) {
        this.L_SHIPINSTRUCT = L_SHIPINSTRUCT;
    }

    public String getL_SHIPMODE() {
        return L_SHIPMODE;
    }

    public void setL_SHIPMODE(String L_SHIPMODE) {
        this.L_SHIPMODE = L_SHIPMODE;
    }

    public String getL_COMMENT() {
        return L_COMMENT;
    }

    public void setL_COMMENT(String L_COMMENT) {
        this.L_COMMENT = L_COMMENT;
    }

    public int getL_ORDERKEY() {
        return getPk().getL_ORDERKEY();
    }

    public void setL_ORDERKEY(int L_ORDERKEY) {
        getPk().setL_ORDERKEY(L_ORDERKEY);
    }

    public ItemPedidoPK getPk() {
        return pk;
    }

    public void setPk(ItemPedidoPK pk) {
        this.pk = pk;
    }
    
    
}
