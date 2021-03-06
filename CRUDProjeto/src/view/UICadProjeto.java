/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ProjetoController;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import model.Projeto;

/**
 *
 * @author Artom
 */
public class UICadProjeto extends javax.swing.JInternalFrame {

    /**
     * Creates new form UICadProjeto
     */
    public UICadProjeto() {
        initComponents();
        gerarMaskara();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        bgStatus = new javax.swing.ButtonGroup();
        jlNome = new javax.swing.JLabel();
        jtNome = new javax.swing.JTextField();
        jlDesc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaDesc = new javax.swing.JTextArea();
        jlPrazo = new javax.swing.JLabel();
        jftPrazo = new javax.swing.JFormattedTextField();
        jlDataInicio = new javax.swing.JLabel();
        jftDataInicio = new javax.swing.JFormattedTextField();
        jlDataFim = new javax.swing.JLabel();
        jftDataFim = new javax.swing.JFormattedTextField();
        jlStatus = new javax.swing.JLabel();
        jlTitulo = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jrAndamento = new javax.swing.JRadioButton();
        jrConcluido = new javax.swing.JRadioButton();
        jrCancelado = new javax.swing.JRadioButton();
        jbCadastrar = new javax.swing.JButton();
        jbLimpar = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        setClosable(true);
        setIconifiable(true);
        setTitle("Cdastro de Projetos");

        jlNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlNome.setText("Nome:");

        jlDesc.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlDesc.setText("Descrição:");

        jtaDesc.setColumns(20);
        jtaDesc.setRows(5);
        jScrollPane1.setViewportView(jtaDesc);

        jlPrazo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlPrazo.setText("Prazo:");

        jlDataInicio.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlDataInicio.setText("Data inicio");

        jlDataFim.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlDataFim.setText("Data fim");

        jlStatus.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlStatus.setText("Status:");

        jlTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jlTitulo.setText("Seja Bem Vindo ao cadastro de projetos!");

        jLayeredPane1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        bgStatus.add(jrAndamento);
        jrAndamento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jrAndamento.setText("Em Andamento");
        jrAndamento.setActionCommand("Em andamento");

        bgStatus.add(jrConcluido);
        jrConcluido.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jrConcluido.setText("Concluído");
        jrConcluido.setActionCommand("Concluído");

        bgStatus.add(jrCancelado);
        jrCancelado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jrCancelado.setText("Cancelado");
        jrCancelado.setActionCommand("Cancelado");

        jLayeredPane1.setLayer(jrAndamento, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jrConcluido, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jrCancelado, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrAndamento)
                    .addComponent(jrConcluido)
                    .addComponent(jrCancelado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jrAndamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrConcluido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jrCancelado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jbCadastrar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbCadastrar.setText("Cadastrar");
        jbCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCadastrarActionPerformed(evt);
            }
        });

        jbLimpar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jbLimpar.setText("Limpar");
        jbLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlDesc)
                                    .addComponent(jlNome))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jtNome)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlStatus)
                                .addGap(38, 38, 38)
                                .addComponent(jLayeredPane1)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlDataInicio)
                                        .addGap(18, 18, 18)
                                        .addComponent(jftDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlPrazo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jftPrazo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlDataFim)
                                        .addGap(30, 30, 30)
                                        .addComponent(jftDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jlTitulo)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jlTitulo)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlNome)
                            .addComponent(jtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlDesc)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlPrazo)
                            .addComponent(jftPrazo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jlDataInicio))
                            .addComponent(jftDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jlDataFim))
                            .addComponent(jftDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlStatus)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jbCadastrar)
                            .addGap(18, 18, 18)
                            .addComponent(jbLimpar))
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        setBounds(0, 0, 560, 352);
    }// </editor-fold>//GEN-END:initComponents

    /**
    * Método responsável por gerar a maskara dos campos prazo, 
    * dataInicio e dataFim.
    */
    private void gerarMaskara(){
        try{
            MaskFormatter data = new MaskFormatter("##/##/####");
            jftPrazo.setFormatterFactory(new DefaultFormatterFactory(data));
            jftDataInicio.setFormatterFactory(new DefaultFormatterFactory(data));
            jftDataFim.setFormatterFactory(new DefaultFormatterFactory(data));
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, 
                    "Erro na maskara" + e.getMessage());
        }
    }
    
    //Método responsável por cadastrar os dados no banco
    private void cadastrar() {

        try {
            Projeto p = new Projeto();
            p.setNome(jtNome.getText());
            p.setDescricao(jtaDesc.getText());
            p.setPrazo(jftPrazo.getText());
            p.setDataInicio(jftDataInicio.getText());
            p.setDataFim(jftDataFim.getText());
            p.setStatus(bgStatus.getSelection().getActionCommand());

            ProjetoController pc = new ProjetoController();

            pc.cadastrarProjeto(p);

            JOptionPane.showMessageDialog(rootPane,
                    "Projeto cadastrado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Erro! " + e.getMessage());
        }
    }

    //Método responsável por limpar os campos de texto
    private void limpar() {
        jtNome.setText(null);
        jtaDesc.setText(null);
        jftPrazo.setText(null);
        jftDataInicio.setText(null);
        jftDataFim.setText(null);
        bgStatus.clearSelection();
        gerarMaskara();
    }


    private void jbCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCadastrarActionPerformed
        cadastrar();
        limpar();
    }//GEN-LAST:event_jbCadastrarActionPerformed

    private void jbLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimparActionPerformed
        limpar();
    }//GEN-LAST:event_jbLimparActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgStatus;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jbCadastrar;
    private javax.swing.JButton jbLimpar;
    private javax.swing.JFormattedTextField jftDataFim;
    private javax.swing.JFormattedTextField jftDataInicio;
    private javax.swing.JFormattedTextField jftPrazo;
    private javax.swing.JLabel jlDataFim;
    private javax.swing.JLabel jlDataInicio;
    private javax.swing.JLabel jlDesc;
    private javax.swing.JLabel jlNome;
    private javax.swing.JLabel jlPrazo;
    private javax.swing.JLabel jlStatus;
    private javax.swing.JLabel jlTitulo;
    private javax.swing.JRadioButton jrAndamento;
    private javax.swing.JRadioButton jrCancelado;
    private javax.swing.JRadioButton jrConcluido;
    private javax.swing.JTextField jtNome;
    private javax.swing.JTextArea jtaDesc;
    // End of variables declaration//GEN-END:variables
}
