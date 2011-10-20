/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ClienteGUI.java
 *
 * Created on 29/12/2010, 15:03:42
 */

package clientepropid;

import javax.swing.JOptionPane;
import controle.ManterVendasRemote;

/**
 *
 * @author azevedo
 */
public class ClienteGUI extends javax.swing.JFrame {

    /** Creates new form ClienteGUI */
    public ClienteGUI() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextMudancaReceita = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jBtnConsultarChave = new javax.swing.JButton();
        jTextChaveUsuario = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Prototipo Vendas");

        jLabel4.setText("Montante descontado:");

        jLabel1.setText("Chave do Usuário:");

        jTextMudancaReceita.setEditable(false);
        jTextMudancaReceita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextMudancaReceitaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Previsão de Mudança Receita");

        jBtnConsultarChave.setText("Consultar");
        jBtnConsultarChave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnConsultarChaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextChaveUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBtnConsultarChave, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84))))
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jTextMudancaReceita, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(107, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextChaveUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnConsultarChave))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextMudancaReceita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnConsultarChaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnConsultarChaveActionPerformed
        ServiceLocator.getInstance().setUsuario(jTextChaveUsuario.getText());

        try{
            //Recuperar o EJB
            ManterVendasRemote ejb = (ManterVendasRemote) ServiceLocator.getInstance().get("ManterVendas/remote");
            //Interromper a execução
            showMessage("Execução interrompida. Clique para continuar.");
            //Recuperar Valor de descontos dados e Chave do usuario no EJB
            double retorno = ejb.consultarPrevisaoMudancaReceita();
            String usuario = ejb.getUsuario();
            //apresentar o resultado da consulta
            showMessage(usuario);
            jTextMudancaReceita.setText(String.valueOf(retorno));

        }catch(Exception e){
            e.printStackTrace();
            showMessage("Erro ao consultar Poço: " + e.getMessage());
        }


}//GEN-LAST:event_jBtnConsultarChaveActionPerformed

    private void jTextMudancaReceitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextMudancaReceitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextMudancaReceitaActionPerformed
    private void showMessage(String message){
        JOptionPane.showMessageDialog(this, message,
                this.getTitle(), JOptionPane.ERROR_MESSAGE);
    }

    private void showErrorMessage(String message){
        showMessage("Erro ao consultar Poço: "+message);
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClienteGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnConsultarChave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextChaveUsuario;
    private javax.swing.JTextField jTextMudancaReceita;
    // End of variables declaration//GEN-END:variables

}