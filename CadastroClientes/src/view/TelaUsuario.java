/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.UsuarioController;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Usuario;
import model.Validacao;

/**
 *
 * @author Artom
 */
public class TelaUsuario extends javax.swing.JInternalFrame {

    //Criando o modelo da tabela
    DefaultTableModel dtm = new DefaultTableModel(
            new Object[][]{},
            new Object[]{"Código", "Nome", "Email", "Login",
                "Senha", "Perfil"});

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() {
        initComponents();
        preencherTabela();
        ativarCampos(true);
    }

    //Método responsável por preencher a tabela
    private void preencherTabela() {
        try {
            UsuarioController uc = new UsuarioController();

            ArrayList<Usuario> user = new ArrayList<>();
            user = uc.buscarUsuario();

            for (int i = 0; i < user.size(); i++) {
                dtm.addRow(new String[]{
                    String.valueOf(user.get(i).getIduser()),
                    user.get(i).getNome(),
                    user.get(i).getEmail(),
                    user.get(i).getLogin(),
                    user.get(i).getSenha(),
                    user.get(i).getPerfil()
                });     
            }
            
            tblUsuarios.setModel(dtm);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Erro! " + e.getMessage());
        }
    }//Fecha método

    //Método capaz de ativar ou desativar os campos
    private void ativarCampos(boolean edit) {
        txtNome.setEnabled(!edit);
        txtEmail.setEnabled(!edit);
        txtLogin.setEnabled(!edit);
        txtSenha.setEnabled(!edit);
        cbxPerfil.setEnabled(!edit);
        btnCancelar.setEnabled(!edit);
        btnSalvar.setEnabled(!edit);
        btnAlterar.setEnabled(!edit);
        btnNovo.setEnabled(edit);
        btnEditar.setEnabled(edit);
        btnDeletar.setEnabled(edit);
    }//fecha método

    //Método que limpa a tabela
    private void limparTabela() {
        dtm.setRowCount(0);
    }//Fecha método

    //Método para limpar os campos
    private void limparCampos() {
        txtCodigo.setText(null);
        txtNome.setText(null);
        txtEmail.setText(null);
        txtLogin.setText(null);
        txtSenha.setText(null);
        cbxPerfil.setSelectedIndex(0);
    }//Fecha método

    //Método responsável por validar os dados dos campos
    private boolean validarCampos() {
        if (!(Validacao.validarNome(txtNome.getText()))) {
            JOptionPane.showMessageDialog(rootPane, "Nome inválido");
            txtNome.requestFocus();
            return false;
        }//fecha if
        if (!(Validacao.validarEmail(txtEmail.getText()))) {
            JOptionPane.showMessageDialog(rootPane, "Email inválido");
            txtEmail.requestFocus();
            return false;
        }//Fecha if
        if (!(Validacao.validarLogin(txtLogin.getText()))) {
            JOptionPane.showMessageDialog(rootPane, "Login inválido");
            txtLogin.requestFocus();
            return false;
        }//Fecha if   
        if (!(Validacao.validarSenha(new String(txtSenha.getPassword())))) {
            JOptionPane.showMessageDialog(rootPane, "Senha inválida");
            txtSenha.requestFocus();
            return false;
        }
        return true;
    }//Fecha método

    //Método responsável por cadastrar os dados 
    private void cadastrar() {
        try {
            if (validarCampos()) {
                Usuario u = new Usuario();
                u.setNome(txtNome.getText());
                u.setEmail(txtEmail.getText());
                u.setLogin(txtLogin.getText());
                u.setSenha(new String(txtSenha.getPassword()));
                u.setPerfil(cbxPerfil.getSelectedItem().toString());

                UsuarioController uc = new UsuarioController();
                uc.cadastrarUsuario(u);

                JOptionPane.showMessageDialog(rootPane,
                        "Usuário cadastrado com sucesso!");

                limparCampos();
                ativarCampos(true);

            }//Fecha if

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,
                    "Erro ao cadastrar usuário! " + e.getMessage());
        }//Fecha catch
    }//Fecha método

    //Método responsável por setar os dados da tabela nos campos
    private void editar() {
        int linha = tblUsuarios.getSelectedRow();
        if (linha != -1) {
            txtCodigo.setText((String) tblUsuarios.getValueAt(linha, 0));
            txtNome.setText((String) tblUsuarios.getValueAt(linha, 1));
            txtEmail.setText((String) tblUsuarios.getValueAt(linha, 2));
            txtLogin.setText((String) tblUsuarios.getValueAt(linha, 3));
            txtSenha.setText((String) tblUsuarios.getValueAt(linha, 4));
            if (tblUsuarios.getValueAt(linha, 5).equals("Admin")) {
                cbxPerfil.setSelectedIndex(0);
            } else {
                cbxPerfil.setSelectedIndex(1);
            }//Fecha else
        } else {
            JOptionPane.showMessageDialog(rootPane,
                    "Selecione um linha na tabela!");
            ativarCampos(true);
        }//Fecha else
    }//Fecha método

    //Método responsável por aletar os dados 
    private void alterar() {
        try {
            if (validarCampos()) {
                Usuario u = new Usuario();
                u.setIduser(Long.parseLong(txtCodigo.getText()));
                u.setNome(txtNome.getText());
                u.setEmail(txtEmail.getText());
                u.setLogin(txtLogin.getText());
                u.setSenha(new String(txtSenha.getPassword()));
                u.setPerfil(cbxPerfil.getSelectedItem().toString());

                UsuarioController uc = new UsuarioController();
                uc.alterarUsuario(u);

                JOptionPane.showMessageDialog(rootPane,
                        "Dados alterados com sucesso!");

                limparCampos();
                ativarCampos(true);
            }//Fecha if

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,
                    "Erro ao alterar dados!" + e.getMessage());

        }//Fecha catch
    }//Fecha método

    //Método que deleta um usuário do banco
    private void deletar() {
        try {
            int linha = tblUsuarios.getSelectedRow();
            if (linha != -1) {
                int op = JOptionPane.showConfirmDialog(rootPane,
                        "Tem certeza que deseja excluir esse usuário?",
                        "Exclusão", JOptionPane.YES_NO_OPTION);
                if (op == JOptionPane.YES_OPTION) {
                    UsuarioController uc = new UsuarioController();
                    String id = tblUsuarios.getValueAt(linha, 0).toString();
                    uc.deletarUsuario(Long.parseLong(id));
                    
                    JOptionPane.showMessageDialog(rootPane, 
                            "Usuário excluido com sucesso!");
                }//Fecha if
            }else {
                JOptionPane.showMessageDialog(rootPane, "Selecione um linha!");
            }//Fecha else
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Erro!" + e.getMessage());
        }//Fecha cacth
    }//Fecha métod
    
    //Método responsável por filtrar o nome do usário no banco 
    private void filtrar(){
        try {
            String busca = txtBusca.getText();
            if(busca.isEmpty()){
                limparTabela();
                preencherTabela();
            }else{
                String query = "where nome like '%" +busca+ "%'";
                
                ArrayList<Usuario> user = new ArrayList<>();
                UsuarioController uc = new UsuarioController();
                
                user = uc.filtrarUsuario(query);
                for(int i = 0; i < user.size(); i++){
                    dtm.addRow(new String[]{
                        String.valueOf(user.get(i).getIduser()),
                        user.get(i).getNome(),
                        user.get(i).getEmail(),
                        user.get(i).getLogin(),
                        user.get(i).getSenha(),
                        user.get(i).getPerfil()
                    });
                }//fecha for
                
                tblUsuarios.setModel(dtm);
            }//fecha else
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, 
                    "Erro ao filtrar!" + e.getMessage());
        }//Fecha cacth     
    }//Fecha método

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtBusca = new javax.swing.JTextField();
        jlIconBusca = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        jlCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jlNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jlEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jlLogin = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        jlSenha = new javax.swing.JLabel();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        jlPerfil = new javax.swing.JLabel();
        cbxPerfil = new javax.swing.JComboBox<>();
        txtSenha = new javax.swing.JPasswordField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setPreferredSize(new java.awt.Dimension(800, 529));

        jlIconBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search25.png"))); // NOI18N

        btnBuscar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblUsuarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblUsuarios);

        jlCodigo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlCodigo.setText("Código:");

        txtCodigo.setEnabled(false);

        jlNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlNome.setText("Nome:");

        jlEmail.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlEmail.setText("Email:");

        jlLogin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlLogin.setText("Login:");

        jlSenha.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlSenha.setText("Senha:");

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/AddFile50.png"))); // NOI18N
        btnNovo.setToolTipText("Adicionar");
        btnNovo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fileEdit50.png"))); // NOI18N
        btnEditar.setToolTipText("Editar");
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnDeletar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fileDelete50.png"))); // NOI18N
        btnDeletar.setToolTipText("Deletar");
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarActionPerformed(evt);
            }
        });

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/fileSave50.png"))); // NOI18N
        btnSalvar.setToolTipText("Salvar");
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel50.png"))); // NOI18N
        btnCancelar.setToolTipText("Cancelar");
        btnCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/FileOK50.png"))); // NOI18N
        btnAlterar.setToolTipText("Confirmar Alteração");
        btnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        jlPerfil.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlPerfil.setText("Senha:");

        cbxPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jlEmail)
                                .addGap(22, 22, 22)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(260, 260, 260)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlLogin)
                                            .addComponent(jlSenha)
                                            .addComponent(jlPerfil)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jlCodigo)
                                            .addComponent(jlNome))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNome)
                                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(104, 104, 104)))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtLogin)
                                    .addComponent(cbxPerfil, 0, 150, Short.MAX_VALUE)
                                    .addComponent(txtSenha))))
                        .addGap(133, 133, 133))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeletar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122))))
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlIconBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlIconBusca))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlCodigo)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlNome)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlLogin)
                            .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlSenha)
                            .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlPerfil)
                    .addComponent(cbxPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNovo)
                    .addComponent(btnDeletar)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar)
                    .addComponent(btnEditar)
                    .addComponent(btnAlterar))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        limparCampos();
        ativarCampos(false);
        btnAlterar.setEnabled(false);
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        limparCampos();
        ativarCampos(false);
        btnSalvar.setEnabled(false);
        editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarActionPerformed
        deletar();
        limparTabela();
        preencherTabela();
    }//GEN-LAST:event_btnDeletarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        cadastrar();
        limparTabela();
        preencherTabela();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        alterar();
        limparTabela();
        preencherTabela();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        ativarCampos(true);
        limparCampos();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        limparTabela();
        filtrar();
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbxPerfil;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlCodigo;
    private javax.swing.JLabel jlEmail;
    private javax.swing.JLabel jlIconBusca;
    private javax.swing.JLabel jlLogin;
    private javax.swing.JLabel jlNome;
    private javax.swing.JLabel jlPerfil;
    private javax.swing.JLabel jlSenha;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
