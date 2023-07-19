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

public class Clientes extends JDialog {
	
	//Instanciar objetos JDBC
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNome;
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
	private JScrollPane scrollPane;
	private JList listClientes;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes dialog = new Clientes();
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
	public Clientes() {
		getContentPane().setBackground(new Color(159, 0, 0));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/img/DR Eggman.png")));
		setTitle("E.G.G Customers");
		setBounds(100, 100, 635, 545);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(82, 111, 331, 133);
		getContentPane().add(scrollPane);
		
		listClientes = new JList();
		listClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buscarClienteLista();
			}
		});
		scrollPane.setViewportView(listClientes);
		listClientes.setForeground(new Color(255, 255, 255));
		listClientes.setBackground(new Color(0, 0, 0));

		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel.setBounds(26, 83, 46, 14);
		getContentPane().add(lblNewLabel);

		txtNome = new JTextField();
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
		txtNome.setForeground(new Color(255, 255, 255));
		txtNome.setBackground(new Color(0, 0, 0));
		txtNome.setBounds(82, 80, 331, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Fone:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_1.setBounds(26, 136, 46, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("ID:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_2.setBounds(26, 36, 46, 14);
		getContentPane().add(lblNewLabel_2);

		txtID = new JTextField();
		txtID.setBackground(new Color(255, 255, 0));
		txtID.setBounds(82, 33, 86, 20);
		getContentPane().add(txtID);
		txtID.setColumns(10);

		txtFone = new JTextField();
		txtFone.setBackground(new Color(255, 255, 0));
		txtFone.setForeground(new Color(0, 0, 0));
		txtFone.setBounds(82, 133, 192, 20);
		getContentPane().add(txtFone);
		txtFone.setColumns(10);

		txtCep = new JTextField();
		txtCep.setForeground(new Color(255, 255, 255));
		txtCep.setBackground(new Color(0, 0, 0));
		txtCep.setBounds(82, 192, 115, 20);
		getContentPane().add(txtCep);
		txtCep.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("CEP:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_3.setBounds(26, 195, 33, 14);
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
		btnBuscarCep.setBounds(498, 420, 75, 65);
		getContentPane().add(btnBuscarCep);

		JLabel lblNewLabel_4 = new JLabel("Endereço:");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_4.setBounds(26, 255, 59, 14);
		getContentPane().add(lblNewLabel_4);

		txtEndereco = new JTextField();
		txtEndereco.setBackground(new Color(255, 255, 0));
		txtEndereco.setBounds(95, 252, 325, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Bairro:");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_5.setBounds(278, 310, 38, 14);
		getContentPane().add(lblNewLabel_5);

		txtBairro = new JTextField();
		txtBairro.setForeground(new Color(0, 0, 0));
		txtBairro.setBackground(new Color(255, 255, 0));
		txtBairro.setBounds(326, 308, 244, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Número:");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_6.setBounds(442, 255, 46, 14);
		getContentPane().add(lblNewLabel_6);

		txtNumero = new JTextField();
		txtNumero.setForeground(new Color(255, 255, 255));
		txtNumero.setBackground(new Color(0, 0, 0));
		txtNumero.setBounds(498, 252, 105, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Complemento:");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_7.setBounds(26, 310, 81, 14);
		getContentPane().add(lblNewLabel_7);

		txtComplemento = new JTextField();
		txtComplemento.setForeground(new Color(255, 255, 255));
		txtComplemento.setBackground(new Color(0, 0, 0));
		txtComplemento.setBounds(117, 307, 151, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Cidade:");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_8.setBounds(26, 374, 46, 14);
		getContentPane().add(lblNewLabel_8);

		txtCidade = new JTextField();
		txtCidade.setBackground(new Color(255, 255, 0));
		txtCidade.setBounds(82, 371, 338, 20);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("UF:");
		lblNewLabel_9.setForeground(new Color(255, 255, 255));
		lblNewLabel_9.setFont(new Font("Comic Sans MS", Font.BOLD, 11));
		lblNewLabel_9.setBounds(442, 374, 33, 14);
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
		btnAdicionar.setBounds(117, 420, 75, 65);
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
		btnEditar.setBounds(202, 420, 75, 65);
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
		btnExcluir.setBounds(326, 420, 75, 65);
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
		btnPesquisar.setBounds(32, 420, 75, 65);
		getContentPane().add(btnPesquisar);

		cboUf = new JComboBox();
		cboUf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cboUf.setBackground(new Color(0, 0, 0));
		cboUf.setForeground(new Color(255, 255, 255));
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "--", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA","PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(485, 370, 53, 22);
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
		btnLimpar.setBounds(411, 420, 75, 65);
		getContentPane().add(btnLimpar);

	}// fim do construtor
	
	/**
	 * Método usado para listar o nome dos Clientes na lista
	 */
	private void listarClientes() {
		//System.out.println("teste");
		//a linha abaixo cria um objeto usando como referência um vetor dinâmico, este objeto irá temporariamente armazenar os nomes
		DefaultListModel<String> modelo = new DefaultListModel<>();
		//setar o modelo (vetor na lista)
		listClientes.setModel(modelo);
		//Query (instrução sql)
		String readLista = "select * from clientes where nome like '" + txtNome.getText() + "%'" + "order by nome";
		try {
			//abrir a conexão
			con = dao.conectar();
			//preparar a query (instrução sql
			pst = con.prepareStatement(readLista);
			//executar a query e trazer o resultado para lista
			rs = pst.executeQuery();
			//uso do while para trazer os usuários enquanto existir
			while(rs.next()) {
				//mostrar a barra de rolagem (scrollpane)
				scrollPane.setVisible(true);
				//adicionar os usuarios no vetor -> lista
				modelo.addElement(rs.getString(2));
				//esconder o scrollpane se nenhuma letra for digitada
				if(txtNome.getText().isEmpty()) {
					scrollPane.setVisible(false);
				}
				
			}
			//fechar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Método para Adicionar um Cliente
	 */
	private void adicionar() {
		//System.out.println("teste");
		//validação de campos obrigatórios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "prencha o Nome do Cliente");
			txtNome.requestFocus();
		}else
			if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "prencha o Fone do Cliente");
			txtFone.requestFocus();
		} else {

			
			//lógica principal
			String create = "insert into clientes(nome,fone,cep,endereco,numero,complemento,bairro,cidade,uf) values (?,?,?,?,?,?,?,?,?)";
			//tratamento de exceções
			try {
				//abrir a conexão
				con = dao.conectar();
				//preparar a execução da query(instrução sql - CRUD Create)
				pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtCep.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumero.getText());
				pst.setString(6, txtComplemento.getText());
				pst.setString(7, txtBairro.getText());
				pst.setString(8, txtCidade.getText());
				pst.setString(9, cboUf.getSelectedItem().toString());
				
				//validação (liberação dos botões alterar e excluir)
				//executa a query(instrução sql (CRUD - Create))
				pst.executeUpdate();
				//confirmar
				JOptionPane.showMessageDialog(null, "Usuário adicionado");
				//limpar os campos
				limparCampos();
				//fechar a conexão
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
		txtNome.setText(null);
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
	 * Método usado para buscar um Cliente no banco
	 */
	private void Usuario() {
		System.out.println("teste do botão buscar");
		String read = "select * from clientes where nome = ?";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(read);
			pst.setString(1, txtNome.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				txtID.setText(rs.getString(1));
				txtNome.setText(rs.getString(2));
				txtFone.setText(rs.getString(3));
				txtCep.setText(rs.getString(4));
				txtEndereco.setText(rs.getString(5));
				txtNumero.setText(rs.getString(6));
				txtComplemento.setText(rs.getString(7));
				txtBairro.setText(rs.getString(8));
				txtCidade.setText(rs.getString(9));
				cboUf.setSelectedItem(rs.getString(10));
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
			} else {
				//se não existir um contato no banco
				JOptionPane.showMessageDialog(null, "Cliente inexistente");
				//validação (liberação do Botão adicionar)
				btnAdicionar.setEnabled(true);
				System.out.println("Cliente inexistente");
				
				JOptionPane.showMessageDialog(null, "Criar novo cliente?");
			con.close();
			}
			} catch (Exception e) {
            System.out.println(e);
		}
		}//fim do método buscar
	
	
	private void editarCliente() {
		//System.out.println("Teste do botão editar");
		if(txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o nome do cliente");
			txtNome.requestFocus();
			
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Digite o Fone do cliente");
			txtFone.requestFocus();
			
		} else {
			//lógica principal
			//CRUD - update
			String update = "update clientes set nome=?,Fone=?,Cep=?,Endereco=?,Numero=?,Complemento=?,Bairro=?,Cidade=?,Uf=? where idcli=?";
			//tratamento de exeções
			try {
				//abrir a conexão
				con = dao.conectar();
				//preparar a query (instrução sql)
				pst = con.prepareStatement(update);
				
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtCep.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumero.getText());
				pst.setString(6, txtComplemento.getText());
				pst.setString(7, txtBairro.getText());
				pst.setString(8, txtCidade.getText());
				pst.setString(9, cboUf.getSelectedItem().toString());
				pst.setString(10, txtID.getText());
				//executar a query
				pst.executeUpdate();
				//confirmar para o usuario
				JOptionPane.showMessageDialog(null, "Dados do cliente editado com sucesso.");
				//limpar os campos
				 limparCampos();
				 con.close();				 
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
	}// fim do método editar
	
	/**
	 * Método usado para excluir um Cliente
	 */
	private void Excluir() {
		//System.out.println("teste do botão excluir");
		//validação de exclusão - a variável confirma captura a opção escolhida
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste contato?","Atenção!",JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			//CRUD - Delete
			String delete = "delete from clientes where idcli=?";
			//tratamento de exceções
			try {
				//abrir a conexão
				con = dao.conectar();
				//preparar a query (instrução sql)
				pst = con.prepareStatement(delete);
				//substituir a ? pelo id do usuário
				pst.setString(1, txtID.getText());
				//executar a query
				pst.executeUpdate();
				//limpar campos
				limparCampos();
				//exibir uma mensagem ao usuário
				JOptionPane.showMessageDialog(null, "Clientes excluído");
				//fechar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}		
	} //fim do método excluirCliente
	
	/**
	 * Método que busca o Cliente selecionado na lista
	 */
	private void buscarClienteLista() {
	 System.out.println("teste");
		// variável que captura o índice da linha da lista
		int linha = listClientes.getSelectedIndex();
		if (linha >= 0) {
			// Query (instrução sql)
			// limit (0,1) -> seleciona o índice 0 e 1 usuário da lista
			String readListaClientes = "select * from clientes where nome like '" + txtNome.getText() + "%'"	+ "order by nome limit " + (linha) + " , 1";
			try {
				//abrir a conexão
				con = dao.conectar();
				//preparar a query para execução
				pst = con.prepareStatement(readListaClientes);
				//executar e obter o resultado
				rs = pst.executeQuery();
				if (rs.next()) {
					//esconder a lista
					scrollPane.setVisible(false);
					//setar os campos
					txtID.setText(rs.getString(1));
					txtNome.setText(rs.getString(2));
					txtFone.setText(rs.getString(3));
					txtCep.setText(rs.getString(4));
					txtEndereco.setText(rs.getString(5));
					txtNumero.setText(rs.getString(6));
					txtComplemento.setText(rs.getString(7));
					txtBairro.setText(rs.getString(8));
					txtCidade.setText(rs.getString(9));
					cboUf.setSelectedItem(rs.getString(10));
				} else {
					JOptionPane.showMessageDialog(null, "Cliente inexistente");
				}
				//fechar a conexão
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			// se não existir no banco um usuário da lista
			scrollPane.setVisible(false);
		}
	}
	
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
} // fim do código