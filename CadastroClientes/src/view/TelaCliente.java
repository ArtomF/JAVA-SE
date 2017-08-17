/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Controller.BairroController;
import Controller.CidadeController;
import Controller.ClienteController;
import Controller.EnderecoController;
import Controller.EstadoController;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import model.Bairro;
import model.Cidade;
import model.Cliente;
import model.Endereco;
import model.Estado;
import model.Validacao;

/**
 *
 * @author Artom
 */
public class TelaCliente extends javax.swing.JInternalFrame {

    //Modelo da tabela para clientes
    DefaultTableModel dtm = new DefaultTableModel(
            new Object[][]{},
            new Object[]{"Código", "Nome", "Fone", "Email", "Rua", "nº",
                "Complemento", "Bairro", "Cidade", "UF"});

    /**
     * Creates new form TelaCliente
     */
    public TelaCliente() {
        initComponents();
        preencherTabela();
        preencherComboUf();
        gerarMaskara();
        ativarCampos(true);
    }

    //Método responsável por preencher a tabela
    private void preencherTabela() {
        try {
            ClienteController cc = new ClienteController();

            ArrayList<Cliente> cli = new ArrayList<>();
            cli = cc.buscarClientes();

            for (int i = 0; i < cli.size(); i++) {
                dtm.addRow(new String[]{
                    String.valueOf(cli.get(i).getIdCli()),
                    cli.get(i).getNome(),
                    cli.get(i).getFone(),
                    cli.get(i).getEmail(),
                    cli.get(i).getEndereco().getRua(),
                    String.valueOf(cli.get(i).getEndereco().getNumero()),
                    cli.get(i).getEndereco().getComplemento(),
                    cli.get(i).getEndereco().getBairro().getNome(),
                    cli.get(i).getEndereco().getCidade().getNome(),
                    cli.get(i).getEndereco().getEstado().getSigla()
                });
            }//Fecha for

            tblClientes.setModel(dtm);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,
                    "Erro ao preencher tabela! " + e.getMessage());
        }//Fecha cacth
    }//fecha método

    //Método responsável por criar maskara no campo fone
    private void gerarMaskara() {
        try {
            MaskFormatter fone = new MaskFormatter("(##)#####-####");
            txtFone.setFormatterFactory(new DefaultFormatterFactory(fone));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,
                    "Erro na maskara! " + e.getMessage());
        }//fecha cacth
    }//Fecha método

    //Método para preencher o combo box de estado
    private void preencherComboUf() {
        try {
            ArrayList<Estado> est = new ArrayList<>();
            EstadoController ec = new EstadoController();

            est = ec.buscarEstados();

            for (int i = 0; i < est.size(); i++) {
                cbxEstado.addItem(est.get(i));
            }//Fecha for

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,
                    "Erro ao preencher combo box de estados!"
                    + e.getMessage());
        }//Fecha cacth
    }//Fecha método

    //Método que lista as cidades da uf selecionada na combo box de estado
    private void preencherComboCidade() {
        try {
            ArrayList<Cidade> cid = new ArrayList<>();
            CidadeController cc = new CidadeController();

            Cidade c = new Cidade();
            c.setEstado((Estado) cbxEstado.getSelectedItem());

            String query = "where idestado = " + c.getEstado().getIdEstado();

            cid = cc.filtrarCidade(query);

            for (int i = 0; i < cid.size(); i++) {
                cbxCidade.addItem(cid.get(i));
            }//Fecha for

        } catch (Exception e) {

        }//Fecha catch
    }//Fecha método

    //Método que lista os bairros da cidade selecionada na combo de cidade
    private void preencherComboBairro() {
        try {
            ArrayList<Bairro> ba = new ArrayList<>();
            BairroController bc = new BairroController();

            Bairro b = new Bairro();
            b.setCidade((Cidade) cbxCidade.getSelectedItem());

            String query = "where idcidade = " + b.getCidade().getIdCidade();

            ba = bc.filtrarBairros(query);

            for (int i = 0; i < ba.size(); i++) {
                cbxBairro.addItem(ba.get(i));
            }//fecha for

        } catch (Exception e) {

        }//Fecha cacth
    }//Fecha método

    //Método que limpa os campos de texto e reseta os de seleção
    private void limparCampos() {
        txtCodigo.setText(null);
        txtNome.setText(null);
        txtFone.setText(null);
        txtEmail.setText(null);
        txtRua.setText(null);
        txtNumero.setText(null);
        txtComplemento.setText(null);
        cbxEstado.setSelectedIndex(0);
        cbxCidade.removeAllItems();
        cbxBairro.removeAllItems();
    }//fecha método

    //Método que limpa a table a de clientes
    private void limparTabela() {
        dtm.setRowCount(0);
    }

    //Métod que controla a ativição dos botões
    private void ativarCampos(boolean edit) {
        txtNome.setEnabled(!edit);
        txtFone.setEnabled(!edit);
        txtEmail.setEnabled(!edit);
        txtRua.setEnabled(!edit);
        txtNumero.setEnabled(!edit);
        txtComplemento.setEnabled(!edit);
        cbxEstado.setEnabled(!edit);
        cbxCidade.setEnabled(!edit);
        cbxBairro.setEnabled(!edit);
        btnCancelar.setEnabled(!edit);
        btnSalvar.setEnabled(!edit);
        btnAlterar.setEnabled(!edit);
        btnNovo.setEnabled(edit);
        btnEditar.setEnabled(edit);
        btnDeletar.setEnabled(edit);
    }

    //Método para validar os campos 
    private boolean validarCampos() {
        if (!(Validacao.validarNome(txtNome.getText()))) {
            JOptionPane.showMessageDialog(rootPane, "Nome inválido");
            txtNome.requestFocus();
            return false;
        }//Fecha if
        if (!(Validacao.validarFone(txtFone.getText()))) {
            JOptionPane.showMessageDialog(rootPane, "Fone inválido!");
            txtFone.requestFocus();
            return false;
        }
        if (!(Validacao.validarEmail(txtEmail.getText()))) {
            JOptionPane.showMessageDialog(rootPane, "Email inválido!");
            txtEmail.requestFocus();
            return false;
        }//Fecha if
        if (!(Validacao.validarNome(txtRua.getText()))) {
            JOptionPane.showMessageDialog(rootPane, "Rua inválida ");
            txtRua.requestFocus();
            return false;
        }//Fecha if
        if (!(Validacao.validarNumero(txtNumero.getText()))) {
            JOptionPane.showMessageDialog(rootPane, "Número inválido!");
            txtNumero.requestFocus();
            return false;
        }//Fecha if
        return true;
    }//Fecha método

    //Método que efetua o cadastro dos clientes e o seu endereço no banco
    private void cadastrar() {
        try {
            if (validarCampos()) {
                Cliente c = new Cliente();
                c.setNome(txtNome.getText());
                c.setFone(txtFone.getText());
                c.setEmail(txtEmail.getText());

                Endereco e = new Endereco();
                e.setRua(txtRua.getText());
                e.setNumero(Integer.parseInt(txtNumero.getText()));
                e.setComplemento(txtComplemento.getText());
                e.setEstado((Estado) cbxEstado.getSelectedItem());
                e.setCidade((Cidade) cbxCidade.getSelectedItem());
                e.setBairro((Bairro) cbxBairro.getSelectedItem());

                c.setEndereco(e);

                ClienteController cc = new ClienteController();
                cc.cadastrarCliente(c);

                JOptionPane.showMessageDialog(rootPane,
                        "Cliente cadastrado com sucesso!");

                limparCampos();
                ativarCampos(true);
            }//Fecha if

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,
                    "" + e.getMessage());
        }//fecha catch
    }//Fecha método

    //Método que seta os campos de texto e seleção com o conteúdo da tabela
    private void editar() {
        int linha = tblClientes.getSelectedRow();
        if (linha != -1) {
            txtCodigo.setText((String) tblClientes.getValueAt(linha, 0));
            txtNome.setText((String) tblClientes.getValueAt(linha, 1));
            txtFone.setText((String) tblClientes.getValueAt(linha, 2));
            txtEmail.setText((String) tblClientes.getValueAt(linha, 3));
            txtRua.setText((String) tblClientes.getValueAt(linha, 4));
            txtNumero.setText((String) tblClientes.getValueAt(linha, 5));
            txtComplemento.setText((String) tblClientes.getValueAt(linha, 6));
        } else {
            JOptionPane.showMessageDialog(rootPane,
                    "Selecione um linha na tabela!");
            ativarCampos(true);
        }//Fecha else 
    }//Fecha método

    //Método que altera os dados nas tabelas cliente e endereco
    private void alterar() {
        try {
           // if(validarCampos()){
                Cliente c = new Cliente();
                c.setIdCli(Integer.parseInt(txtCodigo.getText()));
                c.setNome(txtNome.getText());
                c.setFone(txtFone.getText());
                c.setEmail(txtEmail.getText());
                
                Endereco e = new Endereco();
                e.setIdEnd(Integer.parseInt(txtCodigo.getText()));
                e.setRua(txtRua.getText());
                e.setNumero(Integer.parseInt(txtNumero.getText()));
                e.setComplemento(txtComplemento.getText());
                e.setEstado((Estado)cbxEstado.getSelectedItem());
                e.setCidade((Cidade)cbxCidade.getSelectedItem());
                e.setBairro((Bairro)cbxBairro.getSelectedItem());
                
                c.setEndereco(e);
                
                ClienteController cc = new ClienteController();
                cc.alterar(c);
                
                EnderecoController ec = new EnderecoController();
                ec.alterarEndereco(e);
                
                JOptionPane.showMessageDialog(rootPane, 
                        "Dados alterados com sucesso!");
                
                limparCampos();
                ativarCampos(true);
                
          //  }//Fecha if
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane,"Erro ao alterar!");
        }//Fecha cacth
    }//Fecha método

    //Método responsável por excluir os dados da tabela
    private void deletar() {
        try {
            int linha = tblClientes.getSelectedRow();
            if (linha != -1) {
                int op = JOptionPane.showConfirmDialog(rootPane,
                        "Tem certeza que deseja excluir esse cliente?",
                        "Alerta",
                        JOptionPane.YES_NO_OPTION);
                if (op == JOptionPane.YES_OPTION) {
                    ClienteController cc = new ClienteController();
                    String id = tblClientes.getValueAt(linha, 0).toString();
                    cc.deletar(Integer.parseInt(id));

                    JOptionPane.showMessageDialog(rootPane,
                            "Cliente excluído com sucesso! ");

                }//Fecha if
            } else {
                JOptionPane.showMessageDialog(rootPane, "Selecion uma linha!");
            }//Fecha else

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Erro! " + e.getMessage());
        }//Fecha cacth
    }//Fecha método

    //Método capaz de filtrar os dados no banco 
    private void filtrar() {
        try {
            String busca = txtBusca.getText();
            
            if(busca.isEmpty()){
                limparTabela();
                preencherTabela();
            }else{
                String filtro = cbxBusca.getSelectedItem().toString();
                
                String query = "";
                
                if(filtro.equals("Nome")){
                    query = "where c.nome like '%" + busca + "%'";
                }//Fecha if
                if(filtro.equals("Bairro")){
                    query = "where b.nome like '%" + busca + "%'";
                }//Fecha if
                if(filtro.equals("Cidade")){
                    query = "where l.nome like '%" + busca + "%'";
                }//Fecha if
                if(filtro.equals("Estado")){
                    query = "where u.sigla like '%" + busca + "%'";
                }//Fecha if
                
                ArrayList<Cliente> cli = new ArrayList<>();
                ClienteController cc = new ClienteController();
                
                cli = cc.filtrar(query);
                
                for(int i = 0; i < cli.size(); i++){
                    dtm.addRow(new String[]{
                        String.valueOf(cli.get(i).getIdCli()),
                        cli.get(i).getNome(),
                        cli.get(i).getFone(),
                        cli.get(i).getEmail(),
                        cli.get(i).getEndereco().getRua(),
                        String.valueOf(cli.get(i).getEndereco().getNumero()),
                        cli.get(i).getEndereco().getComplemento(),
                        cli.get(i).getEndereco().getBairro().getNome(),
                        cli.get(i).getEndereco().getCidade().getNome(),
                        cli.get(i).getEndereco().getEstado().getSigla() 
                    });
                }//Fecha for
                
                tblClientes.setModel(dtm);
                
            }//Fecha else
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Erro ao filtrar!");
        }//fecha catch
    }//Fecha método

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnDeletar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtBusca = new javax.swing.JTextField();
        jlIconBusca = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        txtCodigo = new javax.swing.JTextField();
        jlNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        jlCodigo = new javax.swing.JLabel();
        jlFone = new javax.swing.JLabel();
        jlEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jlRua = new javax.swing.JLabel();
        txtRua = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        jlNumero = new javax.swing.JLabel();
        txtComplemento = new javax.swing.JTextField();
        jlComplemento = new javax.swing.JLabel();
        jlEstado = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox();
        jlCidade = new javax.swing.JLabel();
        cbxCidade = new javax.swing.JComboBox();
        jlBairro = new javax.swing.JLabel();
        cbxBairro = new javax.swing.JComboBox();
        txtFone = new javax.swing.JFormattedTextField();
        cbxBusca = new javax.swing.JComboBox<>();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");

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

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/FileOK50.png"))); // NOI18N
        btnAlterar.setToolTipText("Confirmar Alteração");
        btnAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
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

        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscaKeyReleased(evt);
            }
        });

        jlIconBusca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search25.png"))); // NOI18N

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblClientes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblClientes);

        txtCodigo.setEnabled(false);

        jlNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlNome.setText("Nome:");

        jlCodigo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlCodigo.setText("Código:");

        jlFone.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlFone.setText("Fone:");

        jlEmail.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlEmail.setText("Email:");

        jlRua.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlRua.setText("Rua:");

        jlNumero.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlNumero.setText("Número:");

        jlComplemento.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlComplemento.setText("Complemento:");

        jlEstado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlEstado.setText("Estado:");

        cbxEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxEstadoItemStateChanged(evt);
            }
        });
        cbxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEstadoActionPerformed(evt);
            }
        });

        jlCidade.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlCidade.setText("Cidade:");

        cbxCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxCidadeActionPerformed(evt);
            }
        });

        jlBairro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jlBairro.setText("Bairro:");

        cbxBusca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Bairro", "Cidade", "Estado" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(157, Short.MAX_VALUE)
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
                .addGap(127, 127, 127))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlIconBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlFone)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlCodigo)
                                    .addComponent(jlNome))
                                .addComponent(jlEmail)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(103, 103, 103))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtNome)
                                    .addGap(28, 28, 28)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlComplemento)
                                    .addComponent(jlRua))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtRua)
                                        .addGap(18, 18, 18)
                                        .addComponent(jlNumero)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtComplemento)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jlEstado)
                                    .addComponent(jlBairro))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbxBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jlCidade)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbxCidade, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jlIconBusca))
                    .addComponent(cbxBusca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlCodigo)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlRua)
                    .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlNumero)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlComplemento)
                    .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlFone)
                    .addComponent(jlEstado)
                    .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlCidade)
                    .addComponent(cbxCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlBairro)
                    .addComponent(cbxBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnNovo)
                    .addComponent(btnDeletar)
                    .addComponent(btnSalvar)
                    .addComponent(btnCancelar)
                    .addComponent(btnEditar)
                    .addComponent(btnAlterar))
                .addGap(21, 21, 21))
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
        cbxCidade.removeAllItems();
        cbxEstado.removeAllItems();
        cbxBairro.removeAllItems();
        preencherComboUf();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyReleased
        limparTabela();
        filtrar();
    }//GEN-LAST:event_txtBuscaKeyReleased

    private void cbxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEstadoActionPerformed
        cbxCidade.removeAllItems();
        preencherComboCidade();
    }//GEN-LAST:event_cbxEstadoActionPerformed

    private void cbxCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxCidadeActionPerformed
        cbxBairro.removeAllItems();
        preencherComboBairro();
    }//GEN-LAST:event_cbxCidadeActionPerformed

    private void cbxEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxEstadoItemStateChanged
        cbxCidade.removeAllItems();
        preencherComboCidade();
    }//GEN-LAST:event_cbxEstadoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDeletar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox cbxBairro;
    private javax.swing.JComboBox<String> cbxBusca;
    private javax.swing.JComboBox cbxCidade;
    private javax.swing.JComboBox cbxEstado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlBairro;
    private javax.swing.JLabel jlCidade;
    private javax.swing.JLabel jlCodigo;
    private javax.swing.JLabel jlComplemento;
    private javax.swing.JLabel jlEmail;
    private javax.swing.JLabel jlEstado;
    private javax.swing.JLabel jlFone;
    private javax.swing.JLabel jlIconBusca;
    private javax.swing.JLabel jlNome;
    private javax.swing.JLabel jlNumero;
    private javax.swing.JLabel jlRua;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JFormattedTextField txtFone;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRua;
    // End of variables declaration//GEN-END:variables
}
