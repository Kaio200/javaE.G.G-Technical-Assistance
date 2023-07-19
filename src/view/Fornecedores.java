package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.DAO;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JEditorPane;
import java.awt.Cursor;

public class Fornecedores extends JDialog {
	
	//Instanciar objetos JDBC
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFornecedor;
	private JTextField txtID;
	private JTextField txtFone;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtCidade;
	private JComboBox cboUf;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnPesquisar;
	private JButton btnLimpar;
	private JLabel lblNewLabel_10;
	private JTextField txtEmail;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Fornecedores dialog = new Fornecedores();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Fornecedores() {
		getContentPane().setBackground(new Color(159, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/img/DR Eggman.png")));
		setTitle("E.G.G Customers");
		setBounds(100, 100, 555, 600);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Fornecedor:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel.setBounds(26, 78, 86, 14);
		getContentPane().add(lblNewLabel);

		txtFornecedor = new JTextField();
		txtFornecedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		txtFornecedor.setForeground(new Color(0, 0, 0));
		txtFornecedor.setBackground(new Color(255, 255, 0));
		txtFornecedor.setBounds(26, 102, 331, 20);
		getContentPane().add(txtFornecedor);
		txtFornecedor.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Fone:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_1.setBounds(26, 189, 46, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("ID:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_2.setBounds(26, 22, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtID = new JTextField();
		txtID.setForeground(new Color(255, 255, 255));
		txtID.setBackground(new Color(0, 0, 0));
		txtID.setBounds(26, 47, 86, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);

		txtFone = new JTextField();
		txtFone.setBackground(new Color(255, 255, 0));
		txtFone.setForeground(new Color(0, 0, 0));
		txtFone.setBounds(26, 214, 192, 20);
		getContentPane().add(txtFone);
		txtFone.setColumns(10);

		txtCep = new JTextField();
		txtCep.setForeground(new Color(255, 255, 255));
		txtCep.setBackground(new Color(0, 0, 0));
		txtCep.setBounds(26, 270, 115, 20);
		getContentPane().add(txtCep);
		txtCep.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("CEP:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_3.setBounds(26, 245, 33, 14);
		getContentPane().add(lblNewLabel_3);

		JButton btnBuscarCep = new JButton("");
		btnBuscarCep.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarCep.setIcon(new ImageIcon(Clientes.class.getResource("/img/icons8-cep-50.png")));
		btnBuscarCep.setBackground(new Color(255, 255, 0));
		btnBuscarCep.setForeground(new Color(255, 255, 255));
		btnBuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnBuscarCep.setBounds(451, 485, 75, 65);
		getContentPane().add(btnBuscarCep);

		JLabel lblNewLabel_4 = new JLabel("Endereço:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_4.setBounds(26, 301, 59, 14);
		getContentPane().add(lblNewLabel_4);

		txtEndereco = new JTextField();
		txtEndereco.setBackground(new Color(255, 255, 0));
		txtEndereco.setBounds(26, 326, 325, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Bairro:");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_5.setBounds(196, 357, 38, 14);
		getContentPane().add(lblNewLabel_5);

		txtBairro = new JTextField();
		txtBairro.setForeground(new Color(0, 0, 0));
		txtBairro.setBackground(new Color(255, 255, 0));
		txtBairro.setBounds(196, 382, 265, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Número:");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_6.setBounds(246, 245, 46, 14);
		getContentPane().add(lblNewLabel_6);

		txtNumero = new JTextField();
		txtNumero.setForeground(new Color(255, 255, 255));
		txtNumero.setBackground(new Color(0, 0, 0));
		txtNumero.setBounds(246, 270, 105, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Complemento:");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_7.setBounds(26, 357, 81, 14);
		getContentPane().add(lblNewLabel_7);

		txtComplemento = new JTextField();
		txtComplemento.setForeground(new Color(255, 255, 255));
		txtComplemento.setBackground(new Color(0, 0, 0));
		txtComplemento.setBounds(26, 382, 151, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Cidade:");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_8.setBounds(26, 413, 46, 14);
		getContentPane().add(lblNewLabel_8);

		txtCidade = new JTextField();
		txtCidade.setBackground(new Color(255, 255, 0));
		txtCidade.setBounds(26, 438, 338, 20);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("UF:");
		lblNewLabel_9.setForeground(new Color(255, 255, 255));
		lblNewLabel_9.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_9.setBounds(408, 413, 33, 14);
		getContentPane().add(lblNewLabel_9);

		btnAdicionar = new JButton("");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setBackground(new Color(255, 255, 0));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/img/adicionar.png")));
		btnAdicionar.setBounds(111, 485, 75, 65);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setBackground(new Color(0, 0, 0));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCliente();
			}
		});
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/img/editar.png")));
		btnEditar.setBounds(196, 485, 75, 65);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setBackground(new Color(255, 255, 0));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Excluir();
			}
		});
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/img/excluir.png")));
		btnExcluir.setBounds(281, 485, 75, 65);
		getContentPane().add(btnExcluir);

		btnPesquisar = new JButton("");
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuario();
			}
		});
		btnPesquisar.setIcon(new ImageIcon(Clientes.class.getResource("/img/pesquisar.png")));
		btnPesquisar.setForeground(new Color(255, 255, 255));
		btnPesquisar.setBackground(new Color(0, 0, 0));
		btnPesquisar.setBounds(26, 485, 75, 65);
		getContentPane().add(btnPesquisar);

		cboUf = new JComboBox();
		cboUf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboUf.setBackground(new Color(0, 0, 0));
		cboUf.setForeground(new Color(255, 255, 255));
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "--", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA","PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(408, 437, 53, 22);
		getContentPane().add(cboUf);
		
		btnLimpar = new JButton("");
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setBackground(new Color(0, 0, 0));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setIcon(new ImageIcon(Clientes.class.getResource("/img/eraser.png")));
		btnLimpar.setBounds(366, 485, 75, 65);
		getContentPane().add(btnLimpar);
		
		lblNewLabel_10 = new JLabel("E-mail:");
		lblNewLabel_10.setForeground(new Color(255, 255, 255));
		lblNewLabel_10.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_10.setBounds(26, 133, 46, 14);
		getContentPane().add(lblNewLabel_10);
		
		txtEmail = new JTextField();
		txtEmail.setBackground(new Color(0, 0, 0));
		txtEmail.setForeground(new Color(255, 255, 255));
		txtEmail.setBounds(26, 158, 208, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);

	}// fim do construtor
	
	
	/**
	 * Método para Adicionar um Fornecedor
	 */
	private void adicionar() {
		if (txtFornecedor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "prencha o Nome do Fornecedor");
			txtFornecedor.requestFocus();
		}else
			if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "prencha o Fone do Fornecedor");
			txtFone.requestFocus();
		} else {

			
			String create = "insert into Fornecedores(fornecedor,email,fone,cep,endereco,numero,complemento,bairro,cidade,uf) values (?,?,?,?,?,?,?,?,?,?)";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(create);
				pst.setString(1, txtFornecedor.getText());
				pst.setString(2, txtEmail.getText());
				pst.setString(3, txtFone.getText());
				pst.setString(4, txtCep.getText());
				pst.setString(5, txtEndereco.getText());
				pst.setString(6, txtNumero.getText());
				pst.setString(7, txtComplemento.getText());
				pst.setString(8, txtBairro.getText());
				pst.setString(9, txtCidade.getText());
				pst.setString(10, cboUf.getSelectedItem().toString());
				
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "fornecedor adicionado");
				limparCampos();
				con.close();
			}catch (Exception e) {
					System.out.println(e);
		}
	}	
}
	
	/**
	 *  Método responsável por limpar os campos
	 */
	private void limparCampos() {
		txtID.setText(null);
		txtFornecedor.setText(null);
		txtEmail.setText(null);
		txtFone.setText(null);
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem(null);
		
	}//fim do método limparCampos()
	
	/**
	 * Método usado para buscar um Fornecedor no banco
	 */
	private void Usuario() {
		System.out.println("teste do botão buscar");
		String read = "select * from Fornecedores where Fornecedor = ?";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(read);
			pst.setString(1, txtFornecedor.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				txtID.setText(rs.getString(1));
				txtFornecedor.setText(rs.getString(2));
				txtEmail.setText(rs.getString(3));
				txtFone.setText(rs.getString(4));
				txtCep.setText(rs.getString(5));
				txtEndereco.setText(rs.getString(6));
				txtNumero.setText(rs.getString(7));
				txtComplemento.setText(rs.getString(8));
				txtBairro.setText(rs.getString(9));
				txtCidade.setText(rs.getString(10));
				cboUf.setSelectedItem(rs.getString(11));
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Fornecedor inexistente");
				btnAdicionar.setEnabled(true);
				System.out.println("Cliente inexistente");
				
				JOptionPane.showMessageDialog(null, "Criar novo Fornecedor?");
			con.close();
			}
			} catch (Exception e) {
            System.out.println(e);
		}
		}//fim do método buscar
	
	
	private void editarCliente() {
		if(txtFornecedor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do Fornecedor");
			txtFornecedor.requestFocus();
			
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Fone do Fornecedor");
			txtFone.requestFocus();
			
		} else {
			String update = "update clientes set Fornecedor=?,Email=?,Fone=?,Cep=?,Endereco=?,Numero=?,Complemento=?,Bairro=?,Cidade=?,Uf=? where idcli=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtFornecedor.getText());
				pst.setString(2, txtEmail.getText());
				pst.setString(3, txtFone.getText());
				pst.setString(4, txtCep.getText());
				pst.setString(5, txtEndereco.getText());
				pst.setString(6, txtNumero.getText());
				pst.setString(7, txtComplemento.getText());
				pst.setString(8, txtBairro.getText());
				pst.setString(9, txtCidade.getText());
				pst.setString(10, cboUf.getSelectedItem().toString());
				pst.setString(11, txtID.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados do Fornecedor editado com sucesso.");
				 limparCampos();
				 con.close();				 
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
	}// fim do método editar
	
	/**
	 * Método usado para excluir um Fornecedor
	 */
	private void Excluir() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste contato?","Atenção!",JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from fornecedor where idcli=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				pst.executeUpdate();
				limparCampos();
				JOptionPane.showMessageDialog(null, "Fornecedo excluído");
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}		
	} //fim do método excluirFornecedor
	
	/**
	 * buscarCep
	 */
	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						System.out.println("OK");
					} else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}
			}
			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
}// fim do código